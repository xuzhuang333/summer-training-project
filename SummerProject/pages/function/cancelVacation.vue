<template>
	<view class="container">
		<view class="header">
			<text class="back-button" @click="goBack">返回</text>
			<text class="header-title">请假信息</text>
		</view>

		<view class="content">
			<template v-if="leaves && leaves.length">
				<view v-for="(leave, index) in leaves" :key="index">
					<view class="section-title">请假信息</view>

					<view class="form-item">
						<text class="label">请假类型</text>
						<view class="picker">
							{{ leave.type }}
						</view>
					</view>
					<view class="form-item">
						<text class="label">请假时间</text>
						<view class="input">{{ leave.begin_data }}</view>
					</view>
					<view class="form-item">
						<text class="label">状态</text>
						<view class="status">
							<text v-if="zhuangtai(leave)=='0'" class="status-text">待批准</text>
							<button v-else-if="zhuangtai(leave)=='1'" type="default" class="approved-button"
								@click="xiaojia(leave, show), show=true">销假</button>
							<text v-else-if="zhuangtai(leave)=='3'" class="status-text"
								style="color: red ;">请假申请未通过</text>
						</view>
					</view>
				</view>
			</template>
			<template v-else>
				<view class="no-leaves">无请假信息</view>
			</template>
			<u-popup v-if="show" mode="bottom" v-model="show">
				<view class="content">
					<scroll-view scroll-y="true" style="height: 700rpx;">
						<view class="container">
							<view class="header">
								<text class="header-title">销假</text>
							</view>


							<!-- 学号 -->
							<view class="item">
								<text class="dot red"></text>
								<text class="label">学生学号</text>
								<picker mode="selector">
									<view class="picker">
										100001
									</view>
								</picker>
							</view>

							<view class="content">
								<!-- 假期类别 -->
								<view class="item">
									<text class="dot red"></text>
									<text class="label">请假类别</text>
									<view class="picker">
										{{leave1.type}}
									</view>
								</view>
							</view>

							<!-- 开始时间 -->
							<view class="item">
								<text class="dot orange"></text>
								<text class="label">开始时间</text>
								<view class="picker">
									{{leave1.begin_data}}
								</view>
							</view>

							<!-- 结束时间 -->
							<view class="item">
								<text class="label">结束时间</text>
								<view class="picker">
									{{leave1.end_data}}
								</view>
							</view>

							<!-- 学院 -->
							<view class="item">
								<text class="dot red"></text>
								<text class="label">学院</text>
								<view class="picker">
									{{leave1.college}}
								</view>
							</view>

							<!-- 联系电话 -->
							<view class="item">
								<text class="dot green"></text>
								<text class="label">联系电话</text>
								<view class="picker">
									{{leave1.parentPhone}}
								</view>
							</view>
						</view>

					</scroll-view>
					<view class="confrim-btn">
						<u-button @click="xiaojia2(leave1, show), show=false">销假</u-button>
					</view>
				</view>
			</u-popup>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				student_id: '',
				leaves: [],
				show: false,
				leave1: {}
			};
		},
		onLoad() {
			this.fetchLeaveData()
		},
		methods: {
			async fetchLeaveData() {
				const value = uni.getStorageSync("userid");
				this.student_id = value;
				try {
					uni.request({
						// url: `http://192.168.1.82:8080/destroyinfo/${this.studentId}`,
						url: `http://192.168.1.54:8082/destroyinfo/${this.student_id}`,
						method: 'GET',
						success: (res) => {
							console.log(res)
							if (res.data.code == 200) {
								this.leaves = res.data.result
							} else {
								this.$u.toast("请求失败，请刷新")
							}
						}
					});
				} catch (error) {
					console.error(error);
					uni.showToast({
						title: '获取请假信息失败',
						icon: 'none'
					});
				}
			},
			zhuangtai(leave) {
				return leave.state;
			},
			xiaojia(leave, show) {
				this.leave1 = leave
			},
			xiaojia2(leave1, show) {
				uni.request({
					// url: "http://192.168.1.82:8080/destroy/" + this.leave1.vacationId,
					url: "http://192.168.1.54:8082/destroy/" + this.leave1.vacation_id,
					method: 'GET',
					success: (res) => {
						console.log(res)
						if (res.data.code == 200) {
							this.$u.toast("销假成功")

						} else {
							this.$u.toast("销假失败")
						}
					}
				})
				uni.reLaunch({
					url: this.$route.fullPath // 重新加载当前页面
				});
			},
			goBack() {
				uni.switchTab({
					url:'/pages/main/function'
				})
			}
		}
	}
</script>

<style>
	.container {
		display: flex;
		flex-direction: column;
	}

	.header {
		background-color: #f8f8f8;
		padding: 20rpx;
		display: flex;
		align-items: center;
	}

	.header-title {
		font-size: 36rpx;
		text-align: center;
		flex: 1;
	}

	.back-button {
		font-size: 30rpx;
		color: #007aff;
		cursor: pointer;
	}

	.section-title {
		padding: 20rpx;
		background-color: #f0f0f0;
		font-size: 32rpx;
	}

	.content {
		padding: 20rpx;
	}

	.form-item {
		display: flex;
		align-items: center;
		padding: 20rpx 0;
		border-bottom: 1px solid #e0e0e0;
	}

	.label {
		font-size: 30rpx;
		flex: 1;
	}

	.picker {
		color: #999;
		min-width: 120rpx;
	}

	.input {
		flex: 1;
		color: #999;
		text-align: right;
	}

	.status {
		display: flex;
		align-items: center;
	}

	.status-text {
		color: #999;
	}

	.approved-button {
		color: white;
		background-color: green;
		border-radius: 10rpx;
		padding: 5rpx 10rpx;
	}

	.no-leaves {
		text-align: center;
		color: #999;
		font-size: 32rpx;
		margin-top: 50rpx;
	}

	.item {
		display: flex;
		align-items: center;
		padding: 20rpx 0;
		border-bottom: 1px solid #f0f0f0;
	}
</style>