<template>
    <el-container>
        <el-header style="height: 70px;">
            <el-container style="margin:auto 0px ;padding: 10px ;">
                <el-button type="primary" v-on:click="showAdd">增加角色
                </el-button>
                <el-input style="width: 220px" v-model="info"
                          placeholder="请输入角色名称"></el-input>
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
            <el-table stripe :data="tableData">
                <el-table-column
                        prop="mu"
                        label="编号"
                        width="188">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="缩写"
                        min-width="45">
                </el-table-column>
                <el-table-column
                        prop="info"
                        label="名称"
                        min-width="55">
                </el-table-column>
                <el-table-column
                        prop="indexLink"
                        label="首页"
                        min-width="55">
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        label="创建时间"
                        min-width="76">
                </el-table-column>
                <el-table-column
                        label="操作"
                        width="258">
                    <template slot-scope="scope">
                        <el-button plain @click="showUpdate(scope.row.mu)">编辑
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
            <Paging :currentPage="currentPage" :total="total" :limit="limit"
                    @handleCurrentChange="handleCurrentChange"
                    @handleSizeChange="handleSizeChange"/>
        </el-main>
        <el-footer>
            <el-dialog width="35%" title="添加角色" :visible.sync="showAddForm">
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
                    <el-form-item label="缩写" prop="name">
                        <el-input v-model="form.name" style="width:280px"
                                  placeholder="请输入角色缩写"></el-input>
                    </el-form-item>
                    <el-form-item label="名称" prop="info">
                        <el-input v-model="form.info" style="width:280px"
                                  placeholder="请输入角色名称"></el-input>
                    </el-form-item>
                    <el-form-item label="首页链接" prop="indexLink">
                        <el-input v-model="form.indexLink" style="width:280px"
                                  placeholder="请输入首页链接"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="doAdd">确定</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
            <el-dialog width="35%" title="修改角色" :visible.sync="showUpdateForm">
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
                    <el-form-item label="缩写" prop="name">
                        <el-input v-model="updateForm.name" style="width:280px"
                                  placeholder="请输入角色缩写"></el-input>
                    </el-form-item>
                    <el-form-item label="名称" prop="info">
                        <el-input v-model="updateForm.info" style="width:280px"
                                  placeholder="请输入角色名称"></el-input>
                    </el-form-item>
                    <el-form-item label="首页链接" prop="indexLink">
                        <el-input v-model="updateForm.indexLink"
                                  style="width:280px"
                                  placeholder="请输入首页链接"></el-input>
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
                             :props="{key: 'mu',label: 'title'}"></el-transfer>
                <el-button style="margin:  20px 500px" type="primary"
                           @click="doGrant">
                    保存
                </el-button>
            </el-dialog>
        </el-footer>
    </el-container>
</template>

<script>
    import fetch from 'chaos-data/axios/fetch'

    import Data from 'chaos-data/common/Data'
    import PageData from 'chaos-data/common/PageData'
    import RuleData from 'chaos-data/common/RuleData'
    import Array from 'chaos-data/common/Array'

    export default {
        components: {},
        data() {
            return PageData.dataData('chaos_role', {
                name: '',
                info: '',
                indexLink: '',
                platformMu: ''
            }, {
                mu: '',
                name: '',
                info: '',
                indexLink: '',
                platformMu: ''
            }, RuleData.data([{
                min: 2,
                max: 10,
                key: 'name',
                message: '长度在 2 到 10 个字符',
            }, {
                min: 2,
                max: 10,
                key: 'info',
                message: '长度在 2 到 10 个字符',
            }, {
                min: 2,
                max: 10,
                key: 'indexLink',
                message: '长度在 2 到 10 个字符',
            }]), {
                showGrantForm: false,
                platforms: [],
                platformMu: '',
                roleMu: '',
                form: {},
                updateForm: {},
                lack: [],
                own: [],
                grant: [],
                info: ''
            })
        },
        created() {
            this.init()
        },
        methods: {
            search() {
                Data.page(this.table, this.currentPage, this.limit, {
                    info: this.info,
                    platformMu: this.platformMu
                }, (res) => {
                    this.tableData = res.list;
                    this.total = parseInt(res.total);
                })
            },
            showAdd() {
                this.form.platformMu = this.platforms[0].mu
                this.showAddForm = true
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
            showGrant(mu) {
                this.roleMu = mu
                fetch.post('/manage/chaos_permission/list', {
                    platformMu: this.platformMu,
                    isroot: 0
                }).then((res) => {
                    this.lack = res.data;
                })
                fetch.post('/manage/chaos_role_permission/listPermissionMus', {roleMu: this.roleMu}).then((res) => {
                    this.own = res.data || [];
                    this.grant = res.data || [];
                })
                this.showGrantForm = true
            },
            doDelete(mu) {
                Data.remove(this.table, mu, () => {
                    this.search()
                })
            },
            doGrant() {
                fetch.post('/manage/chaos_role_permission/grant', {
                    roleMu: this.roleMu,
                    addMus: Array.difference(this.own, this.grant),
                    deleteMus: Array.difference(this.grant, this.own)
                }).then(() => {
                })
            },
            init() {
                Data.list('chaos_platform', {}, (res) => {
                    this.platforms = res.data
                    this.platformMu = this.platforms[0].mu
                    this.search()
                })
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.search();
            },
            handleSizeChange(val) {
                this.limit = val;
                this.currentPage = 1;
                this.search();
            }
        }
    }
</script>
<style scoped>

</style>
