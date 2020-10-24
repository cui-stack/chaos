import Taro from '@tarojs/taro';

function success(txt) {
    Taro.showToast({
        title: txt
    })
}

function fail(txt) {
    Taro.showToast({
        icon: 'none',
        image: '/images/fail.png',
        title: txt
    })
}

function info(txt) {
    Taro.showToast({
        icon: 'none',
        title: txt,
    })
}

function loading() {
    Taro.showLoading({
        title: '加载中'
    })
}


export default {
    info,
    success,
    fail,
    loading
}
