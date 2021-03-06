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
    switch (process.env.NODE_ENV) {
        case 'development':
            baseUrl = ''
            break
        case 'production':
            baseUrl = '/chaos-api'
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

export function SideMenuData() {
    let m = new Map();
    return m
}

