<template>
  <el-container class="page">
    <el-container class="page_left">
      <img src="./../assets/images/pongo64.png"/>
      <span>管理系统</span>
    </el-container>
    <el-container class="page_right">
      <el-dropdown>
       <span class="el-dropdown-link">
           <div>{{$store.state.backroleinfo.info}}</div>
                {{$store.state.backuserinfo.username}}
          <i class="el-icon-caret-bottom"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-container>
  </el-container>
</template>

<script>
    import store from '@/vuex/store';
    import fetch from '@/axios/fetch'

    export default {
        store,
        methods: {
            logout() {
                fetch.post('/manage/logout', {}).then(() => {
                    store.dispatch('logout', {})
                    setTimeout(function () {
                        window.location.href = '/chaos/'
                    }.bind(this), 100)
                })
            }
        }
    }
</script>
<style scoped>
  .page {
    display: flex;
    width: 100%;
    background-color: #483D8B;
    height: 80px;
  }

  .page_left {
    flex: 1;
  }

  .page_right {
    flex: 1;
    margin: auto 20px;
    display: inline-block;
    text-align: right;
  }

  img {
    height: 25px;
    width: 25px;
    margin: auto 20px;
  }

  span {
    font-size: 20px;
    color: white;
    margin: auto 0px;
  }

  .el-dropdown-link {
    cursor: pointer;
    color: white;
    font-size: 16px;
  }

</style>
