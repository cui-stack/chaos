<template>
    <el-container>
        <el-header style="height: 80px;">
            <Head/>
        </el-header>
        <el-container>
            <el-aside :width="{asideWidth} + 'px'">
                <SideMenu/>
            </el-aside>
            <el-main>
                <CenterHead/>
                <keep-alive>
                    <router-view v-if="$route.meta.keepAlive"/>
                </keep-alive>
                <router-view v-if="!$route.meta.keepAlive"/>
            </el-main>
        </el-container>
        <el-backtop/>
    </el-container>
</template>

<script>
    import Head from '@/chaos/components/Head'
    import SideMenu from '@/chaos/components/SideMenu'
    import CenterHead from '@/chaos/components/CenterHead'

    export default {
        name: 'Main',
        components: {Head, SideMenu, CenterHead},
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


<style lang="less" scoped>
    @import '@/chaos/styles/index';

</style>




