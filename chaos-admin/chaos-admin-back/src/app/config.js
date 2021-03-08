export function routers() {
    return [
        {
            path: '/platform',
            name: 'platform',
            meta: {title: "平台"},
            component: () => import ('@/app/views/platform/index')
        },
        {
            path: '/admin',
            name: 'admin',
            meta: {title: "管理员"},
            component: () => import ('@/app/views/admin/index')
        },
        {
            path: '/addAdmin',
            name: 'addAdmin',
            meta: {title: "新增管理员"},
            component: () => import ('@/app/views/admin/add')
        },
        {
            path: '/updateAdmin',
            name: 'updateAdmin',
            meta: {title: "修改管理员"},
            component: () => import ('@/app/views/admin/update')
        },
        {
            path: '/role',
            name: 'role',
            meta: {title: "角色"},
            component: () => import ('@/app/views/role/index')
        },
        {
            path: '/resource',
            name: 'resource',
            meta: {title: "资源"},
            component: () => import ('@/app/views/resource/index')
        },
        // {
        //     path: '/log',
        //     name: 'log',
        //     meta: {title: "日志"},
        //     component: () => import ('@/app/views/log/index')
        // },
        // {
        //     path: '/link',
        //     name: 'link',
        //     meta: {title: "链接"},
        //     component: () => import ('@/app/views/link/index')
        // },
        // {
        //     path: '/index',
        //     name: 'index',
        //     meta: {title: "面板"},
        //     component: () => import ('@/app/views/index/index')
        // }
    ]
}

export function baseUrl() {
    let baseUrl = ''
    switch (process.env.NODE_ENV) {
        case 'development':
            baseUrl = ''
            break
        case 'production':
            baseUrl = '/admin-api'
            break
    }
    return baseUrl
}

export function appInfo() {
    return {
        headTitle: '管理',
        platformMu: '1'
    }
}


export function SideMenuData() {
    let m = new Map();
    return m
}

