<template>
  <el-container
    style="margin: 0px auto;display:flex;width:100%;background-color:#438eb9;height: 60px ">
    <el-container style="flex: 6;">
      <img style="height: 25px;width: 25px;margin:auto 0px auto 20px;"
           src="@/assets/images/logo-head.png"/>
      <span style="font-size:20px;color: white;margin:auto 0px ">矮芽管理系统</span>
    </el-container>
    <el-container style="flex: 1;margin: auto 0px ;">
      <el-dropdown>
       <span class="el-dropdown-link">
           <div>{{$store.state.roleinfo.info}}</div>
                {{$store.state.userinfo.username}}
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
                fetch.post('/manage/logout', {}).then((res) => {
                    //clearVuexAlong()
                    store.dispatch('logout', {})
                    setTimeout(function () {
                        window.location.href = '/iya/'
                    }.bind(this), 100)
                })
            }
        }
    }
</script>
<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: white;
    font-size: 16px;
  }

  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
