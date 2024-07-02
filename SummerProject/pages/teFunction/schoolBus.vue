<template>
	<view class="container">
		<view class="button-group">
			<button :class="{'active-button': route === 'old'}" @click="changeRoute('old')">我要去老校区</button>
			<button :class="{'active-button': route === 'new'}" @click="changeRoute('new')">我要去新校区</button>
		</view>

		<image :src="route === 'old' ? oldCampusSchedule : newCampusSchedule" class="schedule-image" mode="widthFix">
		</image>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				route: 'old',
				oldCampusSchedule: '/static/校车2.jpg', // 老校区时刻表图片地址
				newCampusSchedule: '/static/校车1.jpg' // 新校区时刻表图片地址
			};
		},
		methods: {
			changeRoute(route) {
				this.route = route;
			}
		}
	}
</script>

<style scoped>
	.container {
		display: flex;
		flex-direction: column;
		align-items: center;
		padding: 20rpx;
	}

	.button-group {
		display: flex;
		justify-content: center;
		margin-bottom: 20rpx;
	}

	button {
		background-color: #f0f0f0;
		color: #333;
		padding: 10rpx 20rpx;
		margin: 0 5rpx;
		border: none;
		border-radius: 5rpx;
	}

	.active-button {
		background-color: #007aff;
		color: white;
	}

	.schedule-image {
		width: 100%;
	}
</style>