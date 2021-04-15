import { Message } from "element-ui";

export function success(text) {
  Message({
    type: "success",
    message: text,
  });
}

export function info(text) {
  Message({
    type: "info",
    message: text,
  });
}

export function warning(text) {
  Message({
    type: "warning",
    message: text,
  });
}

export default {
  info,
  success,
  warning,
};
