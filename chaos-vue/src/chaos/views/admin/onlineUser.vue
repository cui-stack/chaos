<template>
    <el-container>
        <el-main>
            <el-table stripe :data="tableData">
                <el-table-column type="index" width="50"/>
                <el-table-column prop="mu" label="编号" width="180"/>
                <el-table-column prop="phone" label="电话" width="180"/>
                <el-table-column prop="nick" label="昵称" width="180"/>
                <el-table-column prop="ip" label="IP" width="180"/>
                <el-table-column prop="loginTime" label="登录时间" sortable
                                 width="240"/>
                <el-table-column prop="token" label="TOKEN" min-width="80"/>
            </el-table>
        </el-main>
    </el-container>
</template>

<script>
    import Data from '@/chaos/functions/common/Data';

    export default {
        data() {
            return {
                tableData: [],
            }
        },
        created() {
            this.search()
        },
        methods: {
            async search() {
                this.tableData = await Data.query('loginUsers') || []
            }
        }
    }
</script>
<style scoped>

</style>
