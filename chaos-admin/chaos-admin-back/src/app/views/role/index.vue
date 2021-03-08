<template>
    <el-container>
        <el-header>
            <el-container>
                <PrimaryButton text="增加" :click="showAdd"/>
                <SearchInput placeholder="请输入角色名称"
                             :change="(value)=>this.handleChange(value,'info')"/>
                <Platform :init="(value)=>handleChange(value,'platformMu')"
                          :change="(value)=>handleChange(value,'platformMu')"/>
                <SearchButton :click="search"/>
            </el-container>
        </el-header>
        <el-main>
            <el-table stripe :data="tableData"
                      element-loading-text="拼命加载中"
                      element-loading-spinner="el-icon-loading"
                      element-loading-background="rgba(0, 0, 0, 0.8)"
                      v-loading.fullscreen.lock="loading">
                <el-table-column prop="mu" label="编号" width="188"/>
                <el-table-column prop="name" label="缩写" min-width="45"/>
                <el-table-column prop="info" label="名称" min-width="55"/>
                <el-table-column prop="indexLink" label="首页" min-width="55"/>
                <el-table-column prop="createTime" label="创建时间" min-width="76"/>
                <el-table-column label="操作" width="258">
                    <template slot-scope="scope">
                        <PlainButton text="编辑"
                                     :click="()=>showUpdate(scope.row.mu)"/>
                        <PlainButton text="授权"
                                     :click="()=>showGrant(scope.row.mu)"/>
                        <PlainButton text="删除"
                                     :click="()=>doDelete(scope.row.mu)"/>
                    </template>
                </el-table-column>
            </el-table>
            <SearchPagination :currentPage="currentPage" :total="total"
                              :limit="limit"
                              @handleCurrentChange="handleCurrentChange"
                              @handleSizeChange="handleSizeChange"/>
        </el-main>
        <el-footer>
            <el-dialog width="35%" title="添加角色" :visible.sync="showAddForm">
                <el-form ref="form" :rules="rules" :model="form"
                         label-width="100px" size="small">
                    <el-form-item label="平台">
                        <Platform
                                :init="(platformMu)=>form.platformMu = platformMu"
                                :change="(platformMu)=>form.platformMu = platformMu"/>
                    </el-form-item>
                    <el-form-item label="缩写" prop="name">
                        <el-input v-model="form.name" placeholder="请输入角色缩写"/>
                    </el-form-item>
                    <el-form-item label="名称" prop="info">
                        <el-input v-model="form.info" placeholder="请输入角色名称"/>
                    </el-form-item>
                    <el-form-item label="首页链接" prop="indexLink">
                        <el-input v-model="form.indexLink"
                                  placeholder="请输入首页链接"/>
                    </el-form-item>
                    <el-form-item>
                        <PrimaryButton text="确定" :click="doAdd"/>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改角色" :visible.sync="showUpdateForm">
                <el-form ref="updateForm" :rules="rules" :model="updateForm"
                         label-width="100px" size="small">
                    <el-form-item label="平台">
                        <Platform :isInit="false" :value="updateForm.platformMu"
                                  :change="(platformMu)=>updateForm.platformMu = platformMu"/>
                    </el-form-item>
                    <el-form-item label="缩写" prop="name">
                        <el-input v-model="updateForm.name"
                                  placeholder="请输入角色缩写"/>
                    </el-form-item>
                    <el-form-item label="名称" prop="info">
                        <el-input v-model="updateForm.info"
                                  placeholder="请输入角色名称"/>
                    </el-form-item>
                    <el-form-item label="首页链接" prop="indexLink">
                        <el-input v-model="updateForm.indexLink"
                                  placeholder="请输入首页链接"/>
                    </el-form-item>
                    <el-form-item>
                        <PrimaryButton text="确定" :click="doUpdate"/>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="授权页面" :visible.sync="showGrantForm">
                <el-transfer v-model="own" :data="lack"
                             :titles="['未拥有', '已拥有']"
                             :props="{key: 'mu',label: 'title'}"/>
                <PrimaryButton text="保存" :click="doGrant"/>
            </el-dialog>
        </el-footer>
    </el-container>
</template>

<script>
    import Data from '@/chaos/functions/common/Data';
    import Array from '@/chaos/functions/common/Array'
    import Platform from '@/app/components/Platform'
    import {page, remove, create, update} from '@/chaos/functions/mixin/crud'

    export default {
        components: {Platform},
        mixins: [page, remove, create, update],
        data() {
            const rules = {
                name: [
                    {required: true, message: '请输入角色缩写', trigger: 'blur'},
                ],
                info: [
                    {required: true, message: '请输入角色名称', trigger: 'blur'},
                ],
                indexLink: [
                    {required: true, message: '请输入首页链接', trigger: 'blur'},
                ],
            }
            return {
                domain: 'chaos_role',
                rules,
                lack: [],
                own: [],
                grant: [],
                showGrantForm: false,
                form: {
                    indexLink: '/welcome'
                },
                updateForm: {
                    platformMu: ''
                }
            }
        },
        methods: {
            async showGrant(roleMu) {
                this.pickRowMu = roleMu
                this.lack = await Data.query('chaos_resource/list', {
                    platformMu: this.data.platformMu,
                    isRoot: 0
                })
                const res = await Data.query('chaos_role_rsource/listResourceMus', {roleMu: this.pickRowMu})
                this.own = res || [];
                this.grant = res || [];
                this.showGrantForm = true
            },
            doGrant() {
                Data.submit('chaos_role_resource/grant', {
                    roleMu: this.pickRowMu,
                    addMus: Array.difference(this.own, this.grant),
                    deleteMus: Array.difference(this.grant, this.own)
                })
            },

        }
    }
</script>
<style scoped>
    .el-form {
        width: 500px
    }
    .el-transfer{
        width: 600px
    }
</style>
