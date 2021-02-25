<template>
    <div class="pagination">
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                layout="total, sizes, prev, pager, next, jumper"
                :page-sizes="[10, 20, 50, 100,500]"
                :current-page="currentPage"
                :page-size="limit"
                :total="total"/>
    </div>
</template>

<script>
    export default {
        name: "Paging",
        data() {
            return {}
        },
        props: {
            total: {
                required: false,
                default: 0
            },
            limit: {
                required: false,
                default: 20
            },
            currentPage: {
                required: false,
                default: 1
            },
        },
        methods: {
            handleCurrentChange(val) {
                window.scrollTo(0, 0);
                this.$emit("handleCurrentChange", val);
            },
            handleSizeChange(val) {
                this.currentPage = 1;
                this.$emit('handleSizeChange', val);
            }
        }
    }
</script>

<style scoped>
    .pagination {
        margin: auto 0px;
        text-align: right;
    }
</style>
