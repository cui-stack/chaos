<template>
    <el-container>
        <el-header>
            <el-container>
                <el-button type="primary" @click="showAddForm=true">增加平台
                </el-button>
                <el-input v-model="name" placeholder="请输入平台名称"/>
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
    import Data from '@/chaos/functions/common/Data';
    import {page} from '@/chaos/functions/mixin/page'
    import Pagination from '@/chaos/components/Pagination'

    export default {
        components: {Pagination},
        mixins: [page],
        data() {
            return {
                table: 'chaos_platform',
                currentPage: 1,
                limit: 20,
                total: 0,
                tableData: [],
                showAddForm: false,
                showUpdateForm: false,
                form: {
                    name: '',
                    info: ''
                },
                updateForm: {
                    mu: '',
                    name: '',
                    info: ''
                },
                rules: {
                    name: [
                        {required: true, message: '请输入平台名称', trigger: 'blur'},
                        {
                            min: 2,
                            max: 10,
                            message: '长度在 2 到 10 个字符',
                            trigger: 'change'
                        }
                    ]
                },
                name: ''
            }
        },
        created() {
            this.search()
        },
        methods: {
            async search() {
                const res = await Data.page(this.table, this.currentPage, this.limit, {name: this.name})
                this.tableData = res.list;
                this.total = res.total;
            },
            async doAdd() {
                await Data.add(this.table, this.form)
                this.showAddForm = false
                this.search()
            },
            async showUpdate(mu) {
                this.updateForm = await Data.one(this.table, mu)
                this.showUpdateForm = true
            },
            async doUpdate() {
                await Data.update(this.table, this.updateForm.mu, this.updateForm)
                this.showUpdateForm = false
                this.search()
            },
            doDelete(mu) {
                Data.remove(this.table, mu, () => {
                    this.search();
                })
            },

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
