<template>
  <el-container>
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
          width="180">
        </el-table-column>
        <el-table-column
          prop="phone"
          label="电话"
          width="120">
        </el-table-column>
        <el-table-column
          prop="username"
          label="账号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="token"
          label="TOKEN"
          min-width="80">
        </el-table-column>
        <el-table-column
          prop="ip"
          label="IP"
          width="180">
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
                fetch.post('/manage/loginUsers', {}).then((res) => {
                    this.tableData = res.data;
                })
            }
        }
    }
</script>
<style scoped>

</style>
