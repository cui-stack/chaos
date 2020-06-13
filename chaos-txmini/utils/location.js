let Data = require('./data')
let store = require('./store')

function loc() {
    wx.getLocation({
        type: 'gcj02',
        success: function (res) {
            wx.setStorageSync('location', res.longitude + ';' + res.latitude)
            Data.update('api/iya_user/update', store.mu(), {
                lat: res.latitude,
                lng: res.longitude
            })
        },
    })
}

module.exports = {
    loc,
}
