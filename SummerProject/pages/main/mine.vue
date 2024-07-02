<template>
  <view>
    <view class="mine">
      <view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30">
        <view class="u-m-r-10">
          <!-- 用户头像 -->
          <u-avatar :src="pic" size="140"></u-avatar>
        </view>
        <view class="u-flex-1">
          <view class="u-font-18 u-p-b-20">{{ user.name }}</view>
          <view class="u-font-14 u-tips-color">学工号：{{ user.id }}</view>
        </view>
      </view>
    </view>

    <!-- 功能菜单 -->
    <u-table class="table" border-color="#989898">
      <u-tr>
        <u-td>
          <view class="demo-item1" @click="mydetail()">
            <image class="icon1" src="/static/个人信息 (2).png" mode="aspectFit"></image>
            <view class="text1">个人信息</view>
          </view>
        </u-td>
      </u-tr>
      <u-tr>
        <u-td>
          <view class="demo-item1" @click="way()">
            <image class="icon1" src="/static/使用教程.png" mode="aspectFit"></image>
            <view class="text1">使用教程</view>
          </view>
        </u-td>
      </u-tr>
      <u-tr>
        <u-td>
          <view class="demo-item1">
            <image class="icon1" src="/static/关于我们.png" mode="aspectFit"></image>
            <view class="text1" @click="about()">关于我们</view>
          </view>
        </u-td>
      </u-tr>
      <u-tr>
        <u-td>
          <view class="demo-item1" @click="feedback()">
            <image class="icon1" src="/static/意见反馈.png" mode="aspectFit"></image>
            <view class="text1">意见反馈</view>
          </view>
        </u-td>
      </u-tr>
    </u-table>
  </view>
</template>

<script>
export default {
  data() {
    return {
      pic: '',
      user: {
        name: "用户",
        id: "000000"
      }
    }
  },
  onShow() {
    const value = uni.getStorageSync("userinfo");
    if (value) {
      this.user = value;
    }
    const pic = uni.getStorageSync('avatarUrl');
    if (pic) {
      this.pic = pic;
    } else {
      // 如果本地存储中没有头像地址，则设置一个默认头像路径
      this.pic = '/static/me1.png';
    }
  },
  methods: {
    about() {
      uni.navigateTo({
        url: '/pages/my/aboutOurs'
      })
    },
    way() {
      uni.navigateTo({
        url: '/pages/my/usingWay'
      })
    },
    mydetail() {
      uni.navigateTo({
        url: '/pages/my/myDetail'
      })
    },
    feedback() {
      uni.navigateTo({
        url: '/pages/my/feedback'
      })
    },
  }
}
</script>

<style lang="scss">
.mine {
  margin-top: 10rpx;
  margin-left: 10rpx;
}

.camera {
  width: 54px;
  height: 44px;

  &:active {
    background-color: #ededed;
  }
}

.user-box {
  background-color: #fff;
}

.personal {
  width: 40rpx;
  height: 40rpx;
  vertical-align: middle;
}

.table {
  margin-top: 50rpx;
  margin-left: 30rpx;
  margin-right: 100rpx;
  border-collapse: collapse;
}

.demo-item1 {
  display: flex;
  align-items: center;
  margin: 35rpx;
}

.icon1 {
  width: 40rpx;
  height: 40rpx;
  margin-right: 10rpx;
}

.text1 {
  font-size: 30rpx;
  color: #333;
  flex: 1;
}
</style>