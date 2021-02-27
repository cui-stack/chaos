<template>
    <el-select v-model.trim="value" :placeholder="placeholder" @change="change">
        <el-option v-for="item in datas" :key="item" :label="item" :value="item"/>
    </el-select>
</template>
<script>
    export default {
        name: 'Select',
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
