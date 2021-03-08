<template>
    <el-container>
        <el-header>
            <el-container>
                <PrimaryButton text="增加" :click="showAdd"/>
                <SearchInput placeholder="请输入标题"
                             :change="(value)=>this.handleChange(value,'title')"/>
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
                <el-table-column prop="mu" label="编号" width="178"/>
                <el-table-column prop="title" label="标题" width="108"/>
                <el-table-column prop="link" label="链接" min-width="60"/>
                <el-table-column prop="isRoot" label="根节点" sortable width="88">
                    <template slot-scope="scope">
                        <div style="color: #67c23a"
                             v-if="scope.row.isRoot=='1'">是
                        </div>
                        <div style="color: #f56c6c"
                             v-if="scope.row.isRoot=='0'">否
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="supmu" label="根节点编号" width="178"/>
                <el-table-column prop="createTime" sortable label="创建时间"
                                 min-width="76"/>
                <el-table-column label="操作" width="168">
                    <template slot-scope="scope">
                        <PlainButton text="编辑"
                                     :click="()=>showUpdate(scope.row.mu)"/>
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
            <el-dialog width="35%" title="添加资源" :visible.sync="showAddForm">
                <el-form ref="form" :rules="rules" :model="form"
                         label-width="100px" size="small">
                    <el-form-item label="平台">
                        <Platform
                                :init="(platformMu)=>form.platformMu = platformMu"
                                :change="(platformMu)=>form.platformMu = platformMu"/>
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
                        <el-radio-group v-model="form.isRoot"
                                        @change="doFormIsRoot">
                            <el-radio :label="0">否</el-radio>
                            <el-radio :label="1">是</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="根节点编码" prop="supmu">
                        <el-input v-model="form.supmu" :disabled="!formIsRoot"
                                  placeholder="请输入首页链接"/>
                    </el-form-item>
                    <el-form-item label="根节点图标" prop="supmu">
                        <el-input v-model="form.icon" :disabled="formIsRoot"
                                  placeholder="请输入根节点图标"/>
                    </el-form-item>
                    <el-form-item>
                        <PrimaryButton text="确定" :click="doAdd"/>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改资源"
                       :visible.sync="showUpdateForm">
                <el-form ref="updateForm" :rules="rules" :model="updateForm"
                         label-width="100px"
                         size="small">
                    <el-form-item label="平台">
                        <Platform :isInit="false" :value="updateForm.platformMu"
                                  :change="(platformMu)=>updateForm.platformMu = platformMu"/>
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
                        <el-radio-group v-model="updateForm.isRoot"
                                        @change="doUpdateFormIsRoot">
                            <el-radio :label="0">否</el-radio>
                            <el-radio :label="1">是</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="根节点编号" prop="indexLink">
                        <el-input v-model="updateForm.supmu"
                                  placeholder="请输入根节点编号"
                                  :disabled="!updateFormIsRoot"></el-input>
                    </el-form-item>
                    <el-form-item label="根节点图标" prop="supmu">
                        <el-input v-model="updateForm.icon"
                                  placeholder="请输入根节点图标"
                                  :disabled="updateFormIsRoot"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <PrimaryButton text="确定" :click="doUpdate"/>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </el-footer>
    </el-container>
</template>

<script>
    import Platform from '@/app/components/Platform'
    import {page, remove, create, update} from '@/chaos/functions/mixin/crud'

    export default {
        mixins: [page, remove, create, update],
        components: {Platform},
        data() {
            return {
                domain: 'chaos_resource',
                formIsRoot: false,
                updateFormIsRoot: false
            }
        },
        methods: {
            doFormIsRoot() {
                this.formIsRoot = this.form.isRoot == 0
                if (this.form.isRoot == 0) {
                    this.form.icon = ''
                }
                if (this.form.isroot == 1) {
                    this.form.supmu = ''
                }
            },
            doUpdateFormIsRoot() {
                this.updateFormIsRoot = this.updateForm.isRoot == 0
                if (this.updateForm.isRoot == 0) {
                    this.updateForm.icon = ''
                }
                if (this.updateForm.isRoot == 1) {
                    this.updateForm.supmu = ''
                }
            },
        }
    }
</script>
<style scoped>
</style>
