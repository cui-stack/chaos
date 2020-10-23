<template>
  <el-container>
    <el-header>
      <el-container>
        <el-input v-model="userMu"
                  placeholder="请输入用户MU"></el-input>
        <el-input v-model="uri"
                  placeholder="请输入URI"></el-input>
        <el-button type="primary" v-on:click="search">搜索</el-button>
      </el-container>
    </el-header>
    <el-main>
      <el-table
        :data="tableData"
        stripe>
        <el-table-column
          prop="mu"
          label="日志编号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间"
          sortable
          width="100">
        </el-table-column>
        <el-table-column
          prop="userMu"
          label="用户MU"
           width="80">
        </el-table-column>
        <el-table-column
          prop="ip"
          label="IP"
          width="120">
        </el-table-column>
        <el-table-column
          prop="uri"
          label="URI"
          width="250">
        </el-table-column>
        <el-table-column
          prop="time"
          label="时间"
          width="60">
        </el-table-column>
        <el-table-column
          prop="request"
          label="请求信息"
          min-width="60">
        </el-table-column>
        <el-table-column
          prop="response"
          label="响应信息"
          min-width="60">
        </el-table-column>
      </el-table>
      <Paging :currentPage="currentPage" :total="total" :limit="limit"
              @handleCurrentChange="handleCurrentChange"
              @handleSizeChange="handleSizeChange"/>
    </el-main>
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
                table: 'chaos_log',
                currentPage: 1,
                limit: 20,
                total: 0,
                tableData: [],
                userMu: '',
                uri: ''
            }
        },
        created() {
            this.search()
        },
        methods: {
            search() {
                Data.page(this.table, this.currentPage, this.limit, {
                    userMu: this.userMu,
                    uri: this.uri
                }, (res) => {
                    this.tableData = res.page.list;
                    this.total = parseInt(res.page.total);
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
