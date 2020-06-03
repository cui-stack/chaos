<template>
  <div class="login-container">
    <div class="login-head">
      <img src="../../assets/pongo72.png">
      <span style="font-size:35px;padding-bottom: 10px">混沌</span>
    </div>
    <div class="login-area">
      <h2>后台登录</h2>
      <input type="text" placeholder="请输入用户名" v-model="username">
      <input type="password" placeholder="请输入密码" v-model="password">
      <button v-on:click="login">登录</button>
    </div>

  </div>
</template>

<script>
    import fetch from '../../axios/fetch'
    import store from '../../vuex/store'

    export default {
        name: 'Login',
        store,
        methods: {
            login() {
                if (this.username == "" || this.password == "") {
                    alert("请输入用户名或密码")
                } else {
                    const data = {
                        username: this.username,
                        password: this.password,
                        platformMu: 1
                    }
                    fetch.post('/manage/login', data).then((res) => {
                        store.dispatch('setUserinfo', res.data)
                        setTimeout(function () {
                            fetch.post('/manage/chaos_admin/index', {}).then((res) => {
                                store.dispatch('setRoleinfo', res.data)
                                this.$router.push(res.data.indexLink)
                            })
                        }.bind(this), 100)
                    })
                }
            }
        },
        data() {
            return {
                username: '',
                password: ''
            }
        }
    }
</script>

<style scoped>

  .login-container {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    overflow-y: auto;
    background-color: #DCDFE6;
    color: black;
    text-align: center;
  }

  .login-head {
    margin-top: 50px;
  }

  .login-area {
    text-align: center;
    background-color: white;
    color: black;
    width: 360px;
    height: 260px;
    margin: 0 auto;
  }

  .login-area h2 {
    padding-top: 20px;
  }

  input {
    display: block;
    width: 250px;
    height: 40px;
    line-height: 40px;
    margin: 0 auto;
    margin-bottom: 10px;
    outline: none;
    border: 1px solid #888;
    padding: 10px;
    box-sizing: border-box;
  }

  p {
    color: red;
  }

  button {
    display: block;
    width: 250px;
    height: 40px;
    line-height: 40px;
    margin: 0 auto;
    border: none;
    background-color: #41b883;
    color: #fff;
    font-size: 16px;
  }

  span {
    cursor: pointer;
  }

  span:hover {
    color: #41b883;
  }
</style>
