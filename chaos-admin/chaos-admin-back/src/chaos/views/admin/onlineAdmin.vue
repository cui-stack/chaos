<template>
    <el-container>
        <el-header>
            <el-button type="primary" v-on:click="logoutAll">强制下线</el-button>
        </el-header>
        <el-main>
            <el-table stripe :data="tableData">
                <el-table-column type="index" width="50"/>
                <el-table-column prop="mu" label="编号" width="180"/>
                <el-table-column prop="username" label="账号" width="180"/>
                <el-table-column prop="phone" label="手机号" width="180"/>
                <el-table-column prop="roleInfo" label="角色" width="180"/>
                <el-table-column prop="ip" label="IP" width="180"/>
                <el-table-column prop="loginTime" label="登录时间" sortable width="180"/>
                <el-table-column prop="token" label="TOKEN" min-width="60"/>
            </el-table>
        </el-main>
    </el-container>
</template>

<script>
    import fetch from 'chaos-data/axios/fetch'

    export default {
        components: {},
        data() {
            return {
                tableData: []
            }
        },
        created() {
            this.search()
        },
        methods: {
            search() {
                fetch.post('/manage/loginAdmins', {}).then((res) => {
                    this.tableData = res.data;
                })
            },
            logoutAll() {
                fetch.post('/manage/logoutAll', {}).then(() => {
                })
            }
        }
    }
</script>
<style scoped>

</style>
