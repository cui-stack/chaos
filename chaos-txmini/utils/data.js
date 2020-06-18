let fetch = require('./fetch')
let show = require('./show')
let check = require('./check')

function add(table, d = {}, callback) {
    fetch.post('api/' + table + '/add', d, function (res) {
        doCb(callback, res)
    })
}

function remove(table, mu, callback) {
    fetch.post('api/' + table + '/remove', {mu: mu}, function (res) {
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

function one(table, mu, callback) {
    fetch.post('api/' + table + '/one', {mu: mu}, function (res) {
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

function search(uri, d = {}, pageNum = 1, pageSize = 15, callback) {
    const data = {
        pageNum: pageNum,
        pageSize: pageSize,
        data: d
    }
    show.loading()
    fetch.post(uri, data, function (res) {
        wx.hideLoading()
        callback(res.page, res.mark)
    })
}

function query(uri, data = {}, callback) {
    fetch.post(uri, data, function (res) {
        doCb(callback, res)
    })
}

function submit(uri, data = {}, callback) {
    show.loading()
    fetch.post(uri, data, function (res) {
        wx.hideLoading()
        doCb(callback, res)
    })
}

function validate(thing) {
    finish(() => {
        return check.isLogin()
    }, () => {
        thing()
    })
}

function doCb(cb, res) {
    if (cb) {
        cb(res.data)
    } else {
        show.info("操作成功")
    }
}

function chain(a, b) {
    finish(a).then(b)
}


function finish(thing, success, fail) {
    return new Promise(
        (resolve, reject) => {
            const result = thing()
            if (result) {
                resolve(result)
            } else {
                reject(result)
            }
        }
    ).then(
        (res) => {
            if (success) {
                success(res)
            }
        },
        (err) => {
            if (fail) {
                fail(err)
            }
        }
    )
}

//限流
function throttle(fn, data = {}, delay = 500, context = null) {
    clearTimeout(fn.timeoutId);
    fn.timeoutId = setTimeout(function () {
        fn.call(context, data);
    }, delay);
}


module.exports = {
    add,
    remove,
    update,
    one,
    list,
    page,

    search,
    submit,
    query,
    validate,

    chain,
    finish,
    throttle
}
