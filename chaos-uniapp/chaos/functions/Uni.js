export async function login() {
	const response = await uni.login({
		provider: 'weixin'
	});
	const {code,errMsg}=response[1];
	if (errMsg === "login:ok") return code;
}

export function userProfile() {
	return new Promise(reslove => {
		uni.getUserProfile({
			desc: "用于完善会员资料",
			success: res => reslove(res.userInfo)
		});
	});
}

export function location() {
	return new Promise(reslove => {
		uni.getLocation({
			type: "gcj02",
			success: res => reslove(res)
		});
	});
}

export function get(key) {
	return uni.getStorageSync(key);
}

export function successToast(title) {
	uni.showToast({
		title
	});
}

export function failToast(title) {
	uni.showToast({
		icon: "none",
		image: "/static/images/fail.png",
		title
	});
}

export function infoToast(title) {
	uni.showToast({
		icon: "none",
		title
	});
}

export function showLoading() {
	uni.showLoading({
		title: "加载中"
	});
}

export function hideLoading() {
	uni.hideLoading();
}

export function navigateTo(url, params = "") {
	uni.navigateTo({
		url: path(url) + (params && "?") + params
	});
}

export function navigateBack(delta = 1) {
	uni.navigateBack({
		delta
	});
}

export function redirectTo(url, params = "") {
	uni.redirectTo({
		url: path(url) + (params && "?") + params
	});
}

export function reLaunch(url) {
	uni.reLaunch({
		url: path(url)
	});
}

export function path(url) {
	return `/pages/${url}/index`;
}

export function barTitle(title) {
	uni.setNavigationBarTitle({
		title
	});
}

export function message() {
	return new Promise((reslove, reject) => {
		uni.requestSubscribeMessage({
			tmplIds: [""],
			success: res => reslove(res)
		});
	});
}

export function getParams() {
	return this.$route.params;
}
