function to(url, p) {
    wx.navigateTo({
        url: path(url) + (p ? ('?' + p) : '')
    })
}

function back(delta = 1) {
    wx.navigateBack({delta: delta})
}

function reLaunch(url) {
    wx.reLaunch({
        url: path(url),
    })
}

function path(url) {
    return "/pages/" + url + "/" + url
}

module.exports = {
    to,
    back,
    reLaunch,
    path
}
