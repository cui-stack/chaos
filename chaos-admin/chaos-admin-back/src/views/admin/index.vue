<template>
    <el-container>
        <el-header style="height: 70px;">
            <el-container style="margin:auto 0px;padding: 10px;">
                <el-button type="primary" @click="showAdd">增加管理员</el-button>
                <el-input style="width: 220px" v-model="name"
                          placeholder="请输入管理员姓名"/>
                <Platform v-model="platformMu"
                          @platformChange="platformChange"/>
                <el-button type="primary" @click="search">搜索</el-button>
            </el-container>
        </el-header>
        <el-main>
            <el-table stripe :data="tableData">
                <el-table-column type="index" width="50"/>
                <el-table-column prop="mu" label="编号" width="200"/>
                <el-table-column prop="username" label="账号" min-width="120"/>
                <el-table-column prop="name" label="姓名" width="120"/>
                <el-table-column prop="lastloginTime" sortable label="最后登录"
                                 width="200"/>
                <el-table-column prop="ip" label="登录IP" width="140"/>
                <el-table-column prop="loginTimes" sortable label="登录次数"
                                 width="120">
                    <template slot-scope="scope">
                        <el-tag>{{scope.row.loginTimes}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="状态" width="120">
                    <template slot-scope="scope">
                        <div style="color: #67c23a"
                             v-if="scope.row.status=='1'">
                            停用
                        </div>
                        <div style="color: #f56c6c"
                             v-if="scope.row.status=='0'">
                            正常
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="createTime" sortable label="创建时间"
                                 width="200"/>
                <el-table-column label="操作" width="195">
                    <template slot-scope="scope">
                        <el-button plain @click="showUpdate(scope.row.mu)">编辑
                        </el-button>
                        <el-button style="margin: 0px" plain
                                   @click="doDelete(scope.row.mu)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <Pagination :currentPage="currentPage" :total="total" :limit="limit"
                        @handleCurrentChange="handleCurrentChange"
                        @handleSizeChange="handleSizeChange"/>
        </el-main>
    </el-container>
</template>

<script>
    import Pagination from '@/chaos/components/Pagination'
    import Data from '@/chaos/functions/common/Data';
    import PageData from '@/chaos/functions/common/PageData';
    import Platform from '@/components/Platform'
    import {eventBus} from '@/main'
    import {page} from '@/chaos/functions/mixin/page'

    export default {
        components: {
            Platform,
            Pagination
        },
        mixins: [page],
        data() {
            return PageData.tableData('chaos_admin', {
                name: '',
                platformMu: ''
            })
        },
        created() {
            eventBus.$on('platformInited', (platformMu) => {
                this.platformMu = platformMu
                this.search()
            })
        },
        methods: {
            async search() {
                const res = await Data.page(this.table, this.currentPage, this.limit, {
                    name: this.name,
                    platformMu: this.platformMu
                })
                this.tableData = res.list;
                this.total = parseInt(res.total);
            },
            showAdd() {
                this.$router.push('/addAdmin')
            },
            showUpdate(mu) {
                this.$router.push({
                    name: 'updateAdmin',
                    params: {mu: mu}
                })
            },
            doDelete(mu) {
                Data.remove(this.table, mu, () => {
                    this.search()
                })
            },
            listPlatform() {
                Data.list('chaos_platform', {}, (res) => {
                    this.platforms = res.data
                    this.platformMu = this.platforms[0].mu
                })
            },
            platformChange(val) {
                this.platformMu = val
                this.search()
            },
        }
    }
</script>
<style scoped>

</style>
