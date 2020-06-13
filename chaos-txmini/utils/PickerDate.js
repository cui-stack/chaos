function time() {
    let start_time = 9
    let end_time = 17
    let max_mon_length = 6

    let date = new Date()
    let year = date.getFullYear()
    let month = date.getMonth() + 1
    let day = date.getDate()
    let hour = date.getHours()
    let monthday = getMonthday(year, month)

    let monthsArr = []
    let dayArr = []
    let hoursArr = []

    let vmonth = (monthday == day && hour >= end_time) ? 1 + month % 13 : month
    let vday = hour < end_time ? day : (monthday != day ? day + 1 : 1)
    let vhour = (hour >= end_time || hour < start_time) ? start_time : hour + 1

    for (var i = vmonth; i < vmonth + max_mon_length; i++) {
        var ii = i % 12
        monthsArr.push(ii == 0 ? 12 : ii)
    }
    for (var i = vday; i <= monthday; i++) {
        dayArr.push(i)
    }
    for (var i = vhour; i <= end_time; i++) {
        hoursArr.push(i)
    }

    return [monthsArr, dayArr, hoursArr]

}

function getMonthday(year, month) {
    return new Date(year, month, 0).getDate();
}

module.exports = {
    time,
    getMonthday
}
