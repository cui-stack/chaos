import { failToast } from "./Wx";

const { doLogin } = getApp().require("/utils/login");
let lastPost = {};

export function post(uri, param) {
  lastPost = { uri, param };
  return new Promise((reslove, reject) => {
    wx.request({
      url: getApp().host() + "/" + uri,
      data: param,
      method: "post",
      header: {
        token: wx.getStorageSync("token"),
      },
      success: (res) => reslove(res),
      fail,
    });
  }).then(success);
}

async function success(res) {
  const { msg, code, data, page, mark, pages } = res.data;
  const { uri, param } = lastPost;
  if (code === 200) {
    return (
      data ||
      page ||
      (pages && {
        pages,
        mark,
      }) ||
      false
    );
  }
  if (code === 201) {
    wx.setStorageSync("token", msg);
    return post(uri, param);
  }
  if (code === 401) {
    await doLogin({}, true);
    return post(uri, param);
  }
  if (code === 412) {
    navigateTo("authorize");
  }
  failToast(msg);
}

function fail(res) {
  failToast((res && res.data && res.data.msg) || "系统异常");
}
