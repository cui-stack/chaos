<template>
  <el-container>
    <el-header style="height: 70px;">
      <el-container style="margin:auto 0px ;padding: 10px ;">
        <el-button type="primary" v-on:click="showAdd">增加管理员</el-button>
        <el-input style="width: 220px" v-model="name"
                  placeholder="请输入管理员姓名"></el-input>
        <Platform v-model="platformMu" @handleChange="handleChange"></Platform>
        <el-button type="primary" v-on:click="search">搜索</el-button>
      </el-container>
    </el-header>
    <el-main>
      <el-table
        :data="tableData"
        stripe>
        <el-table-column
          type="index"
          width="30">
        </el-table-column>
        <el-table-column
          prop="mu"
          label="编号"
          width="78">
        </el-table-column>
        <el-table-column
          prop="username"
          label="账号"
          min-width="45">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          min-width="55">
        </el-table-column>
        <el-table-column
          prop="lastloginTime"
          sortable
          label="最后登录"
          min-width="100">
        </el-table-column>
        <el-table-column
          prop="ip"
          label="登录IP"
          min-width="80">
        </el-table-column>
        <el-table-column
          prop="loginTimes"
          sortable
          label="登录次数"
          min-width="80">
          <template slot-scope="scope">
            <el-tag>{{scope.row.loginTimes}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="状态"
          width="60">
          <template slot-scope="scope">
            <div style="color: #67c23a" v-if="scope.row.status=='1'">
              停用
            </div>
            <div style="color: #f56c6c" v-if="scope.row.status=='0'">
              正常
            </div>
          </template>
        </el-table-column>
        <el-table-column
          prop="createTime"
          sortable
          label="创建时间"
          min-width="100">
        </el-table-column>
        <el-table-column
          label="操作"
          width="95">
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
  </el-container>
</template>

<script>
    import Paging from '@/components/Paging'
    import Data from '@/common/Data'
    import Platform from '@/components/Platform'
    import {eventBus} from '@/main'
    import PageData from '@/common/PageData'

    export default {
        components: {
            Paging, Platform
        },
        data() {
            return PageData.tableData('chaos_admin', {
                name: '',
                platformMu: ''
            })
        },
        created() {
            eventBus.$on('inited', (message) => {
                this.platformMu = message
                this.search()
            })
        },
        methods: {
            search() {
                Data.page(this.table, this.currentPage, this.limit, {
                    name: this.name,
                    platformMu: this.platformMu
                }, (res) => {
                    this.tableData = res.page.list;
                    this.total = parseInt(res.page.total);
                })
            },
            showAdd() {
                this.$router.push('/addAdmin')
            },
            showUpdate(mu) {
                this.$router.push({
                    name: 'updateAdmin',
                    params: {mu: mu}
                })
            },
            doDelete(mu) {
                Data.remove(this.table, mu, (res) => {
                    this.search()
                })
            },
            listPlatform(after) {
                Data.list('chaos_platform', {}, (res) => {
                    this.platforms = res.data
                    this.platformMu = this.platforms[0].mu
                    if (after != undefined) after.shift()(after)
                })
            },
            handleChange(val) {
                this.platformMu = val
                this.search()
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

</style>
