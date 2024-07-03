<template>
	<view class="container">
		<navigation-bar left-text="返回" title="志愿时长" />
		<scroll-view scroll-y="true" class="scroll-view">
			<view class="card">
				<text class="greeting">亲爱的 {{ studentName }} 同学</text>
			</view>
			<view class="card">
				<text class="result-title">查 询 结 果</text>
				<view class="result-content">
					<view class="result-item">
						<text class="dot red-dot">•</text>
						<text class="label">志愿总时长：</text>
						<text class="value">{{ volunteerHours }}h</text>
					</view>
				</view>
			</view>
			<view class="no-record">
				<text>没有记录可以尝试刷新</text>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				studentName: '赵凯', // 学生姓名
				volunteerHours: 0, // 志愿时长
				id: '100001' // 学号
			};
		},
		methods: {
			fetchData() {
				const value=uni.getStorageSync("userid");
				this.id=value;
				const value1=uni.getStorageSync("username");
				this.studentName=value1;
				uni.request({
					url: `http://192.168.1.54:8082/volunteer/${this.id}`, // 后端接口地址，并在路径中插入id
					method: 'GET',
					success: (res) => {
						if (res.statusCode === 200) {
							console.log(res);
							this.volunteerHours = res.data;
						} else {
							uni.showToast({
								title: '获取数据失败',
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
		},
		// 页面加载时获取数据
		mounted() {
			this.fetchData();
		}
	};
</script>

<style scoped>
	.container {
		flex: 1;
		background-color: #f5f5f5;
	}

	.navigation-bar {
		display: flex;
		justify-content: space-between;
		align-items: center;
		height: 44px;
		background-color: #007aff;
		color: white;
		padding: 0 16px;
	}

	.scroll-view {
		flex: 1;
		padding: 16px;
	}

	.card {
		background-color: white;
		border-radius: 8px;
		padding: 16px;
		margin-bottom: 16px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}

	.greeting,
	.result-title {
		font-size: 16px;
		font-weight: bold;
		margin-bottom: 8px;
	}

	.result-content {
		padding-top: 8px;
	}

	.result-item {
		display: flex;
		align-items: center;
		margin-bottom: 8px;
	}

	.dot {
		font-size: 24px;
		margin-right: 8px;
	}

	.red-dot {
		color: red;
	}

	.label {
		font-size: 14px;
	}

	.value {
		font-size: 14px;
		font-weight: bold;
		color: #007aff;
	}

	.no-record {
		text-align: center;
		color: #999;
		margin-top: 16px;
		font-size: 14px;
	}
</style>