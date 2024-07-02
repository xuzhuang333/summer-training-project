<template>
	<view class="container">
		<u-line color=#9e9e9e />
		<view class="title">学生请假申请</view>
		<u-line color=#030303 />
		<view class="number">

			<view class="title1">请假条数</view>
			<view class="number1">{{number}}</view>

		</view>
		<u-line color=#9e9e9e />
		<view class="u-flex user-box u-p-l-20 u-p-t-30 u-p-b-80" @click="k()">
			<view class="dispose">立即处理</view>
			<view class="u-m-l-10 u-p-10">
				<u-icon  name="arrow-right" color="#969799" size="28" ></u-icon>
			</view>
		</view>
		<u-line color=#9e9e9e />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				number:'',
				people:[],
			}
		},
		onReady() {
			uni.setNavigationBarTitle({
				title: '批假'
			});
			const value = uni.getStorageSync("usercollege")
			uni.request({
				// url: 'http://192.168.1.39:9090/agreehome',
				url: 'http://192.168.1.54:8082/agreehome',
				method: 'POST',
				data: {
					college: value,
				},
				success: (res) => {
					console.log(value),
					console.log("22222");
					console.log(res);
					this.people = res.data.result;
					this.number=this.people.length;
				}
			});
			const value1 = uni.getStorageSync("panduan2")
			if (value1) {
				uni.setStorageSync("panduan2", 0)
				uni.reLaunch({
					url: this.$route.fullPath // 重新加载当前页面
				});
				
			}
		},
		methods: {
			k(){
				uni.navigateTo({
					url: '/pages/teFunction/leaveDetail'
				})
			}
		}
	}
</script>

<style scoped>
	.container {
		flex-direction: column;
		justify-content: space-between;
	}

	.title {
		font-size: 40rpx;
		margin-bottom: 30rpx;
		margin-top: 30rpx;
		margin-left: 20rpx;
	}
	.number {
		display: flex;
		  justify-content: space-between;
		  align-items: center;
		  margin-bottom: 30rpx;
		  margin-top: 50rpx;
		  margin-left: 20rpx;
		  margin-right: 20rpx;
	}
	.title1{
		font-size:30rpx;
		color: #000000;
	}
	.number1{
		font-size:30rpx;
		color: #888888;
	}
	.user-box{
		justify-content: space-between;
		align-items: center;
		margin-bottom: 300rpx;
	}
	
</style>