Component({
    properties: {},
    data: {
        list: [{
            "text": "对话",
            "iconPath": "/images/woman_student.png",
            "selectedIconPath": "/images/man_student.png",
            dot: true
        },
            {
                "text": "设置",
                "iconPath": "/images/woman_student.png",
                "selectedIconPath": "/images/man_student.png",
                badge: 'New'
            }]
    },
    methods: {
        tabChange(e) {
            console.log('tab change', e)
        }
    }
})
