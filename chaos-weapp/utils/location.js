let Data = require('./data')

function loc() {
    wx.getLocation({
        type: 'gcj02',
        success: function (res) {
            wx.setStorageSync('location', res.longitude + ';' + res.latitude)
            Data.update('chaos_user', "", {
                lat: res.latitude,
                lon: res.longitude
            }, () => {})
        },
    })
}

module.exports = {
    loc,
}