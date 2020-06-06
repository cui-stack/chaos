var fetch = require('../../utils/fetch.js')
var show = require('../../utils/show.js')
Page({
    data: {
        isShow: true,
        istrue: 1
    },
    onLoad: function () {
        var that = this
        if (wx.getStorageSync('nick')) {
            that.setData({
                istrue: 2
            })
        }
    },
    getUserInfo: function (e) {
        var that = this
        var errMsg = e.detail.errMsg
        if (errMsg != 'getUserInfo:ok') {
            that.setData({
                istrue: 2
            })
            return false;
        }
        var userInfo = e.detail.userInfo
        fetch.post('api/iya_user/update',
            {
                data: {
                    'nick': userInfo.nickName,
                    'head': userInfo.avatarUrl,
                    'sex': userInfo.gender,
                    'country': userInfo.country,
                    'prov': userInfo.province,
                    'city': userInfo.city
                },
                'mu': wx.getStorageSync('wid')
            },
            function (data) {
                wx.setStorageSync('nick', userInfo.nickName)
                that.setData({
                    istrue: 2
                })
            }
        )
    },

    showXy() {
        wx.navigateTo({
            url: '../xy/xy',
        })
    },
    hideWindow: function () {
        this.setData({
            isShow: true
        })
    },
    bindgetphonenumber: function (e) {
        var errMsg = e.detail.errMsg
        if (errMsg != 'getPhoneNumber:ok') {
            wx.navigateTo({
                url: '../index/index',
            })
            return false;
        }
        wx.showLoading({
            title: '处理中',
        })
        fetch.post('api/iya_user/binding/phone',
            {
                iv: e.detail.iv,
                encryptedData: e.detail.encryptedData,
                session_key: wx.getStorageSync('sessionKey')
            },
            function (data) {
                wx.hideLoading()
                wx.setStorageSync('phone', data.data)
                show.success('获取成功')
                var page = wx.getStorageSync('page')
                setTimeout(function () {
                    wx.redirectTo({
                        url: '../../' + page,
                    })
                }, 100)
            }
        )
    }

})
