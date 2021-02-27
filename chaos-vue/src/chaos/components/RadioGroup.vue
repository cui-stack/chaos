<template>
    <el-radio-group v-model.trim="value" @change="change">
        <el-radio :key="item.label" v-for="item in datas" :label="item.label">
            {{item.text}}
        </el-radio>
    </el-radio-group>
</template>
<script>
    export default {
        name: 'RadioGroup',
        data() {
            return {
                value: 1
            };
        },
        props: {
            datas: {
                required: true,
                default: []
            },
            change: {
                type: Function
            }
        },
    };
</script>

<style scoped>
    .el-radio-group {
        padding:  12px 16px;
    }
</style>
