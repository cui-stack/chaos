<template>
  <div style="padding:10px 23px ;">
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
            this.indexLink = "/iya/#" + this.$store.getters.getRoleinfo.indexLink
        }
    }
</script>
<style scoped>

</style>
