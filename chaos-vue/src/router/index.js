import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

export default new Router({
    routes: [{
        path: '/',
        component: () => import('@/views/login/index'),
        meta: {
            title: "登录"
        }
    },
        {
            path: '/main',
            component: () => import('@/layout/main/index'),
            redirect: '/index',
            children: [{
                path: '/index',
                meta: {
                    title: "首页"
                },
                component: () => import('@/views/index/index')
            }, {
                path: '/user',
                meta: {title: "用户列表"},
                component: () => import('@/views/user/index')
            }, {
                path: '/onlineUser',
                name: 'onlineUser',
                meta: {title: "在线用户", keepAlive: true},
                component: () => import ('@/views/admin/user')
            }, {
                path: '/onlineAdmin',
                name: 'onlineAdmin',
                meta: {title: "在线管理员", keepAlive: true},
                component: () => import ('@/views/admin/admin')
            }, {
                path: '/limit',
                name: 'limit',
                meta: {title: "访问限制"},
                component: () => import ('@/views/admin/limit')
            },
            ]
        }
    ]
})
