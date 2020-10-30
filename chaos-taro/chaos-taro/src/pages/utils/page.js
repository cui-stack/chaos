import Taro from '@tarojs/taro'

function to(url, p) {
    Taro.navigateTo({ url: path(url) + (p ? ('?' + p) : '')}) 
}

function back(delta = 1) {
    Taro.navigateBack({delta: delta})
}

function reLaunch(url) {
    Taro.reLaunch({
        url: path(url),
    })
}

function path(url) {
    return "/pages/" + url + "/index" 
}

export default {
    to,
    back,
    reLaunch,
    path
}
