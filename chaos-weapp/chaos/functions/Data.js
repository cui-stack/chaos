import { post } from "./fetch";

export function add(domain, data = {}) {
  return post(`api/${domain}/add`, data);
}

export function update(domain, mu, data = {}) {
  return post(`api/${domain}/update`, { mu, data });
}

export function one(domain, mu) {
  return post(`api/${domain}/one`, { mu });
}

export function list(domain, data = {}) {
  return post(`api/${domain}/list`, data);
}

export function page(domain, data = {}, pageNum = 1, pageSize = 15) {
  return post(`api/${domain}/page`, { pageNum, pageSize, data });
}

export function search(uri, data = {}, pageNum = 1, pageSize = 15) {
  return post(uri, { pageNum, pageSize, data });
}
export function query(uri, data = {}) {
  return post(uri, data);
}

export function submit(uri, data = {}) {
  return post(uri, data);
}

export function throttle(fn, data = {}, delay = 500, context = null) {
  clearTimeout(fn.timeoutId);
  fn.timeoutId = setTimeout(function () {
    fn.call(context, data);
  }, delay);
}
