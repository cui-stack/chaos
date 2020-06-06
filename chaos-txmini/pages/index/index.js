var fetch = require('../../utils/fetch.js')
Page({
    data: {
        background: ['demo-text-1', 'demo-text-2', 'demo-text-3'],
        keyword: ''
    },
    loginSuccess: function (e) {
        console.log(e.detail.code) // wx.login 的 code
        console.log(e.detail.userInfo) // wx.getUserInfo 的 userInfo
    },
    search: function (value) {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                resolve([{text: '搜索结果', value: 1}, {text: '搜索结果2', value: 2}])
            }, 200)
        })
    },
    selectResult: function (e) {
        console.log('select result', e.detail)
    },
    onLoad: function (e) {
        this.setData({
            search: this.search.bind(this)
        })
    },
    onPullDownRefresh: function (e) {
    },
    onReachBottom: function (e) {
    },
    onShareAppMessage: function (e) {
    },
    onAddToFavorites: function (e) {
    },
    onPageScroll: function (e) {
    },
    onTabItemTap: function (e) {
    }

})
