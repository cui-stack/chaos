<template>
    <el-container>
        <el-header style="height: 70px;">
            <el-container style="margin:auto 0px;padding: 10px ;">
                <el-button type="primary" @click="showAdd">增加角色</el-button>
                <el-input style="width: 220px" v-model="data.info"
                          placeholder="请输入角色名称"/>
                <Platform @platformChange="platformChange"
                          @platformInit="platformChange"/>
                <el-button type="primary" @click="search">搜索</el-button>
            </el-container>
        </el-header>
        <el-main>
            <el-table stripe :data="tableData">
                <el-table-column prop="mu" label="编号" width="188"/>
                <el-table-column prop="name" label="缩写" min-width="45"/>
                <el-table-column prop="info" label="名称" min-width="55"/>
                <el-table-column prop="indexLink" label="首页" min-width="55"/>
                <el-table-column prop="createTime" label="创建时间" min-width="76"/>
                <el-table-column label="操作" width="258">
                    <template slot-scope="scope">
                        <el-button plain @click="showPlatformUpdate(scope.row.mu)">编辑
                        </el-button>
                        <el-button style="margin: 0px" plain
                                   @click="showGrant(scope.row.mu)">授权
                        </el-button>
                        <el-button style="margin: 0px" plain
                                   @click="doDelete(scope.row.mu)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <Pagination :currentPage="currentPage" :total="total" :limit="limit"
                        @handleCurrentChange="handleCurrentChange"
                        @handleSizeChange="handleSizeChange"/>
        </el-main>
        <el-footer>
            <el-dialog width="35%" title="添加角色" :visible.sync="showAddForm">
                <el-form ref="form" :rules="rules" :model="form"
                         label-width="100px" size="small">
                    <el-form-item label="平台">
                        <Platform @platformChange="addFormPlatformChange"
                                  @platformInit="addFormPlatformChange"/>
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
                        <el-button type="primary" @click="doAdd">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改角色" :visible.sync="showUpdateForm">
                <el-form ref="updateForm" :rules="rules" :model="updateForm"
                         label-width="100px" size="small">
                    <el-form-item label="平台">
                        <Platform inited="false"
                                  @platformInit="platformInit"
                                  @platformChange="updateFormPlatformChange"
                                  :platformMu="updateForm.platformMu"/>
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
                        <el-button type="primary" @click="doUpdate">确定
                        </el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="680px" title="授权页面" :visible.sync="showGrantForm">
                <el-transfer v-model="own" :data="lack"
                             :titles="['未拥有', '已拥有']"
                             :props="{key: 'mu',label: 'title'}"/>
                <el-button style="margin:  20px 500px" type="primary"
                           @click="doGrant">
                    保存
                </el-button>
            </el-dialog>
        </el-footer>
    </el-container>
</template>

<script>
    import Data from '@/chaos/functions/common/Data';
    import Pagination from '@/chaos/components/Pagination'
    import {page} from '@/chaos/functions/mixin/page'
    import {crud} from '@/chaos/functions/mixin/crud'
    import {platform} from '@/chaos/functions/mixin/platform'

    import Platform from '@/components/Platform'
    import Array from '@/chaos/functions/common/Array'

    export default {
        components: {Pagination, Platform},
        mixins: [page, crud, platform],
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
                rules,
                table: 'chaos_role',
                lack: [],
                own: [],
                grant: [],
                showGrantForm: false,
            }
        },
        methods: {
            async showGrant(roleMu) {
                this.pickRowMu = roleMu
                this.lack = await Data.query('chaos_permission/list', {
                    platformMu: this.data.platformMu,
                    isroot: 0
                })
                const res = await Data.query('chaos_role_permission/listPermissionMus', {roleMu: this.pickRowMu})
                this.own = res || [];
                this.grant = res || [];
                this.showGrantForm = true
            },
            doGrant() {
                Data.submit('chaos_role_permission/grant', {
                    roleMu: this.pickRowMu,
                    addMus: Array.difference(this.own, this.grant),
                    deleteMus: Array.difference(this.grant, this.own)
                })
            },

        }
    }
</script>
<style scoped>
    .el-input {
        width: 280px
    }
</style>
