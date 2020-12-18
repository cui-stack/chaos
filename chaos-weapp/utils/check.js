let page = require('./page')

function isLogin() {
    let phone = wx.getStorageSync('phone')
    if (!phone.match(/^1\d{10}/)) {
        page.to('authorize')
        return false
    }
    return true
}

module.exports = {
    isLogin
}
