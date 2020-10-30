import Taro from '@tarojs/taro';

function mu() {
  return Taro.getStorageSync('mu')
}

function loc() {
  return Taro.getStorageSync('location')
}

function phone() {
  return Taro.getStorageSync('phone')
}

export default {
  mu,
  loc,
  phone
}
