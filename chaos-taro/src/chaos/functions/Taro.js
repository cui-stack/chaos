import Taro, { getCurrentInstance } from "@tarojs/taro";

export async function login() {
  const response = await Taro.login();
  if (response.errMsg === "login:ok") return response.code;
}

export function userProfile() {
  return new Promise(reslove => {
    Taro.getUserProfile({
      desc: "用于完善会员资料",
      success: res => reslove(res.userInfo)
    });
  });
}

export function location() {
  return new Promise(reslove => {
    Taro.getLocation({
      type: "gcj02",
      success: res => reslove(res)
    });
  });
}

export function get(key) {
  return Taro.getStorageSync(key);
}

export function successToast(title) {
  Taro.showToast({ title });
}

export function failToast(title) {
  Taro.showToast({
    icon: "none",
    image: "/images/fail.png",
    title
  });
}

export function infoToast(title) {
  Taro.showToast({
    icon: "none",
    title
  });
}

export function showLoading() {
  Taro.showLoading({
    title: "加载中"
  });
}

export function hideLoading() {
  Taro.hideLoading();
}

export function navigateTo(url, params = "") {
  Taro.navigateTo({ url: path(url) + (params && "?") + params });
}

export function navigateBack(delta = 1) {
  Taro.navigateBack({ delta });
}

export function redirectTo(url, params = "") {
  Taro.redirectTo({ url: path(url) + (params && "?") + params });
}

export function reLaunch(url) {
  Taro.reLaunch({ url: path(url) });
}

export function path(url) {
  return `/pages/${url}/index`;
}

export function barTitle(title) {
  Taro.setNavigationBarTitle({ title });
}

export function message() {
  // return new Promise((reslove, reject) => {
  //   wx.requestSubscribeMessage({
  //     tmplIds: ["SNNWGO_mXRxQvLik3-zvViC2rv1qdQPDRaU4qykVAVo"],
  //     success: res => reslove(res)
  //   });
  // });
}

export function getParams() {
  return getCurrentInstance().router.params;
}
