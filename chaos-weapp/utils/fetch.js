let show = require('./show');

let isLocal = false
let isPro = false
let host = isPro ? "https://iya101.com/iya-api" : "https://dev.iya101.com/iya-api";
let localHost = "http://127.0.0.1:8089"
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
            } else if (res.data.code == "201") {
                wx.setStorageSync('token', res.data.msg)
                post(param, data, cb)
            } else if (res.data.code == "401") {
                wx.login({
                    success: res => {
                        post('wxmini/login', {
                            code: res.code,
                        }, (res) => {
                            wx.setStorageSync('token', res.data.token)
                            post(param, data, cb)
                        })
                    }
                })
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
