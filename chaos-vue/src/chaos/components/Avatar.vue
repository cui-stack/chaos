<template>
    <el-avatar shape="square" :size="100" :src="src"/>
</template>
<script>
    export default {
        name: 'Avatar',
        data() {
            return {};
        },
        props: {
            src: {
                required: false,
                default: ''
            },
        },
        methods: {}
    };
</script>
