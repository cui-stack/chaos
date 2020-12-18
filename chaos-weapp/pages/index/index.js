let Data = require('./../../utils/data')
let store = require('./../../utils/store')
let page = require('./../../utils/page')
let login = require('./../../utils/login')

Page({
    data: {
        active: 0,
        list: []
    },
    onLoad: function (e) {
        wx.setStorageSync('referrer', e.referrer)
        wx.setStorageSync('source', e.source)
        //login.login()
        this.init()
    },
    init: function () {
        Data.list('chaosInfo', {}, (res) => {
            this.setData({
                list: res
            })
        })
    },
    onShareAppMessage: function (e) {
        return {
            title: "去火星",
            path: page.path("index") + "?referrer=" + store.mu() + "&source=首页"
        }
    },

})
