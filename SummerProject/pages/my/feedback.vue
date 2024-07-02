<template>
	<view class="container">
		<view class="header">
			<text class="title">向开发者反馈</text>
			<image class="icon" src="/static/意见反馈.png"></image>
		</view>
		<view class="separator"></view>
		<textarea class="feedback-textarea" placeholder="请输入反馈信息" v-model="feedbackText" maxlength="300">
    </textarea>
		<view class="text-count">
			已输入{{ feedbackText.length }}/300
		</view>
		<button class="submit-btn" @click="submitFeedback">发送</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				feedbackText: ''
			};
		},
		methods: {
			submitFeedback() {
				if (!this.feedbackText) {
					uni.showToast({
						title: '请输入反馈信息',
						icon: 'none'
					});
					return;
				}
				uni.request({
					url: 'http://192.168.1.54:8082/postfeedback',
					method: 'POST',
					data: {
						content: this.feedbackText
					},
					success: (res) => {
						console.log(res)
						if (res.data.code === 200) {
							uni.showToast({
								title: '反馈成功',
								icon: 'success'
							});
							this.feedbackText = '';
						} else {
							uni.showToast({
								title: res.data.message,
								icon: 'none'
							});
						}
					},
					fail: (err) => {
						uni.showToast({
							title: '请求失败，请稍后重试',
							icon: 'none'
						});
						console.error('Request failed', err);
					}
				});
			}
		}
	}
</script>

<style scoped>
	.container {
		padding: 20rpx;
	}

	.header {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-bottom: 20rpx;
		font-size: 60rpx;
	}

	.title {
		font-size: 32rpx;
		font-weight: bold;
	}

	.icon {
		width: 50rpx;
		height: 50rpx;
		margin-left: 10rpx;
	}

	.separator {
		height: 1px;
		background-color: #ddd;
		margin: 20rpx 0;
	}

	.feedback-textarea {
		width: 100%;
		height: 200rpx;
		border: 1px solid #ddd;
		padding: 10rpx;
		border-radius: 8rpx;
		margin-bottom: 10rpx;
	}

	.text-count {
		text-align: right;
		color: #888;
		font-size: 24rpx;
	}

	.submit-btn {
		background-color: #32CD32;
		color: white;
		padding: 20rpx;
		text-align: center;
		border-radius: 8rpx;
	}
</style>