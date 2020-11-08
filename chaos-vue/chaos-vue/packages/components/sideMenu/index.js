import SideMenu from './main.vue'

SideMenu.install = function (Vue) {
    Vue.component(SideMenu.name, SideMenu)
}

export default SideMenu
