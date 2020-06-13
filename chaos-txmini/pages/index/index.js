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
    goDocterlist: function () {
        Data.doCheck(() => {
            page.to('docterlist')
        })
    },
    onLoad: function (e) {
        wx.setStorageSync('referrer', e.referrer)
        wx.setStorageSync('source', e.source)
        Data.list('wxmini/tbl-iya-info/infolist', {}, (res) => {
            this.setData({
                list: res.list
            })
        })
    },
    onPullDownRefresh: function (e) {
    },
    onReachBottom: function (e) {
    },
    onShareAppMessage: function (e) {
        return {
            title: "矮芽-医生资质查询",
            path: "pages/index/index?referrer=" + store.mu() + "&source=首页"
        }
    },
    onAddToFavorites: function (e) {
    },
    onPageScroll: function (e) {
    },
    onTabItemTap: function (e) {
    }

})
