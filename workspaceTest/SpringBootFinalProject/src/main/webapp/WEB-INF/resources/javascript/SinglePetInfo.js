// JavaScript source code
var customers;
var cusID = new Array();
//檢測是否有SessionAttribute 根據結果變更顯示的選項
var sessioncheck = $("#sessionUsername").val();

$(function() {
	
	//按下領養預約按鈕時清空表單欄位並檢測是否有登入，未登入則跳轉登入頁面
	$("#reserveBtn").click(function(){
		$("#reserveTime,#cusId,#cusRealname,#phone").val("");
		$("#reserveTime,#cusId,#cusRealname,#phone").attr("placeholder","");
		console.log("session username="+sessioncheck);
			$.ajax({
				type: "GET",
				url: "/users/petreserve/checktheCus",
				datatype: "JSON",
				contentType: "application/json",
				success:function(data){
					console.log('get theCus successfully');
					var theCus = jQuery.parseJSON(data).theCus;
					console.log("cusId="+theCus.cusId)
					$("#cusId").val(`${theCus.cusId}`);
					$("#cusRealname").val(`${theCus.cusRealname}`);
					$("#phone").val(`${theCus.phoneNumber}`);
					$("#reservePet").modal("show");					
				},
				error:function(){
					console.log('get the cus fail');
					window.location.replace("http://localhost:8080/login.Controller");
				}
			});
	});
	
	//DatePicker
	$("#reserveTime").datepicker({
		dateFormat: 'yy/mm/dd',
		changeYear: true,
		changeMonth: true
	});

	
	//確認權限，如果為EMPLOYEE就顯示後台按鈕
	checkIfLogin();
	

	
	//stickybar
	window.onscroll = function() { stickTop() };
	var topbar = document.getElementById("topbar");
	var distance = topbar.offsetTop; //抓topbar離頁面頂部距離

	function stickTop() {
		if (window.pageYOffset >= distance) { //偏移量>distance時增加stick
			topbar.classList.add("sticky");
		} else {
			topbar.classList.remove("sticky");
		}
	}

	//將不同背景色放入陣列
	const bgColor = ["bg-success", "bg-danger", "bg-warning", "bg-info"];
	//隨機從陣列中取詞彙
	const getBgColor = () => bgColor[
		Math.floor(Math.random() * (bgColor.length))];
	//將搜索結果加上隨機背景色
	$(".selectResult").each(function() {
		$(this).addClass(`${getBgColor()}`)
	});
	

	//表單事件-生成錯誤提示訊息
	function createErr(obj) {
		$(obj).attr("placeholder", "此為必填");
	}

	//表單事件-新增錯誤提示元素
	$("#reserveForm .requiredValue").blur(function() {
		if ($(this).val() == "") {		//若必填的input空值
			createErr(this);
		}
	});

	//表單事件-提交控制
	$("#reserveForm").submit(function() {
		var errs = [];
		$("#reserveForm .requiredValue").each(function() {
			if ($(this).val() == "") {
				createErr(this);
				errs.push(this);
			}
		});
		if (errs.length > 0) {
			$(errs[0]).focus();
			return false; //阻止表單提交	
		}
	});
	
//	//抓取cusData後續檢測使用
//	$.ajax({
//		type: "GET",
//		url: "/backstage/pet/getAllCustomerData.controller",
//		datatype: "JSON",
//		contentType: "application/json",
//		success: function(data) {
//			console.log("getCusData Success")
//			customers=jQuery.parseJSON(data).cusData; //客戶的資料JSON陣列
//			//console.log("cusId=1="+customers.find(i=>i.cusId=="1").cusRealname); //JOE
//			
//			for(let i=0;i<customers.length;i++){
//				cusID.push(customers[i].cusId);	
//			}
//			console.log("cusID members ="+cusID);
//		},
//		error:function(){
//			console.log("get cusData failed");
//		}
//	})
//	
//	//更新寵物資料-輸入客戶ID後檢測客戶是否存在
//	$("#cusId").blur(function(){
//		var check = parseInt($(this).val()); //確定轉為整數值
//
//		if(cusID.includes(check)){
//			console.log("find cus success");
//			$("#cusRealname").val(customers.find(i=>i.cusId==`${check}`).cusRealname);
//			$("#phone").val(customers.find(i=>i.cusId==`${check}`).phoneNumber);
//		}else{
//			console.log("Who is it ?");
//			$("#cusId,#cusRealname,#phone").val("");
//			$("#cusId,#cusRealname,#phone").attr("placeholder","查無此會員");
//		}
//	});
	
	//送出表單並檢測是否已預約
	$("#sendReserveBtn").click(function(){
		var reserveData = $("#reserveForm").serialize();
		$.ajax({
			type:"POST",
			url:"/users/petreserve/checkthenupdate",
			data:`${reserveData}`,
			dataType:"JSON",
			success:function(result){
				console.log("send success")
				console.log("result="+result)
				if(result==1){
					$("#alertDialog").text("預約成功")
					$("#statusAlert").modal("show");
				}else{
					$("#alertDialog").text("當日已有其他預約，請勿重複預約")
					$("#statusAlert").modal("show");
				}
			},
			error:function(){
				console.log("send failed")
			}
		});
	});
	

});

//確認權限，如果為EMPLOYEE就顯示後台按鈕
function checkIfLogin(){
	var emp = $(".backstage").hasClass("EMPLOYEE");
	var admin = $(".backstage").hasClass("ADMIN");
	console.log("sessioncheck="+$("#sessionUsername").val());
	console.log(emp);
	console.log(admin);
	if (emp || admin) {
		$(".backstage").each(function() {
			$(this).find("li:first").after(`<li id="whp5"><a id="backstage" href="/Backstage/SelectCustomerAll.Controller"
											style="font-size: 1.1em;color:black"
											class="dropdown-item d-flex justify-content-center"
											target="_self">後台管理</a></li>`);
		});
	}
	if(sessioncheck=="null"){//沒抓到就換回來
		console.log("隱藏");
		$(".memberOption").addClass("d-none");
		$(".loginOption").removeClass("d-none");
	}else{//當有抓到SessionAttribute時顯示會員選項	、隱藏登入選項
		console.log("顯示");
		$(".memberOption").removeClass("d-none");
		$(".loginOption").addClass("d-none");
	}
}