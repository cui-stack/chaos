let PickerDate = require('./PickerDate')
Component({
    properties: {
        isHidden: {
            type: Boolean,
            value: true
        }
    },
    data: {
        months: [],
        days: [],
        hours: [],
        timeDetail: [0, 0, 0],
        orderTime: ''
    },
    lifetimes: {
        attached: function () {
            let time = PickerDate.time()
            this.setData({
                months: time[0],
                days: time[1],
                hours: time[2]
            })
        },
    },
    methods: {
        bindChange: function (e) {
            let data = e.detail.value
            let start_time = 9
            let end_time = 17
            let dayArr = this.data.days
            let hoursArr = this.data.hours

            let date = new Date()
            let pickerYear = (this.data.months[data[0]] < date.getMonth() + 1) ? date.getFullYear() + 1 : date.getFullYear()
            if (data[0] == 0 && data[1] == 0 && data[2] == 0) {
                let time = PickerDate.time()
                dayArr = time[1]
                hoursArr = time[2]
            } else {
                if (data[0] != 0) {//变月
                    let monthday = PickerDate.getMonthday(pickerYear, this.data.months[data[0]])
                    if (this.data.days.length != monthday) {
                        dayArr = []
                        for (let i = 1; i <= monthday; i++) {
                            dayArr.push(i)
                        }
                    }
                }
                if (!(data[0] == 0 && data[1] == 0 && data[2] != 0)) {
                    hoursArr = []
                    for (let i = start_time; i <= end_time; i++) {
                        hoursArr.push(i)
                    }
                }
            }
            this.setData({
                days: dayArr,
                hours: hoursArr,
                orderTime: pickerYear + '-' + this.data.months[data[0]] + '-' + dayArr[data[1]] + ' ' + hoursArr[data[2]] + ':' + "00",
            })
        },
        sureTime: function () {
            if (this.data.orderTime == '') {
                let date = new Date()
                this.data.orderTime = date.getFullYear() + '-' + this.data.months[0] + '-' + this.data.days[0] + ' ' + this.data.hours[0] + ':' + "00"
            }
            this.setData({
                isHidden: true
            })
            this.triggerEvent('sureTime', this.data.orderTime)
        }
    }
})
