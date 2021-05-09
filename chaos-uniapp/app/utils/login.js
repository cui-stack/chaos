import { submit, update } from "@/chaos/functions/Data";
import { get, location, login } from "@/chaos/functions/Uni";

export async function doLogin({ referrer = "", source = "" }, force = false) {
  if (!force && get("token")) {
    return;
  }

  const { mu, token } = await submit("wxmini/login", {
    code: await login(),
    referrer,
    source
  });
  uni.setStorageSync("mu", mu);
  uni.setStorageSync("token", token);

  const { longitude: lng, latitude: lat } = await location();
  uni.setStorageSync("location", lng + ";" + lat);
}
