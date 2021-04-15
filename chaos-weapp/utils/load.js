const login = getApp().require("/utils/login");
const check = getApp().require("/utils/check");

export async function doLoad(props) {
  await login.doLogin(props);
  await check.phone();
}
