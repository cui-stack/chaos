<template>
  <el-container class="page">
    <el-header style="height: 80px;">
      <Head></Head>
    </el-header>
    <el-container class="page_body">
      <el-aside :width="{asideWidth} + 'px'">
        <SideMenu @sideMenuChange="sideMenuChange"></SideMenu>
      </el-aside>
      <el-main>
        <CenterHead></CenterHead>
        <keep-alive>
          <router-view v-if="$route.meta.keepAlive"></router-view>
        </keep-alive>
        <router-view v-if="!$route.meta.keepAlive"></router-view>
      </el-main>
    </el-container>
    <el-backtop></el-backtop>
  </el-container>
</template>

<script>
  import Head from '@/components/Head'
  import SideMenu from '@/components/SideMenu'
  import CenterHead from '@/components/CenterHead'
  import store from '@/vuex/store'

  export default {
    name: 'Main',
    store,
    components: {Head, SideMenu, CenterHead},
    data() {
      return {
        asideWidth: 300
      }
    },
    created() {
      if (!this.$store.getters.getUserinfo.username) {
        this.$router.push('/')
      }
    },
    methods: {
      sideMenuChange(isCollapse) {
        this.asideWidth = isCollapse ? 80 : 300
      }
    }
  }
</script>


<style scoped>
  * {
    margin: 0;
    padding: 0;
    border: 0;
  }

  .page {

  }

  .page_body {
  }


</style>




