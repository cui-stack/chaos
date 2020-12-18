function mu() {
    return wx.getStorageSync('mu')
}

function loc() {
    return wx.getStorageSync('location')
}

function phone() {
    return wx.getStorageSync('phone')
}
module.exports = {
    mu,
    loc,
    phone
}
