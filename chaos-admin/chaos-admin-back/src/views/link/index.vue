<template>
    <el-container>
        <el-header>
            <el-container>
                <el-button type="primary" @click="showAddForm=true">增加链接
                </el-button>
            </el-container>
        </el-header>
        <el-main>
            <el-table :data="tableData" stripe>
                <el-table-column prop="mu" label="编号" width="188"/>
                <el-table-column prop="title" label="标题" width="108"/>
                <el-table-column prop="link" label="地址" min-width="60"/>
                <el-table-column prop="info" label="介绍" width="160"/>
                <el-table-column prop="createTime" sortable label="创建时间" width="200"/>
                <el-table-column label="操作" width="168">
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
                        <el-button type="primary" @click="doAdd">确定</el-button>
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
                        <el-button type="primary" @click="doUpdate">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </el-footer>
    </el-container>
</template>

<script>
    import Pagination from '@/chaos/components/Pagination'
    import {page} from '@/chaos/functions/mixin/page'
    import {crud} from '@/chaos/functions/mixin/crud'

    export default {
        components: {Pagination},
        mixins: [page, crud],
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
                table: 'chaos_link',
            }
        },
        created() {
            this.search()
        },
        methods: {
        }
    }
</script>
<style scoped>
    .el-header .el-container {
        margin: auto 0px;
        padding: 10px;
    }

    .el-input {
        width: 280px;
    }
</style>
