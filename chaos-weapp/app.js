import { reLaunch } from "/chaos/functions/Wx";
const localHost = "http://127.0.0.1:8089";
const devHost = "http://127.0.0.1:8089";
const prodHost = "http://127.0.0.1:8089";

App({
  onPageNotFound() {
    reLaunch("index");
  },
  require: ($uri) => require($uri),
  host: () => localHost,
});
