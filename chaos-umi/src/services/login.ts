import request from '@/utils/request';

export interface LoginParamsType {
  userName: string;
  password: string;
}

export async function manageLogin(params: LoginParamsType) {
  return request('/manage/login', {
    method: 'POST',
    data: params,
  });
}
