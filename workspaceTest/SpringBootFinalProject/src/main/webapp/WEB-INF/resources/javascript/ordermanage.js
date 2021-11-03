
$(function () {
	//進入頁面載入新訂單
	$.ajax({
		method: 'get',
		url: '/order/getbackorders.controller',
		data: { "status":'新訂單'},
		success: function(data) {
			var data2 = data.reverse();
			$('#newordertbody').html("");
			
			for (let i in data2) {
				var order = JSON.parse(data[i]);
				//日期格式化
				var pickuptime = new Date(order.pickuptime);
				var formatePickupTime = ('0' + (pickuptime.getMonth() + 1)).slice(-2) + '-' + ('0' + pickuptime.getDate()).slice(-2) + ' ' + pickuptime.getHours() + ':' + ('0' + (pickuptime.getMinutes())).slice(-2);
				var ordertime = new Date(order.ordertime);
				var formateOredertime = ('0' + (ordertime.getMonth() + 1)).slice(-2) + '-' + ('0' + ordertime.getDate()).slice(-2) + ' ' + ordertime.getHours() + ':' + ('0' + (ordertime.getMinutes())).slice(-2);
				//判斷付款狀態
				var status = "";
				switch (order.paystatus) {
					case 0:
						status = '未付款';
						break;
					case 1:
						status = '已付款'
						break;
				}
				//遍歷訂單
				console.log(order);
				let str = `<tr onclick="neworDerShowDetail(event, ${order.id})" class="tablinks" id="tr${order.id}">
					        <th scope="row">${order.customer.phoneNumber}</th>
					        <td>${formatePickupTime}</td>
					        <td>${order.total}</td>
					        <td>${status}</td>
				        </tr>`
				$('#newordertbody').append(str);
				//遍歷商品明細
				var productStr = '';
				for (let k in order.orderDetails) {
					productStr += `<tr>
									<th class="col-sm-6">${order.orderDetails[k].product.name}</th>
									<td>${order.orderDetails[k].num}</td>
									<td>${order.orderDetails[k].subtotal}</td>
								</tr>`;
				}
				//遍歷訂單明細
				let str2 = `<div class="neworderleft container tabcontent" id="${order.id}">

								<div class="row borderrow">
									<div class="neworderdetailleft">
										<!--左1of2-->
										<table class="table table-hover">
											<thead class="thead-light ">
												<tr>
													<th scope="col" class="col-sm-6">商品名稱</th>
													<th scope="col">數量</th>
													<th scope="col">小計</th>
												</tr>
											</thead>
											<tbody id='orderproduct'>
											`+
												productStr
											+`	
											</tbody>
										</table>
									</div>
									<!--右2of2-->
									<div class="neworderdetailright">
										<table class="table">
											<thead class="thead-light ">
												<tr>
													<th scope="col">備註</th>
												</tr>
											</thead>
											<tbody style="overflow-y: scroll;">
												<tr>
													<th>${order.note}</th>
											</tbody>
										</table>
									</div>
								</div>
								<!--左下-->
								<div class="row borderrow">
									<!--1of2-->
									<div class=".neworderdetailbottomright col-sm-9">
										<p>合計 : $${order.total}</p>
										<P>
											訂餐時間 : ${formateOredertime}&emsp;&emsp;取餐時間 : ${formatePickupTime}</span>
										</P>
										<p>姓名 : ${order.customer.cusRealname}</p>
										<p>訂單號碼 : ${order.id}</p>
									</div>
									<!--2of2-->
									<div class="neworderdetailbottomleft col pt-3">
										<p>
											<button class="btnn success" data-orderid="${order.id}">接受</button>
										</p>
										<p>
											<button type="button" class="btnn danger"  data-orderid="${order.id}">取消</button>
										</p>
									</div>
								</div>
							</div>`
				
				$('#home').append(str2);
			}
			$('#newordertbody > tr:first').click();
				//var defaultOpen = $('#newordertbody > tr:first').attr("id");
				//console.log(defaultOpen);
				//document.getElementById(defaultOpen).click;
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(`錯誤${jqXHR} --> ${textStatus} --> ${errorThrown}`);
		}
	});
	//點擊完成訂單，取消訂單
	$('#home').on('click', 'button', function () {
		//抓取此訂單div及訂單編號id
		let divid = $(this).attr('data-orderid');
		if ($(this).hasClass('success')) {
			swal("已接受訂單!", "社畜上工拉 !", "success");
			//抓取此訂單div及訂單編號id
			var orderdiv = $(this).parents(`div[id=${divid}]`).attr('id');
			//移除訂單以及訂單明細
			$(this).parents(`div[id=${divid}]`).remove();
			$(`#tr${divid}`).remove();
			//變更狀態
			changestate(divid, "製作中");
			//判斷是否最後一筆
			lastorder('newordertbody');
			//點擊下一個tr
			$('#newordertbody > tr:first').click();
		} else {
			swal({
				title: "即將取消訂單!",
				text: "最好和你的主管及顧客有個很好的交代",
				type: "warning",
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				cancelButtonText: "保留訂單！",
				confirmButtonText: "確定取消！",
				closeOnConfirm: false
			},
				function () {
					swal("取消訂單成功", "", "success");
					//移除訂單以及訂單明細
					$(this).parents(`div[id=${divid}]`).remove();
					$(`#tr${divid}`).remove();
					//變更訂單狀態
					changestate(divid, "取消");
					//判斷是否最後一筆
					lastorder('newordertbody');
					//點擊下一個tr
					$('#newordertbody > tr:first').click();
				});
		}
		
	});
	//點擊製作中載入製作中
	$('#tab_menu_initial').on('click', `a[href = "#profile"]`, function () {
		let button = '<p><button class="btnn success" data-orderid="?">製作完成</button></p>'
		let clickStr = 'makingShowDetail';
		getorder('profile', 'making', button, '製作中', clickStr);
	});
	//點擊製作完成
	$('#profile').on('click', 'button', function () {
		let divid = $(this).attr('data-orderid');
		//移除訂單以及訂單明細
		$(this).parents(`div[id=${divid}]`).remove();
		$(`#tr${divid}`).remove();
		swal("製作完成!", "社畜收工囉 !", "success");
		//變更訂單狀態
		changestate(divid, "未取餐");
		//判斷最後一筆
		lastorder('making');
	});
	//點擊製作中載入未取餐
	$('#tab_menu_initial').on('click', `a[href = "#contact"]`, function () {
		let button = '<p><button class="btnn success" data-orderid="?">取餐完成</button></p>'
		let clickStr = 'untakeShowDetail';
		getorder('contact', 'untaken', button, '未取餐', clickStr);
	});
	//點擊取餐完成
	$('#contact').on('click', 'button', function () {
		let divid = $(this).attr('data-orderid');
		//移除訂單以及訂單明細
		$(this).parents(`div[id=${divid}]`).remove();
		$(`#tr${divid}`).remove();
		swal("取餐完成!", "社畜收錢囉 !", "success");
		//變更訂單狀態
		changestate(divid, "已取餐");
		//判斷最後一筆
		lastorder('untaken');
	});
	//點擊載入已取餐
	$('#tab_menu_initial').on('click', `a[href = "#service_01"]`, function () {
		let clickStr = 'havebeentakeShowDetail';
		var button = "<p></p>";
		getorder('service_01', 'havebeentaken', button, '已取餐', clickStr);
	});
	//點擊載入取消
	$('#tab_menu_initial').on('click', `a[href = "#service_02"]`, function () {
		let clickStr = 'cancelShowDetail';
		var button = "<p></p>";
		getorder('service_02', 'cancel', button, '取消', clickStr);
	}); 
	//搜尋
	$('.searchdiv form').submit(function (data) {
		console.log('送出');
		let str = ($('.searchdiv input').val());
		$('.searchdiv input').val('');
		let clickStr = 'searchShowDetail';
		var button = "<p></p>";
		searchorder('service_03', 'search', button, str, clickStr);
		return false;
	});
});
// more btn for add info
var elemWidth, fitCount, fixedWidth = 120,
	$menu = $("ul#tab_menu_initial"), $collectedSet;

function collect() {
	elemWidth = $menu.width();
	fitCount = Math.floor(elemWidth / fixedWidth) - 1;
	$collectedSet = $menu.children(":gt(" + fitCount + ")");
	$("#more_menu").empty().append($collectedSet.clone());
}
$(document.body).on('mouseenter', ".c_dropdown", function() {
	collect();
});
$(window).resize(collect);

//tr點擊
function neworDerShowDetail(evt, orderID) {
	var i, tabcontent, tablinks;
	//tabcontent = document.getElementsByClassName("tabcontent");
	//for (i = 0; i < tabcontent.length; i++) {
	//	tabcontent[i].style.display = "none";
	//}
	$('.tabcontent').each(function () {
		$(this).hide();
		
		if ($(this).attr('id') == orderID) {
			$(this).show();
		} else {
			$(this).hide();
		}

		
	})
	//tablinks = document.getElementsByClassName("tablinks");
	//for (i = 0; i < tablinks.length; i++) {
	//	tablinks[i].className = tablinks[i].className.replace(" active", "");
	//}
	$('tr').each(function () {
		$(this).removeClass('active');
	})
	//document.getElementById(orderID).style.display = "block";
	//evt.currentTarget.className += " active";
	//$(`#${orderID}`).css('display', 'block' );
	//$(`#${orderID}`).show();
	console.log($(`#${orderID}`).attr('id'))
	$(`#tr${orderID}`).addClass('active');


}

// Get the element with id="defaultOpen" and click on it
var defaultOpen = $('#newordertbody > tr:first').attr("id");
document.getElementById(defaultOpen).click();

//----------
function makingShowDetail(evt, orderID) {
	neworDerShowDetail(evt, orderID)
}
//----------
function untakeShowDetail(evt, orderID) {
	neworDerShowDetail(evt, orderID)
}
//----------
function havebeentakeShowDetail(evt, orderID) {
	neworDerShowDetail(evt, orderID)
}
//----------
function cancelShowDetail(evt, orderID) {
	neworDerShowDetail(evt, orderID)
}
//----------
function searchShowDetail(evt, orderID) {
	neworDerShowDetail(evt, orderID)
}

//變更訂單狀態
function changestate(orderid, status) {
	$.ajax({
		method: 'post',
		url: '/order/changestate',
		data: { 'orderid': orderid, 'status': status },
		success: function (data) {
			console.log('變更訂單完成' + data);
			return data;
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log(`錯誤${jqXHR} --> ${textStatus} --> ${errorThrown}`);
		}
	});
}
//封裝取得訂單
function getorder(divid, tboty, buttonStr, orderstatus, clickStr) {
	
	$.ajax({
		method: 'get',
		url: '/order/getbackorders.controller',
		data: { "status": orderstatus },
		success: function (data) {
			var data2 = data.reverse();
			$(`#${tboty}`).html("");
			$(`#div${tboty}`).html('');
			for (let i in data2) {
				var order = JSON.parse(data[i]);
				//日期格式化
				var pickuptime = new Date(order.pickuptime);
				var formatePickupTime = ('0' + (pickuptime.getMonth() + 1)).slice(-2) + '-' + ('0' + pickuptime.getDate()).slice(-2) + ' ' + pickuptime.getHours() + ':' + ('0' + (pickuptime.getMinutes())).slice(-2);
				var ordertime = new Date(order.ordertime);
				var formateOredertime = ('0' + (ordertime.getMonth() + 1)).slice(-2) + '-' + ('0' + ordertime.getDate()).slice(-2) + ' ' + ordertime.getHours() + ':' + ('0' + (ordertime.getMinutes())).slice(-2);
				//判斷付款狀態
				var status = "";
				switch (order.paystatus) {
					case 0:
						status = '未付款';
						break;
					case 1:
						status = '已付款'
						break;
				}
				//遍歷訂單
				console.log(order);
				let str = `<tr onclick="${clickStr}(event, ${order.id})" id="tr${order.id}">
					        <th scope="row">${order.customer.phoneNumber}</th>
					        <td>${formatePickupTime}</td>
					        <td>${order.total}</td>
					        <td>${status}</td>
				        </tr>`
				let str1 = `<tr onclick="${clickStr}(event, ${order.id})" id="tr${order.id}">
					        <th scope="row">${order.customer.phoneNumber}</th>
					        <td>${formatePickupTime}</td>
					        <td>${order.total}</td>
				        </tr>`
				if (orderstatus == '取消') {
					$(`#${tboty}`).append(str1);
				} else {
					$(`#${tboty}`).append(str);
				}
				
				//遍歷商品明細
				var productStr = '';
				for (let k in order.orderDetails) {
					productStr += `<tr>
									<th class="col-sm-6">${order.orderDetails[k].product.name}</th>
									<td>${order.orderDetails[k].num}</td>
									<td>${order.orderDetails[k].subtotal}</td>
								</tr>`;
				}
				//遍歷訂單明細
				let str2 = `<div class="neworderleft container tabcontent" id="${order.id}">

								<div class="row borderrow">
									<div class="neworderdetailleft">
										<!--左1of2-->
										<table class="table table-hover">
											<thead class="thead-light ">
												<tr>
													<th scope="col" class="col-sm-6">商品名稱</th>
													<th scope="col">數量</th>
													<th scope="col">小計</th>
												</tr>
											</thead>
											<tbody id='orderproduct'>
												`+
													productStr
												 + `	
											</tbody>
										</table>
									</div>
									<!--右2of2-->
									<div class="neworderdetailright">
										<table class="table">
											<thead class="thead-light ">
												<tr>
													<th scope="col">備註</th>
												</tr>
											</thead>
											<tbody style="overflow-y: scroll;">
												<tr>
													<th>${order.note}</th>
											</tbody>
										</table>
									</div>
								</div>
								<!--左下-->
								<div class="row borderrow">
									<!--1of2-->
									<div class=".neworderdetailbottomright col-sm-9">
										<p>合計 : $${order.total}</p>
										<P>
											訂餐時間 : ${formateOredertime}&emsp;&emsp;取餐時間 : ${formatePickupTime}</span>
										</P>
										<p>姓名 : ${order.customer.cusRealname}</p>
										<p>訂單號碼 : ${order.id}</p>
									</div>
									<!--2of2-->
									<div class="neworderdetailbottomleft col pt-3">
										`+
											buttonStr.replace("?", order.id);
										+`
									</div>
								</div>
							</div>`

				
				$(`#div${tboty}`).append(str2);
				//$(`#${divid}`).append(str2);
			}
			$(`#${tboty} > tr:first`).click();
			//var defaultOpen = $('#newordertbody > tr:first').attr("id");
			//console.log(defaultOpen);
			//document.getElementById(defaultOpen).click;
		},
		error: function (jqXHR, textStatus, errorThrown) {
			console.log(`錯誤${jqXHR} --> ${textStatus} --> ${errorThrown}`);
		}
	});
}

//判斷是否最後一筆
function lastorder(tbody) {
	var rownum = $(`#${tbody}`).find('tr').length
	if (rownum == 0) {
		$(`#${tbody}`).append(`<tr onclick="makingShowDetail(event, 'order011')" class="tablinks" id="open2">
									<th scope="row"  colspan="4" style="text-align:center;">尚無訂單</th></tr>`)
	}

}

//搜尋//容許我直接複製貼上懶得再改了QQ打五百多行好累//
function searchorder(divid, tboty, buttonStr, str, clickStr) {

	$.ajax({
		method: 'get',
		url: '/order/back.searchorder',
		data: { "str": str },
		success: function (data) {
			$(`#${tboty}`).html("");
			$(`#div${tboty}`).html('');
			if (data == '') {
				console.log('我是查無資料');
				let notfound = `<tr onclick="cancelShowDetail(event, '')" class="tablinks" id="open5"><th scope="row" colspan="4" style="text-align: center;">查無訂單</th></tr>`
				$(`#${tboty}`).append(notfound);
			} else {

			var data3 = data.reverse();
				for (let i in data3) {
					var order = JSON.parse(data[i]);
					//日期格式化
					var pickuptime = new Date(order.pickuptime);
					var formatePickupTime = ('0' + (pickuptime.getMonth() + 1)).slice(-2) + '-' + ('0' + pickuptime.getDate()).slice(-2) + ' ' + pickuptime.getHours() + ':' + ('0' + (pickuptime.getMinutes())).slice(-2);
					var ordertime = new Date(order.ordertime);
					var formateOredertime = ('0' + (ordertime.getMonth() + 1)).slice(-2) + '-' + ('0' + ordertime.getDate()).slice(-2) + ' ' + ordertime.getHours() + ':' + ('0' + (ordertime.getMinutes())).slice(-2);
					//判斷付款狀態
					var status = "";
					switch (order.paystatus) {
						case 0:
							status = '未付款';
							break;
						case 1:
							status = '已付款'
							break;
					}
					//遍歷訂單
					console.log(order);
					let str = `<tr onclick="${clickStr}(event, ${order.id})" id="tr${order.id}">
					        <th scope="row">${order.customer.phoneNumber}</th>
					        <td>${formatePickupTime}</td>
					        <td>${order.total}</td>
					        <td>${status}</td>
				        </tr>`

					$(`#${tboty}`).append(str);

					//遍歷商品明細
					var productStr = '';
					for (let k in order.orderDetails) {
						productStr += `<tr>
									<th class="col-sm-6">${order.orderDetails[k].product.name}</th>
									<td>${order.orderDetails[k].num}</td>
									<td>${order.orderDetails[k].subtotal}</td>
								</tr>`;
					}
					//遍歷訂單明細
					let str2 = `<div class="neworderleft container tabcontent" id="${order.id}">

								<div class="row borderrow">
									<div class="neworderdetailleft">
										<!--左1of2-->
										<table class="table table-hover">
											<thead class="thead-light ">
												<tr>
													<th scope="col" class="col-sm-6">商品名稱</th>
													<th scope="col">數量</th>
													<th scope="col">小計</th>
												</tr>
											</thead>
											<tbody id='orderproduct'>
												`+
						productStr
						+ `	
											</tbody>
										</table>
									</div>
									<!--右2of2-->
									<div class="neworderdetailright">
										<table class="table">
											<thead class="thead-light ">
												<tr>
													<th scope="col">備註</th>
												</tr>
											</thead>
											<tbody style="overflow-y: scroll;">
												<tr>
													<th>${order.note}</th>
											</tbody>
										</table>
									</div>
								</div>
								<!--左下-->
								<div class="row borderrow">
									<!--1of2-->
									<div class=".neworderdetailbottomright col-sm-9">
										<p>合計 : $${order.total}</p>
										<P>
											訂餐時間 : ${formateOredertime}&emsp;&emsp;取餐時間 : ${formatePickupTime}</span>
										</P>
										<p>姓名 : ${order.customer.cusRealname}</p>
										<p>訂單號碼 : ${order.id}</p>
									</div>
									<!--2of2-->
									<div class="neworderdetailbottomleft col pt-3">
										`+
						buttonStr.replace("?", order.id);
					+`
									</div>
								</div>
							</div>`


					$(`#div${tboty}`).append(str2);
					//$(`#${divid}`).append(str2);
				}
				$(`#${tboty} > tr:first`).click();
				//var defaultOpen = $('#newordertbody > tr:first').attr("id");
				//console.log(defaultOpen);
				//document.getElementById(defaultOpen).click;
			}},
			error: function (jqXHR, textStatus, errorThrown) {
				console.log(`錯誤${jqXHR} --> ${textStatus} --> ${errorThrown}`);
			}
		}
	
		);
}