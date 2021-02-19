<template>
    <el-container >
        <el-main>
            <el-row>
                <el-col :span="5" v-for="(o) in links" :key="o" :offset="1">
                    <el-card :body-style="{ padding: '0px' }">
                        <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
                        <div style="padding: 14px;">
                            <span>{{o.title}}</span>
                            <div >
                                <el-button type="text" class="button">
                                    <el-link :href=o.link target="_blank">访问地址</el-link>
                                </el-button>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-main>
    </el-container>

</template>

<script>
    import Data from '@/chaos/functions/common/Data';
    export default {
        name: 'Index',
        data() {
            return {
                links: []

            }
        },
        created() {
           // this.search()
        },
        methods: {
            search() {
                Data.list('chaos_link', {}, (res) => {
                    this.links = res.data;
                })
                Data.list('chaos_env', {}, (res) => {
                    this.envs = res.data;
                })
            },
        }
    }
</script>

<style scoped>
    .image {
        width: 100%;
        display: block;
    }
</style>




