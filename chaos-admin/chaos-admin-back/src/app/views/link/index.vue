<template>
    <el-container>
        <el-header>
            <PrimaryButton text="增加" :click="showAdd"/>
        </el-header>
        <el-main>
            <el-table stripe :data="tableData"
                      element-loading-text="拼命加载中"
                      element-loading-spinner="el-icon-loading"
                      element-loading-background="rgba(0, 0, 0, 0.8)"
                      v-loading.fullscreen.lock="loading">
                <el-table-column prop="mu" label="编号" width="188"/>
                <el-table-column prop="title" label="标题" width="108"/>
                <el-table-column prop="link" label="地址" min-width="60"/>
                <el-table-column prop="info" label="介绍" width="160"/>
                <el-table-column prop="createTime" sortable label="创建时间"
                                 width="200"/>
                <el-table-column label="操作" width="168">
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
            <el-dialog width="35%" title="添加链接" :visible.sync="showAddForm">
                <el-form ref="form" :rules="rules" :model="form"
                         label-width="100px" size="small">
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="form.title" placeholder="请输入标题"/>
                    </el-form-item>
                    <el-form-item label="地址" prop="link">
                        <el-input v-model="form.link" placeholder="请输入地址"/>
                    </el-form-item>
                    <el-form-item label="介绍" prop="info">
                        <el-input v-model="form.info" placeholder="请输入介绍"/>
                    </el-form-item>
                    <el-form-item>
                        <PrimaryButton text="确定" :click="doAdd"/>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改链接" :visible.sync="showUpdateForm">
                <el-form ref="updateForm" :rules="rules" :model="updateForm"
                         label-width="100px" size="small">
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="updateForm.title"
                                  placeholder="请输入标题"/>
                    </el-form-item>
                    <el-form-item label="地址" prop="link">
                        <el-input v-model="updateForm.link"
                                  placeholder="请输入链接"/>
                    </el-form-item>
                    <el-form-item label="介绍" prop="info">
                        <el-input v-model="updateForm.info"
                                  placeholder="请输入链接"/>
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
        mixins: [page, remove, create, update],
        data() {
            const rules = {
                title: [
                    {required: true, message: '请输入标题', trigger: 'blur'},
                ],
                link: [
                    {required: true, message: '请输入地址', trigger: 'blur'},
                ]
            }
            return {
                rules,
                domain: 'chaos_link',
            }
        },
        created() {
            this.search()
        },
        methods: {}
    }
</script>
<style scoped>

    .el-form {
        width: 500px;
    }
</style>
