let fetch = require('./fetch')
let show = require('./show')
let check = require('./check')

function add(table, d = {}, callback) {
    fetch.post('api/' + table + '/add', d, function (res) {
        doCb(callback, res)
    })
}

function update(table, mu, d = {}, callback) {
    fetch.post('api/' + table + '/update', {
        mu: mu,
        data: d
    }, function (res) {
        doCb(callback, res)
    })
}

function one(table, data, callback) {
    let d
    if ((typeof data) == 'string') {
        d = {
            mu: data
        }
    } else if ((typeof data) == 'object') {
        d = data
    }
    fetch.post('api/' + table + '/one', d, function (res) {
        doCb(callback, res)
    })
}

function list(table, data = {}, callback) {
    fetch.post('api/' + table + '/list', data, (res) => {
        doCb(callback, res)
    })
}

function page(table, d = {}, pageNum = 1, pageSize = 15, callback) {
    const data = {
        pageNum: pageNum,
        pageSize: pageSize,
        data: d
    }
    fetch.post('api/' + table + '/page', data, (res) => {
        callback(res.page, res.mark)
    })
}

function pages(table, d = {}, pageNum = 1, pageSize = 15, callback) {
    const data = {
        pageNum: 1,
        pageSize: 15,
        data: d
    }
    show.loading()
    fetch.post('api/' + table + '/page', data, (res) => {
        wx.hideLoading()
        callback(res.pages, res.mark)
    })
}

function search(uri, d = {}, pageNum = 1, pageSize = 15, callback, isShow = true) {
    const data = {
        pageNum: pageNum,
        pageSize: pageSize,
        data: d
    }
    if (isShow)
        show.loading()
    fetch.post(uri, data, function (res) {
        if (isShow)
            wx.hideLoading()
        callback(res.page, res.mark)
    })
}

function submit(uri, data = {}, callback, isShow = true) {
    if (isShow)
        show.loading()
    fetch.post(uri, data, function (res) {
        if (isShow)
            wx.hideLoading()
        doCb(callback, res)
    })
}

function validate(thing) {
    if (check.isLogin())
        thing()
}

function doCb(cb, res) {
    if (cb) {
        cb(res.data)
    } else {
        show.info("操作成功")
    }
}

function throttle(fn, data = {}, delay = 500, context = null) {
    clearTimeout(fn.timeoutId);
    fn.timeoutId = setTimeout(function () {
        fn.call(context, data);
    }, delay);
}


module.exports = {
    add,
    update,
    one,
    list,
    page,
    pages,

    search,
    submit,
    validate,

    throttle
}
