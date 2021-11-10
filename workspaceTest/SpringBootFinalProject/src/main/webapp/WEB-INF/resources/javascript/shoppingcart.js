
$(function() {
	unirowprice();
	totalprice();
	var userName= $('#sessionUsername').val();
	//減數量
	$('.fa-minus').click(function() {
		//抓id
		var id = $(this).closest('tr').attr('id');
		carjsonpart(userName, id, '-',$(this));
		var num = $(this).siblings('input').val();
		if (num == 1) {
			$(this).siblings('input').val(1);
		} else {
			var price = parseInt($(this).parent().parent().children().filter('td.price').text());
			var numm = parseInt($(this).parent().parent().children().filter('td.subtotal').text((num - 1) * price));
			$(this).siblings('input').val(num - 1);
		}
		testCartNum(userName);
		totalprice();
	});
	//加數量
	$('.fa-plus').click(function() {
		//抓id
		var id = $(this).closest('tr').attr('id');
		carjsonpart(userName, id, '+');
		var num = parseInt($(this).siblings('input').val());
		var price = parseInt($(this).parent().parent().children().filter('td.price').text());
		var numm = parseInt($(this).parent().parent().children().filter('td.subtotal').text((num + 1) * price));
		$(this).siblings('input').val(num + 1);
		testCartNum(userName);
		totalprice();
	});
	//直接使用輸入框變更數量
	$('td input').change(function() {
		var num = $(this).val();
		var price = parseInt($(this).parent().parent().children().filter('td.price').text());
		var numm = parseInt($(this).parent().parent().children().filter('td.subtotal').text(num * price));
		testCartNum(userName);
		totalprice();
	});
	//更新總計
	function totalprice() {
		var row = $('tbody tr');
		var totalprice = 0;
		$.each(row, function(index, value) {
			totalprice += parseInt($(this).find('.subtotal').text());
		});
		$('.totalprice').text(totalprice);
	}
	//初始化面計算所有小計
	function unirowprice() {
		var row = $('tbody tr');
		$.each(row, function(index, value) {
			var price = parseInt($(this).find('.price').text());
			var num = parseInt($(this).children().find('input').val());
			$(this).find('.subtotal').text(price * num);
		});
	}
	//刪除
	$('td button[type="button"]').click(function () {
		var pid1 = $(this).parent().parent('tr').attr('id');
		var pid = parseInt(pid1);
		console.log('刪除id'+pid);
		var pname = $(this).parent('td').siblings().eq(0).text();
		console.log('刪除名稱'+pname);
		var cookie = $.cookie(`cart${userName}`);
		var carJSONpart = JSON.parse(cookie)
		for (var i=0;i < carJSONpart.length;i++) {
			console.log("變數"+i);
			if (carJSONpart[i].id == pid) {
				console.log('要被刪除的id'+carJSONpart[i].id);
				swal({
					title: `確定刪除${pname}?`,
					text: "不買可惜",
					type: "warning",
					showCancelButton: true,
					confirmButtonColor: "#DD6B55",
					cancelButtonText: "取消",
					confirmButtonText: "確定",
					closeOnConfirm: false
				},
					function () {	
						console.log(carJSONpart);				
						carJSONpart.splice(i,1);					
						console.log("變數i:"+i);
						$(`#${pid}`).remove();
						totalprice();
						swal("已移除！", "從購物車移除。", "success");
						$.cookie(`cart${userName}`, JSON.stringify(carJSONpart));
						testCartNum(userName);
					});
					break;
			}
		}
	});
	//

});

function totalprice2() {
		var row = $('tbody tr');
		var totalprice = 0;
		$.each(row, function(index, value) {
			totalprice += parseInt($(this).find('.subtotal').text());
		});
		$('.totalprice').text(totalprice);
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
//購物車數量
function testCartNum(username) {
    var cookie = $.cookie(`cart${username}`);
    var testcarJSON = JSON.parse(cookie);
	let itemnum=0;
    for (let j in testcarJSON) {
        console.log('====');
        console.log(testcarJSON[j].id + ":" + testcarJSON[j].num);
		num = parseInt(testcarJSON[j].num);
		itemnum +=num
	}	
	return itemnum;
		
}
//加減方法
function carjsonpart(username, pid, state,this1) {
	var cookie = $.cookie(`cart${username}`);
	var carJSONpart = JSON.parse(cookie)
	//遍歷所有cooki進行加減
	for (var i in carJSONpart) {
		if (carJSONpart[i].id == pid) {
			switch (state) {
				case '+':
					let pluseNum = carJSONpart[i].num++;
					let pluseNum1 = parseInt(pluseNum) + 1;
					swal('數量增加!','數量:' + pluseNum1,'success')
					//swal('數量增加', '數量:' + pluseNum1, "success");
					$.cookie(`cart${username}`, JSON.stringify(carJSONpart));
					break;
				case '-':
					//購物車數量減少
					let reduceNum0 = carJSONpart[i].num;
					if (reduceNum0 > 1) {
						let reduceNum = carJSONpart[i].num--;
						let reduceNum1 = parseInt(reduceNum) - 1;
						swal('數量減少!','數量:' + reduceNum1,'success')
						$.cookie(`cart${username}`, JSON.stringify(carJSONpart));
						break;
					} else {
						//當數量為一時刪除購物項
						swal({
							title: "數量已經是1",
							text: "不買可惜",
							type: "warning",
							showCancelButton: true,
							confirmButtonColor: "#DD6B55",
							cancelButtonText: "明智選擇！",
							confirmButtonText: "就讓它可惜！",
							closeOnConfirm: false
						},
							function() {
								carJSONpart.splice(i, 1);
								this1.closest('tr').remove();
								totalprice2();
								swal("已移除！", "從購物車移除。", "success");
								$.cookie(`cart${username}`, JSON.stringify(carJSONpart));
							});
					}
					break;
			}
			break;
		};
	}
}

