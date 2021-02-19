export function routers() {
    return [{
        path: '/platform',
        name: 'platform',
        meta: {title: "平台"},
        component: () => import ('@/views/platform/index')
    }, {
        path: '/admin',
        name: 'admin',
        meta: {title: "管理员"},
        component: () => import ('@/views/admin/index')
    }, {
        path: '/addAdmin',
        name: 'addAdmin',
        meta: {title: "新增管理员"},
        component: () => import ('@/views/admin/addAdmin')
    }, {
        path: '/updateAdmin',
        name: 'updateAdmin',
        meta: {title: "修改管理员"},
        component: () => import ('@/views/admin/updateAdmin')
    }, {
        path: '/role',
        name: 'role',
        meta: {title: "角色"},
        component: () => import ('@/views/role/index')
    }, {
        path: '/permission',
        name: 'permission',
        meta: {title: "资源"},
        component: () => import ('@/views/permission/index')
    }, {
        path: '/log',
        name: 'log',
        meta: {title: "日志"},
        component: () => import ('@/views/log/index')
    }, {
        path: '/link',
        name: 'link',
        meta: {title: "链接"},
        component: () => import ('@/views/link/index')
    }, {
        path: '/index',
        name: 'index',
        meta: {title: "面板"},
        component: () => import ('@/views/index/index')
    }]
}

export function baseUrl() {
    let baseUrl = ''
    switch (process.env.VUE_APP_ENV) {
        case 'prod':
            baseUrl = ''
            break
        case 'test':
            baseUrl = ''
            break
        case 'dev':
            baseUrl = '127.0.0.1:'
            break
    }
    return baseUrl
}

export function appInfo() {
    return {
        headTitle: '管理后台',
        platformMu: '1'
    }
}
