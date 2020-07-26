/**
 * 图片预加载组件
 */
Component({
    properties: {
        //默认图片
        defaultImage: String,
        //原始图片
        originalImage: String,
        width: String,
        height: String,
        //图片剪裁mode，同Image组件的mode
        mode: String,
        load: {
            type: Function,
            value: function () {}
        }
    },
    data: {
        finishLoadFlag: false,
        windowWidth: 0,

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
        finishLoad: function (e) {
            // 获取图片宽高比
            const ratio = e.detail.width / e.detail.height
            // 按照宽高比计算图片宽度 100% 时的高度
            const imgHeight = this.windowWidth / ratio
            // 把每一张图片对应的高度记录到 imgsHeight 数组里

            this.triggerEvent('imgHeight', imgHeight)

            this.setData({
                finishLoadFlag: true
            })
        }
    }
})