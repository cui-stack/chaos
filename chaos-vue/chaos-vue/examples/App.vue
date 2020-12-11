<template>

    <div>
        <Head ></Head>
        <CenterHead></CenterHead>
        <SideMenu> </SideMenu>
    </div>

</template>

<script>
    export default {
        name: 'app',
        components: {},
        data() {
            return {
                currentPage: 1,
                limit: 20,
                total: 0,
                username: 'ccc',
                rolename: 'admin',
                menus: [
                    {
                        icon: 'el-icon-menu',
                        link: '',
                        submenus: [
                            {
                                'icon': '',
                                'link': '/admin',
                                'submenus': [],
                                'title': '管理员'
                            },
                            {
                                'icon': '',
                                'link': '/role',
                                'submenus': [],
                                'title': '角色'
                            },
                            {
                                'icon': '',
                                'link': '/permission',
                                'submenus': [],
                                'title': '资源'
                            },
                            {
                                'icon': '',
                                'link': '/platform',
                                'submenus': [],
                                'title': '平台'
                            },
                            {
                                'icon': '',
                                'link': '/log',
                                'submenus': [],
                                'title': '日志'
                            },
                            {
                                'icon': '',
                                'link': '/onlineAdmin',
                                'submenus': [],
                                'title': '在线管理员'
                            },
                            {
                                'icon': '',
                                'link': '/link',
                                'submenus': [],
                                'title': '首页链接'
                            },
                            {
                                'icon': '',
                                'link': '/env',
                                'submenus': [],
                                'title': '环境'
                            }
                        ],
                        title: '控制台'
                    }
                ],
            }
        },
        methods: {
            handleCurrentChange(val) {
                this.currentPage = val;
            },
            handleSizeChange(val) {
                this.limit = val;
            },
            onLogout() {
                console.log(555)
                // console.log(val);
                // this.$emit('onLogout');
            },
        }
    }
</script>

<style lang='scss' scoped>

</style>
