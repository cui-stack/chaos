<template>
    <el-radio-group v-model.trim="innverValue" @change="change">
        <el-radio :key="item.label" v-for="item in datas" :label="item.label">
            {{item.text}}
        </el-radio>
    </el-radio-group>
</template>
<script>
    export default {
        name: 'SearchRadioGroup',
        data() {
            return {
                innverValue: 1
            };
        },
        props: {
            datas: {
                required: true,
                default: []
            },
            value: {},
            change: {
                type: Function
            }
        },
        created() {
            this.innverValue = this.value
        }
    };
</script>

<style scoped>
    .el-radio-group {
        padding: 12px 16px;
    }
</style>
