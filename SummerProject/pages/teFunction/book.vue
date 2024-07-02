<template>
	<view class="container">
		<view class="tab-container">
			<text class="tab">书籍查询</text>
		</view>

		<view class="search-container">

			<u-search placeholder="请输入书籍名称" v-model="bookname" :clearabled="false" height="70" :show-action="true"
				@custom="onSearch()" @search="onSearch()" />
			<button @click="clearSearch" class="clear-button">取消</button>
		</view>
		<view class="result-container">
			<!-- <scroll-view>
				<view v-for="(book, index) in books" :key="index" class="book-item">
					<text>{{ book }}</text>
				</view>
			</scroll-view> -->
			<view>书籍名：{{ book.name }}</view>
			<u-line color=#9e9e9e />
			<view>作者：{{ book.author }}</view>
			<u-line color=#9e9e9e />
			<view>校区：{{ book.area }}</view>
			<u-line color=#9e9e9e />
			<view>位置：{{ book.location }}</view>
			<u-line color=#9e9e9e />
			<view>是否借出：{{ borrow }}</view>
			<u-line color=#9e9e9e />
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				searchQuery: '',
				books: [],
				bookname: "",
				book: {
					name: '',
					author: '',
					area: '',
					location: '',
					borrowed: '',
				},
				borrow:''
			};
		},
		onReady() {
			uni.setNavigationBarTitle({
				title: '图书馆'
			});
		},
		methods: {
			navigateBack() {
				uni.navigateBack();
			},
			clearSearch() {
				this.keywords = '';
				this.books = [];
			},
			onSearch() {
				console.log("触发搜索");
				console.log(this.bookname);
				uni.request({
					// url: "http://192.168.1.82:8080/library",
					url: "http://192.168.1.54:8082/library",
					method: "POST",
					data: this.bookname,
					success: (res) => {
						console.log(res);
						this.book = res.data.result;
						console.log(this.book);
						if(this.book.borrowed){
							this.borrow="已借出";
						}
						else{
							this.borrow="未借出";
						}
					}
				})
				//this.books = ['我在这', '这里有', '一本书', '书籍查询示例'];
				// uni.request({
				// 					url: "http://localhost:8090/dianyings",
				// 					success: (res) => {
				// 						console.log(res.data)
				// 						this.dianyings = res.data.result;
				// 					}
				// 				})
			}
		}
	}
</script>

<style>
	.container {
		display: flex;
		flex-direction: column;
		height: 100%;
	}


	.tab-container {
		display: flex;
		justify-content: center;
		padding: 10px 0;
		border-bottom: 1px solid #00B591;
	}

	.tab {
		font-size: 18px;
		color: #00B591;

	}

	.search-container {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 10px;
		border-top: 1px solid #ddd;
		border-bottom: 1px solid #ddd;
	}

	.search-input {
		flex: 1;
		padding: 5px;
		font-size: 16px;
	}

	.clear-button {

		font-size: 25rpx;
		display: flex;
		align-items: center;
		justify-content: center;
		cursor: pointer;
		color: #00aaff;
		background-color: #ffffff;
	}

	.result-container {
		
	}

	.book-item {
		padding: 10px 0;
		border-bottom: 1px solid #ddd;
	}
</style>