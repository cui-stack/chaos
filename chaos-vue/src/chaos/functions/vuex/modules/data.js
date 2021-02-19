const state = {
    data: {},
}

const mutations = {
    SetData(state, data) {
        state.data = data
    }
}

const actions = {
    setData({commit}, data) {
        commit('SetData', data)
    }
}

export default {
    namespaced: true,
    state,
    mutations,
    actions
}
