<template>
    <el-container>
        <el-main>
            <el-form ref="form" :model="form" :rules="rules"
                     label-width="100px">
                <el-form-item label="账号" prop="username">
                    <el-input v-model="form.username" placeholder="请输入账号"/>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="form.password" type="password"
                              placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="form.name" placeholder="请输入姓名"/>
                </el-form-item>
                <el-form-item label="平台">
                    <Platform :init="(platformMu)=>form.platformMu = platformMu"
                              :change="(platformMu)=>form.platformMu = platformMu"/>
                </el-form-item>
                <el-form-item label="角色">
                    <Role :platformMu="form.platformMu"
                          :change="(roleMu)=>form.roleMu = roleMu"
                          @change="(roleMu)=>form.roleMu = roleMu"/>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="form.phone"
                              placeholder="请输入电话"/>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email"
                              placeholder="请输入邮箱"/>
                </el-form-item>
                <el-form-item label="QQ">
                    <el-input v-model="form.qq" placeholder="请输入QQ"/>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.addr"
                              placeholder="请输入地址"/>
                </el-form-item>
                <el-form-item label="账号状态">
                    <el-radio-group v-model="form.status">
                        <el-radio :label=0>正常</el-radio>
                        <el-radio :label=1>屏蔽</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item>
                    <PrimaryButton text="确定" :click="doAdd"/>
                </el-form-item>
            </el-form>
        </el-main>
    </el-container>
</template>

<script>
    import {create} from '@/chaos/functions/mixin/pushcrud'
    import Platform from '@/app/components/Platform'
    import Role from '@/app/components/Role'

    export default {
        name: 'AddAdmin',
        mixins: [create],
        components: {
            Platform,
            Role
        },
        data() {
            const rules = {
                username: [
                    {required: true, message: '请输入账号', trigger: 'blur'},
                    {
                        min: 2,
                        max: 30,
                        trigger: 'change',
                        message: '长度在 2 到 30 个字符',
                    }
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'blur'},
                    {
                        min: 2,
                        max: 30,
                        trigger: 'change',
                        message: '长度在 2 到 30 个字符',
                    }
                ],
                name: [
                    {required: true, message: '请输入姓名', trigger: 'blur'},
                ],
                phone: [
                    {required: true, message: '请输入电话', trigger: 'blur'},
                    {
                        min: 11,
                        max: 11,
                        trigger: 'change',
                        message: '请输入11位长度的电话号码',
                    }
                ],
            }
            return {
                rules,
                domain: 'chaos_admin',
                indexPath: 'admin',
                form: {
                    status: 0,
                    platformMu: ''
                },

            }
        },
    }
</script>
<style scoped>
    .el-form {
        width: 500px;
    }
</style>
