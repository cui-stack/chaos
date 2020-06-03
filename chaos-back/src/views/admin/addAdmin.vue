<template>
  <el-container>
    <el-header style="height: 30px;margin-bottom: 20px">
      <h2>添加管理员</h2>
    </el-header>
    <el-main>
      <el-alert style="margin-bottom: 20px"
                title="Tips：标星的内容为必填项"
                type="success">
      </el-alert>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px"
               size="small">
        <el-form-item label="账号" prop="username">
          <el-input v-model="form.username" style="width:280px"
                    placeholder="请输入账号"></el-input>
          <span style="margin-left: 20px">邮箱、手机号码、qq号码</span>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" style="width:280px"
                    placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.name" style="width:280px"
                    placeholder="请输入名称"></el-input>
        </el-form-item>
        <el-form-item label="平台">
          <el-select v-model="form.platformMu" @change="listRole">
            <el-option v-for="item in platforms"
                       :key="item.mu"
                       :label="item.name"
                       :value="item.mu"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="form.roleMu">
            <el-option v-for="item in roles"
                       :key="item.mu"
                       :label="item.info"
                       :value="item.mu">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" style="width:280px"
                    placeholder="请输入电话"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" style="width:280px"
                    placeholder="请输入电话"></el-input>
        </el-form-item>
        <el-form-item label="QQ">
          <el-input v-model="form.qq" style="width:280px"
                    placeholder="请输入QQ"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.addr" style="width:280px"
                    placeholder="请输入地址"></el-input>
        </el-form-item>
        <el-form-item label="账号状态">
          <el-radio-group v-model="form.status">
            <el-radio :label="0">正常</el-radio>
            <el-radio :label="1">屏蔽</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">保存
          </el-button>
        </el-form-item>
      </el-form>
    </el-main>

  </el-container>
</template>

<script>
    import fetch from '@/axios/fetch'
    import Data from '@/common/Data'

    export default {
        name: 'addAdmin',
        data() {
            return {
                table: 'chaos_admin',
                platforms: [],
                roles: [],
                form: {
                    username: '',
                    password: '',
                    name: '',
                    platformMu: '',
                    roleMu: '',
                    phone: '',
                    email: '',
                    qq: '',
                    addr: '',
                    status: 0
                },
                rules: {
                    username: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        {
                            min: 6,
                            max: 16,
                            message: '长度在 6 到 16 个字符',
                            trigger: 'change'
                        }
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {
                            min: 6,
                            max: 16,
                            message: '长度在 6 到 16 个字符',
                            trigger: 'change'
                        }
                    ]
                }
            }
        },
        created() {
            Data.chain([this.listPlatform, this.listRole])
        },
        methods: {
            onSubmit() {
                fetch.post('/manage/chaos_admin/add', this.form).then((res) => {
                    this.$router.push('/admin')
                })
            },
            listPlatform(after) {
                Data.list('chaos_platform', {}, (res) => {
                    this.platforms = res.data;
                    this.form.platformMu = this.platforms[0].mu
                    if (after != undefined) after.shift()(after)
                })
            },
            listRole() {
                Data.list('chaos_role', {platformMu: this.form.platformMu}, (res) => {
                    this.roles = res.data;
                    this.form.roleMu = this.roles[0].mu
                })
            }
        }
    }
</script>
<style scoped>

</style>
