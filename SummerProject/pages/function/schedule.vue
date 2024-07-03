<template>
	<view class="container">
		<view class="header">
			<picker mode="selector" :range="weeks" @change="onWeekChange">
				<view class="picker">
					<text>选择周次: 第{{ selectedWeek }}周</text>
				</view>
			</picker>
		</view>
		<view class="schedule">
			<view class="day" v-for="day in days" :key="day">
				<text class="day-name">{{ day }}</text>
				<view class="time-slot" v-for="slot in timeSlots" :key="slot">
					<view class="course" v-for="course in getCourses(day, slot)"
						:key="course.course_name + course.time">
						<text>{{ course.course_name }}</text>
						<text>{{ course.course_room }}</text>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				selectedWeek: 1,
				courses: [],
				days: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天"],
				timeSlots: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
				weeks: [], // 动态计算可选周数，初始化为空
				baseDate: "", // 初始化为空，稍后计算
			};
		},
		mounted() {
			this.flash(); // 在 mounted 中调用 flash 获取数据
		},
		onShow() {
			this.flash(); // 在页面显示时刷新数据
		},
		methods: {
			onWeekChange(event) {
				this.selectedWeek = this.weeks[event.detail.value];
			},
			getCourses(day, slot) {
				return this.courses.filter((course) => {
					const courseWeek = this.calculateCourseWeek(course.startDate);
					return (
						courseWeek <= this.selectedWeek &&
						courseWeek + course.duration > this.selectedWeek &&
						course.week == this.days.indexOf(day) + 1 &&
						course.time == slot
					);
				});
			},
			calculateBaseDateAndWeeks() {
				// 找到所有课程中最早和最晚的开始日期
				const dates = this.courses.map((course) => new Date(2024, parseInt(course.startDate.slice(0, 2)) - 1,
					parseInt(course.startDate.slice(2))));
				const minDate = new Date(Math.min.apply(null, dates));
				const maxDate = new Date(Math.max.apply(null, dates));

				// 找到最早课程的开始日期
				const month = (minDate.getMonth() + 1).toString().padStart(2, "0");
				const day = minDate.getDate().toString().padStart(2, "0");
				this.baseDate = month + day;

				// 计算课程结束的最大周数
				let maxWeeks = 0;
				this.courses.forEach(course => {
					const courseStartDate = new Date(2024, parseInt(course.startDate.slice(0, 2)) - 1, parseInt(
						course.startDate.slice(2)));
					const courseEndDate = new Date(courseStartDate);
					courseEndDate.setDate(courseStartDate.getDate() + (course.duration * 7) - 1);
					const weeks = this.calculateCourseWeek(`${courseEndDate.getMonth() + 1}`.padStart(2, "0") +
						`${courseEndDate.getDate()}`.padStart(2, "0"));
					if (weeks > maxWeeks) {
						maxWeeks = weeks;
					}
				});

				// 生成 1 到 maxWeeks 的周数组
				this.weeks = Array.from({
					length: maxWeeks
				}, (_, i) => i + 1);
			},
			calculateCourseWeek(startDate) {
				const baseDate = new Date(
					2024,
					parseInt(this.baseDate.slice(0, 2)) - 1,
					parseInt(this.baseDate.slice(2))
				);
				const courseDate = new Date(
					2024,
					parseInt(startDate.slice(0, 2)) - 1,
					parseInt(startDate.slice(2))
				);
				const diffTime = courseDate - baseDate;
				const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
				return Math.floor(diffDays / 7) + 1; // 确保从第1周开始计算
			},
			flash() {
				const value=uni.getStorageSync("userid");
				uni.request({
					url: "http://192.168.1.54:8082/queryCourse",
					data: {
						id: value,
					},
					method: 'POST',
					success: (res) => {
						console.log(res)
						if (res.data.code == 200) {
							this.courses = res.data.result;
							this.calculateBaseDateAndWeeks(); // 获取数据后重新计算基准日期和周数
						} else {
							this.$u.toast("查询失败")
						}
					}
				})
			}
		},
	};
</script>

<style scoped>
	.container {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.header {
		margin-top: 20px;
	}

	.picker {
		margin-bottom: 20px;
		padding: 10px;
		border: 1px solid #ccc;
		border-radius: 5px;
		background-color: #f9f9f9;
	}

	.schedule {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
	}

	.day {
		flex-basis: 14%;
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.day-name {
		font-weight: bold;
		margin-bottom: 10px;
	}

	.time-slot {
		flex-basis: 100%;
		border: 1px solid #ccc;
		margin-bottom: 10px;
		min-height: 50px;
	}

	.course {
		background-color: #f0f0f0;
		padding: 5px;
		margin: 2px;
		text-align: center;
	}
</style>
