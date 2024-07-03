<template>
	<view>
		<view class="u-p-b-40"></view>

		<view class="u-flex user-box u-p-l-40 u-p-r-20 u-p-b-30">
			<view class="u-m-r-40">
				<u-avatar :src="pic" size="148"></u-avatar>
			</view>
			<view class="u-flex-1">
				<view class="u-font-19 u-p-b-25">{{xingming}}</view>
				<view class="u-font-13 u-tips-color">{{xuehao}}</view>
			</view>
		</view>

		<view class="u-p-l-30 u-p-r-30">
			<u-cell-group title="班级情况">
				<view class="u-flex-1" v-for="(temp,index) of banjiinfo" v-if="index==0" :key="index">
					<u-cell-item title="辅导员" :arrow="false" hover-class="none">{{temp}}</u-cell-item>
				</view>
				<view class="u-flex-1" v-for="(temp,index) of banjiinfo" v-if="index==1" :key="index">
					<u-cell-item title="所在学院" :arrow="false" hover-class="none">{{temp}}</u-cell-item>
				</view>
				<view class="u-flex-1" v-for="(temp,index) of banjiinfo" v-if="index==2" :key="index">
					<u-cell-item title="教学班号" :arrow="false" hover-class="none">{{temp}}</u-cell-item>
				</view>
				<view class="u-flex-1" v-for="(temp,index) of banjiinfo" v-if="index==3" :key="index">
					<u-cell-item title="本班人数" :arrow="false" hover-class="none">{{temp}}</u-cell-item>
				</view>
			</u-cell-group>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {

				xingming: "姓名",
				xuehao: '学号',
				banjiinfo: [
					"后端数据",
					"后端数据",
					"后端数据",
					"后端数据",
				],

			}
		},
		onShow() {

			//获取登陆时存储的用户基本信息并展示
			const value = uni.getStorageSync("userinfo");
			this.xingming = value.name;
			this.xuehao = value.id;

			uni.request({
				url: "http://192.168.1.54:8082/myclass/" + this.xuehao,
				
				//data:this.xuehao,
				method: "GET",
				success: (res) => {
					console.log(res.data);
					this.banjiinfo = res.data.result;
					//判断后端成功接收数据并响应
					if (res.data.code == 200) {
						this.banjiinfo = res.data.result;
					} else {
						console.log("查询班级失败");
					}
				}
			})


		},
		onReady() {
			uni.setNavigationBarTitle({
				title: '我的班级'
			});
		},
		methods: {

		}
	}
</script>

<style>

</style>