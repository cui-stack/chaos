<template>
  <div style="padding: 10px;">
    <i class="el-icon-s-home"></i>
    <el-link :href="this.indexLink" type="info">首页</el-link>
  </div>
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
            this.indexLink = "/chaos/#" + this.$store.getters.getRoleinfo.indexLink
        }
    }
</script>
<style scoped>

</style>
