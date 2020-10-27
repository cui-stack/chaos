let show = require('./show');

let host

if(process.env.NODE_ENV === 'development'){
    host = "https://dev.iya101.com/iya-api"  
}if(process.env.NODE_ENV === 'production'){
    host =  "https://iya101.com/iya-api" 
}else{
	host = "http://127.0.0.1:8089"
}

function post(param, data, cb) {
    uni.request({
        url: host + "/" + param,
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
