<template>
    <el-container>
        <el-main>
            <el-table stripe :data="tableData" :row-class-name="tableRowClassName">
                <el-table-column type="index" width="50"/>
                <el-table-column sortable prop="mu" label="用户MU" width="178"/>
                <el-table-column prop="uri" label="路径" min-width="108"/>
                <el-table-column sortable prop="count" label="次数" width="180"/>
                <el-table-column label="操作" width="268">
                    <template slot-scope="scope">
                        <el-button plain @click="release(scope.row.mu,scope.row.uri)">
                            释放
                        </el-button>
                        <el-button plain @click="lock(scope.row.mu,scope.row.uri)">永封
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
    </el-container>
</template>

<script>
    import fetch from 'chaos-data/axios/fetch';

    export default {
        data() {
            return {
                tableData: []
            }
        },
        mounted() {
            this.search()
        },
        methods: {
            search() {
                fetch.post('/manage/limit', {}).then((res) => {
                    this.tableData = res.data;
                })
            },
            release(mu, uri) {
                fetch.post('/manage/release', {mu, uri}).then(() => {
                    this.search()
                })
            },
            lock(mu, uri) {
                fetch.post('/manage/lock', {mu, uri}).then(() => {
                    this.search()
                })
            },
            tableRowClassName({row}) {
                if (row.count === 9999) {
                    return 'warning-row';
                }
                return '';
            }
        }
    }
</script>
<style scoped>
    .el-header .el-container {
        margin: auto 0px;
        padding: 10px;
    }

    .el-input {
        width: 280px;
    }

    .el-table .warning-row {
        background: oldlace;
    }

    .el-table .success-row {
        background: #f0f9eb;
    }
</style>
