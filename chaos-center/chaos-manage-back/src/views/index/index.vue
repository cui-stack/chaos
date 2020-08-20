<template>
    <el-container class="hello">
        <el-main>
            <img src="@/assets/images/pongo72.png">
            <br>
            <ul>
                <li v-for="(item,i) in links" :index="i"
                    :key="i"
                    :label="i"
                    :value="i">
                    <a :href=item.link target="_blank">{{item.title}}</a>
                </li>
            </ul>
            <br>
            <el-select style="margin-top: 10px" v-model="host" placeholder="请选择">
                <el-option
                        v-for="item in envs"
                        :key="item.mu"
                        :label="item.title"
                        :value="item.link">
                </el-option>
            </el-select>
            <el-input v-model="host"
                      placeholder="请输入域名"></el-input>
            <br>
            <ul>
                <li v-for="(item,i) in services"
                    :key="i"
                    :label="i"
                    :value="i">
                    <a :href=item.head+host+item.link
                       target="_blank">{{item.title}}</a>
                </li>
            </ul>
            <br>
            <el-select v-model="hs" style="width: 100px;">
                <el-option
                        v-for="(item,i) in hss"
                        :key="i"
                        :label="item.head"
                        :value="item.head">
                </el-option>
            </el-select>
            <el-input v-model="port" style="width: 420px;margin-top: 10px">
                <template slot="prepend">{{host}}</template>
                <template slot="append">{{apiLink}}</template>
            </el-input>
            <a style="margin-left: 10px"
               :href=hs+host+port+apiLink
               target="_blank">API管理</a>
        </el-main>
    </el-container>

</template>

<script>
    import Data from '@/common/Data'

    export default {
        name: 'Index',
        data() {
            return {
                links: [], envs: [], host: 'iya101.com', port: ':8089',
                hss: [
                    {
                        head: 'http://',
                    },
                    {
                        head: 'https://',
                    },
                ], hs: 'https://',
                apiLink: '/swagger-ui.html#',
                services: [
                    {
                        head: 'http://',
                        link: ':15672/#/exchanges',
                        title: '消息中台'
                    },
                    {
                        head: 'http://',
                        link: ':18888/#/',
                        title: '服务治理'
                    },
                    {
                        head: 'https://',
                        link: '/back/#/main/',
                        title: '管理后台'
                    },
                ]
            }
        },
        created() {
            this.search()
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
    .hello {
        text-align: center;
        color: #2c3e50;
    }

    .el-input {
        width: 220px
    }

    ul {
        display: inline-block;
        width: 400px;
        margin-top: 8px;
    }

    li {
        display: inline-block;
        margin: 5px 10px;
    }

    a {
        color: #42b983;
    }
</style>




