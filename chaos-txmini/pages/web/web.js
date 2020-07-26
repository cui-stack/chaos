let Data = require('./../../utils/data')
let page = require('./../../utils/page')
let store = require('./../../utils/store')

Page({
    data: {
        mu: '',
        url: '',
        title: '',
    },
    onLoad: function (e) {
        wx.setStorageSync('referrer', e.referrer)
        wx.setStorageSync('source', e.source)

        Data.one('info', e.mu,
            (res) => {
                this.setData({
                    mu: e.mu,
                    url: res.url,
                    title: res.title
                })
            })
    },
    onShow: function () {

    },
    onShareAppMessage: function (e) {
        return {
            title: "",
            path: page.path("web") + "?referrer=" + store.mu() + "&mu=" + this.data.mu
        }
    },
})
