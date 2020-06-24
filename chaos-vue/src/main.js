import Vue from 'vue'
import App from './App'
import VueWechatTitle from 'vue-wechat-title';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import store from './vuex/store'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueWechatTitle);

export const eventBus = new Vue()

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app')

router.beforeEach((to, from, next) => {
  if (to.name !== "Login") {
    // 非 login 页面，检查是否登录
    // 这里简单前端模拟是否填写了用户名，真实环境需要走后台登录，缓存到本地
    if (!getGlobalData("username")) {
      next({ name: "Login" });
    }
  }
  // 其他情况正常执行
  next();
});
