<template>
    <el-container>
        <el-header>
            <el-container>
                <el-button type="primary" v-on:click="showAddForm=true">增加资源
                </el-button>
                <el-input v-model="title"
                          placeholder="请输入资源标题"></el-input>
                <el-select v-model="platformMu" placeholder="请选择">
                    <el-option
                            v-for="item in platforms"
                            :key="item.mu"
                            :label="item.name"
                            :value="item.mu">
                    </el-option>
                </el-select>
                <el-button type="primary" v-on:click="search">搜索</el-button>
            </el-container>
        </el-header>
        <el-main>
            <el-table
                    :data="tableData"
                    stripe>
                <el-table-column
                        prop="mu"
                        label="编号"
                        width="178">
                </el-table-column>
                <el-table-column
                        prop="title"
                        label="标题"
                        width="108">
                </el-table-column>
                <el-table-column
                        prop="link"
                        label="链接"
                        min-width="60">
                </el-table-column>
                <el-table-column
                        prop="isroot"
                        label="根节点"
                        sortable
                        width="88">
                    <template slot-scope="scope">
                        <div style="color: #67c23a"
                             v-if="scope.row.isroot=='1'">
                            是
                        </div>
                        <div style="color: #f56c6c"
                             v-if="scope.row.isroot=='0'">
                            否
                        </div>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="supmu"
                        label="根节点编号"
                        width="178">
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        sortable
                        label="创建时间"
                        min-width="76">
                </el-table-column>
                <el-table-column
                        label="操作"
                        width="168">
                    <template slot-scope="scope">
                        <el-button plain @click="showUpdate(scope.row.mu)">编辑
                        </el-button>
                        <el-button style="margin: 0px" plain
                                   @click="doDelete(scope.row.mu)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <Paging :currentPage="currentPage" :total="total" :limit="limit"
                    @handleCurrentChange="handleCurrentChange"
                    @handleSizeChange="handleSizeChange"/>
        </el-main>
        <el-footer>
            <el-dialog width="35%" title="添加资源" :visible.sync="showAddForm">
                <el-form ref="form" :rules="rules" :model="form"
                         label-width="100px"
                         size="small">
                    <el-form-item label="平台">
                        <el-select v-model="form.platformMu">
                            <el-option v-for="item in platforms"
                                       :key="item.mu"
                                       :label="item.name"
                                       :value="item.mu"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="form.title"
                                  placeholder="请输入标题"></el-input>
                    </el-form-item>
                    <el-form-item label="地址" prop="link">
                        <el-input v-model="form.link"
                                  placeholder="请输入地址"></el-input>
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
                                  placeholder="请输入首页链接"></el-input>
                    </el-form-item>
                    <el-form-item label="根节点图标" prop="supmu">
                        <el-input v-model="form.icon" :disabled="formIsroot"
                                  placeholder="请输入根节点图标"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="doAdd">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改资源" :visible.sync="showUpdateForm">
                <el-form ref="updateForm" :rules="rules" :model="updateForm"
                         label-width="100px"
                         size="small">
                    <el-form-item label="平台">
                        <el-select v-model="updateForm.platformMu">
                            <el-option v-for="item in platforms"
                                       :key="item.mu"
                                       :label="item.name"
                                       :value="item.mu"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="标题" prop="title">
                        <el-input v-model="updateForm.title"
                                  placeholder="请输入标题"></el-input>
                    </el-form-item>
                    <el-form-item label="链接" prop="link">
                        <el-input v-model="updateForm.link"
                                  placeholder="请输入链接"></el-input>
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
    import Data from '@/chaos/functions/common/Data'
    import PageData from '@/chaos/functions/common/PageData'
    import RuleData from '@/chaos/functions/common/RuleData'
    import {page} from '@/chaos/functions/mixin/page'

    export default {
        mixins: [page],
        data() {
            return PageData.dataData('chaos_permission', {
                title: '',
                link: '',
                isroot: 0,
                supmu: '',
                icon: ''
            }, {
                mu: '',
                title: '',
                link: '',
                isroot: 0,
                supmu: '',
                icon: ''
            }, RuleData.data([{
                min: 2,
                max: 10,
                key: 'title',
                message: '长度在 2 到 10 个字符',
            }, {
                min: 2,
                max: 10,
                key: 'link',
                message: '长度在 2 到 10 个字符',
            }]), {
                platforms: [],
                platformMu: '',
                formIsroot: true,
                updateFormIsroot: true,
                title: ''
            })
        },
        created() {
            this.listPlatform()
        },
        methods: {
            search() {
                Data.page(this.table, this.currentPage, this.limit, {
                    title: this.title,
                    platformMu: this.platformMu
                }, (res) => {
                    this.tableData = res.list;
                    this.total = parseInt(res.total);
                })
            },
            doAdd() {
                Data.add(this.table, this.form, () => {
                    this.showAddForm = false
                    this.search()
                })
            },
            showUpdate(mu) {
                Data.one(this.table, mu, (res) => {
                    this.updateForm = res.data
                    this.showUpdateForm = true
                })
            },
            doUpdate() {
                Data.update(this.table, this.updateForm.mu, this.updateForm, () => {
                    this.showUpdateForm = false
                    this.search()
                })
            },
            doDelete(mu) {
                Data.remove(this.table, mu, () => {
                    this.search();
                })
            },
            listPlatform() {
                Data.list('chaos_platform', {}, (res) => {
                    this.platforms = res.data
                    this.platformMu = this.platforms[0].mu
                    this.search()
                })
            },
            doFormIsroot() {
                if (this.form.isroot == 0) {
                    this.formIsroot = true
                    this.form.icon = ''
                } else if (this.form.isroot == 1) {
                    this.formIsroot = false
                    this.form.supmu = ''
                }
            },
            doUpdateFormIsroot() {
                if (this.updateForm.isroot == 0) {
                    this.updateFormIsroot = true
                    this.updateForm.icon = ''
                } else if (this.updateForm.isroot == 1) {
                    this.updateFormIsroot = false
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
