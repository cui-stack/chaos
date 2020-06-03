<template>
  <el-container>
    <el-header>
      <el-container>
        <el-button type="primary" v-on:click="showAddForm=true">增加平台</el-button>
        <el-input v-model="name"
                  placeholder="请输入平台名称"></el-input>
        <el-button type="primary" v-on:click="search">搜索</el-button>
      </el-container>
    </el-header>
    <el-main>
      <el-table
        :data="tableData"
        stripe>
        <el-table-column
          prop="mu"
          label="编号"
          width="178">
        </el-table-column>
        <el-table-column
          prop="name"
          label="平台名称"
          min-width="60">
        </el-table-column>
        <el-table-column
          prop="createTime"
          sortable
          label="创建时间"
          min-width="76">
        </el-table-column>
        <el-table-column
          label="操作"
          width="178">
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
      <el-dialog width="35%" title="添加平台" :visible.sync="showAddForm">
        <el-form ref="form" :rules="rules" :model="form" label-width="100px"
                 size="small">
          <el-form-item label="平台名称" prop="name">
            <el-input v-model="form.name"
                      placeholder="请输入平台名称"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="doAdd">确定</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-dialog width="35%" title="修改平台" :visible.sync="showUpdateForm">
        <el-form ref="updateForm" :rules="rules" :model="updateForm"
                 label-width="100px"
                 size="small">
          <el-form-item label="平台名称" prop="name">
            <el-input v-model="updateForm.name"
                      placeholder="请输入平台名称"></el-input>
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
    import fetch from '@/axios/fetch'
    import Paging from '@/components/Paging'
    import Data from '@/common/Data'

    export default {
        components: {
            Paging,
        },
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
                    name: ''
                },
                updateForm: {
                    mu: '',
                    name: ''
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
            search() {
                Data.page(this.table, this.currentPage, this.limit, {name: this.name}, (res) => {
                    this.tableData = res.page.list;
                    this.total = parseInt(res.page.total);
                })
            },
            doAdd() {
                Data.add(this.table, this.form, (res) => {
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
                Data.update(this.table, this.updateForm.mu, this.updateForm, (res) => {
                    this.showUpdateForm = false
                    this.search()
                })
            },
            doDelete(mu) {
                Data.remove(this.table, mu, (res) => {
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
