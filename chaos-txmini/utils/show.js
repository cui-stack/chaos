
function success(txt) {
    wx.showToast({
        title: txt
    })
}

function fail(txt) {
    wx.showToast({
        icon: 'none',
        image: '/img/21.png',
        title: txt
    })
}

function info(txt) {
    wx.showToast({
        icon: 'none',
        title: txt,
    })
}


module.exports = {
    info: info,
    success:success,
    fail:fail
}
