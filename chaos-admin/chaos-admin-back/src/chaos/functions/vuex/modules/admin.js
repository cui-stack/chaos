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
    Logout(state) {
        state.user.token = ""
        state.user.userMu = ""
        state.user.username = ""
        state.user.indexLink = ""
        state.user.roleInfo = ""
        state.user.roleName = ""
        state.menus = []
    },
    SetMenus(state, data) {
        state.menus = data
    },
}

const actions = {
    setUserinfo({commit}, data) {
        commit('SetUserinfo', data)
    },
    refreshToken({commit}, data) {
        commit('RefreshToken', data)
    },
    logout({commit}) {
        commit('Logout')
    },
    setMenus({commit}, data) {
        commit('SetMenus', data)
    },
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}
