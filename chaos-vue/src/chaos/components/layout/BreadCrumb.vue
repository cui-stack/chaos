<template>
    <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: this.$store.getters.user.indexLink }">
            首页
        </el-breadcrumb-item>
        <el-breadcrumb-item v-for="(item,index) in list"
                            :key="index" :to="{ path: item.path }">
            {{item.meta.title}}
        </el-breadcrumb-item>
    </el-breadcrumb>
</template>

<script>
    export default {
        name: 'Breadcrumb',
        data() {
            return {
                list: []
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
            async getBreadcrumb() {
                this.list = this.$route.meta.breadcrumb || []
                if (this.$store.getters.user.indexLink !== this.$route.path && this.$route.meta.title) {
                    this.list = this.list.concat({
                        path: this.$route.path,
                        meta: {
                            title: this.$route.meta.title
                        }
                    })
                }
            },
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
