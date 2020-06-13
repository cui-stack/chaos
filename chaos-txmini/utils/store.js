function mu() {
    return wx.getStorageSync('mu')
}

function loc() {
    return wx.getStorageSync('location')
}

module.exports = {
    mu,
    loc
}
