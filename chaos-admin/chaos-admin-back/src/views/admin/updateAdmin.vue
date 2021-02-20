<template>
    <el-container>
        <el-header style="height: 30px;margin-bottom: 20px">
            <h2>更新管理员</h2>
        </el-header>
        <el-main>
            <el-alert style="margin-bottom: 20px" title="Tips：标星的内容为必填项"
                      type="success"/>
            <el-form ref="form" :model="form" :rules="rules" label-width="100px"
                     size="small">
                <el-form-item label="账号" prop="username">
                    <el-input v-model="form.username" placeholder="请输入账号"/>
                    <span style="margin-left: 20px">邮箱、手机号码、qq号码</span>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="form.password"
                              placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="form.name"
                              placeholder="请输入名称"></el-input>
                </el-form-item>
                <el-form-item label="平台">
                    <Platform @platformChange="platformChange" inited="false"
                              :platformMu="form.platformMu"/>
                </el-form-item>
                <el-form-item label="角色">
                    <Role @roleChange="roleChange" :role="role"/>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="form.phone" placeholder="请输入电话"/>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email" placeholder="请输入电话"/>
                </el-form-item>
                <el-form-item label="QQ">
                    <el-input v-model="form.qq" placeholder="请输入QQ"/>
                </el-form-item>
                <el-form-item label="addr">
                    <el-input v-model="form.addr" placeholder="请输入地址"/>
                </el-form-item>
                <el-form-item label="账号状态">
                    <el-radio-group v-model="form.status">
                        <el-radio :label="0">正常</el-radio>
                        <el-radio :label="1">屏蔽</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">保存</el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </el-container>
</template>

<script>
    import Data from '@/chaos/functions/common/Data';
    import {Message} from 'element-ui';
    import Platform from '@/components/Platform'
    import Role from '@/components/Role'

    export default {
        name: 'UpdateAdmin',
        components: {
            Platform,
            Role
        },
        data() {
            return {
                table: 'chaos_admin',
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
                role: {},
                rules: {
                    username: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        {
                            min: 2,
                            max: 10,
                            trigger: 'change',
                            message: '长度在 2 到 10 个字符',
                        }
                    ],
                    password: [
                        {
                            min: 2,
                            max: 10,
                            trigger: 'change',
                            message: '长度在 2 到 10 个字符',
                        }
                    ],
                    name: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {
                            min: 2,
                            max: 10,
                            trigger: 'change',
                            message: '长度在 2 到 10 个字符',
                        }
                    ],
                    phone: [
                        {
                            min: 11,
                            max: 11,
                            trigger: 'change',
                            message: '请输入11位长度的电话号码',
                        }
                    ],
                }
            }
        },
        created() {
            if (!this.$route.params.mu) {
                this.$router.push('/admin')
            }
        },
        methods: {
            onSubmit() {
                Data.validate(this, 'form', async () => {
                    Data.submit(this.table + '/updateAdminRole', {
                        mu: this.$route.params.mu,
                        data: this.form
                    }, () => {
                        Message({
                            type: 'success',
                            message: '更新成功!'
                        });
                        this.$router.push('/admin')
                    })
                })
            },
            async platformChange(platformMu) {
                if (this.form.platformMu) {
                    this.form.platformMu = platformMu
                    this.role = {platformMu, roleMu: ''}
                } else {
                    this.form = await Data.query(this.table + '/adminRole', {
                        mu: this.$route.params.mu
                    })
                    const {platformMu, roleMu} = this.form
                    this.role = {platformMu, roleMu}
                }
            },
            roleChange(roleMu) {
                this.form.roleMu = roleMu
            }
        }
    }
</script>
<style scoped>
    .el-input {
        width: 280px
    }
</style>
