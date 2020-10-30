import { list, } from './../../utils/mservice';

export default {

    namespace: 'index',

    state: {
        list: []
    },

    effects: {
        *list({ payload }, { call, put }) {
            const response = yield call(list, 'chaos_info', payload);
            yield put({
                type: 'listed',
                payload: response,
            });
        }

    },

    reducers: {
        listed(state, { payload }) {
            state.list = payload
            return {
                ...state,
            };
        },

    }

}
