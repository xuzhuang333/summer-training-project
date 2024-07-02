<template>
	<view class="content"  >
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<text class="title">{{title}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: 'Hello',
			}
		},
		onLoad() {

		},
		methods: {

		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		//height:100vh
	}
	
	page{
		background-color: #55ffff;
	}

	.logo {
		height: 50rpx; /* 图标高度为屏幕高度的10% */
		width: 50rpx; /* 图标宽度为屏幕宽度的20% */
		margin-top: 10vh; /* 图标距离顶部10%高度的位置 */
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 10rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 10rpx;
		color: #0055ff;
	}
</style>
