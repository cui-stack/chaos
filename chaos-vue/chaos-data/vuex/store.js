import Vue from 'vue'
import Vuex from 'vuex'
import VueXAlong from 'vuex-along'

Vue.use(Vuex)

const state = {
    user: {
        token: '',
        userMu: '',
        username: '',
        indexLink: '',
        roleInfo: '',
        roleName: ''
    },
    menus: [],
    lastPost: {
        url: '',
        params: {}
    }
}

const mutations = {
    SetUserinfo(state, data) {
        state.user.token = data.token;
        state.user.userMu = data.mu;
        state.user.username = data.username;
        state.user.indexLink = data.indexLink;
        state.user.roleInfo = data.roleInfo;
        state.user.roleName = data.roleName;
    },
    RefreshToken(state, token) {
        state.user.token = token;
    },
    SetMenus(state, data) {
        state.menus = data
    },
    SetLastPost(state, data) {
        state.lastPost = data
    },
    Logout(state) {
        state.user.token = ""
        state.user.userMu = ""
        state.user.username = ""
        state.user.indexLink = ""
        state.user.info = ""
        state.user.name = ""
        state.menus = []
        state.lastPost.params = {}
        state.lastPosturl = ''
    }
}

const getters = {
    getUserinfo: function (state) {
        return state.user;
    },
    getMenus: function (state) {
        return state.menus;
    },
    getLastPost: function (state) {
        return state.lastPost;
    }
}

const actions = {
    setUserinfo({commit}, data) {
        commit('SetUserinfo', data)
    },
    refreshToken({commit}, data) {
        commit('RefreshToken', data)
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

const store = new Vuex.Store({
    state,
    mutations,
    getters,
    actions,
    plugins: [VueXAlong()]
});

export default store;
