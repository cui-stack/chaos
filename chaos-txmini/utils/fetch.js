let show = require('./show')

let isLocal = true
let isPro = false
let host = isPro ? "https://firepongo.tech/api" : "https://dev.firepongo.tech/api";
let localHost = "http://127.0.0.1:38088"
let finalHost = isLocal ? localHost : host

function post(param, data, cb) {
    wx.request({
        url: finalHost + "/" + param,
        data: data,
        method: 'post',
        header: {
            "token": wx.getStorageSync('token')
        },
        success: function (res) {
            if (res.data.code == "200") {
                return typeof cb == "function" && cb(res.data)
            } else if (res.data.code == "201") {} else if (res.data.code == "401") {} else {
                show.fail(res.data.msg)
            }
        },
        fail: function (res) {
            if (res && res.data && res.data.msg) {
                show.fail(res.data.msg)
            }
        }
    })
}

module.exports = {
    post
}
