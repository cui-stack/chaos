<template>
    <el-container>
        <el-header>
            <el-container>
                <el-button type="primary" @click="showAddForm=true">增加资源</el-button>
                <el-input v-model="data.title" placeholder="请输入资源标题"/>
                <Platform @platformInit="platformChange"
                          @platformChange="platformChange"/>
                <el-button type="primary" @click="search">搜索</el-button>
            </el-container>
        </el-header>
        <el-main>
            <el-table :data="tableData" stripe>
                <el-table-column prop="mu" label="编号" width="178"/>
                <el-table-column prop="title" label="标题" width="108"/>
                <el-table-column prop="link" label="链接" min-width="60"/>
                <el-table-column prop="isroot" label="根节点" sortable width="88">
                    <template slot-scope="scope">
                        <div style="color: #67c23a"
                             v-if="scope.row.isroot=='1'">是
                        </div>
                        <div style="color: #f56c6c"
                             v-if="scope.row.isroot=='0'">否
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="supmu" label="根节点编号" width="178"/>
                <el-table-column prop="createTime" sortable label="创建时间"
                                 min-width="76"/>
                <el-table-column label="操作" width="168">
                    <template slot-scope="scope">
                        <el-button plain
                                   @click="showPlatformUpdate(scope.row.mu)">编辑
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
            <el-dialog width="35%" title="添加资源" :visible.sync="showAddForm">
                <el-form ref="form" :rules="rules" :model="form"
                         label-width="100px" size="small">
                    <el-form-item label="平台">
                        <Platform @platformChange="addFormPlatformChange"
                                  @platformInit="addFormPlatformChange"/>
                    </el-form-item>
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="form.title"
                                  placeholder="请输入标题"/>
                    </el-form-item>
                    <el-form-item label="地址" prop="link">
                        <el-input v-model="form.link"
                                  placeholder="请输入地址"/>
                    </el-form-item>
                    <el-form-item label="是否根节点">
                        <el-radio-group v-model="form.isroot"
                                        @change="doFormIsroot">
                            <el-radio :label="0">否</el-radio>
                            <el-radio :label="1">是</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="根节点编码" prop="supmu">
                        <el-input v-model="form.supmu" :disabled="!formIsroot"
                                  placeholder="请输入首页链接"/>
                    </el-form-item>
                    <el-form-item label="根节点图标" prop="supmu">
                        <el-input v-model="form.icon" :disabled="formIsroot"
                                  placeholder="请输入根节点图标"/>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="doAdd">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改资源"
                       :visible.sync="showUpdateForm">
                <el-form ref="updateForm" :rules="rules" :model="updateForm"
                         label-width="100px"
                         size="small">
                    <el-form-item label="平台">
                        <Platform inited="false"
                                  @platformInit="platformInit"
                                  @platformChange="updateFormPlatformChange"
                                  :platformMu="updateForm.platformMu"/>
                    </el-form-item>
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="updateForm.title"
                                  placeholder="请输入标题"/>
                    </el-form-item>
                    <el-form-item label="链接" prop="link">
                        <el-input v-model="updateForm.link"
                                  placeholder="请输入链接"/>
                    </el-form-item>
                    <el-form-item label="是否根节点">
                        <el-radio-group v-model="updateForm.isroot"
                                        @change="doUpdateFormIsroot">
                            <el-radio :label="0">否</el-radio>
                            <el-radio :label="1">是</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="根节点编号" prop="indexLink">
                        <el-input v-model="updateForm.supmu"
                                  placeholder="请输入根节点编号"
                                  :disabled="!updateFormIsroot"></el-input>
                    </el-form-item>
                    <el-form-item label="根节点图标" prop="supmu">
                        <el-input v-model="updateForm.icon"
                                  placeholder="请输入根节点图标"
                                  :disabled="updateFormIsroot"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="doUpdate">确定
                        </el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </el-footer>
    </el-container>
</template>

<script>
    import Pagination from '@/chaos/components/Pagination'
    import Platform from '@/components/Platform'
    import {page} from '@/chaos/functions/mixin/page'
    import {crud} from '@/chaos/functions/mixin/crud'
    import {platform} from '@/chaos/functions/mixin/platform'

    export default {
        mixins: [page, crud, platform],
        components: {Pagination, Platform},
        data() {
            return {
                table: 'chaos_permission',
                formIsroot: false,
                updateFormIsroot: false
            }
        },
        methods: {
            doFormIsroot() {
                this.formIsroot = this.form.isroot == 0
                if (this.form.isroot == 0) {
                    this.form.icon = ''
                }
                if (this.form.isroot == 1) {
                    this.form.supmu = ''
                }
            },
            doUpdateFormIsroot() {
                this.updateFormIsroot = this.updateForm.isroot == 0
                if (this.updateForm.isroot == 0) {
                    this.updateForm.icon = ''
                }
                if (this.updateForm.isroot == 1) {
                    this.updateForm.supmu = ''
                }
            },
        }
    }
</script>
<style scoped>
    .el-header .el-container {
        margin: auto 0px;
        padding: 10px;
    }

    .el-input {
        width: 280px;
    }
</style>
