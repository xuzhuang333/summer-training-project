<template>
  <view>
    <view class="mine">
      <view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30">
        <view class="u-m-r-10">
          <!-- 用户头像 -->
          <image :src="pic" class="avatar" @click="chooseImage"></image>
        </view>
        <view class="u-flex-1">
          <view class="name">{{ user.name }}</view>
          <view class="id">学工号：{{ user.id }}</view>
        </view>
      </view>
    </view>

    <!-- 用户信息表单 -->
    <u-form :model="user" class="detail">
      <u-form-item left-icon="account" label="学工号" label-width="200">
        <view>{{ user.id }}</view>
      </u-form-item>
      <u-form-item left-icon="account" label="姓名" label-width="200">
        <view>{{ user.name }}</view>
      </u-form-item>
      <u-form-item left-icon="account" label="年龄" label-width="200">
        <view>{{ user.age }}</view>
      </u-form-item>
      <u-form-item left-icon="account" label="性别" label-width="200">
        <view>{{ user.gender }}</view>
      </u-form-item>
      <u-form-item left-icon="phone" label="电话" label-width="200">
        <view>{{ user.phone }}</view>
      </u-form-item>
      <u-form-item left-icon="email" label="邮箱" label-width="200">
        <view>{{ user.email }}</view>
      </u-form-item>
      <u-form-item left-icon="account" label="类别" label-width="200">
        <view>{{ job }}</view>
      </u-form-item>
      <u-form-item left-icon="account" label="学院" label-width="200">
        <view>{{ user.college }}</view>
      </u-form-item>
    </u-form>

    <!-- 修改个人信息按钮 -->
    <button class="s" @click="submit()">修改个人信息</button>
  </view>
</template>

<script>
  export default {
    data() {
      return {
        pic: '', // 初始为空，后续从本地存储读取
        user: {
          name: "用户",
          id: "200000",
          age: "",
          gender: "",
          phone: "",
          email: "",
          college: "",
          major_class: "",
          domitory_number: "",
          leibie: "",
          password: "",
          volunteer: ""
        },
        mima1: "",
      }
    },
    computed: {
      job() {
        if (this.user.leibie == 1) {
          return "老师";
        } else if (this.user.leibie == 0) {
          return "学生";
        } else {
          return "";
        }
      }
    },
    onShow() {
      console.log("页面展示了")
      const value = uni.getStorageSync("userinfo")
      if (value) {
        this.user = value
      }
      const pic = uni.getStorageSync('avatarUrl');
      if (pic) {
        this.pic = pic;
      }
    },
    methods: {
      submit() {
        uni.navigateTo({
          url: '/pages/my/alterMyDetail'
        })
      },
      chooseImage() {
        uni.showActionSheet({
          itemList: ['拍照', '从相册选择'],
          success: (res) => {
            if (res.tapIndex === 0) {
              // 拍照
              this.selectImage('camera');
            } else if (res.tapIndex === 1) {
              // 从相册选择
              this.selectImage('album');
            }
          }
        });
      },
      selectImage(sourceType) {
        uni.chooseImage({
          count: 1,
          sizeType: ['original', 'compressed'],
          sourceType: [sourceType],
          success: (res) => {
            this.pic = res.tempFilePaths[0]; // 选择的图片路径
            uni.setStorageSync('avatarUrl', this.pic); // 存储到本地
          }
        });
      }
    }
  }
</script>

<style>
  .s {
    width: 400rpx;
    height: 80rpx;
    background-color: #55aa7f;
    color: white;
    border-radius: 5rpx;
    margin-top: 10rpx;
    text-align: center;
    line-height: 80rpx;
    font-size: 25rpx;
  }

  .mine {
    background-color: #69d5ae;
    height: 25vh;
    display: flex;
    align-items: flex-end;
  }

  .name {
    color: #ffffff;
    font-size: 50rpx;
    margin-left: 60rpx;
  }

  .id {
    color: #ffffff;
    font-size: 30rpx;
    margin-left: 60rpx;
    margin-top: 20rpx;
  }

  .detail {
    margin: 20rpx;
  }

  .avatar {
    width: 140rpx;
    height: 140rpx;
    border-radius: 50%;
    background-color: #f0f0f0;
    margin-bottom: 20rpx;
  }
</style>
