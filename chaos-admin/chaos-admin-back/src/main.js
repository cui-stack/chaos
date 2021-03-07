import Vue from 'vue'
import App from '@/chaos/App'
import router from '@/chaos/router/index'
import store from '@/chaos/functions/vuex/index'
import VueWechatTitle from 'vue-wechat-title';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'normalize.css/normalize.css'

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueWechatTitle);

import Avatar from '@/chaos/components/Avatar'
import PlainButton from '@/chaos/components/PlainButton'
import PrimaryButton from '@/chaos/components/PrimaryButton'

import SearchButton from '@/chaos/components/search/Button'
import SearchDatePicker from '@/chaos/components/search/DatePicker'
import SearchInput from '@/chaos/components/search/Input'
import SearchPagination from '@/chaos/components/search/Pagination'
import SearchRadioGroup from '@/chaos/components/search/RadioGroup'
import SearchSelect from '@/chaos/components/search/Select'

Vue.component("Avatar", Avatar);
Vue.component("PlainButton", PlainButton);
Vue.component("PrimaryButton", PrimaryButton);

Vue.component("SearchButton", SearchButton);
Vue.component("SearchDatePicker", SearchDatePicker);
Vue.component("SearchInput", SearchInput);
Vue.component("SearchPagination", SearchPagination);
Vue.component("SearchRadioGroup", SearchRadioGroup);
Vue.component("SearchSelect", SearchSelect);

new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
}).$mount('#app')

