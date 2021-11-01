$(function(){
	//訪問403頁面時3秒後跳轉回首頁
	setTimeout(() => {
		$("#time").html(`...3`);//為了讓第一秒稍微延遲顯示
	}, 50);
	
	//3秒後跳轉
	setTimeout(() => {
		location.href = "/Users/loginIndex.Controller";
	}, 3000);
	
	//setInterval方法第一次會跟著設定時間憶起延遲，例如目前會延遲一秒後才開始執行
	var count = 2;
	setInterval(() => {
		if (count > 0) {
			console.log(count);
			$("#time").html(`...${count}`);
			count--;
			console.log(count);
		}
	}, 1000);
});