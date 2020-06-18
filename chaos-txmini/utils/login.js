let location = require('./location')
let Data = require('./data')

function login() {
    wx.login({
        success: res => {
            Data.submit('wxmini/login', {
                code: res.code,
                referrer: wx.getStorageSync('referrer'),
                source: wx.getStorageSync('source')
            }, (res) => {
                wx.setStorageSync('mu', res.mu)
                wx.setStorageSync('token', res.token)
                location.loc()
            })
        }
    })
}

module.exports = {
    login
}
