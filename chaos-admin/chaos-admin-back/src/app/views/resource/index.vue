<template>
    <el-container>
        <el-header>
            <el-container>
                <PrimaryButton text="增加" :click="showAdd"/>
                <SearchInput placeholder="请输入标题"
                             :change="(value)=>this.handleChange(value,'title')"/>
                <Platform :init="(value)=>handleChange(value,'platformMu')"
                          :change="(value)=>handleChange(value,'platformMu')"/>
                <IsRadioGroup :value="0"
                              :change="(value)=>handleChange(value,'isRoot')"/>
                <SearchButton :click="search"/>
            </el-container>
        </el-header>
        <el-main>
            <el-table stripe :data="tableData"
                      element-loading-text="拼命加载中"
                      element-loading-spinner="el-icon-loading"
                      element-loading-background="rgba(0, 0, 0, 0.8)"
                      v-loading.fullscreen.lock="loading">
                <el-table-column prop="mu" label="编号" width="200"/>
                <el-table-column prop="supTitle" label="根节点标题" width="200"/>
                <el-table-column prop="title" label="标题" width="200"/>
                <el-table-column prop="link" label="链接" min-width="200"/>
                <el-table-column prop="createTime" sortable label="创建时间"
                                 width="180"/>
                <el-table-column label="操作" width="200">
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
                        <el-radio-group v-model="form.isRoot">
                            <el-radio :label=0>否</el-radio>
                            <el-radio :label=1>是</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="根节点"
                                  :hidden="form.isRoot==1">
                        <RootSource :platformMu="form.platformMu"
                                    :change="(supMu)=>form.supMu = supMu"
                                    @change="(supMu)=>form.supMu = supMu"/>
                    </el-form-item>
                    <el-form-item label="根节点图标" :hidden="form.isRoot==0">
                        <el-input v-model="form.icon"
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
                        <el-radio-group v-model="updateForm.isRoot">
                            <el-radio :label="0">否</el-radio>
                            <el-radio :label="1">是</el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="根节点"
                                  :hidden="updateForm.isRoot==1">
                        <RootSource :platformMu="updateForm.platformMu"
                                    :value="updateForm.supMu"
                                    :change="(supMu)=>updateForm.supMu = supMu"
                                    @change="(supMu)=>updateForm.supMu = supMu"/>
                    </el-form-item>
                    <el-form-item label="根节点图标" :hidden="updateForm.isRoot==0">
                        <el-input v-model="updateForm.icon"
                                  placeholder="请输入根节点图标"/>
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
    import RootSource from '@/app/components/RootSource'
    import IsRadioGroup from '@/app/components/IsRadioGroup'

    import {page, remove, create, update} from '@/chaos/functions/mixin/crud'

    export default {
        mixins: [page, remove, create, update],
        components: {Platform, IsRadioGroup,RootSource},
        data() {
            const rules = {
                title: [
                    {required: true, message: '请输入标题', trigger: 'blur'},
                ],
                link: [
                    {required: true, message: '请输入链接', trigger: 'blur'},
                ],
            }
            return {
                rules,
                domain: 'chaos_resource',
                data: {
                    isRoot: 0
                },
                form: {
                    platformMu:'',
                    isRoot: 0,
                    link:'/',
                    icon: 'el-icon-menu'
                },
                updateForm: {
                    platformMu: ''
                }
            }
        },
    }
</script>
<style scoped>
    .el-form {
        width: 500px;
    }
</style>
