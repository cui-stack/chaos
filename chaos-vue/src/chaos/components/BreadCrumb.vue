<template>
    <el-breadcrumb separator="/">
        <transition-group name="breadcrumb">
            <el-breadcrumb-item v-for="(item,index) in levelList"
                                :key="item.path">
                <span v-if="item.redirect==='noRedirect'||index==levelList.length-1"
                      class="no-redirect">{{ item.meta.title }}</span>
                <a v-else @click.prevent="handleLink(item)">
                    {{ item.meta.title}}</a>
            </el-breadcrumb-item>
        </transition-group>
    </el-breadcrumb>
</template>

<script>
    export default {
        name: 'Breadcrumb',
        data() {
            return {
                levelList: null
            }
        },
        watch: {
            $route() {
                this.getBreadcrumb()
            }
        },
        created() {
            this.getBreadcrumb()
        },
        methods: {

            getBreadcrumb() {
                let matched = this.$route.matched.filter(item => item.meta && item.meta.title && item.name !== 'Main' && item.name !== 'index')
                matched = [{
                    path: this.$store.getters.user.indexLink,
                    meta: {title: '首页'}
                }].concat(matched)
                this.levelList = matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
            },
            handleLink(item) {
                this.$router.push(item.path)
            }
        }
    }
</script>

<style lang="less" scoped>
    .el-breadcrumb {
        font-size: 15px;
        line-height: 50px;

        .no-redirect {
            color: #97a8be;
            cursor: text;
        }
    }
</style>
