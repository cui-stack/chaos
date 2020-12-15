import Head from './main.vue'

Head.install = function (Vue) {
    Vue.component(Head.name, Head)
}

export default Head
