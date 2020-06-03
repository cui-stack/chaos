<template>
  <el-container>
    <el-menu
      :default-active="$route.path"
      class="side-menu"
      @open="handleOpen"
      @close="handleClose"
      @select="handleSelect"
      router
      background-color="#fff"
      text-color="#545c64"
      active-text-color="#438eb9">
      <el-submenu v-for="(menu,i) in menus" :key="i" :index="i+''">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>{{menu.title}}</span>
        </template>
        <el-menu-item v-for="(item,i) in menu.submenus" :key="i"
                      :index="item.link">
          <template slot="title">
            <span>{{item.title}}</span>
          </template>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </el-container>
</template>
<script>
    import fetch from '@/axios/fetch'

    export default {
        data() {
            return {
                menus: []
            }
        },
        mounted() {
            fetch.post('/manage/chaos_permission/adminps', {}).then((res) => {
                this.menus = res.data
            })
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>
<style scoped>

</style>
