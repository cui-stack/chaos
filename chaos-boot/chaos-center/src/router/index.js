import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login/login'
import Main from '@/views/main/main'

Vue.use(Router)

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
      //redirect: '/main',
      children: [
      ]
    }
  ]
})
