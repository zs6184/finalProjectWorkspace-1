$(function(){
	//取得使用者名稱
	let userName = $('#sessionUsername').val();
	console.log('使用者名稱:'+userName);
	//初始化購物車圖示
	$('.shoppingcartnum').hide();
	let totalnum = CarticonNum(userName);
	if(totalnum > 0 && totalnum){
		$('.shoppingcartnum').show();
		$('.shoppingcartnum p').text(totalnum);
	}
	
	
	$('#productForm').submit(function(){
		let addtoCartNum = parseInt($("#productForm #num").val());
		let productId = $("#productForm #productID").val();
		console.log('購物車id:'+productId);
		//加入購物車
		addproductTocart(addtoCartNum,userName,productId);
	    //查看購物車內容
        testCartNum(userName);
		//購物車圖示
		CarticonNum(userName)
		//結束
		$('.btn-secondary').click();
		return false;	
	});
	
});//DOMReady結束

//購物車圖示數量
function CarticonNum(username) {
    var cookie = $.cookie(`cart${username}`);
	itemnum = '';
	if(cookie){
			var testcarJSON = JSON.parse(cookie);
		let itemnum=0;
	    for (let j in testcarJSON) {
	        console.log('====');
	        console.log(testcarJSON[j].id + ":" + testcarJSON[j].num);
			num = parseInt(testcarJSON[j].num);
			itemnum +=num
		}	
		if(itemnum > 0 ){
			$('.shoppingcartnum').show();
			$('.shoppingcartnum p').text(itemnum);
		}
	}
    
	return itemnum;
		
}

//控制台印出目前購物車狀況
function testCartNum(username) {
    var cookie = $.cookie(`cart${username}`);
    var testcarJSON = JSON.parse(cookie);
    for (let j in testcarJSON) {
        console.log('====');
        console.log(testcarJSON[j].id + ":" + testcarJSON[j].num);
    }
}

//加入購物車方法
function addproductTocart(addtoCartNum,userName,productId){
	 //如果購物車為空
    var cookie = $.cookie(`cart${userName}`);
    if (!cookie) {
        let item = [{ 'id': productId, 'num': addtoCartNum }];
        $.cookie(`cart${userName}`, JSON.stringify(item),{path: '/'});
        swal('已加入購物車', '目前數量:'+addtoCartNum, "success");
    } else {
        var cookie = $.cookie(`cart${userName}`);
        var carJSONpart = JSON.parse(cookie);
        var same = false;
        //如果有加入過購物車 則數量加一
        for (let i in carJSONpart) {
            if (carJSONpart[i].id == productId) {
                carJSONpart[i].num+=addtoCartNum;
                var num = carJSONpart[i].num;
                same = true;
                swal('已加入購物車', `目前數量:${num}`, "success");
                break;
            };
        }
        //如果沒有加入過購物車則加入
        if (!same) {
            let item = { 'id': productId, 'num': addtoCartNum };
            carJSONpart.push(item);
            swal('已加入購物車', '目前數量:'+addtoCartNum, "success");
        }
        $.cookie(`cart${userName}`, JSON.stringify(carJSONpart),{path: '/'});
    }
	
}

