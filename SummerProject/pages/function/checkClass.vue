//查课
<template>
	<view class="container">
		<view class="explanation">
			<text>说明</text>
		</view>
		<text class="highlight">搜索时请入完整的课程名称</text>
		<view class="input-group">
			<view class="label">课程名称</view>
			<input type="text" placeholder="请填写" v-model="courseName" />
		</view>
		<view class="btn-group">
			<button class="search-btn" @click="searchCourse">搜索</button>
		</view>
		<view v-if="results.length > 0">
			<view class="results">
				<text>搜索结果：</text>
				<view v-for="(result, index) in results" :key="index" class="result-item">
					<text>课程名称：{{ result.course_name }}</text>
					<text>课程号：{{ result.course_id }}</text>
					<text>授课教师：{{ result.course_teacher }}</text>
					<text>上课地点：{{ result.course_room}}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				courseName: '',
				results: []
			};
		},
		methods: {
			searchCourse() {
				if (!this.courseName) {
					uni.showToast({
						title: '请输入完整的课程名称',
						icon: 'none'
					});
					return;
				}
				uni.request({
					// url: 'http://192.168.1.39:9090/coursemsg',
					url: 'http://192.168.1.54:8082/coursemsg',
					method: 'POST',
					data: {
						course_name: this.courseName,
					},
					success: (res) => {
						console.log(res);
						if (res.data.code == 200) {
							if(res.data.result.length==0){
								this.$u.toast("请给出完整正确的课程名称"),
								this.results = res.data.result;
							}else{
									this.results = res.data.result;
							}
						} else {
							uni.showToast({
								title: res.data.result,
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
		}
	}
</script>

<style scoped>
	.container {
		padding: 20rpx;
	}

	.explanation {
		margin-bottom: 20rpx;
		font-size: 35rpx;
	}

	.highlight {
		color: red;
		margin-top: 10rpx;
		margin-bottom: 10rpx;
	}

	.input-group {
		display: flex;
		flex-direction: column;
		margin-top: 30rpx;
		margin-bottom: 30rpx;
		font-size: 35rpx;
		/* 增大字体 */
	}

	.label {
		margin-bottom: 10rpx;
	}

	.search-btn {
		background-color: #32CD32;
		color: white;
		padding: 10rpx;
		text-align: center;
		margin-top: 20rpx;
	}

	.results {
		margin-top: 30rpx;
	}

	.result-item {
		display: flex;
		flex-direction: column;
		padding: 10rpx 0;
		border-bottom: 1rpx solid #f0f0f0;
	}
</style>

