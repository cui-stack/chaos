let fetch = require('./fetch')
let show = require('./show')
let check = require('./check')

function list(uri, data = {}, callback) {
    fetch.post(uri, data, (res) => {
        if (callback && res) {
            callback(res.page)
        }
    })
}

function page(uri, d = {}, pageNum = 1, pageSize = 15, callback) {
    const data = {
        pageNum: pageNum,
        pageSize: pageSize,
        data: d
    }
    fetch.post(uri, data, (res) => {
        callback(res)
    })
}

function one(uri, mu, callback) {
    fetch.post(uri, {mu: mu}, function (res) {
        if (callback && res) {
            callback(res.data)
        }
    })
}

function update(uri, mu, d = {}, callback) {
    fetch.post(uri, {
        mu: mu,
        data: d
    }, function (res) {
        if (callback && res) {
            callback(res.data)
        }
    })
}

function search(uri, data = {}, callback) {
    show.loading()
    fetch.post(uri, data, function (res) {
        wx.hideLoading()
        if (callback && res) {
            callback(res.data)
        }
    })
}

function chain(a = []) {
    a.shift()(a)
}

function contact(a, b) {
    doThing(a).then(b)
}

function doThing(thing, success, fail) {
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

function doCheck(thing) {
    doThing(() => {
        return check.isLogin()
    }, () => {
        thing()
    })
}

module.exports = {
    search,
    page,
    list,
    one,
    //add,
    update,
    //remove,
    chain,
    contact,
    doThing,
    doCheck,
    throttle
}
