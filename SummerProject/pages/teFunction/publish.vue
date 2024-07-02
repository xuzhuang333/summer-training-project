<template>
	<view class="container">
		<view class="header">
			<text class="title">信息发布</text>
		</view>
		<view class="form">
			<view class="form-item">
				<text class="label">信息标题</text>
				<input class="input" type="text" placeholder="请输入信息标题" v-model="title" />
			</view>
			<view class="form-item">
				<textarea class="textarea" placeholder="请输入公布内容" v-model="content" maxlength="20" />
				<view class="char-count">{{ content.length }}/20</view>
			</view>
		</view>
		<button class="submit-button" @click="submitInfo">公布</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				title: '',
				content: ''
			};
		},
		methods: {
			submitInfo() {
				uni.request({
					url: "http://192.168.1.54:8082/postNotice",
					method: 'POST',
					header: {
						'Content-Type': 'application/json'
					},
					data: {
						title: this.title,
						content: this.content
					},
					success: (res) => {
						console.log(res)
						if (res.data.code === 200) {
							uni.showToast({
								title: '发布成功',
								icon: 'success'
							});
						} else {
							uni.showToast({
								title: '发布失败',
								icon: 'none'
							});
						}
					},
					fail: (err) => {
						uni.showToast({
							title: '请求失败',
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
		background-color: #fff;
		height: 100vh;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}

	.header {
		display: flex;
		justify-content: center;
		align-items: center;
		padding: 20rpx 0;
		border-bottom: 1rpx solid #f0f0f0;
	}

	.title {
		font-size: 50rpx;
		font-weight: bold;
	}

	.form {
		flex: 1;
		margin: 20rpx 0;
	}

	.form-item {
		margin-bottom: 20rpx;
		padding: 10rpx 0;
		border-bottom: 1rpx solid #f0f0f0;
		display: flex;
		align-items: center;
	}

	.label {
		width: 180rpx;
		font-size: 35rpx;
		color: #333;
		margin-left: 30rpx;
	}

	.input {
		flex: 1;
		padding: 10rpx;
		border: none;
		font-size: 30rpx;
	}

	.textarea {
		width: 100%;
		height: 200rpx;
		border: none;
		padding: 30rpx;
		font-size: 30rpx;
	}

	.char-count {
		text-align: right;
		color: #999;
	}

	.submit-button {
		width: 80%;
		height: 100rpx;
		background-color: #4ec346;
		color: #fff;
		font-size: 40rpx;
		text-align: center;
		line-height: 44px;
		border-radius: 22px;
		margin-bottom: 200rpx;
	}
</style>