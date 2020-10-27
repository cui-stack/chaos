function barTitle(title) {
    wx.setNavigationBarTitle({
        title: title
    })
}

function message(callback) {
    wx.requestSubscribeMessage({
        tmplIds: ['SNNWGO_mXRxQvLik3-zvViC2rv1qdQPDRaU4qykVAVo'],
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
