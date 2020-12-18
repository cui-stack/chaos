function success(txt) {
    wx.showToast({
        title: txt
    })
}

function fail(txt) {
    wx.showToast({
        icon: 'none',
        image: '/images/fail.png',
        title: txt
    })
}

function info(txt) {
    wx.showToast({
        icon: 'none',
        title: txt,
    })
}

function loading() {
    wx.showLoading({
        title: '加载中'
    })
}


module.exports = {
    info,
    success,
    fail,
    loading
}
