let Data = require('./data')

function loc() {
    wx.getLocation({
        type: 'gcj02',
        success: function (res) {
            wx.setStorageSync('location', res.longitude + ';' + res.latitude)
            Data.update('iya_user',"", {
                lat: res.latitude,
                lng: res.longitude
            }, () => {
            })
        },
    })
}

module.exports = {
    loc,
}
