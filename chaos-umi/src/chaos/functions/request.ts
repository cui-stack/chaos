import { extend } from 'umi-request';
import { notification } from 'antd';
import isLocal from '@/chaos/config/config';

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

const request = extend({ errorHandler });

// @ts-ignore
request.interceptors.request.use((url, { data, ...options }) => {
  localStorage.setItem('lastUrl', url)
  localStorage.setItem('lastParams', JSON.stringify(data))
  const headers = {
    'Content-Type': 'application/json',
    Accept: 'application/json',
    token: localStorage.getItem('token'),
  };
  return {
    url: (isLocal() ? '' : '/umi-api/') + url,
    options: { ...options, data, method: 'POST', headers },
  };
});

request.interceptors.response.use(async (response) => {
  const { code, data, page, msg } = await response.clone().json();
  if (code === 200) {
    if (page) {
      const { list, total } = page;
      return { data: list, total };
    }
    if (data) return data;
    return false;
  }
  if (code === 201) {
    localStorage.setItem('token', msg);
    const url = localStorage.getItem('lastUrl') || '';
    const params = JSON.parse(localStorage.getItem('lastParams') || '');
    return request(url, { data: params });
  }
  if (code === 401) {
    window.location.href = '/umi/user/login';
    return false;
  }
  notification.error({
    description: msg || '您的网络发生异常，无法连接服务器',
    message: '业务异常',
  });
  return false;
});

export default request;
