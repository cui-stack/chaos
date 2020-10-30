let Data = require('./../../utils/data')
let page = require('./../../utils/page')
let store = require('./../../utils/store')
let login = require('./../../utils/login')

Page({
    data: {
        mu: '',
        url: '',
        title: '',
    },
    onLoad: function (e) {
        login.login(e.referrer, e.source)
        Data.one('iya_info', e.mu,
            (res) => {
                this.setData({
                    mu: e.mu,
                    url: res.url,
                    title: res.title
                })
            })
    },
    onShareAppMessage: function (e) {
        return {
            title: "混沌",
            path: page.path("web") + "?referrer=" + store.mu() + "&mu=" + this.data.mu
        }
    },
})
