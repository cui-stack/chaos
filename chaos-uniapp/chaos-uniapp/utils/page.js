function to(url, p) {
    uni.navigateTo({
        url: path(url) + (p ? ('?' + p) : '')
    })
}

function back(delta = 1) {
    uni.navigateBack({delta: delta})
}

function redirectTo(url, p) {
    uni.redirectTo({
        url: path(url) + (p ? ('?' + p) : '')
    })
}

function reLaunch(url) {
    uni.reLaunch({
        url: path(url),
    })
}

function path(url) {
    return "/pages/" + url + "/" + url
}

module.exports = {
    to,
    back,
    reLaunch,
    path,
    redirectTo
}
