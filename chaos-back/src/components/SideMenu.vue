<template>
  <el-container>
    <el-header style="padding: 0px;height: 40px">
      <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;"
                      @change="doChange">
        <el-radio-button :label="false">展开</el-radio-button>
        <el-radio-button :label="true" v-if="isShow">收起</el-radio-button>
      </el-radio-group>
    </el-header>
    <el-main style="padding: 0px">
      <el-menu
        :default-active="$route.path"
        class="side-menu"
        @open="handleOpen"
        @close="handleClose"
        @select="handleSelect"
        :collapse="isCollapse"
        router
        background-color="#fff"
        text-color="#545c64"
        active-text-color="#438eb9">
        <el-submenu v-for="(menu,i) in menus" :key="i" :index="i+''">
          <template slot="title">
            <i :class="menu.icon"></i>
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
    </el-main>
  </el-container>
</template>
<script>
    import fetch from '@/axios/fetch'
    import store from '@/vuex/store'

    export default {
        data() {
            return {
                menus: [],
                isCollapse: false,
                isShow: true
            }
        },
        mounted() {
            if (this.$store.getters.getMenus.length == 0) {
                fetch.post('/manage/chaos_permission/adminps', {}).then((res) => {
                    store.dispatch('setMenus', res.data)
                    this.menus = this.$store.getters.getMenus
                })
            }
            this.menus = this.$store.getters.getMenus
        },
        methods: {
            handleOpen(key, keyPath) {
            },
            handleClose(key, keyPath) {
            },
            handleSelect(key, keyPath) {
                //console.log(key, keyPath);
            },
            doChange() {
                if (this.isCollapse) {
                    this.isShow = false
                    setTimeout(function () {
                        this.$emit("sideMenuChange", this.isCollapse)
                    }.bind(this), 300)
                } else {
                    this.isShow = true
                    this.$emit("sideMenuChange", this.isCollapse)
                }
            }
        }
    }
</script>
<style scoped>
  .side-menu:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
</style>
