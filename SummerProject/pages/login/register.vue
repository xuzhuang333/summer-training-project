<template>
	<view>
		<view class="content">
			<image class="logo" src="/static/大窗.png"></image>
			<u-form :model="user">
				<u-form-item left-icon="account" label="" label-width="200">
					<u-input placeholder="输入您的学工号" type="text" v-model="user.id"></u-input>
				</u-form-item>
				<u-form-item left-icon="lock" label="" label-width="200">
					<u-input placeholder="输入初始密码" type="Fpassword" v-model="user.password"></u-input>
				</u-form-item>
			</u-form>
			<u-radio-group class="userAgreement" v-model="agreeProtocol" @change="handleAgreeChange">
				<u-radio class="agree" :checked="agreeProtocol" :active-color="agreeProtocol ? '#ccc' : '#18bc98'">
					<view class="container">
						<view class="part1">已阅读并同意《</view>
						<view class="part2" @click="agreement()">用户服务协议</view>
						<view class="part3">》</view>
					</view>
				</u-radio>
			</u-radio-group>
			<view class="button-group">
				<u-button class="login-button" type="primary" @click="confirm()">确认</u-button>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: {
					id: "",
					password: "",
					phone: ""
				},
				mima1: "",
				agreeProtocol: true

			}
		},
		methods: {
			agreement(){
				uni.navigateTo({
					url:"/pages/login/userAgreement"
				})
			},
			handleAgreeChange() {
				console.log(this.agreeProtocol);
			},
			confirm() {
				if (!this.agreeProtocol) {
					console.log("确认按钮被点击");
					if (this.user.id.length < 1) {
						this.$u.toast("请输入您的学工号");
						return;
					}
					if (this.user.password.length < 1) {
						this.$u.toast("请输入初始密码");
						return;
					}
					uni.request({
						// url: "http://192.168.1.82:8080/doActivate",
						url: "http://192.168.1.54:8082/doActivate",
						data: this.user,
						method: "POST",
						success: (response) => {
							console.log(response)

							if (response.data.code == 200) {
								this.$u.toast("激活成功")
								uni.setStorageSync("userinfo", response.data.result)
								uni.navigateBack({

								})
							} else if (response.data.code == 201) {
								this.$u.toast("用户已注册")
							} else {
								this.$u.toast("激活失败")
							}

						}
					})
				} else {
					this.$u.toast("请先同意用户服务协议")
					return;
				}
			}
		}
	}
</script>

<style>
	page {
		background-color: #ffffff;
	}

	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		//height:100vh
	}

	.logo {
		height: 150rpx;
		width: 120rpx;
		margin-top: 8vh;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 10rpx;
	}

	.button-group {
		display: flex;
		flex-direction: column;
		align-items: center;
		margin-top: 30rpx;
	}

	.login-button {
		background-color: #18bc98;
		color: white;
		margin-bottom: 10rpx;
		width: 450rpx;
		height: 80rpx;
		border-radius: 40px;
		font-size: 25rpx;
		display: flex;
		align-items: center;
		/* 交叉轴居中对齐 */
		justify-content: center;
		/* 主轴居中对齐 */
		cursor: pointer;
	}

	.agree {
		margin-top: 20rpx;
		display: flex;
	}

	.part1,
	.part2,
	.part3 {
		font-size: 16rpx;
		margin-right: 5rpx;
	}

	.part2 {
		color: #18bc98;
	}

	.container {
		display: flex;
		align-items: center;
	}
</style>