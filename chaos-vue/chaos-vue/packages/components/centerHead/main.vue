<template>
    <el-container>
        <i class="el-icon-s-home"></i>
        <el-link :href="indexLink" type="info">首页</el-link>
    </el-container>
</template>

<script>

    export default {
        name: 'CenterHead',
        data() {
            return {}
        },
        props: {
            'indexLink': {
                required: false,
                default: ''
            },
        },
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
