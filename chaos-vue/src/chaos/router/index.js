import Vue from 'vue'
import Router from 'vue-router'
import {routers} from '@/app/config'

Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
let children = [{
    path: '/welcome',
    meta: {
        title: "欢迎"
    },
    component: () => import('@/chaos/views/welcome/index')
}, {
    path: '/onlineUser',
    name: 'onlineUser',
    meta: {title: "在线用户", keepAlive: true},
    component: () => import ('@/chaos/views/admin/onlineUser')
}, {
    path: '/onlineAdmin',
    name: 'onlineAdmin',
    meta: {title: "在线管理员", keepAlive: true},
    component: () => import ('@/chaos/views/admin/onlineAdmin')
}, {
    path: '/limit',
    name: 'limit',
    meta: {title: "访问限制"},
    component: () => import ('@/chaos/views/admin/limit')
}]
let routes = [{
    path: '/',
    component: () => import('@/views/login/index'),
    meta: {
        title: "登录"
    }
}, {
    path: '/main',
    component: () => import('@/chaos/layout/main/index'),
    redirect: '/welcome',
    children: children.concat(routers())
}]

export default new Router({routes})
