import { submit } from "@/chaos/functions/Data";
import { get, login } from "@/chaos/functions/Taro";
import Taro from "@tarojs/taro";

export async function doLogin({ referrer = "", source = "" }, force = false) {
  if (!force && get("token")) {
    return;
  }
  const { mu, token } = await submit("wxmini/login", {
    code: await login(),
    referrer,
    source
  });
  Taro.setStorageSync("mu", mu);
  Taro.setStorageSync("token", token);
}
