import {Message} from 'element-ui';
import {post} from '@/chaos/functions/axios/fetch'
import {appInfo} from '@/app/config'

export const login = {
    data() {
        return {
            username: '',
            password: '',
            headTitle: appInfo().headTitle
        }
    },
    methods: {
        async login() {
            if (this.username && this.password) {
                const res = await post('/manage/login', {
                    username: this.username,
                    password: this.password,
                    platformMu: appInfo().platformMu
                })
                await this.$store.dispatch('admin/setUserinfo', res)
                await this.$store.dispatch('admin/setMenus', res.menus)
                await this.$router.push(res.indexLink)
            } else {
                Message({
                    type: 'warning',
                    message: '请输入用户名和密码'
                });
            }
        },
    }
}
