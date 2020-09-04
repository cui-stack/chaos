import request from 'umi-request';

export async function userData() {
  return request('/data/iya_user/data', {
    method: 'POST',
    data: {}
  });
}

export async function phoneData() {
  return request('/data/iya_user/phoneData', {
    method: 'POST',
    data: {}
  });
}

export async function registerDatas() {
  return request('/data/iya_user/registerDatas', {
    method: 'POST',
    data: {}
  });
}

export async function orderData() {
  return request('/data/iya_order/data', {
    method: 'POST',
    data: {}
  });
}
