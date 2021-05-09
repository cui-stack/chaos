import { doLogin } from "@/utils/login";
import { phone } from "@/utils/check";

export async function doLoad(props) {
  await doLogin(props);
  await phone();
}
