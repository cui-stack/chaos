let location = require('./location')
let Data = require('./data')

function login(cb) {
    if (!wx.getStorageSync('token')) {
        wx.login({
            success: res => {
                Data.submit('wxmini/login', {
                    code: res.code,
                    referrer: wx.getStorageSync('referrer'),
                    source: wx.getStorageSync('source')
                }, (res) => {
                    wx.setStorageSync('token', res.token)
                    wx.setStorageSync('mu', res.mu)
                    location.loc()
                    if (cb) cb()
                })
            }
        })
    } else {
        if (cb) cb()
    }
}

module.exports = {
    login
}
