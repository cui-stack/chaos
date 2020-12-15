import Hamburger from './main.vue'

Hamburger.install = function (Vue) {
    Vue.component(Hamburger.name, Hamburger)
}

export default Hamburger
