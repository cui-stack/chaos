<template>
    <el-button type="primary" icon="el-icon-search" @click="click">搜索</el-button>
</template>
<script>
    export default {
        name: 'SearchButton',
        data() {
            return {};
        },
        props: {
            click: {
                type: Function
            }
        },
        methods: {}
    };
</script>
