<template>
    <el-select v-model.trim="innerValue" :placeholder="placeholder"
               @change="change">
        <el-option v-for="item in datas" :key="item" :label="item"
                   :value="item"/>
    </el-select>
</template>
<script>
    export default {
        name: 'SearchSelect',
        data() {
            return {
                innerValue: ''
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
            },
            value: {
                type: String,
                default: ''
            },
        },
        created() {
            this.innerValue=this.value
        }
    };
</script>
