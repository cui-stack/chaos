<template>
    <el-container>
        <el-header style="height: 80px;">
            <Head></Head>
        </el-header>
        <el-container>
            <el-aside :width="{asideWidth} + 'px'">
                <SideMenu></SideMenu>
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
    export default {
        name: 'Main',
        data() {
            return {
                asideWidth: 300,
            }
        },
        created() {
            if (!this.$store.getters.user.username) {
                this.$router.push('/')
                return
            }
            this.asideWidth = this.$store.getters.asideWidth
        },
    }
</script>


<style scoped>
    * {
        margin: 0;
        padding: 0;
        border: 0;
    }
</style>




