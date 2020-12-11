<template>
    <el-container>
        <el-main>
            <el-menu
                    :collapse=sidebar
                    router
                    class="side-menu"
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

    export default {
        name: 'SideMenu',
        data() {
            return {
                isCollapse: false,
                asideWidth: 300,
                menus: []
            }
        },
        computed: {
            sidebar() {
                return this.$store.getters.isCollapse
            },
        },
        created() {
            this.menus = this.$store.getters.menus
        },
    }
</script>
<style scoped>
    .el-header {
        padding: 0;
    }

    .el-main {
        padding: 0;
    }

    .side-menu:not(.el-menu--collapse) {
        width: 200px;
        min-height: 400px;
    }

</style>
