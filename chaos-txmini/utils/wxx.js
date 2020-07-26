function barTitle(title) {
    wx.setNavigationBarTitle({
        title: title
    })
}

function message(callback) {
    wx.requestSubscribeMessage({
        tmplIds: [''],
        success(res) {
        },
        fail(res) {
        },
        complete() {
            callback()
        }
    })
}

module.exports = {
    barTitle,
    message
}
