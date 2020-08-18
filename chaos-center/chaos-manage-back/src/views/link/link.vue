<template>
    <el-container>
        <el-header>
            <el-container>
                <el-button type="primary" v-on:click="showAddForm=true">增加链接
                </el-button>
            </el-container>
        </el-header>
        <el-main>
            <el-table :data="tableData" stripe>
                <el-table-column
                        prop="mu"
                        label="编号"
                        width="188">
                </el-table-column>
                <el-table-column
                        prop="title"
                        label="标题"
                        width="108">
                </el-table-column>
                <el-table-column
                        prop="link"
                        label="地址"
                        min-width="60">
                </el-table-column>
                <el-table-column
                        prop="info"
                        label="介绍"
                        width="160">
                </el-table-column>
                <el-table-column
                        prop="status"
                        sortable
                        label="状态"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        sortable
                        label="创建时间"
                        width="200">
                </el-table-column>
                <el-table-column
                        label="操作"
                        width="168">
                    <template slot-scope="scope">
                        <el-button plain @click="showUpdate(scope.row.mu)">编辑
                        </el-button>
                        <el-button style="margin: 0px" plain
                                   @click="doDelete(scope.row.mu)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <Paging :currentPage="currentPage" :total="total" :limit="limit"
                    @handleCurrentChange="handleCurrentChange"
                    @handleSizeChange="handleSizeChange"/>
        </el-main>
        <el-footer>
            <el-dialog width="35%" title="添加链接" :visible.sync="showAddForm">
                <el-form ref="form" :rules="rules" :model="form"
                         label-width="100px"
                         size="small">
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="form.title"
                                  placeholder="请输入标题"></el-input>
                    </el-form-item>
                    <el-form-item label="地址" prop="link">
                        <el-input v-model="form.link"
                                  placeholder="请输入地址"></el-input>
                    </el-form-item>
                    <el-form-item label="介绍" prop="info">
                        <el-input v-model="form.supmu"
                                  placeholder="请输入介绍"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="doAdd">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改链接" :visible.sync="showUpdateForm">
                <el-form ref="updateForm" :rules="rules" :model="updateForm"
                         label-width="100px"
                         size="small">
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="updateForm.title"
                                  placeholder="请输入标题"></el-input>
                    </el-form-item>
                    <el-form-item label="地址" prop="link">
                        <el-input v-model="updateForm.link"
                                  placeholder="请输入链接"></el-input>
                    </el-form-item>
                    <el-form-item label="介绍" prop="info">
                        <el-input v-model="updateForm.info"
                                  placeholder="请输入链接"></el-input>
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
    import Paging from '@/components/Paging'
    import Data from '@/common/Data'

    export default {
        components: {
            Paging,
        },
        data() {
            return {
                table: 'chaos_link',
                currentPage: 1,
                limit: 20,
                total: 0,
                tableData: [],
                showAddForm: false,
                showUpdateForm: false,
                platforms: [],
                platformMu: '',
                formIsroot: true,
                updateFormIsroot: true,
                form: {
                    title: '',
                    link: '',
                    info: '',
                },
                updateForm: {
                    title: '',
                    link: '',
                    info: '',
                },
                rules: {
                    title: [
                        {required: true, message: '请输入标题', trigger: 'blur'},
                        {
                            min: 2,
                            max: 10,
                            message: '长度在 2 到 10 个字符',
                            trigger: 'change'
                        }
                    ],
                    link: [
                        {required: true, message: '请输入地址', trigger: 'blur'},
                        {
                            min: 2,
                            max: 10,
                            message: '长度在 2 到 10 个字符',
                            trigger: 'change'
                        }
                    ]
                },
                title: ''
            }
        },
        created() {
            this.search()
        },
        methods: {
            search() {
                Data.page(this.table, this.currentPage, this.limit, {}, (res) => {
                    this.tableData = res.page.list;
                    this.total = parseInt(res.page.total);
                })
            },
            doAdd() {
                Data.add(this.table, this.form, () => {
                    this.showAddForm = false
                    this.search()
                })
            },
            showUpdate(mu) {
                Data.one(this.table, mu, (res) => {
                    this.updateForm = res.data
                    this.showUpdateForm = true
                })
            },
            doUpdate() {
                Data.update(this.table, this.updateForm.mu, this.updateForm, () => {
                    this.showUpdateForm = false
                    this.search()
                })
            },
            doDelete(mu) {
                Data.remove(this.table, mu, () => {
                    this.search();
                })
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.search();
            },
            handleSizeChange(val) {
                this.limit = val;
                this.currentPage = 1;
                this.search();
            }
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
