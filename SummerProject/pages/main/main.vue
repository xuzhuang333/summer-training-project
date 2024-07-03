<template>
	<view>
		<view v-if="leibie">
			<view class="wrap">
				<u-swiper :list="list"></u-swiper>
			</view>
			<view class="mainFunction">主要功能</view>
			<view class="wrap1">
				<u-row gutter="16">
					<u-col span="5">
						<u-button class="kebiao" @click="kebiao()">课表</u-button>
					</u-col>
					<u-col span="1">
						<view></view>
					</u-col>
					<u-col span="5">
						<u-button class="xuanke" @click="xuanke()">选课</u-button>
					</u-col>
				</u-row>
			</view>
			<view class="wrap1">
				<u-row gutter="16">
					<u-col span="5">
						<u-button class="peiyangfangan" @click="qingjia()">请假</u-button>
					</u-col>
					<u-col span="1">
						<view></view>
					</u-col>
					<u-col span="5">
						<u-button class="xiaoyuanhuanjing" @click="xiaoyuanhuanjing()">校园环境</u-button>
					</u-col>
				</u-row>
			</view>
			<view class="wrap1">
				<u-row gutter="16">
					<u-col span="5">
						<u-button class="myClass" @click="myClass()">我的班级</u-button>
					</u-col>
					<u-col span="1">
						<view></view>
					</u-col>
					<u-col span="5">
						<u-button class="qinshi" @click="qinshi()">寝室查询</u-button>
					</u-col>
				</u-row>
			</view>
			<view class="recommend">推荐内容</view>
			<view class="movie-list">
				<u-grid :col="1">
					<u-grid-item v-for="(nt,index) in notices" :key="nt.noticeId">
						<view class="notices">
							<view class="title">{{nt.noticeTitle}}</view>
							<view class="time">{{nt.time}}</view>
							<view class="content">{{nt.content}}</view>
						</view>
					</u-grid-item>
				</u-grid>
			</view>
		</view>
		<view v-else>
			<view class="wrap">
				<u-swiper :list="list"></u-swiper>
			</view>
			<view class="recommend">推荐内容</view>
			<view class="movie-list">
				<u-grid :col="1">
					<u-grid-item v-for="(nt,index) in notices" :key="nt.noticeId">
						<view class="notices">
							<view class="title">{{nt.noticeTitle}}</view>
							<view class="time">{{nt.time}}</view>
							<view class="content">{{nt.content}}</view>
						</view>
					</u-grid-item>
				</u-grid>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list: [{
						image: '/static/a1.jpg',
						title: ''
					},
					{
						image: '/static/a2.jpg',
						title: ''
					},
					{
						image: 'static/a3.jpg',
						title: ''
					}
				],
				scrollTop: 0,
				notices: [],
				leibie: true
			}
		},

		methods: {
			onLoad() {
				uni.request({
					// url: "http://192.168.1.82:8080/notices",
					url: "http://192.168.1.54:8082/notices",
					method: "GET",
					success: (res) => {
						console.log(res)
						this.notices = res.data.result;
					},
				})
			},
			onShow() {
				console.log("页面展示了")
				const value = uni.getStorageSync("userjob")
				if (value) {
					this.leibie = false;
				}
			},
			kebiao() {
				uni.navigateTo({
					url: '/pages/function/schedule'
				})
			},
			xuanke(){
				uni.navigateTo({
					url: '/pages/function/selectClass'
				})
			},
			qingjia(){
				uni.navigateTo({
					url: '/pages/function/leave'
				})
			},
			xiaoyuanhuanjing(){
				
			},
			myClass(){
				uni.navigateTo({
					url: '/pages/function/class'
				})
			},
			qinshi(){
				uni.navigateTo({
					url: '/pages/function/bedroom'
				})
			}

		}
	}
</script>

<style>
	.wrap {
		padding: 10rpx;
	}

	.wrap1 {
		margin-left: 50rpx;
		margin-right: 50rpx;
	}


	.image-item {
		margin-left: 50rpx;
		margin-right: 50rpx;
	}

	.mainFunction {
		margin-left: 50rpx;
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		font-size: 28rpx;
		color: #918a8a;
	}

	.recommend {
		margin-left: 50rpx;
		margin-top: 20rpx;
		margin-bottom: 20rpx;
		font-size: 28rpx;
		color: #918a8a;
	}

	.u-row {
		margin: 20rpx 0;
	}

	.u-col {
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.kebiao {
		width: 450rpx;
		height: 80rpx;
		border-radius: 0rpx;
		font-size: 25rpx;
		display: flex;
		align-items: center;
		/* 交叉轴居中对齐 */
		justify-content: center;
		/* 主轴居中对齐 */
		cursor: pointer;
		background-color: #f5f5f5;
		color: #6d6d6d;
		margin-bottom: 10rpx;

	}

	.xuanke {
		width: 450rpx;
		height: 80rpx;
		border-radius: 0rpx;
		font-size: 25rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		cursor: pointer;
		background-color: #18bc98;
		color: white;
		margin-bottom: 10rpx;
	}

	.peiyangfangan {
		width: 450rpx;
		height: 80rpx;
		border-radius: 0rpx;
		font-size: 25rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		cursor: pointer;
		background-color: #ffffff;
		color: #6d6d6d;
		margin-bottom: 10rpx;
	}

	.xiaoyuanhuanjing {
		width: 450rpx;
		height: 80rpx;
		border-radius: 0rpx;
		font-size: 25rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		cursor: pointer;
		background-color: #ffffff;
		color: #18bc98;
		margin-bottom: 10rpx;
	}

	.myClass {
		width: 450rpx;
		height: 80rpx;
		border-radius: 0rpx;
		font-size: 25rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		cursor: pointer;
		background-color: #ffffff;
		color: #18bc98;
		margin-bottom: 10rpx;
	}

	.qinshi {
		width: 450rpx;
		height: 80rpx;
		border-radius: 0rpx;
		font-size: 25rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		cursor: pointer;
		background-color: #ffffff;
		color: #18bc98;
		margin-bottom: 10rpx;
	}

	.title {
		font-size: 30rpx;
		position: absolute;
		top: -30rpx;
		left: 30rpx;
	}

	.time {
		font-size: 15rpx;
		position: absolute;
		bottom: 0rpx;
		right: 10rpx;
	}

	.content {
		font-size: 25rpx;
		position: absolute;
		bottom: 10rpx;
		left: 30rpx;
	}

	.u-grid-item {
		padding: 20rpx;
	}
</style>