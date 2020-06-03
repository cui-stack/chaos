<template>
  <el-container>
    <el-header class="main-header">
      <Head></Head>
    </el-header>
    <el-container style="display: flex;">
      <el-aside style="width: 201px">
        <SideMenu></SideMenu>
      </el-aside>
      <el-main style="flex: 1;padding: 0px;">
        <CenterHead></CenterHead>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
    import Head from '@/components/Head'
    import SideMenu from '@/components/SideMenu'
    import CenterHead from '@/components/CenterHead'
    import store from '@/vuex/store'

    export default {
        store,
        components: {Head, SideMenu, CenterHead},
        data() {
            return {}
        },
        mounted() {
            if (!this.$store.getters.getUserinfo.username) {
                this.$router.push('/')
            }
        }
    }
</script>


<style scoped>
  body {
    margin: 0px;
  }

  .main-header {
    padding: 0px;
  }

</style>




