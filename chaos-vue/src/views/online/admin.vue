<template>
  <el-container>
    <el-header>
      <el-button type="primary" v-on:click="logoutAll">强制下线</el-button>
    </el-header>
    <el-main>
      <el-table
        :data="tableData"
        stripe>
        <el-table-column
          type="index"
          width="50">
        </el-table-column>
        <el-table-column
          prop="mu"
          label="编号"
          width="118">
        </el-table-column>
        <el-table-column
          prop="username"
          label="账号"
          width="80">
        </el-table-column>
        <el-table-column
          prop="ip"
          label="IP"
          width="138">
        </el-table-column>
        <el-table-column
          prop="role.info"
          label="角色"
          width="90">
        </el-table-column>
        <el-table-column
          prop="loginTime"
          label="登录时间"
          sortable
          width="108">
        </el-table-column>
        <el-table-column
          prop="token"
          label="TOKEN"
          min-width="45">
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
    import fetch from '@/axios/fetch'

    export default {
        components: {},
        data() {
            return {
                tableData: []
            }
        },
        created() {
            this.search()
        },
        methods: {
            search() {
                fetch.post('/manage/loginAdmins', {}).then((res) => {
                    this.tableData = res.data;
                })
            },
            logoutAll() {
                fetch.post('/manage/logoutAll', {}).then((res) => {
                })
            }
        }
    }
</script>
<style scoped>

</style>
