import Vue from 'vue'
import Vuex from 'vuex'
import VueXAlong from 'vuex-along'

Vue.use(Vuex)

const state = {
  backuserinfo: {
    token: '',
    userid: '',
    username: ''
  },
  backroleinfo: {
    indexLink: '',
    info: '',
    name: ''
  },
  backmenus: [],
  backlastPost: {
    url: '',
    params: {}
  }
}

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
  },
  setMenus({commit}, data) {
    commit('SetMenus', data)
  },
  setLastPost({commit}, data) {
    commit('SetLastPost', data)
  },
  logout({commit}, data) {
    commit('Logout', data)
  }
}

const mutations = {
  SetUserinfo(state, data) {
    state.backuserinfo.token = data.token;
    state.backuserinfo.userid = data.unique;
    state.backuserinfo.username = data.username;
  },
  RefreshToken(state, token) {
    state.backuserinfo.token = token;
  },
  SetRoleinfo(state, data) {
    state.backroleinfo.indexLink = data.indexLink;
    state.backroleinfo.info = data.info;
    state.backroleinfo.name = data.name;
  },
  SetMenus(state, data) {
    state.backmenus = data
  },
  SetLastPost(state, data) {
    state.backlastPost = data
  },
  Logout(state, data) {
    state.backuserinfo.token = ""
    state.backuserinfo.userid = ""
    state.backuserinfo.username = ""
    state.backroleinfo.indexLink = ""
    state.backroleinfo.info = ""
    state.backroleinfo.name = ""
    state.backmenus = []
  }
}

const getters = {
  getUserinfo: function (state) {
    return state.backuserinfo;
  },
  getRoleinfo: function (state) {
    return state.backroleinfo;
  },
  getMenus: function (state) {
    return state.backmenus;
  },
  getLastPost: function (state) {
    return state.backlastPost;
  }
}

const store = new Vuex.Store({
  state,
  mutations,
  getters,
  actions,
  plugins: [VueXAlong()]
});

export default store;
