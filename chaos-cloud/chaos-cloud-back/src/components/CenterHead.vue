<template>
  <el-container>
    <i class="el-icon-s-home"></i>
    <el-link :href="this.indexLink" type="info">首页</el-link>
  </el-container>
</template>

<script>

    export default {
        data() {
            return {
                name: '',
                indexLink: ''
            }
        },
        created() {
            this.indexLink = "/manage/#" + this.$store.getters.getRoleinfo.indexLink
        }
    }
</script>
<style scoped>
  .el-container {
    padding: 20px;
  }

  .el-link {
    margin-left: 10px;
  }
</style>
