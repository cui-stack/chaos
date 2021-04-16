import { history, Reducer, Effect } from 'umi';
import { message } from 'antd';

import { setAuthority } from '@/chaos/utils/authority';
import { ModelType } from '@/chaos/functions/dataType'
import { submit } from '@/chaos/functions/data';

export interface CurrentUser {
  mu?: string;
  avatar?: string;
  phone?: string;
  roleName?: string;
  roleInfo?: string;
  status?: number;
  username?: string;
}

export interface UserModelState {
  currentUser?: CurrentUser;
  status?: 'ok' | 'error';
  currentAuthority?: 'user' | 'guest' | 'admin';
}

export interface UserModelType extends ModelType {
  state: UserModelState;
  effects: {
    login: Effect;
    logout: Effect;
    fetchCurrent: Effect;
  };
  reducers: {
    changeLoginStatus: Reducer<UserModelState>;
    saveCurrentUser: Reducer<UserModelState>;
  };
}

const UserModel: UserModelType = {
  namespace: 'user',

  state: {
    currentUser: {},
  },

  effects: {
    * login({ payload }, { call, put }) {
      const response = yield call(submit, "login", payload);
      if (!response) return
      localStorage.setItem('token', response.token)
      localStorage.setItem('user', JSON.stringify(response))
      yield put({ type: 'changeLoginStatus', payload: response });
      message.success('🎉 🎉 🎉  登录成功！');
      history.replace('/');
    },

    logout() {
      localStorage.setItem('token', "")
      localStorage.setItem('user', "")
      history.replace('/user/login');
    },

    * fetchCurrent(_, { put }) {
      const json = localStorage.getItem('user') || ""
      const user = JSON.parse(json)
      yield put({ type: 'saveCurrentUser', payload: user, });
    },
  },

  reducers: {
    changeLoginStatus(state, { payload }) {
      setAuthority(payload.roleName);
      return {
        ...state,
        status: payload.status,
        type: payload.type,
      };
    },
    saveCurrentUser(state, action) {
      return {
        ...state,
        currentUser: action.payload || {},
      };
    }
  },
};

export default UserModel;
