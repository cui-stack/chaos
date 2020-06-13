let Data = require('./../../utils/data')
let page = require('./../../utils/page')
let check = require('./../../utils/check')
Component({
    properties: {},
    data: {
        list: [{
            "text": "按城市看",
            "iconPath": "/images/city1.png",
            "selectedIconPath": "/images/city2.png",
            "url": "citylist"
        },
            {
                "text": "我的预约",
                "iconPath": "/images/xiadan.png",
                "selectedIconPath": "/images/xiadan.png",
                "url": "booklist",
                badge: 'New'
            }]
    },
    methods: {
        tabChange(e) {
            Data.doThing(() => {
                return check.isLogin()
            }, () => {
                page.to(e.detail.item.url)
            })
        }
    }
})
