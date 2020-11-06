<template>

    <div>
        <Paging :currentPage="currentPage" :total="total" :limit="limit"
                @handleCurrentChange="handleCurrentChange"
                @handleSizeChange="handleSizeChange"/>
    </div>

</template>

<script>

    export default {
        name: 'app',
        components: {},
        data() {
            return {
                currentPage: 1,
                limit: 20,
                total: 0,
            }
        },
        methods: {
            handleCurrentChange(val) {
                this.currentPage = val;
            },
            handleSizeChange(val) {
                this.limit = val;
                this.currentPage = 1;
            }
        }
    }
</script>

<style lang="scss" scoped>

</style>
