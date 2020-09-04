import { Effect, history, Reducer } from 'umi';
import { message } from 'antd';
import { parse } from 'qs';
import { manageLogin } from './service';

export function getPageQuery() {
  return parse(window.location.href.split('?')[1]);
}

export function setAuthority(authority: string | string[]) {
  return 'admin'
  // const proAuthority = typeof authority === 'string' ? [authority] : authority;
  // localStorage.setItem('antd-pro-authority', JSON.stringify(proAuthority));
  // // hard code
  // // reload Authorized component
  // try {
  //   if ((window as any).reloadAuthorized) {
  //     (window as any).reloadAuthorized();
  //   }
  // } catch (error) {
  //   // do not need do anything
  // }

  // return authority;
}

export interface StateType {
  status?: 'ok' | 'error';
  type?: string;
  currentAuthority?: 'user' | 'guest' | 'admin';
}

export interface ModelType {
  namespace: string;
  state: StateType;
  effects: {
    login: Effect;
  };
  reducers: {
    changeLoginStatus: Reducer<StateType>;
  };
}

const Model: ModelType = {
  namespace: 'userAndlogin',

  state: {
    status: undefined,
  },

  effects: {
    *login({ payload }, { call, put }) {
      const response = yield call(manageLogin, payload);
      yield put({
        type: 'changeLoginStatus',
        payload: response,
      });
      if (response.code === '200') {
        message.success('登录成功！');
        const urlParams = new URL(window.location.href);
        const params = getPageQuery();
        let { redirect } = params as { redirect: string };
        if (redirect) {
          const redirectUrlParams = new URL(redirect);
          if (redirectUrlParams.origin === urlParams.origin) {
            redirect = redirect.substr(urlParams.origin.length);
            if (redirect.match(/^\/.*#/)) {
              redirect = redirect.substr(redirect.indexOf('#') + 1);
            }
          } else {
            window.location.href = redirect;
            return;
          }
        }
        history.replace(redirect || '/');
      } else {
        message.success(response.msg);
      }
    },

  },

  reducers: {
    changeLoginStatus(state, { payload }) {
      setAuthority(payload.data);
      return {
        ...state,
        status: 'ok',
      };
    },
  },
};

export default Model;
