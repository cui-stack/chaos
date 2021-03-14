import { extend } from 'umi-request';
import { notification } from 'antd';
import config from './config';

const codeMessage = {
  200: '服务器成功返回请求的数据。',
  201: '新建或修改数据成功。',
  202: '一个请求已经进入后台排队（异步任务）。',
  204: '删除数据成功。',
  400: '发出的请求有错误，服务器没有进行新建或修改数据的操作。',
  401: '用户没有权限（令牌、用户名、密码错误）。',
  403: '用户得到授权，但是访问是被禁止的。',
  404: '发出的请求针对的是不存在的记录，服务器没有进行操作。',
  406: '请求的格式不可得。',
  410: '请求的资源被永久删除，且不会再得到的。',
  422: '当创建一个对象时，发生一个验证错误。',
  500: '服务器发生错误，请检查服务器。',
  502: '网关错误。',
  503: '服务不可用，服务器暂时过载或维护。',
  504: '网关超时。',
};

const errorHandler = (error: { response: Response }): Response => {
  const { response } = error;
  if (response && response.status) {
    const errorText = codeMessage[response.status] || response.statusText;
    const { status, url } = response;

    notification.error({
      message: `请求错误 ${status}: ${url}`,
      description: errorText,
    });
  } else if (!response) {
    notification.error({
      description: '您的网络发生异常，无法连接服务器',
      message: '网络异常',
    });
  }
  return response;
};


const request = extend({
  errorHandler
});

// @ts-ignore
request.interceptors.request.use((url, options) => {
  localStorage.setItem('lastUrl', url)
  localStorage.setItem('lastParams', JSON.stringify(options.data))
  const { pageSize, current, ...rest } = options.data
  let { data } = options.data
  if (pageSize) {
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    data = {
      data: rest,
      pageNum: current,
      pageSize,
    }
  }
  const headers = {
    'Content-Type': 'application/json',
    Accept: 'application/json',
    token: localStorage.getItem("token")
  };
  return {
    url: (process.env.apiUrl || '') + url,
    options: { ...options, headers },
  };
});

request.interceptors.response.use(async response => {
  const data = await response.clone().json()
  if (data.code === 200) {
    if (data.page) {
      return {
        data: data.page.list,
        total: data.page.total
      }
    }
    if (data.data)
      return data.data
    return ''
  }
  if (data.code === 201) {
    localStorage.setItem('token', data.msg)
    const url = localStorage.getItem('lastUrl') || ''
    const params = JSON.parse(localStorage.getItem('lastParams') || '')
    return request(url, {
      method: 'POST',
      data: params,
    })
  }
  if (data.code === 401) {
    window.location.href = config.loginUrl
    return ''
  }
  notification.error({
    description: data.msg || '您的网络发生异常，无法连接服务器',
    message: '业务异常',
  });
});

export default request;
