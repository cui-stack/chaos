<template>
    <el-input v-model.trim="value"
              prefix-icon="el-icon-search"
              clearable
              :placeholder="placeholder"
              @change="change">
    </el-input>
</template>
<script>
    export default {
        name: 'Input',
        data() {
            return {
                value: ''
            };
        },
        props: {
            placeholder: {
                required: false,
                default: ''
            },
            change: {
                type:Function
            },
        },
    };
</script>

<style scoped>
    .el-input{
        width: 280px;
    }
</style>
