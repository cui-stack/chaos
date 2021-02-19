export function routers() {
    return [{
        path: '/user',
        name: 'user',
        meta: {title: "用户列表"},
        component: () => import ('@/views/user/index')
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
        headTitle: '后台',
        platformMu: '2'
    }
}
