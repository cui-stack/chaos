<template>
    <el-container>
        <Hamburger :is-active="sideMenuOpened" @toggleClick="toggleSideMenu"/>
        <Breadcrumb/>
    </el-container>
</template>

<script>
    import Breadcrumb from './BreadCrumb'
    import Hamburger from './Hamburger'

    export default {
        name: 'CenterHead',
        components: {
            Breadcrumb,
            Hamburger
        },
        data() {
            return {
                sideMenuOpened: true
            }
        },
        methods: {
            toggleSideMenu() {
                this.$store.dispatch('app/toggleSideMenu')
                this.sideMenuOpened = !this.sideMenuOpened
            }
        }
    }
</script>
<style lang="less" scoped>
    .el-container {
        padding: 4px;
    }
</style>
