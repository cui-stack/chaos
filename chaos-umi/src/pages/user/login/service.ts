import request from 'umi-request';

export interface LoginParamsType {
  userName: string;
  password: string;
  platformMu: string;
}

export async function manageLogin(params: LoginParamsType) {
  params.platformMu = '1300643098254139393'
  return request('/manage/login', {
    method: 'POST',
    data: params,
  });
}
