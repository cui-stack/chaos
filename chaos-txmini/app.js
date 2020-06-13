let login = require('/utils/login')
let page = require('/utils/page')
App({
    onLaunch(options) {
        login.login()
    },
    onShow(options) {
    },
    onHide() {
    },
    onError(msg) {
    },
    onPageNotFound() {
        page.to('index')
    },
    onUnhandledRejection() {
    },
    onThemeChange() {
    },
    globalData: {}
})
