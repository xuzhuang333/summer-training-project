<template>
	<view>
		<view class="content">
			<image class="logo" src="/static/大窗.png"></image>
			<u-form :model="user">
				<u-form-item left-icon="account" label="" label-width="200">
					<u-input placeholder="输入学工号" type="text" v-model="user.id"></u-input>
				</u-form-item>
				<u-form-item left-icon="lock" label="" label-width="200">
					<u-input placeholder="登录密码" type="password" v-model="user.password"></u-input>
				</u-form-item>
			</u-form>
			<view class="forget" @click="k()">忘记密码</view>
			<view class="button-group">
				<u-button class="login-button" type="primary" @click="submit()">登录</u-button>
				<u-button class="create-account-button" type="success" @click="handleCreateAccount()">激活账号</u-button>
			</view>
			<u-divider class="or" border-color=#828282>or</u-divider>
			<u-button class="phoneLogin" type="primary" @click="phoneLogin()">
				<uview class="icon-wrapper">
					<image class="icon" src="/static/ill_setting.png"></image>
				</uview>
				手机号登录
			</u-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: {
					id: "",
					password: ""
				}

			}
		},
		methods: {
			k() {
				uni.navigateTo({
					url: '/pages/index/index'
				})
			},
			submit(){
				
				//发送请求
				uni.request({
					url:"http://192.168.1.54:8082/doLogin",
					data:this.user,
					method:"POST",
					success:(response)=>{
						console.log(response)
						if(response.statusCode==200){
							if(response.data.code==200){
								this.$u.toast("登陆成功")
								uni.setStorageSync("userinfo",response.data.result)
								uni.setStorageSync("userjob",response.data.result.leibie)
								uni.setStorageSync("userid",response.data.result.id)
								uni.setStorageSync("usercollege",response.data.result.college)
								uni.setStorageSync("username",response.data.result.name)
								uni.switchTab({
									url:'/pages/main/mine'
								})
							}
							else if(response.data.code==201){
								this.$u.toast("账号未激活")
							}
							else{
								this.$u.toast("密码错误")
							}
						}
						else{
							//登录失败，提示
							this.$u.toast("登陆失败")
						}
					}
				})
			},
			handleCreateAccount() {
				uni.navigateTo({
					url: '/pages/login/register'
				})
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

	.login-button,
	.create-account-button {
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

	.login-button {
		background-color: #18bc98;
		color: white;
		margin-bottom: 10rpx;
	}

	.create-account-button {
		color: #18bc98;
		background-color: #ffffff;
		margin-bottom: 10rpx;
	}

	.forget {
		margin-top: 10rpx;
		margin-left: 300rpx;
		text-align: right;
		color: #18bc98;
		font-size: 18rpx;
	}

	.phoneLogin {

		margin-top: 50rpx;
		width: 450rpx;
		height: 80rpx;
		border-radius: 40px;
		font-size: 25rpx;
		align-items: center;
		justify-content: center;
		cursor: pointer;
		color: #989898;
		background-color: #ffffff;
		border: #bababa;
		border-style: solid;
		padding-left: 1rpx;

	}

	.icon-wrapper {
		margin-right: 50rpx;
		/* 图标与文字之间的间距 */
	}
	
	.or{
		color: #dadada;
		bg-color:#dadada;
		border-color:#dadada;
		fontSize:30rpx;
	}

	.icon {
		width: 40rpx;
		height: 40rpx;
		vertical-align: middle;

	}
</style>