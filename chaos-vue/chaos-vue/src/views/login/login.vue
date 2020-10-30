<template>
  <div class="login-container">
    <div class="login-head">
      <img src="../../assets/images/pongo72.png">
    </div>
    <div class="login-area">
      <div class="login-input">
        <input type="text" placeholder="请输入用户名" v-model="username">
        <input type="password" placeholder="请输入密码" v-model="password"
               v-on:keyup.enter="login">
        <button v-on:click="login">登录</button>
      </div>
    </div>

  </div>
</template>

<script>
  import fetch from '@/axios/fetch'
  import store from '@/vuex/store'

  export default {
    name: 'Login',
    store,
    methods: {
      login() {
        if (this.username == "" || this.password == "") {
          alert("请输入用户名或密码")
        } else {
          fetch.post('/manage/login', {
            username: this.username,
            password: this.password,
            platformMu: '1'
          }).then((res) => {
            store.dispatch('setUserinfo', res.data)
            store.dispatch('setRoleinfo', res.data.role)
            store.dispatch('setMenus', [])
            setTimeout(function () {
              this.$router.push(res.data.role.indexLink)
            }.bind(this), 100)
          })
        }
      },
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
    background-color: #B0C4DE;
    font-family: "宋体";
    color: white;
    text-align: center;
  }

  .login-head {
    margin-top: 180px;
  }

  .login-area {
    text-align: center;
    background-color: white;
    color: #483D8B;
    width: 400px;
    height: 280px;
    margin: 10px auto;
    border-radius: 15px;
  }

  .login-input {
    padding-top: 30px;
  }


  .login-area h2 {
    padding-top: 20px;
  }

  input {
    display: block;
    width: 250px;
    height: 40px;
    line-height: 40px;
    margin: 20px auto;
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
