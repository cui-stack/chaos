const state = {
    lastPost: {
        url: '',
        params: {}
    },
    baseUrl: {
        dev: '',
        test: '',
        prod: ''
    },
    isCollapse: false,
    asideWidth: 300,
    path:'/back/'
}

const mutations = {
    SetLastPost(state, data) {
        state.lastPost = data
    },
    SetBaseUrl(state, data) {
        state.baseUrl = data
    },
    SetPath(state, data) {
        state.path = data
    },
    ToggleSideMenu: state => {
        state.isCollapse = !state.isCollapse
        state.asideWidth = state.isCollapse ? 80 : 300
    },
    Logout(state) {
        state.lastPost = {}
        state.baseUrl = {}
    },
}

const actions = {
    setLastPost({commit}, data) {
        commit('SetLastPost', data)
    },
    setBaseUrl({commit}, data) {
        commit('SetBaseUrl', data)
    },
    setPath({commit}, data) {
        commit('SetPath', data)
    },
    toggleSideMenu({commit}) {
        commit('ToggleSideMenu')
    },
    logout({commit}) {
        commit('Logout')
    },
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}
