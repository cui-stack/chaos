const { get, navigateTo } = getApp().require("/chaos/functions/Wx");

export function phone() {
  return new Promise((reslove, reject) => {
    if (get("phone").match(/^1\d{10}/)) {
      reslove(true);
    } else {
      navigateTo("authorize");
    }
  });
}
