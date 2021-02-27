<template>
    <div>
        <div style="color: #67c23a" v-if="status==1">
            停用
        </div>
        <div style="color: #f56c6c" v-if="status==0">
            正常
        </div>
    </div>
</template>
<script>
    export default {
        name: 'AdminStatus',
        data() {
            return {};
        },
        props: {
            status: {
                type: Number,
                default: 0
            }
        },
    };
</script>
