let Data = require('./../../utils/data')
let store = require('./../../utils/store')
let page = require('./../../utils/page')

Page({
    data: {
        list: []
    },
    goWeb: function (e) {
        let list = this.data.list
        page.to('web', 'mu=' + list[e.currentTarget.dataset.id]['mu'])
    },
    onLoad: function (e) {
        wx.setStorageSync('referrer', e.referrer)
        wx.setStorageSync('source', e.source)
        login.login()
        this.init()
    },
    init: function () {
        Data.list('chaos_info', {}, (res) => {
            this.setData({
                list: res
            })
        })
    },
    onShareAppMessage: function (e) {
        return {
            title: "",
            path: page.path("index") + "?referrer=" + store.mu() + "&source=首页"
        }
    },

})
