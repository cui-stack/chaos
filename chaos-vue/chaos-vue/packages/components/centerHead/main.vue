<template>
    <el-container>
        <hamburger :is-active="sidebarOpened" class="hamburger-container" @toggleClick="toggleSideBar" />
        <breadcrumb class="breadcrumb-container"/>
    </el-container>
</template>

<script>
    import Breadcrumb from './../breadcrumb/main'
    import Hamburger from './../hamburger/main'

    export default {
        name: 'CenterHead',
        components: {
            Breadcrumb,
            Hamburger
        },
        data() {
            return {
                sidebarOpened:true
            }
        },
        props: {
            'indexLink': {
                required: false,
                default: ''
            },
        },
        methods: {
            toggleSideBar() {
                this.$store.dispatch('app/toggleSideBar')
                this.sidebarOpened=!this.sidebarOpened
            }
        }
    }
</script>
<style scoped>
    .el-container {
        padding: 3px;
    }

    .el-link {
        margin-left: 10px;
    }

    .hamburger-container {
        line-height: 46px;
        height: 100%;
        float: left;
        cursor: pointer;
        transition: background .3s;
        -webkit-tap-highlight-color: transparent;

        &:hover {
            background: rgba(0, 0, 0, .025)
        }

    }

    .breadcrumb-container {
        float: left;
    }
</style>
