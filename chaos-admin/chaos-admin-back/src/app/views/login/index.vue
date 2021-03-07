<template>
    <div class="login-container">
        <div class="login-head">
            <img src="@/app/logo.png"/>
            <h1 style="display: inline-block">{{headTitle}}</h1>
        </div>
        <div class="login-area">
            <input placeholder="请输入用户名" v-model="username"/>
            <input type="password" placeholder="请输入密码"
                   v-model="password" @keyup.enter="login"/>
            <button @click="login">登录</button>
        </div>
    </div>
</template>

<script>
    import {Message} from 'element-ui';
    import {post} from '@/chaos/functions/axios/fetch'
    import {appInfo} from '@/app/config'

    export default {
        name: 'Login',
        data() {
            return {
                username: '',
                password: '',
                headTitle:appInfo().headTitle
            }
        },
        methods: {
            async login() {
                if (this.username && this.password) {
                    const res = await post('/manage/login', {
                        username: this.username,
                        password: this.password,
                        platformMu: appInfo().platformMu
                    })
                    await this.$store.dispatch('admin/setUserinfo', res)
                    await this.$store.dispatch('admin/setMenus', res.menus)
                    await this.$router.push(res.indexLink)
                } else {
                    Message({
                        type: 'warning',
                        message: '请输入用户名和密码'
                    });
                }
            },
        },
    }
</script>

<style lang="less" scoped>

    .login-container {
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0;
        left: 0;
        background-color: #483D8B;
        color: white;
        text-align: center;

        .login-head {
            margin-top: 180px;
        }

        .login-area {
            background-color: white;
            width: 400px;
            height: 240px;
            margin: 10px auto;
            border-radius: 30px;
            padding-top: 40px;
        }
    }

    input {
        display: block;
        width: 250px;
        height: 40px;
        line-height: 40px;
        margin: 20px auto;
        border: 1px solid #888;
        padding: 10px;
        box-sizing: border-box;
        font-size: 18px;
    }

    button {
        display: block;
        width: 250px;
        height: 45px;
        line-height: 40px;
        margin: 0 auto;
        border: none;
        background-color: #483D8B;
        color: #fff;
        font-size: 18px;

        &:hover {
            background-color: #1f2d3d;
        }
    }

</style>
