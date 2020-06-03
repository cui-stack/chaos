import Vue from 'vue'
import Vuex from 'vuex'
import VueXAlong from 'vuex-along'
Vue.use(Vuex)

const state = {
  // 放置初始状态
  userinfo: {
    token: '',
    userid: '',
    username: ''
  },
  roleinfo: {
    indexLink: '',
    info: ''
  }
}

const mutations = {
  SetUserinfo(state, data) {
    state.userinfo.token = data.token;
    state.userinfo.userid = data.unique;
    state.userinfo.username = data.username;
  },
  RefreshToken(state, token) {
    state.userinfo.token = token;
  },
  SetRoleinfo(state, data) {
    state.roleinfo.indexLink = data.indexLink;
    state.roleinfo.info = data.info;
  },
}

// 3、getters：提供外部获取state
const getters = {
  getUserinfo: function (state) {
    return state.userinfo;
  },
  getRoleinfo: function (state) {
    return state.roleinfo;
  }
}

// 4、actions：创建驱动方法改变mutations
const actions = {
  // 触发mutations中相应的方法-一般小写
  setUserinfo({commit}, data) {
    commit('SetUserinfo', data)
  },
  refreshToken({commit}, data) {
    commit('RefreshToken', data)
  },
  setRoleinfo({commit}, data) {
    commit('SetRoleinfo', data)
  }
}

// 5、全部注入Store中
const store = new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  plugins: [VueXAlong()]
});

// 6、输出store
export default store;
