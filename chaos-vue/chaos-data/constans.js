import store from './vuex/store'

let baseUrl = ''
switch (process.env.VUE_APP_ENV) {
    case 'production':
        baseUrl = store.getters.getBaseUrl.prod
        break
    case 'testing':
        baseUrl = store.getters.getBaseUrl.test
        break
    case 'development':
        baseUrl = store.getters.getBaseUrl.dev
        break
}
export default baseUrl;

