const { get, location, login } = getApp().require("/chaos/functions/Wx");
const { submit, update } = getApp().require("/chaos/functions/Data");

export async function doLogin({ referrer = "", source = "" }, force = false) {
  if (!force && get("token")) {
    return;
  }
  const { mu, token } = await submit("wxmini/login", {
    code: await login(),
    referrer,
    source,
  });
  wx.setStorageSync("mu", mu);
  wx.setStorageSync("token", token);

  const { longitude: lng, latitude: lat } = await location();
  wx.setStorageSync("location", lng + ";" + lat);
}
