import Taro from '@tarojs/taro';
import {
  baseUrl,
  noConsole
} from '../config';

export default (options = {
  url: '',
  data: {},
}) => {
  if (!noConsole) {
    console.log(`${new Date().toLocaleString()}【 M=${options.url} 】P=${JSON.stringify(options.data)}`);
  }
  return Taro.request({
    url: baseUrl + options.url,
    data: JSON.stringify(options.data),
    header: {
      'content-type': 'application/json',
      "token": Taro.getStorageSync('token')
    },
    method: 'POST',
  }).then((res) => {
    const {
      statusCode,
      data
    } = res;
    if (statusCode >= 200 && statusCode < 300) {
      if (!noConsole) {
        console.log(`${new Date().toLocaleString()}【 M=${options.url} 】【接口响应：】`, res.data);
      }
      if (data.code !== '200') {
        Taro.showToast({
          title: `${res.data.msg}~` || res.data.code,
          icon: 'none',
          mask: true,
        });
      } else {
        return  data.data||data.page||{pages:data.pages,mark:data.mark}
      }
    } else {
      throw new Error(`网络请求错误，状态码${statusCode}`);
    }

  })
}
