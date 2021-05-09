<template>
	<view class="page">
		<web-view :src="url" />
	</view>
</template>

<script>
	import {
		one
	} from '@/chaos/functions/Data';
	import {
		path,
		get,
		getParams
	} from '@/chaos/functions/Uni';
	import {
		doLogin
	} from '@/utils/login';

	export default {
		data() {
			return {
				mu: "",
				url: "",
			};
		},
		async created({
			mu,
			...rest
		}) {
			doLogin({ ...rest});
			const {
				url
			} = await one("info", mu);
			this.mu = mu;
			this.url = url;
		},
		onShareAppMessage() {
			title: "", path: `${path("webview")}?referrer=${get("mu")}&mu=${this.mu}`
		},
		methods: {

		},
	}

</script>

<style lang="stylus" scoped>

</style>
