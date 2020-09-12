Component({
    properties: {
        list: {
            type: Array,
            value: [],
        },
    },
    data: {
        indicatorDots: false,
        vertical: false,
        autoplay: false,
        interval: 5000,
        duration: 500,

        windowWidth: 0,
        imgsHeight: [],
        current: 0,
        imgWidth: 750,
        height: 400,

    },
    lifetimes: {
        attached: function () {
            wx.getSystemInfo({
                success: (res) => {
                    this.windowWidth = res.windowWidth
                }
            })
        },
    },
    methods: {
        imageLoad: function (e) {
            this.data.imgsHeight[e.target.dataset.id] = e.detail
            this.setData({
                height: this.data.imgsHeight[0]
            })

        },
        // swiper 绑定 bindchange 函数，用来获取 current 值，传入 data
        bindchange: function (e) {
            this.setData({
                height: this.data.imgsHeight[e.detail.current]
            })
            this.triggerEvent('count', e.detail.current)
        }
    }
})