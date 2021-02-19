import Vue from 'vue'
import {Message} from 'element-ui';
import axios from 'axios';
import store from '../vuex'
import {baseUrl} from '../../../app/config'

Vue.prototype.$axios = axios
axios.defaults.headers.post['Content-Type'] = 'application/json';
const httpService = axios.create({
    baseURL: baseUrl(),
    timeout: 30000
});

httpService.interceptors.request.use(
    config => {
        config.headers['token'] = store.getters.user.token;
        return config;
    },
    error => {
        Promise.reject(error);
    }
)

httpService.interceptors.response.use(
    response => {
        if (response.status !== 200) {
            return Promise.reject({
                status: response.status,
            });
        }
        if (response.data.code == 201) {
            store.dispatch('admin/refreshToken', response.data.msg)
            return post(store.getters.lastPost.url, store.getters.lastPost.params)
        }
        if (response.data.code == 401 || response.data.code == 403) {
            Message({
                type: 'info',
                message: "登录失效,请重新登录!"
            });
            window.location.href = store.getters.path;
            return
        }
        if (response.data.code == 408) {
            Message({
                type: 'warning',
                message: '请求超时!'
            });
            return
        }
        if (response.data.code == 500) {
            Message({
                type: 'error',
                message: response.data.msg
            });
            return
        }
        if (response.data.code == 501) {
            Message({
                type: 'error',
                message: '参数错误!' + response.data.msg
            });
            return
        }
        if (response.data.code == 560) {
            Message({
                type: 'error',
                message: response.data.msg
            });
            return
        }
        if (response.data.code == 555) {
            Message({
                type: 'error',
                message: '未知错误!'
            });
            return
        }
        if (response.data.code == 200) {
            if (response.data.data) {
                if (response.data.data == true) {
                    Message({
                        type: 'success',
                        message: response.data.msg
                    });
                } else if (response.data.data == false) {
                    Message({
                        type: 'warning',
                        message: response.data.msg
                    });
                }
                return response.data.data;
            }
            if (response.data.page) {
                return response.data.page;
            }
            return
        }
        Message({
            type: 'error',
            message: '操作异常!'
        });
    },
    error => {
        if (error && error.response && error.response.status) {
            switch (error.response.status) {
                case 400:
                    error.message = '错误请求';
                    break;
                case 401:
                    error.message = '未授权，请重新登录';
                    break;
                case 403:
                    error.message = '拒绝访问';
                    break;
                case 404:
                    error.message = '请求错误,未找到该资源';
                    break;
                case 405:
                    error.message = '请求方法未允许';
                    break;
                case 408:
                    error.message = '请求超时';
                    break;
                case 500:
                    error.message = '服务器端出错';
                    break;
                case 501:
                    error.message = '网络未实现';
                    break;
                case 502:
                    error.message = '网络错误';
                    break;
                case 503:
                    error.message = '服务不可用';
                    break;
                case 504:
                    error.message = '网络超时';
                    break;
                case 505:
                    error.message = 'http版本不支持该请求';
                    break;
                default:
                    error.message = `未知错误${error.response.status}`;
            }
        } else {
            error.message = "服务器未响应";
        }
        Message({
            type: 'error',
            message: error.message
        });
    }
)

export function post(url, params = {}) {
    store.dispatch('app/setLastPost', {url, params})
    return new Promise((resolve, reject) => {
        httpService({
            url: url,
            method: 'post',
            data: params
        }).then(response => {
            if (response != undefined) {
                resolve(response);
            }
        }).catch(error => {
            reject(error);
        });
    });
}


export default {
    post
}
