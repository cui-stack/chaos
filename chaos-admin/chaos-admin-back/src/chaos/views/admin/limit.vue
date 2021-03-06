<template>
    <el-container>
        <el-main>
            <el-table stripe :data="tableData"
                      :row-class-name="tableRowClassName">
                <el-table-column type="index" width="50"/>
                <el-table-column sortable prop="mu" label="用户MU" width="178"/>
                <el-table-column prop="uri" label="路径" min-width="108"/>
                <el-table-column sortable prop="count" label="次数" width="180"/>
                <el-table-column label="操作" width="268">
                    <template slot-scope="scope">
                        <el-button plain
                                   @click="release(scope.row.mu,scope.row.uri)">释放
                        </el-button>
                        <el-button plain
                                   @click="lock(scope.row.mu,scope.row.uri)">永封
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
    </el-container>
</template>

<script>
    import Data from '@/chaos/functions/common/Data';

    export default {
        data() {
            return {
                tableData: []
            }
        },
        created() {
            this.search()
        },
        methods: {
            async search() {
                this.tableData = await Data.query('limit')||[]
            },
            async release(mu, uri) {
                Data.submit('release', {mu, uri}, () => {
                    this.search()
                })
            },
            async lock(mu, uri) {
                Data.submit('lock', {mu, uri}, () => {
                    this.search()
                })
            },
            tableRowClassName({row}) {
                return row.count === 9999 ? 'warning-row' : '';
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
