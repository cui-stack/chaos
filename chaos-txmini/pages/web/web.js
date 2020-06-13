let Data = require('./../../utils/data')
Page({
    data: {},
    onLoad: function (e) {
        Data.one('wxmini/tbl-iya-info/info', e.mu,
            (res) => {
                this.setData({
                    url: res.url,
                    title: res.title
                })
            })
    }
})
