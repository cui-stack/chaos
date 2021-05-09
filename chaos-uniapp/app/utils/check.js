import { get, navigateTo } from "@/chaos/functions/Uni";

export function phone() {
  return new Promise(reslove => {
    if (get("phone").match(/^1\d{10}/)) {
      reslove(true);
    } else {
      navigateTo("authorize");
    }
  });
}
