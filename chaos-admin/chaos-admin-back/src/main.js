import Vue from 'vue'
import App from './App'
import 'normalize.css/normalize.css'
import VueWechatTitle from 'vue-wechat-title';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import store from 'chaos-data/vuex/index';
import ChaosVue from 'chaos-vue';
import 'chaos-vue/lib/chaos-vue.css';

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueWechatTitle);
Vue.use(ChaosVue);

export const eventBus = new Vue()

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app')

