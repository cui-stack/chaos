export default [
  {
    path: '/user',
    component: '@/chaos/layouts/UserLayout',
    routes: [
      {
        name: '登录',
        path: '/user/login',
        component: '@/pages/user/login',
      },
    ],
  },
  {
    path: '/',
    component: '@/chaos/layouts/SecurityLayout',
    routes: [
      {
        path: '/',
        component: '@/chaos/layouts/BasicLayout',
        authority: ['ADMIN', 'CHAN'],
        routes: [
          {
            path: '/',
            redirect: '/welcome',
          },
          {
            path: '/welcome',
            name: '欢迎',
            icon: 'smile',
            component: '@/chaos/pages/welcome',
          },
          {
            name: '字典管理',
            icon: 'usergroupAdd',
            path: '/dicts',
            component: '@/pages/dicts',
            authority: ['ADMIN'],
          },
          {
            component: '@/chaos/pages/404',
          },
        ],
      },
      {
        component: '@/chaos/pages/404',
      },
    ],
  },
  {
    component: '@/chaos/pages/404',
  },
];
