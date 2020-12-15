/**
 * pages模版快速生成脚本,执行命令 npm run tep `文件名`
 */

const fs = require('fs');

const dirName = process.argv[2];

if (!dirName) {
    console.log('文件夹名称不能为空！');
    console.log('示例：npm run tep test');
    process.exit(0);
}

// 页面模版
const indexTep = `<template>
    <el-container>
        <el-header>
            <el-container>
                <el-button type="primary" v-on:click="showAddForm=true">增加</el-button>
                <Input :placeholder="'请输入MU'" @search="handleMuChange"></Input>
                <el-button type="primary" v-on:click="search">搜索</el-button>
            </el-container>
        </el-header>
        <el-main>
            <el-table :data="tableData" stripe>
                <el-table-column prop="mu" label="编号" min-width="88"></el-table-column>
                <el-table-column prop="title" label="标题" min-width="108"></el-table-column>
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
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="form.title" placeholder="请输入标题"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="doAdd">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改" :visible.sync="showUpdateForm">
                <el-form ref="updateForm" :rules="rules" :model="updateForm" label-width="100px" size="small">
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="updateForm.title" placeholder="请输入标题"></el-input>
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
    import store from '@/vuex/store';
    import Input from '@/components/Input'
    import Paging from '@/components/Paging'
    import Data from '@/common/Data'
    import PageData from '@/common/PageData'
    import RuleData from '@/common/RuleData'

    export default {
        name: 'chaos_${dirName}',
        store,
        components: {
            Input,
            Paging,
        },
        data() {
            return PageData.dataData('iya_${dirName}', {
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
                Data.page(this.table, 1, this.limit, {
                    mu: this.mu,
                }, (res) => {
                    this.tableData = res.page.list;
                    this.total = parseInt(res.page.total);
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
            handleMuChange(mu) {
                this.currentPage = 1;
                this.userMu = mu;
                this.search();
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
<style lang="less" scoped>
    @import './../../../styles/base';

</style>

`;


fs.mkdirSync(`./src/views/${dirName}`); // mkdir $1
process.chdir(`./src/views/${dirName}`); // cd $1

fs.writeFileSync('index.vue', indexTep);

console.log(`模版${dirName}已创建,请手动增加route`);


process.exit(0);
