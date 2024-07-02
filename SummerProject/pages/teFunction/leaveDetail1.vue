<template>
	<view class="container">
		<view class="content">
			<!-- 请假人 -->
			<view class="item">
				<text class="label">请假人：</text>
				<text class="value">{{ person.student_name }}</text>
			</view>
			<view class="divider"></view>

			<!-- 请假类型 -->
			<view class="item">
				<text class="label">请假类型：</text>
				<text class="value">{{ person.type }}</text>
			</view>
			<view class="divider"></view>

			<!-- 请假时间 -->
			<view class="item">
				<text class="label">请假时间：</text>
				<text class="value">{{ person.begin_data }}至{{ person.end_data }}</text>
			</view>
			<view class="divider"></view>

			<!-- 请假原因 -->
			<view class="item">
				<text class="label">请假原因：</text>
				<text class="value">{{ person.reason }}</text>
			</view>
			<view class="divider"></view>

			<!-- 知晓监护人 -->
			<view class="item">
				<text class="label">是否告知监护人：</text>
				<text class="value">是</text>
			</view>
			<view class="divider"></view>

			<!-- 监护人姓名 -->
			<view class="item">
				<text class="label">监护人姓名：</text>
				<text class="value">{{ person.parent }}</text>
			</view>
			<view class="divider"></view>

			<!-- 监护人电话 -->
			<view class="item">
				<text class="label">监护人电话：</text>
				<text class="value">{{ person.parent_phone }}</text>
			</view>

			<!-- 批准和拒绝按钮 -->
			<view class="button-container">
				<button class="approve-button" @click="approveRequest">批准申请</button>
				<button class="reject-button" @click="rejectRequest">拒绝申请</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				person: {
					student_name: '',
					type: '',
					begin_data: '',
					end_data: '',
					reason: '',
					parent: '',
					parent_phone: '',
					student_id: '',
					vacation_id: ''
				}
			};
		},
		onReady() {
			uni.setNavigationBarTitle({
				title: '批假'
			});
			// 假设这里从后端获取数据
			this.person = uni.getStorageSync("studentinfo")
		},
		methods: {
			approveRequest() {
				uni.request({
					// url: 'http://192.168.1.39:9090/agree',
					url: 'http://192.168.1.54:8082/agree',
					method: 'POST',
					data: {
						id: this.person.vacation_id,
						submit: 1
					},
					success: (res) => {
						console.log("22222");
						console.log(res);
						this.$u.toast("批准成功")
						uni.setStorageSync("panduan1",1)
						uni.setStorageSync("panduan2",1)
						//uni.navigateBack()
						
					}
				});
			},
			rejectRequest() {
				uni.request({
					// url: 'http://192.168.1.39:9090/agree',
					url: 'http://192.168.1.54:8082/agree',
					method: 'POST',
					data: {
						student_id: this.person.student_id,
						submit: 0
					},
					success: (res) => {
						console.log("22222");
						console.log(res);
						this.$u.toast("拒绝成功")
						uni.setStorageSync("panduan1",1)
						uni.setStorageSync("panduan2",1)
					}
				});
			}
		}
	}
</script>

<style scoped>
	.container {
		display: flex;
		flex-direction: column;
		height: 100%;
	}

	.content {
		flex: 1;
		padding: 15px;
	}

	.item {
		display: flex;
		justify-content: space-between;
		padding: 10px 0;
	}

	.label {
		font-weight: bold;
	}

	.value {
		color: #333;
	}

	.divider {
		height: 1px;
		background-color: #ddd;
		margin: 5px 0;
	}

	.button-container {
		display: flex;
		justify-content: space-between;
		margin-top: 20px;
	}

	.approve-button {
		background-color: green;
		color: white;
		padding: 10px 20px;
		border-radius: 5px;
	}

	.reject-button {
		background-color: red;
		color: white;
		padding: 10px 20px;
		border-radius: 5px;
	}
</style>