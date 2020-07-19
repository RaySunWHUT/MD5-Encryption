import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from '@/views/Login'
import Home from '@/views/Home'
import Register from '@/views/Register'
import UserRegister from '@/views/UserRegister'
import Test from '@/views/Test'
import NotFound from '@/views/NotFound'

Vue.use(VueRouter)

const routes = [{
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/home',
        name: 'home',
        component: Home
    },
    {
        path: '/userRegister',
        name: 'userRegister',
        component: UserRegister
    },
    {
        path: '/test',
        name: 'test',
        component: Test
    },
    {
        path: '/notFound',
        name: 'notFound',
        component: NotFound
    },
    {
        path: '/',
        name: 'main',
        redirect: '/register'
    },
    {
        path: '/register',
        name: 'register',
        component: Register
    },
    {
        path: '*', // 访问路径不存在时, 重定向到404
        redirect: '/notFound'
    }

]

const router = new VueRouter({

    // mode: 'history',
    mode: process.env.IS_ELECTRON ? 'hash' : 'history',

    base: process.env.BASE_URL,

    routes

})

export default router