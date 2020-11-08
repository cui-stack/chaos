import Vue from 'vue'
import App from './App.vue'
import ChaosVue from '../packages/index'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
Vue.use(ChaosVue)

Vue.config.productionTip = false

new Vue({
    render: h => h(App)
}).$mount('#app')
