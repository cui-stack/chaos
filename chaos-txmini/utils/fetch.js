let show = require('./show')

let isLocal = true
let isPro = false
var host = isPro ? "https://iya101.com/iya-api" : "https://dev.iya101.com/iya-api";
let localHost = "http://127.0.0.1:28083"
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
            } else if (res.data.code == "401") {
            } else {
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
