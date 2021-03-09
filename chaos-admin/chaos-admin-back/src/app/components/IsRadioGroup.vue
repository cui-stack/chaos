<template>
    <SearchRadioGroup :datas="datas" :value="value" :change="change"/>
</template>
<script>
    export default {
        name: 'IsRadioGroup',
        props: {
            change: {
                type: Function
            },
            value: {}
        },
        data() {
            return {
                datas: [
                    {label: 1, text: '是'},
                    {label: 0, text: '否'},
                    {text: '全部'},
                ],
            };
        },
    };
</script>
<style scoped>
    .el-radio-group {
        padding: 12px 16px;
    }
</style>
