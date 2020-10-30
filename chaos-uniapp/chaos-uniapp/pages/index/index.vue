<template>
	<view class="page">
	    <view class="page_head">
	        <image src='/static/images/logo.png'></image>
	    </view>
	</view>
</template>

<script>
	import Data from '../../utils/data.js'
	import login from '../../utils/login.js'

	export default {
		name: 'index',
		data() {
			return {
				list: []
			}
		},
		components: {
		},
		onLoad(e) {
		    uni.setStorageSync('referrer', e.referrer)
		    uni.setStorageSync('source', e.source)
		    //login.login()
		    this.init()
		},
		onShareAppMessage (e) {
			return {
				title: "",
				path: page.path("index") + "?referrer=" + store.mu() + "&source=首页"
			}
		},
		methods: {
			
			init () {
				// Data.list('chaos_info', {}, (res) => {
				// 	this.list=res
				// })
			},
			
		}
	}
</script>

<style lang="scss">
	
	$search_background_color: #F8F8F7;
	
	.page {
	  @include padding(38rpx);
	
	  Image {
	    width: 120rpx;
	    height: 120rpx;
	  }
	
	  .page_head {
	    text-align: center;
	    margin: 104rpx 0 60rpx;
	  }
	
	
	}

</style>
