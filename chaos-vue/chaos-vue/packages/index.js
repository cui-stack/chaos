import Paging from './components/paging/index'
import Head from './components/head/index'
import CenterHead from './components/centerHead/index'
import Input from './components/input/index'
import SideMenu from './components/sideMenu/index'

const components = [
    Paging,
    Head,
    CenterHead,
    Input,
    SideMenu,
]

const install = function (Vue) {
    if (install.installed) return
    install.installed = true
    components.map(component => {
        Vue.component(component.name, component)
    })
}

if (typeof window !== 'undefined' && window.Vue) {
    install(window.Vue)
}

export default {
    install,
    ...components,
}
