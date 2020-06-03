<template>
  <div class="hello">
    <img src="@/assets/pongo96.png">
    <h1>欢 迎 进 入 中 台 系 统</h1>
    <h2>代码中台</h2>
    <el-alert style="margin-bottom: 0px; display: inline-block; width: 33%;"
              title="Tips：部分服务打开较慢请耐心等待"
              type="success">
    </el-alert>
    <br/>
    <ul class="code">
      <li v-for="(item,i) in code" :index="i">
        <a :href=item.link target="_blank">{{item.title}}</a>
      </li>
    </ul>

    <h2>研发中台</h2>
    <el-select class="select-input" v-model="host" placeholder="请选择">
      <el-option
        v-for="item in env"
        :key="item.id"
        :label="item.title"
        :value="item.host">
      </el-option>
    </el-select>
    <el-input class="host-input" v-model="host" placeholder="请输入域名"></el-input>
    <ul>
      <li class="li-api" v-for="(item,i) in links" :index="i">
        <a :href=host+item.link target="_blank">{{item.title}}</a>
      </li>
    </ul>
    <ul class="service">
      <li style=" display: flex;width: 800px" v-for="(item,i) in service"
          :index="i">
        <el-input class="service-input" v-model="item.port">
          <template slot="prepend">{{host}}</template>
          <template slot="append">{{item.link}}</template>
        </el-input>
        <a style="margin: 0 10px;padding:10px 0" :href=host+item.port+item.link
           target="_blank">{{item.title}}</a>
      </li>
    </ul>
    <h2>管理中台</h2>
    <a href="http://center.okyakid.com/chaos/#/"
       target="_blank">混沌后台</a>
    <a href="http://center.okyakid.com:6688/poli/workspace/report"
       target="_blank">BI数据</a>

  </div>
</template>

<script>
    import fetch from '../../axios/fetch'
    import store from '../../vuex/store'

    export default {
        store,
        name: 'Main',
        data() {
            return {
                host: 'http://center.okyakid.com',
                code: [
                    {
                        link: 'https://github.com/cui-stack/chaos',
                        title: '框架代码'
                    },
                    {
                        link: 'http://center.okyakid.com:8080/code-generator/',
                        title: '代码生成'
                    },
                    {
                        link: 'http://git.okyakid.com/',
                        title: '代码仓库'
                    },
                    {
                        link: 'http://center.okyakid.com:8081/#admin/security/users',
                        title: '构件仓库'
                    },
                    {
                        link: 'http://center.okyakid.com:8288/harbor/projects',
                        title: '镜像仓库'
                    },
                    {
                        link: 'https://center.okyakid.com:9000/',
                        title: '环境管理'
                    },
                    {
                        link: 'http://center.okyakid.com:8848/nacos/#',
                        title: '配置中心'
                    }
                ],
                env: [
                    {
                        id: 'local',
                        title: '本地环境',
                        host: 'http://localhost'
                    },
                    {
                        id: 'dev',
                        title: '开发环境',
                        host: 'http://center.okyakid.com'
                    },
                    {
                        id: 'test',
                        title: '测试环境',
                        host: 'http://test.okyakid.com'
                    },
                    {
                        id: 'prod',
                        title: '生产环境',
                        host: 'http://okyakid.com'
                    }
                ],
                links: [
                    {
                        link: ':15672/#/exchanges',
                        title: '消息中台'
                    },
                    {
                        link: ':18888/#/',
                        title: '服务治理'
                    },
                    {
                        link: ':9411/zipkin/',
                        title: '链路跟踪'
                    },
                    {
                        link: ':8000/',
                        title: '微服务管理'
                    },
                    {
                        link: ':19999/xxl-job-admin/jobgroup',
                        title: '任务中台'
                    }
                ],
                service: [
                    {
                        port: ':8086',
                        link: '/swagger-ui.html#',
                        title: 'API管理'
                    },
                    {
                        port: ':8086',
                        link: '/druid/datasource.html',
                        title: '数据库监控'
                    },
                ]

            }
        },
        mounted() {
            if (!this.$store.getters.getUserinfo.username) {
                this.$router.push('/')
            }
            // fetch.get('/web/getMessage').then((res) => {
            //     console.log(res)
            // })
        }
    }
</script>

<style scoped>
  .hello {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
  h1, h2 {
    font-weight: normal;
  }

  .el-select .el-input {
    width: 130px;
  }

  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }

  .code {
    display: inline-block;
    width: 33%;
  }

  .service {
    display: inline-block;
    width: 55%;
  }

  .select-input {
    width: 120px;
  }

  .host-input {
    width: 250px;
  }

  .service-input {
    width: 450px;
  }

  li {
    display: inline-block;
    margin: 1px 10px;
  }

  a {
    color: #42b983;
  }
</style>




