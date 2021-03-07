<template>
    <el-container>
        <el-header>
            <el-container>
                <PrimaryButton text="增加" :click="showAdd"/>
                <SearchInput placeholder="请输入管理员姓名"
                             :change="(value)=>handleChange(value,'name')"/>
                <Platform :init="(value)=>handleChange(value,'platformMu')"
                          :change="(value)=>handleChange(value,'platformMu')"/>
                <SearchButton :click="search"/>
            </el-container>
        </el-header>
        <el-main>
            <el-table stripe :data="tableData"
                      element-loading-text="拼命加载中"
                      element-loading-spinner="el-icon-loading"
                      element-loading-background="rgba(0, 0, 0, 0.8)"
                      v-loading.fullscreen.lock="loading">
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
                        <PlainButton text="编辑"
                                     :click="()=>showUpdate(scope.row.mu)"/>
                        <PlainButton text="删除"
                                     :click="()=>doDelete(scope.row.mu)"/>
                    </template>
                </el-table-column>
            </el-table>
            <SearchPagination :currentPage="currentPage" :total="total"
                              :limit="limit"
                              @handleCurrentChange="handleCurrentChange"
                              @handleSizeChange="handleSizeChange"/>
        </el-main>
    </el-container>
</template>

<script>
    import Platform from '@/app/components/Platform'
    import {page, remove} from '@/chaos/functions/mixin/crud'
    import {pushcreate, pushupdate} from '@/chaos/functions/mixin/pushcrud'

    export default {
        components: {
            Platform,
        },
        mixins: [page, remove, pushcreate, pushupdate],
        data() {
            return {
                domain: 'chaos_admin',
                addPath: 'addAdmin',
                updatePath: 'updateAdmin',
                platforms: []
            }
        },
    }
</script>
<style scoped>

</style>
