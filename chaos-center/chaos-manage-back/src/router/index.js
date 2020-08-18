import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login/login'
import Main from '@/views/main/main'
import index from '@/views/index/index'

import admin from '@/views/admin/admin'
import addAdmin from '@/views/admin/addAdmin'
import updateAdmin from '@/views/admin/updateAdmin'
import limit from '@/views/admin/limit'

import role from '@/views/role/role'

import permission from '@/views/permission/permission'

import platform from '@/views/platform/platform'

import log from '@/views/log/log'

import onlineAdmin from '@/views/online/admin'
import onlineUser from '@/views/online/user'
import link from '@/views/link/link'


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
            component: Login,
            meta: {title: "登录"}
        },
        {
            path: '/main',
            name: 'Main',
            component: Main,
            meta: {title: "首页"},
            redirect: '/index',
            children: [
                {
                    path: '/index',
                    name: 'index',
                    meta: {title: "首页"},
                    component: index
                },
                {
                    path: '/admin',
                    name: 'admin',
                    meta: {title: "管理员"},
                    component: admin
                },
                {
                    path: '/addAdmin',
                    name: 'addAdmin',
                    meta: {title: "添加管理员"},
                    component: addAdmin
                },
                {
                    path: '/updateAdmin',
                    name: 'updateAdmin',
                    meta: {title: "添加管理员"},
                    component: updateAdmin
                },
                {
                    path: '/limit',
                    name: 'limit',
                    meta: {title: "访问限制"},
                    component: limit
                },
                {
                    path: '/role',
                    name: 'role',
                    meta: {title: "角色"},
                    component: role
                },
                {
                    path: '/permission',
                    name: 'permission',
                    meta: {title: "资源"},
                    component: permission
                },
                {
                    path: '/platform',
                    name: 'platform',
                    meta: {title: "平台"},
                    component: platform
                }, {
                    path: '/log',
                    name: 'log',
                    meta: {title: "日志", keepAlive: true},
                    component: log
                }, {
                    path: '/onlineAdmin',
                    name: 'onlineAdmin',
                    meta: {title: "在线管理员", keepAlive: true},
                    component: onlineAdmin
                },
                {
                    path: '/onlineUser',
                    name: 'onlineUser',
                    meta: {title: "在线用户", keepAlive: true},
                    component: onlineUser
                },
                {
                    path: '/link',
                    name: 'link',
                    meta: {title: "首页链接", keepAlive: true},
                    component: link
                }
            ]
        }
    ]
})
