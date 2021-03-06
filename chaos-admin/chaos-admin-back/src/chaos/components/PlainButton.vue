<template>
    <el-button plain @click="click">{{text}}</el-button>
</template>
<script>
    export default {
        name: 'PlainButton',
        data() {
            return {};
        },
        props: {
            click: {
                type: Function
            },
            text: {
                type: String
            }
        },
    };
</script>

<style scoped>
    .el-button {
        margin: 0 0 6px 0;
    }
</style>
