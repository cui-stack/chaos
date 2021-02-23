<template>
    <el-container>
        <el-header>
            <el-container>
                <el-button type="primary" @click="showAddForm=true">增加平台</el-button>
                <el-input v-model="data.name" placeholder="请输入平台名称"/>
                <el-button type="primary" @click="search">搜索</el-button>
            </el-container>
        </el-header>
        <el-main>
            <el-table :data="tableData" stripe>
                <el-table-column prop="mu" label="编号" width="120"/>
                <el-table-column prop="name" label="平台名称" min-width="120"/>
                <el-table-column prop="createTime" sortable label="创建时间"
                                 min-width="120"/>
                <el-table-column prop="info" label="介绍" min-width="120"/>
                <el-table-column label="操作" width="178">
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
                        <el-button type="primary" @click="doAdd">确定</el-button>
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
                        <el-button type="primary" @click="doUpdate">确定
                        </el-button>
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
                name: [
                    {required: true, message: '请输入平台名称', trigger: 'blur'},
                ]
            }
            return {
                rules,
                table: 'chaos_platform',
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
