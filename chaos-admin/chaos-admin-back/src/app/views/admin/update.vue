<template>
    <el-container>
        <el-main>
            <el-form ref="updateForm" :model="updateForm" :rules="rules"
                     label-width="100px">
                <el-form-item label="账号" prop="username">
                    <el-input v-model="updateForm.username"
                              placeholder="请输入账号"/>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="updateForm.password" type="password"
                              placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="updateForm.name" placeholder="请输入名称"/>
                </el-form-item>
                <el-form-item label="平台">
                    <Platform :isInit="false"
                              :change="(platformMu)=>updateForm.platformMu = platformMu"
                              :value="updateForm.platformMu"/>
                </el-form-item>
                <el-form-item label="角色">
                    <Role :platformMu="updateForm.platformMu"
                          :value="updateForm.roleMu"
                          :change="(roleMu)=>updateForm.roleMu = roleMu"
                          @change="(roleMu)=>updateForm.roleMu = roleMu"/>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="updateForm.phone" placeholder="请输入电话"/>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="updateForm.email" placeholder="请输入电话"/>
                </el-form-item>
                <el-form-item label="QQ">
                    <el-input v-model="updateForm.qq" placeholder="请输入QQ"/>
                </el-form-item>
                <el-form-item label="addr">
                    <el-input v-model="updateForm.addr" placeholder="请输入地址"/>
                </el-form-item>
                <el-form-item label="账号状态">
                    <el-radio-group v-model="updateForm.status">
                        <el-radio :label="0">正常</el-radio>
                        <el-radio :label="1">屏蔽</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item>
                    <PrimaryButton text="确定" :click="doUpdate"/>
                </el-form-item>
            </el-form>
        </el-main>
    </el-container>
</template>

<script>
    import Platform from '@/app/components/Platform'
    import Role from '@/app/components/Role'
    import {update} from '@/chaos/functions/mixin/pushcrud'

    export default {
        name: 'UpdateAdmin',
        components: {
            Platform,
            Role
        },
        mixins: [update],
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
                updateForm: {
                    platformMu: ''
                }
            }
        },
        created() {
            this.initUpdateMethod = 'adminRole'
            this.updateMethod = 'updateAdminRole'
            this.initUpdate()
        },
        methods: {
        }
    }
</script>
<style scoped>
    .el-form {
        width: 500px;
    }
</style>
