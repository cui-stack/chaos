import Vue from 'vue'
import App from './chaos/App'
import router from './chaos/router/index'
import store from './chaos/functions/vuex/index'
import VueWechatTitle from 'vue-wechat-title';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'normalize.css/normalize.css'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueWechatTitle);
export const eventBus = new Vue()

Vue.component("Avatar", () => import ('@/chaos/components/Avatar'));
Vue.component("SearchButton", () => import ('@/chaos/components/SearchButton'));
Vue.component("Pagination", () => import ('@/chaos/components/Pagination'));
Vue.component("Input", () => import ('@/chaos/components/Input'));
Vue.component("RadioGroup", () => import ('@/chaos/components/RadioGroup'));
Vue.component("PlainButton", () => import ('@/chaos/components/PlainButton'));
Vue.component("PrimaryButton", () => import ('@/chaos/components/PrimaryButton'));

new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
}).$mount('#app')
