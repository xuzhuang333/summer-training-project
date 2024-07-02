<template>
	<view>
		<!-- 表单 -->
		<u-form :model="user">
			<u-form-item left-icon="phone" label="电话" label-width="200">
				<u-input placeholder="输入电话" type="text" v-model="user.phone"></u-input>
			</u-form-item>
			<u-form-item left-icon="email" label="邮箱" label-width="200">
				<u-input placeholder="输入邮箱" type="text" v-model="user.email"></u-input>
			</u-form-item>
			<u-form-item left-icon="lock" label="修改密码" label-width="200">
				<u-input placeholder="输入密码" type="password" v-model="user.password"></u-input>
			</u-form-item>
			<u-form-item left-icon="lock" type="password" label="确认密码" prop="mima1" label-width="200">
				<u-input v-model="mima1" />
			</u-form-item>

		</u-form>
		<button class="s" @click="submit()">提交</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {

				user: {
					id: "200000",
					phone: "",
					email: "",
					password: "",
				},
				mima1: "",
			}
		},
		onShow() {
			console.log("页面展示了")
			const value = uni.getStorageSync("userinfo")
			if (value) {
				this.user = value
			}
		},
		methods: {
			submit() {
				
				if (this.user.phone.length !=11) {
					this.$u.toast("请输入正确的手机号");
					return;
				}
				if (this.user.password.length < 8) {
					this.$u.toast("密码至少8位");
					return;
				}
				if (this.user.password !== this.mima1) {
					this.$u.toast("两次输入密码不一致");
					return;
				}
				uni.request({
					// url: "http://192.168.1.39:9090/update",
					url: "http://192.168.1.54:8082/update",
					data: this.user,
					method: "POST",
					success: (response) => {
						console.log(response)
						uni.setStorageSync("userinfo", this.user)
						this.$u.toast("修改成功");
					}

				})
			}
		}
	}
</script>

<style>
	.s {
		width: 150rpx;
		/* 按钮的宽度 */
		height: 80rpx;
		/* 按钮的高度 */
		background-color: #4CAF50;
		/* 按钮的背景颜色 */
		color: white;
		/* 按钮文本的颜色 */
		border-radius: 5rpx;
		/* 圆角 */
		margin-top: 10rpx;
		/* 外边距 */
		text-align: center;
		/* 文本居中 */
		line-height: 80rpx;
		/* 垂直居中文本 */
		font-size: 25rpx;
		/* 文本字体大小 */
	}
</style>