let Data = require('./../../utils/data')
let page = require('./../../utils/page')
let store = require('./../../utils/store')
let show = require('./../../utils/show')
Page({
    data: {
        step: 1,
    },
    showXy() {
        page.to('xy')
    },
    onLoad: function () {
        if (wx.getStorageSync('step')) {
            this.step2()
        }
    },
    bindgetuserinfo: function (e) {
        if (e.detail.errMsg == 'getUserInfo:ok') {
            let userInfo = e.detail.userInfo
            Data.update('user', store.mu(), {
                nick: userInfo.nickName,
                head: userInfo.avatarUrl,
                sex: userInfo.gender,
                wxcountry: userInfo.country,
                wxprov: userInfo.province,
                wxcity: userInfo.city
            }, (res) => {
                wx.setStorageSync('step', 2)
                this.step2()
            })
        }
    },
    step2: function () {
        this.setData({
            step: 2,
        })
    },
    bindgetphonenumber: function (e) {
        if (e.detail.errMsg != 'getPhoneNumber:ok') {
            page.to('index')
            return false;
        }
        Data.submit('api/user/binding/phone', {
            iv: e.detail.iv,
            encryptedData: e.detail.encryptedData,
            session_key: wx.getStorageSync('token')
        }, (res) => {
            wx.setStorageSync('phone', res)
            show.success('获取成功')
            setTimeout(function () {
                page.back()
            }, 100)
        })
    }
})
