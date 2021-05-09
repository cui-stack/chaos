import {
	navigateTo
} from "@/chaos/functions/Uni";
import {
	doLogin
} from "@/utils/login";
import {
	host,
	noLog
} from "@/utils/config";

const noConsole = noLog();
export async function post(url, params) {
	if (!noConsole) {
		console.log(
			`${new Date().toLocaleString()}【 M=${url} 】P=${JSON.stringify(params)}`
		);
	}
	const res = await uni.request({
		url: `${host()}/${url}`,
		data: JSON.stringify(params),
		header: {
			"content-type": "application/json",
			token: uni.getStorageSync("token")
		},
		method: "POST"
	});
	const {
		statusCode,
		errMsg,
		data: resData
	} = res[1];
	
	if (statusCode != 200) {
		throw new Error(`网络请求错误，状态码${statusCode}`);
	}
	if (!noConsole) {
		console.log(
			`${new Date().toLocaleString()}【 M=${url} 】【接口响应：】`,
			resData
		);
	}
	const {
		msg,
		code,
		data,
		page,
		mark,
		pages
	} = resData;
	if (code === 200) {
		return (
			data ||
			page ||
			(pages && {
				pages,
				mark
			}) ||
			false
		);
	}
	if (code === 201) {
		uni.setStorageSync("token", msg);
		return post(url, params);
	}
	if (code === 401) {
		await doLogin({}, true);
		return post(url, params);
	}
	if (code === 412) {
		navigateTo("authorize");
	}
	uni.showToast({
		title: `${msg}~` || code,
		icon: "none",
		mask: true
	});
}
