<template>
    <el-container class="hello">
        <img src="@/assets/images/pongo96.png">
        <ul class="code">
            <li v-for="(item,i) in code" :index="i"
                :key="i"
                :label="i"
                :value="i">
                <a :href=item.link target="_blank">{{item.title}}</a>
            </li>
        </ul>
        <el-select class="select-input" v-model="host" placeholder="请选择">
            <el-option
                    v-for="item in env"
                    :key="item.id"
                    :label="item.title"
                    :value="item.host">
            </el-option>
        </el-select>
        <el-input class="host-input" v-model="host"
                  placeholder="请输入域名"></el-input>
    </el-container>

</template>

<script>

    export default {
        name: 'Main',
        data() {
            return {
            }
        },
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
        width: 400px;
        margin: 20px;
    }

    .service {
        display: inline-block;
        width: 550px;
        margin: 0px;
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
        margin: 5px 10px;
    }

    a {
        color: #42b983;
    }
</style>




