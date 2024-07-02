<template>
	<view class="container">
		<u-line color="#9e9e9e" />
		<view class="title">学生请假详情</view>
		<u-line color="#030303" />
		<view class="number">
			<view class="title1">请假人：</view>
		</view>
		<u-line color="#9e9e9e" />
		<view v-for="(person, index) in people" :key="index" class="u-flex user-box u-p-l-20 u-p-t-30 u-p-b-30"
			@click="k(person)">
			<view class="dispose">{{ person.student_name }}</view>
			<!-- <view class="dispose">{{ people[index] }}</view> -->
			<view class="u-m-l-10 u-p-10">
				<u-icon name="arrow-right" color="#969799" size="28"></u-icon>
			</view>
		</view>
		<u-line color="#9e9e9e" />
	</view>
</template>

<script>
	export default {
		data() {
			return {
				people: [] // 定义一个数组来存储请假人信息
			}
		},
		onReady() {
			uni.setNavigationBarTitle({
				title: '批假'
			});
			// 假设这里从后端获取数据
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
				}
			});
			const value1 = uni.getStorageSync("panduan1")
			if (value1) {
				uni.setStorageSync("panduan1", 0)
				uni.reLaunch({
					url: this.$route.fullPath // 重新加载当前页面
				});
				
			}

		},

		methods: {
			k(person) {
				uni.setStorageSync("studentinfo", person)
				uni.navigateTo({
					url: '/pages/teFunction/leaveDetail1' // 传递person的ID或其他标识符
				});
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

	.title1 {
		font-size: 30rpx;
		color: #000000;
	}

	.number1 {
		font-size: 30rpx;
		color: #888888;
	}

	.user-box {
		justify-content: space-between;
		align-items: center;

	}
</style>