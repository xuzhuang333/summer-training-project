<template>
	<view class="container">
		<view class="section">
			<view class="section-title">
				<text class="dot">•</text>
				<text class="section-title-text">水电费</text>
			</view>
			<view class="card">
				<view class="card-item">
					<text class="icon">📕</text>
					<text class="label">余额</text>
					<text class="value">¥{{shenghuo.money}}</text>
				</view>
				<view class="card-item">
					<text class="icon">⚡</text>
					<text class="label">电费补贴余额</text>
					<text class="value">¥{{shenghuo.electric}}</text>
				</view>
				<view class="card-item">
					<text class="icon">🏆</text>
					<text class="label">水费补贴余额</text>
					<text class="value">¥{{shenghuo.water}}</text>
				</view>
			</view>
		</view>

		<view class="section">
			<view class="section-title">
				<text class="dot">•</text>
				<text class="section-title-text">一卡通</text>
			</view>
			<view class="card">
				<view class="card-item">
					<text class="icon">📕</text>
					<text class="label">余额</text>
					<text class="value">¥{{shenghuo.livingCost}}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
	            shenghuo:{
					money:"",
					electric:"",
					water:"",
					livingCost:"",
				},
			
			};
		},
		onShow() {
			const dormitory_number = uni.getStorageSync("userinfo")
			uni.request({
				url:"http://192.168.1.82:8080/life/100001",
				success:(res)=>{
					console.log(res)
					if(res.data.code==200){
						this.shenghuo=res.data.result;
					}else{
						console.log("登录失败");
					}
				}
				
			})
		}
	};
</script>

<style scoped>
	.container {
		padding: 20rpx;
	}

	.section {
		margin-bottom: 40rpx;
	}

	.section-title {
		display: flex;
		align-items: center;
		margin-bottom: 20rpx;
	}

	.dot {
		color: #007aff;
		font-size: 24rpx;
		margin-right: 10rpx;
	}

	.section-title-text {
		font-size: 28rpx;
	}

	.card {
		background-color: #fff;
		border-radius: 10rpx;
		box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
	}

	.card-item {
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding: 20rpx;
		border-bottom: 1px solid #f5f5f5;
	}

	.card-item:last-child {
		border-bottom: none;
	}

	.icon {
		font-size: 30rpx;
		margin-right: 20rpx;
	}

	.label {
		flex: 1;
		font-size: 28rpx;
	}

	.value {
		font-size: 28rpx;
		color: #333;
	}
</style>