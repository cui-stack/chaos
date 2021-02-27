<template>
    <el-container class="page">
        <el-container class="page_left">
            <img src="@/app/logo.png"/>
            <span>{{headTitle}}后台</span>
        </el-container>
        <el-container class="page_right">
            <el-dropdown>
                <span class="el-dropdown-link">
                    {{rolename}}<br>{{username}}
                    <i class="el-icon-caret-bottom"/>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item @click.native="logout">退出
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-container>
    </el-container>
</template>

<script>
    import {appInfo} from '@/app/config'

    export default {
        name: "Head",
        data() {
            return {
                username: '',
                rolename: '',
                headTitle:appInfo().headTitle
            }
        },
        created() {
            let {username, roleName} = this.$store.getters.user
            this.username = username;
            this.rolename = roleName;
        },
        methods: {
            logout() {
                this.$store.dispatch('admin/logout')
                this.$store.dispatch('app/logout')
                this.$router.push('/')
            },
        }
    }
</script>
<style scoped>
    .page {
        width: 100%;
        background-color: #483D8B;
        height: 80px;
    }

    .page_left {
        flex: 1;
    }

    .page_right {
        flex: 1;
        margin: auto 20px;
        display: inline-block;
        text-align: right;
    }

    img {
        height: 25px;
        width: 25px;
        margin: auto 20px;
    }

    span {
        font-size: 20px;
        color: white;
        margin: auto 0px;
    }

    .el-dropdown-link {
        cursor: pointer;
        color: white;
        font-size: 16px;
    }

</style>
