<template>
	<view class="page">

	</view>
</template>

<script>
	import {
		list
	} from '@/chaos/functions/Data';
	import {
		getParams,
		path,
		get
	} from '@/chaos/functions/Uni';
	import {
		doLogin
	} from '@/utils/login';

	export default {
		data() {
			return {
				loading: true,
				list: [],

			}
		},
		async created(props = {}) {
			this.list = await list("info");
			this.loading = false;
			doLogin(props, true);
		},
		methods: {

		}
	}
</script>

<style lang="stylus" scoped>

</style>
