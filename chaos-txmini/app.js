App({
    onLaunch(options) {
    },
    onShow(options) {
    },
    onHide() {
    },
    onError(msg) {
    },
    onPageNotFound() {
        wx.redirectTo({
            url: 'pages/index/index'
        })
    },
    onUnhandledRejection() {
    },
    onThemeChange() {
    },
    globalData: {}
})
