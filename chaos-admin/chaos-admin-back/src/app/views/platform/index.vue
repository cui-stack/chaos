<template>
    <el-container>
        <el-header>
            <el-container>
                <PrimaryButton text="增加" :click="showAdd"/>
                <SearchInput placeholder="请输入平台名称"
                             :change="(value)=>this.handleChange(value,'name')"/>
                <SearchButton :click="search"/>
            </el-container>
        </el-header>
        <el-main>
            <el-table stripe :data="tableData"
                      element-loading-text="拼命加载中"
                      element-loading-spinner="el-icon-loading"
                      element-loading-background="rgba(0, 0, 0, 0.8)"
                      v-loading.fullscreen.lock="loading">
                <el-table-column prop="mu" label="编号" width="120"/>
                <el-table-column prop="name" label="平台名称" min-width="120"/>
                <el-table-column prop="createTime" sortable label="创建时间"
                                 min-width="120"/>
                <el-table-column prop="info" label="介绍" min-width="120"/>
                <el-table-column label="操作" width="178">
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
        <el-footer>
            <el-dialog width="35%" title="添加平台" :visible.sync="showAddForm">
                <el-form ref="form" :rules="rules" :model="form"
                         label-width="100px" size="small">
                    <el-form-item label="平台名称" prop="name">
                        <el-input v-model="form.name" placeholder="请输入平台名称"/>
                    </el-form-item>
                    <el-form-item label="平台介绍">
                        <el-input v-model="form.info" placeholder="请输入平台介绍"/>
                    </el-form-item>
                    <el-form-item>
                        <PrimaryButton text="确定" :click="doAdd"/>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改平台" :visible.sync="showUpdateForm">
                <el-form ref="updateForm" :rules="rules" :model="updateForm"
                         label-width="100px" size="small">
                    <el-form-item label="平台名称" prop="name">
                        <el-input v-model="updateForm.name"
                                  placeholder="请输入平台名称"/>
                    </el-form-item>
                    <el-form-item label="平台介绍">
                        <el-input v-model="updateForm.info"
                                  placeholder="请输入平台介绍"/>
                    </el-form-item>
                    <el-form-item>
                        <PrimaryButton text="确定" :click="doUpdate"/>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </el-footer>
    </el-container>
</template>

<script>
    import {page, remove, create, update} from '@/chaos/functions/mixin/crud'

    export default {
        name: "ChaosPlatform",
        mixins: [page, remove, create, update],
        data() {
            const rules = {
                name: [
                    {required: true, message: '请输入平台名称', trigger: 'blur'},
                ]
            }
            return {
                rules,
                domain: 'chaos_platform',
            }
        },
        created() {
            this.search()
        },
    }
</script>
<style scoped>
    .el-form {
        width: 500px;
    }
</style>
