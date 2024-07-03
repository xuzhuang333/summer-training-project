<template>
	<view>
		<view v-if="leibie">
			<view class="recommend">
				<view class="recommend1">推荐功能</view>
			</view>
			<view class="wrap">
				<u-row gutter="16">
					<u-col span="3">
						<view class="demo-item" @click="schedule()">
							<image class="icon" src="/static/课表.png" mode="aspectFit"></image>
							<view class="text">课表查看</view>
						</view>
					</u-col>
					<u-col span="3">
						<view class="demo-item" @click="qingjiaDetail()">
							<image class="icon" src="/static/请假信息.png" mode="aspectFit"></image>
							<view class="text">请假信息</view>
						</view>
					</u-col>
					<u-col span="3">
						<view class="demo-item">
							<image class="icon" src="/static/校园环境.png" mode="aspectFit"></image>
							<view class="text">校园环境</view>
						</view>
					</u-col>
					<u-col span="3">
						<view class="demo-item" @click="volunteer()">
							<image class="icon" src="/static/志愿者.png" mode="aspectFit"></image>
							<view class="text">志愿查询</view>
						</view>
					</u-col>
				</u-row>
				<u-row gutter="16">
					<u-col span="3">
						<view class="demo-item" @click="bus()">
							<image class="icon" src="/static/校车管理.png" mode="aspectFit"></image>
							<view class="text">校车时刻</view>
						</view>
					</u-col>
					<u-col span="3">
						<view class="demo-item" @click="xuanke()">
							<image class="icon" src="/static/选课系统.png" mode="aspectFit"></image>
							<view class="text">选课管理</view>
						</view>
					</u-col>
					<u-col span="3">
						<view class="demo-item" @click="chake()">
							<image class="icon" src="/static/查一查.png" mode="aspectFit"></image>
							<view class="text">查课管理</view>
						</view>
					</u-col>
					<u-col span="3">
						<view @click="bedroom()">
							<view class="demo-item">
								<image class="icon" src="/static/寝室学生查询.png" mode="aspectFit"></image>
								<view class="text">寝室查询</view>
							</view>
						</view>
					</u-col>
				</u-row>
				<u-row gutter="16">
					<u-col span="3">
						<view @click="myClass()">
							<view class="demo-item">
								<image class="icon" src="/static/班级.png" mode="aspectFit"></image>
								<view class="text">我的班级</view>
							</view>
						</view>
					</u-col>
					<u-col span="3">
						<view class="demo-item" @click="life()">
							<image class="icon" src="/static/生活服务.png" mode="aspectFit"></image>
							<view class="text">生活服务</view>
						</view>
					</u-col>
				</u-row>
			</view>
			<view class="moreFunction">更多功能</view>
			<view class="wrap1">
				<u-row gutter="16">
					<u-col span="6">
						<view class="demo-item1" @click="library()">
							<image class="icon1" src="/static/图书馆 (1).png" mode="aspectFit"></image>
							<view class="text1">图书馆</view>
						</view>
					</u-col>
					<u-col span="6">
						<view class="demo-item1" @click="leave()">
							<image class="icon1" src="/static/请假.png" mode="aspectFit"></image>
							<view class="text1">请假</view>
						</view>
					</u-col>
				</u-row>
			</view>
			<view class="wrap1">
				<u-row gutter="16">
					<u-col span="6">
						<view class="demo-item1">
							<image class="icon1" src="/static/icon - 学校信息.png" mode="aspectFit"></image>
							<view class="text1">学校信息查询</view>
						</view>
					</u-col>

				</u-row>
			</view>
		</view>
		<view v-else>
			<view class="recommend">
				<view class="recommend1">管理项目</view>
			</view>
			<view class="wrap1">
				<u-row gutter="16">
					<u-col span="6">
						<view class="demo-item1" @click="studentData()">
							<image class="icon1" src="/static/学生数据分析.png" mode="aspectFit"></image>
							<view class="text1">学生数据</view>
						</view>
					</u-col>
					<u-col span="6">
						<view class="demo-item1" @click="leaveAbsence()">
							<image class="icon1" src="/static/icon-审批假期.png" mode="aspectFit"></image>
							<view class="text1">批假</view>
						</view>
					</u-col>
				</u-row>
				<u-row gutter="16">
					<u-col span="6">
						<view class="demo-item1" @click="attendance()">
							<image class="icon1" src="/static/考勤管理.png" mode="aspectFit"></image>
							<view class="text1">考勤管理</view>
						</view>
					</u-col>
					<u-col span="6">
						<view class="demo-item1" @click="publish()">
							<image class="icon1" src="/static/信息发布_1.png" mode="aspectFit"></image>
							<view class="text1">信息发布</view>
						</view>
					</u-col>
				</u-row>
				<u-row gutter="16">
					<u-col span="6">
						<view class="demo-item1" @click="book()">
							<image class="icon1" src="/static/图书馆.png" mode="aspectFit"></image>
							<view class="text1">图书馆</view>
						</view>
					</u-col>
					<u-col span="6">
						<view class="demo-item1" @click="bus()">
							<image class="icon1" src="/static/校车时刻.png" mode="aspectFit"></image>
							<view class="text1">校车时刻</view>
						</view>
					</u-col>
				</u-row>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				leibie: true,
			}
		},
		methods: {
			k() {
				uni.navigateTo({
					url: '/pages/index/index'
				})
			},
			schedule() {
				uni.navigateTo({
					url: '/pages/function/schedule'
				})
			},
			library() {
				uni.navigateTo({
					url: '/pages/teFunction/book'
				})
			},
			qingjiaDetail(){
				uni.navigateTo({
					url: '/pages/function/cancelVacation'
				})
			},
			volunteer() {
				uni.navigateTo({
					url: '/pages/function/volunteer'
				})
			},
			bedroom() {
				uni.navigateTo({
					url: '/pages/function/bedroom'
				})
			},
			myClass() {
				uni.navigateTo({
					url: '/pages/function/class'
				})
			},
			leave() {
				uni.navigateTo({
					url: '/pages/function/leave'
				})
			},
			life() {
				uni.navigateTo({
					url: '/pages/function/life'
				})
			},
			xuanke() {
				uni.navigateTo({
					url: '/pages/function/selectClass'
				})
			},
			chake() {
				uni.navigateTo({
					url: '/pages/function/checkClass'
				})
			},
			bus() {
				uni.navigateTo({
					url: '/pages/teFunction/schoolBus'
				})
			},
			publish() {
				uni.navigateTo({
					url: '/pages/teFunction/publish'
				})
			},
			studentData() {

			},
			leaveAbsence() {
				uni.navigateTo({
					url: '/pages/teFunction/leaveAbsence'
				})
			},
			attendance() {

			},
			book() {
				uni.navigateTo({
					url: '/pages/teFunction/book'
				})
			}


		},
		onShow() {
			//console.log("页面展示了")
			const value = uni.getStorageSync("userjob")
			if (value) {
				this.leibie = false;
			}
		},
	}
</script>

<style>
	.recommend {
		margin-top: 40rpx;
		position: relative;
		margin-bottom: 5rpx;
	}

	.recommend::before,
	.recommend::after {
		content: '';
		position: absolute;
		left: 0;
		right: 0;
		height: 1px;
		background-color: #9e9e9e;
	}

	.recommend::before {
		top: -30rpx;
	}

	.recommend::after {
		bottom: -30rpx;
	}

	.recommend1 {
		margin-left: 30rpx;
		font-weight: bold;
		font-size: 30rpx
	}

	.wrap {
		padding: 24rpx;
	}

	.u-row {
		margin: 40rpx 0;
	}

	.u-col {
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.demo-item {
		text-align: center;
	}

	.icon {
		width: 80rpx;
		height: 80rpx;
		margin-bottom: 10rpx;
	}

	.text {
		font-size: 20rpx;
		color: #333;
	}

	.moreFunction {
		margin-left: 30rpx;
		margin-bottom: 20rpx;
		font-weight: bold;
		font-size: 30rpx
	}

	.demo-item1 {
		display: flex;
		align-items: center;
		margin-left: 20rpx;
	}

	.icon1 {
		width: 80rpx;
		height: 80rpx;
		margin-right: 10rpx;
	}

	.text1 {
		font-size: 30rpx;
		color: #333;
		flex: 1;
	}
</style>