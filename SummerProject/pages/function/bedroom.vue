<template>
	<view>

		<view class="u-p-b-40"></view>

		<view class="u-flex user-box u-p-l-40 u-p-r-20 u-p-b-30">
			<view class="u-m-r-40">
				<u-avatar :src="pic" size="120"></u-avatar>
			</view>
			<view class="u-flex-1">
				<view class="u-font-16 u-p-l-20 u-p-b-15">{{xingming}}</view>
				<view class="u-font-11 u-p-l-20 u-tips-color">{{xuehao}}</view>
			</view>
		</view>

		<view class="u-p-l-20 u-p-r-40 u-p-b-30" v-for="(temp,index) of qinshiinfo" v-if="index==0" :key="index">
			<u-cell-item title="我的寝室" :arrow="false" hover-class="none">{{temp}}</u-cell-item>
		</view>

		<u-cell-group title="寝室成员">

			<view class="u-flex user-box u-p-l-40 u-p-r-40 u-p-b-0">
				<view class="u-m-r-40">
					<u-avatar :src="pic" size="80"></u-avatar>
				</view>
				<u-cell-group>
					<view class="u-flex-1">
						<u-cell-item title="一号床" :arrow="false" hover-class="none">
							<view class>{{xingming}}</view>
						</u-cell-item>
					</view>
				</u-cell-group>
			</view>

			<view class="u-flex user-box u-p-l-40 u-p-r-40 u-p-b-0">
				<view class="u-m-r-40">
					<u-avatar :src="pic" size="80"></u-avatar>
				</view>
				<u-cell-group>
					<view class="u-flex-1" v-for="(temp,index) of qinshiinfo" v-if="index==1" :key="index">
						<u-cell-item title="二号床" :arrow="false" hover-class="none">
							<view class>{{temp}}</view>
						</u-cell-item>
					</view>
				</u-cell-group>
			</view>
			
			<view class="u-flex user-box u-p-l-40 u-p-r-40 u-p-b-0">
				<view class="u-m-r-40">
					<u-avatar :src="pic" size="80"></u-avatar>
				</view>
				<u-cell-group>
					<view class="u-flex-1" v-for="(temp,index) of qinshiinfo" v-if="index==2" :key="index">
						<u-cell-item title="三号床" :arrow="false" hover-class="none">
							<view class>{{temp}}</view>
						</u-cell-item>
					</view>
				</u-cell-group>
			</view>
			
			<view class="u-flex user-box u-p-l-40 u-p-r-40 u-p-b-0">
				<view class="u-m-r-40">
					<u-avatar :src="pic" size="80"></u-avatar>
				</view>
				<u-cell-group>
					<view class="u-flex-1" v-for="(temp,index) of qinshiinfo" v-if="index==3" :key="index">
						<u-cell-item title="四号床" :arrow="false" hover-class="none">
							<view class>{{temp}}</view>
						</u-cell-item>
					</view>
				</u-cell-group>
			</view>


		</u-cell-group>

	</view>
</template>

<script>
	export default {
		data() {
			return {

				qinshiinfo: [
					"寝室名",
					"用户名",
					"用户名",
					"用户名",
				],
				xingming: "姓名",
				xuehao: "学号",

			}
		},
		onReady() {
			uni.setNavigationBarTitle({
				title: '我的寝室'
			});
		},
		onShow() {
			
			//获取登陆时存储的用户基本信息并展示
			const value = uni.getStorageSync("userinfo");
			this.xingming = value.name;
			this.xuehao = value.id;
			
			uni.request({
				url:"http://192.168.1.54:8082/dormitory/"+this.xuehao,
				method:"GET",
				success: (res) => {
				    console.log(res.data);
					this.qinshiinfo=res.data.result;
					//判断后端成功接收数据并响应
					if(res.data.code==200){
						this.qinshiinfo=res.data.result;
					}
					else{
						console.log("查询寝室失败");
					}
				}
			})
			
		},
		methods: {
			
			
		}
	}
</script>

<style>

</style>
