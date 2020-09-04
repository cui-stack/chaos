import {Effect, Reducer} from 'umi';

import {AnalysisData} from './data.d';
import {userData,phoneData,registerDatas,orderData} from './service';

export interface ModelType {
  namespace: string;
  state: AnalysisData;
  effects: {
    fetch: Effect;
  };
  reducers: {
    save: Reducer<AnalysisData>;
    clear: Reducer<AnalysisData>;
  };
}

const initState = {
  userData: {
    total: 0,
    wyoy: 0,
    dyoy: 0,
    todayTotal: 0,
  },
  phoneData: {
    total: 0,
  },
  registerDatas:[],
  orderData: {
    total: 0,
    wyoy: 0,
    dyoy: 0,
    todayTotal: 0,
  },
}

const Model: ModelType = {
  namespace: 'dashboardAndanalysis',

  state: initState,

  effects: {
    * fetch(_, {call, put}) {
      const response = yield call(userData);
      const response1 = yield call(phoneData);
      const response2 = yield call(registerDatas);
      const orderDataR = yield call(orderData);

      yield put({
        type: 'save',
        payload: {
          userData: response.data,
          phoneData: response1.data,
          registerDatas: response2.data,
          orderData:orderDataR.data
        },
      });
    },
  },

  reducers: {
    save(state, {payload}) {
      return {
        ...state,
        ...payload,
      };
    },
    clear() {
      return initState;
    },
  },
};

export default Model;
