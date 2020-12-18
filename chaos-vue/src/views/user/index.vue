<template>
    <el-container>
        <el-header>
            <el-container>
                <el-button type="primary" @click="showAddForm=true">增加</el-button>
                <Input :placeholder="'请输入MU'" @search="handleChange"></Input>
                <el-button type="primary" @click="search">搜索</el-button>
            </el-container>
        </el-header>
        <el-main>
            <el-table :data="tableData" stripe>
                <el-table-column prop="mu" label="编号" min-width="88"></el-table-column>
                <el-table-column label="操作" width="168">
                    <template slot-scope="scope">
                        <el-button plain @click="showUpdate(scope.row.mu)">编辑</el-button>
                        <el-button plain @click="doDelete(scope.row.mu)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <Paging :currentPage="currentPage" :total="total" :limit="limit"
                    @handleCurrentChange="handleCurrentChange"
                    @handleSizeChange="handleSizeChange"/>
        </el-main>
        <el-footer>
            <el-dialog width="35%" title="添加" :visible.sync="showAddForm">
                <el-form ref="form" :rules="rules" :model="form"  label-width="100px" size="small">
                    <el-form-item>
                        <el-button type="primary" @click="doAdd">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改" :visible.sync="showUpdateForm">
                <el-form ref="updateForm" :rules="rules" :model="updateForm" label-width="100px" size="small">
                    <el-form-item>
                        <el-button type="primary" @click="doUpdate">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </el-footer>
    </el-container>
</template>

<script>
    import Data from 'chaos-data/common/Data'
    import PageData from 'chaos-data/common/PageData'
    import RuleData from 'chaos-data/common/RuleData'
    import {page} from 'chaos-data/mixin/page'

    export default {
        name: 'user',
        mixins: [page],
        components: {
        },
        data() {
            return PageData.dataData('chaosUser', {
                title: '',
            }, {
                mu: '',
                title: '',
            }, RuleData.data([{
                key: 'title',
                msg: "请输入标题"
            }]),{})
        },
        created() {
            this.search()
        },
        methods: {
            search() {
                Data.page(this.table, this.currentPage, this.limit, {
                    mu: this.mu,
                }, (res) => {
                    this.tableData = res.list;
                    this.total = parseInt(res.total);
                })
            },
            doAdd() {
                Data.validate(this, 'form', () => {
                    Data.add(this.table, this.form, () => {
                        this.showAddForm = false
                        this.search()
                    })
                })
            },
            showUpdate(mu) {
                Data.one(this.table, mu, (res) => {
                    this.updateForm = res.data
                    this.showUpdateForm = true
                })
            },
            doUpdate() {
                Data.validate(this, 'updateForm', () => {
                    Data.update(this.table, this.updateForm.mu, this.updateForm, () => {
                        this.showUpdateForm = false
                        this.search()
                    })
                })
            },
            doDelete(mu) {
                Data.remove(this.table, mu, () => {
                    this.search();
                })
            },
            handleChange(mu) {
                this.currentPage = 1;
                this.userMu = mu;
                this.search();
            },
        }
    }
</script>
<style lang="less" scoped>

</style>

