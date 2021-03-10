<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :span="3" v-for="(item,index) in links" :key="item.mu"
                        :offset="1">
                    <el-card shadow="hover">
                        <div slot="header">
                            <el-link :href=item.link target="_blank">
                                {{item.title}}
                            </el-link>
                            <el-button type="text"
                                       :icon="item.isStar?'el-icon-star-on':'el-icon-star-off'"
                                       style="float: right ; padding: 3px 0"
                                       @click="isStar(item,index)"/>
                        </div>
                        {{item.info}}
                    </el-card>
                </el-col>
            </el-row>
        </el-main>
    </el-container>

</template>

<script>
    import Data from '@/chaos/functions/common/Data';

    export default {
        name: 'Dashboard',
        data() {
            return {
                links: []
            }
        },
        created() {
            this.search()
        },
        methods: {
            async search() {
                this.links = await Data.list('chaos_link')
            },
            isStar(item, index) {
                item.isStar = !item.isStar;
                this.$set(this.links, index, item);
            }
        }
    }
</script>

<style scoped>
    .el-card {
        margin-bottom: 10px;
    }
</style>




