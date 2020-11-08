<template>
    <el-container>
        <el-header style="height: 40px">
            <el-radio-group v-model="isCollapse" @change="doChange">
                <el-radio-button :label="false">展开</el-radio-button>
                <el-radio-button :label="true" v-if="isShow">收起
                </el-radio-button>
            </el-radio-group>
        </el-header>
        <el-main>
            <el-menu
                    router
                    :collapse="isCollapse"
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
                isShow: true
            }
        },
        props: {
            'menus': {
                required: false,
                default: function () {
                    return []
                }
            },
        },
        methods: {
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
