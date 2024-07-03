<template>
	<view class="container">
		<view class="header">
			<text class="back-button" @click="goBack">返回</text>
			<text class="cell">课程名称</text>
			<text class="cell">课程号</text>
			<text class="cell">操作</text>
			<text class="cell">状态</text>
		</view>
		<view class="course-row" v-for="(course, index) in courses1" :key="index">
			<text class="cell">{{course.course_name}}</text>
			<!-- 课程名称 -->
			<text class="cell">{{course.course_num}}</text>
			<!-- 课程号 -->
			<button @click="selectCourse(course), show=true" class="select-button">选课</button>
			<text v-if="zhuangtai(course)" class="cell">已选满</text>
			<text v-else class="cell">可选</text>
			<!-- 状态 -->
			<u-popup mode="bottom" v-model="show">
				<view class="content">
					<scroll-view scroll-y="true" style="height: 700rpx;">
						<view class="header">
							<text class="cell">教师</text>
							<text class="cell">最多可选</text>
							<text class="cell">当前已选</text>
							<text class="cell">操作</text>
						</view>
						<view>
							<u-form-item class="ourse-row" v-for="(course, index) in courses2 " :key="index">
								<text class="cell">{{course.course_teacher}}老师</text>
								<text class="cell">{{course.max_capacity}}</text>
								<text class="cell">{{course.now_capacity}}</text>
								<u-button v-if="zhuangtai(course)=='3'" style="background-color: #00ff00;">已选</u-button>
								<u-button v-else-if="zhuangtai(course)=='0'" style="background-color: #00ff00;"
									@click="xuanke(course)">选课</u-button>
								<u-button v-else-if="zhuangtai(course)=='1'"
									style="background-color: #ff0000;">已选满</u-button>
								<u-button v-else-if="zhuangtai(course)=='2'"
									style="background-color: #ff0000;">选课</u-button>
							</u-form-item>
						</view>
					</scroll-view>
					<view class="confrim-btn">
						<u-button @click="shuaxin(show), show=false">确定</u-button>
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
				courses1: [],
				courses2: [],
				show: false
			};
		},
		mounted() {
			this.fetchCourses();
		},
		methods: {
			fetchCourses() {
				const value = uni.getStorageSync("userid");
				uni.request({
					url: "http://192.168.1.54:8082/selectingcourses1",
					data: { id: value },
					method: 'POST',
					success: (res) => {
						console.log(res);
						if (res.data.code == 200) {
							this.courses1 = res.data.result;
						} else if (res.data.code == 202) {
							this.$u.toast("课程已选满");
						} else {
							this.$u.toast("选课失败");
						}
					},
					fail: (err) => {
						console.error('Request failed', err);
					}
				});
			},
			selectCourse(course) {
				const value = uni.getStorageSync("userid");
				uni.request({
					url: "http://192.168.1.54:8082/selectingcourses2",
					data: {
						course_name: course.course_name,
						course_num: course.course_num,
						student_id: value,
					},
					method: 'POST',
					success: (res) => {
						console.log(res);
						if (res.data.code == 200) {
							this.courses2 = res.data.result;
						} else {
							this.$u.toast("选课失败，请重试");
						}
					}
				});
			},
			xuanke(course) {
				const value = uni.getStorageSync("userid");
				uni.request({
					url: "http://192.168.1.54:8082/selectingcourses3",
					data: {
						course_id: course.course_id,
						course_name: course.course_name,
						course_num: course.course_num,
						student_id: value,
					},
					method: 'POST',
					success: (res) => {
						console.log(res);
						if (res.data.code == 200) {
							this.$u.toast("选课成功");
						} else if (res.data.code == 203) {
							this.$u.toast("你已经选过了");
						} else {
							this.$u.toast("选课失败，请重试");
						}
					}
				});
				this.shuaxin();
			},
			zhuangtai(course) {
				return course.state;
			},
			shuaxin() {
				this.fetchCourses();
			},
			goBack() {
				uni.navigateBack();
			}
		}
	}
</script>

<style scoped>
	.content {
		padding: 30rpx;
		text-align: center;
	}

	.container {
		padding: 20rpx;
	}

	.header {
		display: flex;
		justify-content: space-between;
		font-weight: bold;
		padding-bottom: 20rpx;
		border-bottom: 2rpx solid #f5f5f5;
	}

	.course-row {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 20rpx 0;
		border-bottom: 1rpx solid #f5f5f5;
	}

	.cell {
		flex: 4;
		text-align: center;
	}

	.select-button {
		background-color: #007aff;
		color: white;
		padding: 1rpx 10rpx;
		border-radius: 10rpx;
	}

	.back-button {
		font-size: 30rpx;
		color: #007aff;
		cursor: pointer;
	}
</style>