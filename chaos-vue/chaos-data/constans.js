import store from './vuex/index'

let baseUrl = ''
switch (process.env.VUE_APP_ENV) {
    case 'production':
        baseUrl = store.getters.baseUrl.prod
        break
    case 'testing':
        baseUrl = store.getters.baseUrl.test
        break
    case 'development':
        baseUrl = store.getters.baseUrl.dev
        break
}
export default baseUrl;

