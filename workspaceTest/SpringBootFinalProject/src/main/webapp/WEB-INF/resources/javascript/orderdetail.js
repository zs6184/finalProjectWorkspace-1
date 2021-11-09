$(function () {
	
	//使用者名稱
	var username = $('#sessionUsername').val();
	console.log("使用者名稱:"+username);
    //取得使用訂單
	getsessionuserorders();

	//明細按鈕
    $("tbody").on('click',"a[class='godetail']", function () {
        var id = $(this).parent().siblings('th').text();
		getorderdetail(id);
        console.log("我是id:"+id);
        $(".orderdetail").css({ "right": "0px" });
        $(".orderdetail").addClass('show');
        $(this).siblings('.preloader').show();


    })
   
	//離開按鈕
    $('.wrap button').click(function (e) {
        console.log($(".orderdetail").hasClass('show'));
        var con = $(".orderdetail")
        if ($(".orderdetail").hasClass('show') ) {
            $(".orderdetail").css({ "right": "-1000px" })
            $(".orderdetail").removeClass('show');
            $('.preloader').hide()

        }
    });
	//搜尋表單
	$('#search').submit(function(){
		let key = $(`input[type="search"]`).val();
		console.log(key);
		search(key);
		return false;
	});

    $('.wrap button').hover(function () {
        console.log('hihi');
        $('.wrap button i').removeClass('bi-x-circle').addClass('bi-x-circle-fill')
    }, function () {
        $('.wrap button i').removeClass('bi-x-circle-fill').addClass('bi-x-circle')
    });
    
});

//變更狀態
function step(state) {
    switch (state) {
        case '取消':
            $('#progress').html("<li><div class='node  grey'></div><p>新訂單</p></li>"+
                "<li><div class='divider grey '></div></li>"+
                "<li><div class='node grey'></div><p>製作中</p></li>"+
                "<li><div class='divider grey'></div></li>"+
                "<li><div class='node grey'></div><p>待取餐</p></li>"+
                "<li><div class='divider grey'></div></li>"+
                "<li><div class='node grey'></div><p>取餐完成</p></li>"+
                "<li><div class='node red'></div><p>取消</p></li>");
            break;
        case '新訂單':
            $('#progress').html("<li><div class='node  green'></div><p>新訂單</p></li>" +
                "<li><div class='divider green '></div></li>" +
                "<li><div class='node grey'></div><p>製作中</p></li>" +
                "<li><div class='divider grey'></div></li>" +
                "<li><div class='node grey'></div><p>待取餐</p></li>" +
                "<li><div class='divider grey'></div></li>" +
                "<li><div class='node grey'></div><p>取餐完成</p></li>" +
                "<li><div class='node grey'></div><p>取消</p></li>");
            break;
        case '製作中':
            $('#progress').html("<li><div class='node  green'></div><p>新訂單</p></li>" +
                "<li><div class='divider green '></div></li>" +
                "<li><div class='node green'></div><p>製作中</p></li>" +
                "<li><div class='divider green'></div></li>" +
                "<li><div class='node grey'></div><p>待取餐</p></li>" +
                "<li><div class='divider grey'></div></li>" +
                "<li><div class='node grey'></div><p>取餐完成</p></li>" +
                "<li><div class='node grey'></div><p>取消</p></li>");
            break;
        case '未取餐':
            $('#progress').html("<li><div class='node  green'></div><p>準備中</p></li>" +
                "<li><div class='divider green '></div></li>" +
                "<li><div class='node green'></div><p>製作中</p></li>" +
                "<li><div class='divider green'></div></li>" +
                "<li><div class='node green'></div><p>待取餐</p></li>" +
                "<li><div class='divider green'></div></li>" +
                "<li><div class='node grey'></div><p>取餐完成</p></li>" +
                "<li><div class='node grey'></div><p>取消</p></li>");
            break;
        case '已取餐':
            $('#progress').html("<li><div class='node  green'></div><p>準備中</p></li>" +
                "<li><div class='divider green '></div></li>" +
                "<li><div class='node green'></div><p>製作中</p></li>" +
                "<li><div class='divider green'></div></li>" +
                "<li><div class='node green'></div><p>待取餐</p></li>" +
                "<li><div class='divider green'></div></li>" +
                "<li><div class='node blue'></div><p>取餐完成</p></li>" +
                "<li><div class='node grey'></div><p>取消</p></li>");
            break;
    }
}

//取得使用者訂單
function getsessionuserorders(){
	jQuery.ajax({
		method: 'get',
		url: '/order/findorderbyusername',
		success: function(data) {
			//var data2 = data.reverse();
			$('.userorders').html("");
			
			for (let i in data) {
				var order = JSON.parse(data[i]);
				//日期格式化
				
				var ordertime = new Date(order.ordertime);
				var formateOredertime = (ordertime.getUTCFullYear()+ '-' +'0' + (ordertime.getMonth() + 1)).slice(-2) + '-' + ('0' + ordertime.getDate()).slice(-2) + ' ' + ordertime.getHours() + ':' + ('0' + (ordertime.getMinutes())).slice(-2);
				//判斷付款狀態
				var status = "";
				var statusStr ="";
				var payurl = "";
				switch (order.paystatus) {
					case 0:
						status = '未付款';
						statusStr = '<i class="bi bi-check-lg" style="color: #E0E0E0 "></i>未付款';
						payurl =`<a href="/ec/ECPayServer/${order.id}" class="gopay">付款去</a>`;
						break;
					case 1:
						status = '已付款';
						statusStr =  '<i class="bi bi-check-lg" style="color:forestgreen"></i>已付款';
						payurl ='<a href="#this" class="gopay" style="display: none;">付款去</a>';                       
						break;
				}
				if(order.orderstatus =='取消'){
					statusStr =  '<i class="far fa-times-circle" style="color:#fc9d9c"></i>已取消';
				}
				//遍歷訂單
				console.log(order);
				let orderstr = ` <tr>
                    <th scope="row">${order.id}</th>
                    <td>${ordertime.getUTCFullYear()}-${formateOredertime}</td>
                    <td>${order.total}</td>
                    <td>`
						+statusStr+
					`</td>
                    <td >
                        <a class="godetail" href="#this">明細</a>
					</td>
					<td>`+payurl+`</td>	
                </tr>`
				
				$('.userorders').append(orderstr);

			}
			 //隱藏載入按鈕
   			 $('.preloader').hide()
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(`錯誤${jqXHR} --> ${textStatus} --> ${errorThrown}`);
		}
	});
};//結束

//取得訂單明細
function getorderdetail(id){
	$.ajax({
		type:'POST',
		data:{'productid':id},
		url:'/order/findorderbyid',
		data:{"productid":id},
		success:function(data){
			$('.products').html('')
			console.log(data);
				for (let i in data) {
				var order = JSON.parse(data[i]);
				console.log(order.orderDetails)
				str = '';
				for (let k in order.orderDetails) {
					str += `<div class="wrap">
							<div class="item">
								<img src="data:image/png;base64,${order.orderDetails[k].product.pic}" />
							</div>
							<div class="item">
								<p>產品名稱:${order.orderDetails[k].product.name}</p>
								<p>數量:${order.orderDetails[k].num}</p>
							</div>
						  </div>`
					}
				$('.products').append(str);	
				step(order.orderstatus)
				}
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(`錯誤${jqXHR} --> ${textStatus} --> ${errorThrown}`);
		}
		
	});//ajax結束
};

//搜尋
function search(str){
	jQuery.ajax({
		method: 'get',
		url: '/order/searchcusorder',
		data:{'str':str},
		success: function(data) {
			$('.userorders').html("");
			var data2 = data.reverse();
			for (let i in data2) {
				var order = JSON.parse(data[i]);
				//日期格式化
				
				var ordertime = new Date(order.ordertime);
				var formateOredertime = (ordertime.getUTCFullYear()+ '-' +'0' + (ordertime.getMonth() + 1)).slice(-2) + '-' + ('0' + ordertime.getDate()).slice(-2) + ' ' + ordertime.getHours() + ':' + ('0' + (ordertime.getMinutes())).slice(-2);
				//判斷付款狀態
				var status = "";
				var statusStr ="";
				var payurl = "";
				switch (order.paystatus) {
					case 0:
						status = '未付款';
						statusStr = '<i class="bi bi-check-lg" style="color: #E0E0E0 "></i>未付款';
						payurl ='<a href="/ec/ECPayServer/${order.id}" class="gopay">付款去</a>';
						break;
					case 1:
						status = '已付款';
						statusStr =  '<i class="bi bi-check-lg" style="color:forestgreen"></i>已付款';
						payurl ='<a href="#this" class="gopay" style="display: none;">付款去</a>';                       
						break;
				}
				if(order.orderstatus =='取消'){
					statusStr =  '<i class="far fa-times-circle" style="color:#fc9d9c"></i>已取消';
				}
				//遍歷訂單
				console.log(order);
				let orderstr = ` <tr>
                    <th scope="row">${order.id}</th>
                    <td>${ordertime.getUTCFullYear()}-${formateOredertime}</td>
                    <td>${order.total}</td>
                    <td>`
						+statusStr+
					`</td>
                    <td >
                        <a class="godetail" href="#this">明細</a>
					</td>
					<td>`+payurl+`</td>	
                </tr>`
				
				$('.userorders').append(orderstr);

			}
			 //隱藏載入按鈕
   			 $('.preloader').hide()
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(`錯誤${jqXHR} --> ${textStatus} --> ${errorThrown}`);
		}
	});
};	
	






