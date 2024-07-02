<template>
	<view>

		<view class="u-p-t-40"></view>

		<view>
			<u-steps :list="numList" mode="number" :current="-1"></u-steps>
		</view>
		<!-- 进程 -->

		<u-cell-group title="请假信息">
			<u-cell-item title="请假类型" @click="show1 = true">{{qingjia.type}}</u-cell-item>
			<u-select v-model="show1" mode="single-column" :list="list1" @confirm="confirm"></u-select>
			<u-cell-item title="请假时间" @click="show2 = true">{{qingjiatime}}</u-cell-item>
			<u-calendar max-date="2050-01-01" v-model="show2" :mode="mode" @change="change"></u-calendar>
		</u-cell-group>

		<u-cell-group title="请假原因">
			<u-cell-item :arrow="false" hover-class="none">
				<u-input v-model="qingjia.reason" :type="type" :border="border" :height="height"
					:auto-height="autoHeight" />
			</u-cell-item>
		</u-cell-group>

		<u-cell-group>
			<u-cell-item title="是否告知监护人" :arrow="false" hover-class="none">
				<u-switch v-model="checked" active-color="#19be6b"></u-switch>
			</u-cell-item>
		</u-cell-group>

		<view class="u-p-l-32 u-p-r-32 u-p-b-30">
			<u-form>
				<u-form-item label="监护人姓名" label-width="180">
					<u-input placeholder="请输入监护人姓名" type="text" v-model="qingjia.parent"></u-input>
				</u-form-item>
				<u-form-item label="监护人电话" label-width="180">
					<u-input placeholder="请输入监护人电话" type="text" v-model="qingjia.parent_phone"></u-input>
				</u-form-item>
			</u-form>
		</view>

		<view class="u-p-t-20 u-p-b-50 u-p-l-32 u-p-r-30">
			<u-button type="success" shape="circle" @click="submit()">提交</u-button>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {

				qingjia: {
					type: '请选择',
					reason: '',
					parent: '',
					parent_phone: '',
					begin_data: '',
					end_data: '',
					state: '',
					student_id: '',
					college: '',
					student_name: ''
				},

				numList: [{
					name: '学生'
				}, {
					name: '辅导员'
				}, {
					name: '结束'
				}, ],
				//进程列表

				show1: false,
				list1: [{
						value: "1",
						label: "旅行"
					},
					{
						value: '2',
						label: '返乡'
					},
					{
						value: '3',
						label: '生病'
					}
				], //请假类型选项


				show2: false,
				mode: 'range',
				//请假时间参数，Calendar日历组件

				//value: '',
				type: 'textarea',
				border: false,
				height: 120,
				autoHeight: true,
				//请假原因参数，INput输入框组件

				checked: false, //是否告知监护人

				qingjiatype: "请选择",
				qingjiatime: "请选择",

			}
		},
		onReady() {
			uni.setNavigationBarTitle({
				title: '请假申请'
			});
		},
		onShow() {

		},
		methods: {

			confirm(e) {
				console.log(e);
				const item = e.find(item => item);
				this.qingjia.type = item.label;

			}, //请假类型选择成功后的数据处理

			change(e) {
				console.log(e);
				this.qingjiatime = e.startDate + " 至 " + e.endDate;
				this.qingjia.begin_data = e.startDate;
				this.qingjia.end_data = e.endDate;
			}, //请假时间选择成功后的数据处理
			submit() {
				if (this.checked) {
					const value1 = uni.getStorageSync("userid");
					const value2 = uni.getStorageSync("usercollege");
					const value3 = uni.getStorageSync("username");
					console.log(value2);
					this.qingjia.student_id = value1;
					this.qingjia.college = value2;
					this.qingjia.student_name = value3;
					this.qingjia.state = 0;
					console.log("11111"),
						console.log(this.qingjia),
						uni.request({
							url: "http://192.168.1.54:8082/requestVacation",
							method: "POST",
							data: this.qingjia,
							success: (res) => {
								console.log("上传");
								console.log(res);
								if (res.data.code == 200) {
									this.$u.toast("申请成功");
								} else {
									this.$u.toast("请假失败")
								}
							}
						})
				} else {
					this.$u.toast("请告知监护人")
				}
			}
		}
	}
</script>

<style>

</style>