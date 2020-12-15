import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Login',
            component: () => import ('@/views/login/index'),
            meta: {title: "登录"}
        },
        {
            path: '/main',
            name: 'Main',
            component: () => import ('@/views/main/index'),
            meta: {title: "首页"},
            redirect: '/index',
            children: [
                {
                    path: '/index',
                    name: 'index',
                    meta: {title: "首页"},
                    component: () => import ('@/views/index/index')
                },
                {
                    path: '/admin',
                    name: 'admin',
                    meta: {title: "管理员"},
                    component: () => import ('@/views/admin/index')
                },
                {
                    path: '/addAdmin',
                    name: 'addAdmin',
                    meta: {title: "添加管理员"},
                    component: () => import ('@/views/admin/addAdmin')
                },
                {
                    path: '/updateAdmin',
                    name: 'updateAdmin',
                    meta: {title: "添加管理员"},
                    component: () => import ('@/views/admin/updateAdmin')
                },
                {
                    path: '/role',
                    name: 'role',
                    meta: {title: "角色"},
                    component: () => import ('@/views/role/index')
                },
                {
                    path: '/permission',
                    name: 'permission',
                    meta: {title: "资源"},
                    component: () => import ('@/views/permission/index')
                },
                {
                    path: '/platform',
                    name: 'platform',
                    meta: {title: "平台"},
                    component: () => import ('@/views/platform/index')
                },
                {
                    path: '/log',
                    name: 'log',
                    meta: {title: "日志", keepAlive: true},
                    component: () => import  ('@/views/log/index')
                },
                {
                    path: '/onlineAdmin',
                    name: 'onlineAdmin',
                    meta: {title: "在线管理员", keepAlive: true},
                    component: () => import ('@/views/online/admin')
                },
                {
                    path: '/link',
                    name: 'link',
                    meta: {title: "首页链接", keepAlive: true},
                    component: () => import ('@/views/link/index')
                },
                {
                    path: '/env',
                    name: 'env',
                    meta: {title: "环境", keepAlive: true},
                    component: () => import ('@/views/env/index')
                }
            ]
        }
    ]
})
