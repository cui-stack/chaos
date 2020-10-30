import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login/login'
import Main from '@/views/main/main'
import index from '@/views/index/index'

Vue.use(Router)

export default new Router({
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return {
        x: 0,
        y: 0
      }
    }
  },
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

      ]
    }
  ]
})
