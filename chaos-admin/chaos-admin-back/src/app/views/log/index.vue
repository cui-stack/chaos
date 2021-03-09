<template>
    <el-container>
        <el-header>
            <el-container>
                <SearchInput placeholder="请输入用户MU"
                             :change="(value)=>this.handleChange(value,'userMu')"/>
                <SearchInput placeholder="请输入URI"
                             :change="(value)=>this.handleChange(value,'uri')"/>
                <SearchButton :click="search"/>
            </el-container>
        </el-header>
        <el-main>
            <el-table stripe :data="tableData"
                      element-loading-text="拼命加载中"
                      element-loading-spinner="el-icon-loading"
                      element-loading-background="rgba(0, 0, 0, 0.8)"
                      v-loading.fullscreen.lock="loading">
                <el-table-column prop="mu" label="日志编号" width="180"/>
                <el-table-column prop="createTime" label="创建时间" width="100"/>
                <el-table-column prop="userMu" label="用户MU" width="80"/>
                <el-table-column prop="ip" label="IP" width="120"/>
                <el-table-column prop="uri" label="URI" width="250"/>
                <el-table-column prop="time" label="时间" width="60"/>
                <el-table-column prop="request" label="请求信息" min-width="60"/>
                <el-table-column prop="response" label="响应信息" min-width="60"/>
            </el-table>
            <SearchPagination :currentPage="currentPage" :total="total"
                              :limit="limit"
                              @handleCurrentChange="handleCurrentChange"
                              @handleSizeChange="handleSizeChange"/>
        </el-main>
    </el-container>
</template>

<script>
    import {page} from '@/chaos/functions/mixin/crud'

    export default {
        mixins: [page],
        data() {
            return {
                domain: 'chaos_log',
            }
        },
        created() {
            this.search()
        }
    }
</script>
<style scoped>
</style>
