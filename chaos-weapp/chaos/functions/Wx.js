export async function login() {
  return new Promise((reslove, reject) => {
    wx.login({
      success: (res) => reslove(res.code),
    });
  });
}

export function userProfile() {
  return new Promise((reslove, reject) => {
    wx.getUserProfile({
      desc: "用于完善会员资料",
      success: (res) => reslove(res.userInfo),
    });
  });
}

export function location() {
  return new Promise((reslove, reject) => {
    wx.getLocation({
      type: "gcj02",
      success: (res) => reslove(res),
    });
  });
}

export function get(key) {
  return wx.getStorageSync(key);
}

export function successToast(txt) {
  wx.showToast({
    title: txt,
  });
}

export function failToast(txt) {
  wx.showToast({
    icon: "none",
    image: "/images/fail.png",
    title: txt,
  });
}

export function infoToast(txt) {
  wx.showToast({
    icon: "none",
    title: txt,
  });
}

export function showLoading() {
  wx.showLoading({
    title: "加载中",
  });
}

export function hideLoading() {
  wx.hideLoading();
}

export function navigateTo(url, params = "") {
  wx.navigateTo({
    url: path(url) + (params && "?") + params,
  });
}

export function navigateBack(delta = 1) {
  wx.navigateBack({
    delta: delta,
  });
}

export function redirectTo(url, params = "") {
  wx.redirectTo({
    url: path(url) + (params && "?") + params,
  });
}

export function reLaunch(url) {
  wx.reLaunch({
    url: path(url),
  });
}

export function path(url) {
  return `/pages/${url}/index`;
}

export function barTitle(title) {
  wx.setNavigationBarTitle({
    title,
  });
}

export function message() {
  return new Promise((reslove, reject) => {
    wx.requestSubscribeMessage({
      tmplIds: ["SNNWGO_mXRxQvLik3-zvViC2rv1qdQPDRaU4qykVAVo"],
      success: (res) => reslove(res),
    });
  });
}
