// JavaScript source code

var indexPage = 1;

$(function() {
	
	//製造頁籤
	$("#tabs").tabs();
	
	//確認權限，如果為EMPLOYEE就顯示後台按鈕
	var emp = $(".backstage").hasClass("EMPLOYEE");
	var admin = $(".backstage").hasClass("ADMIN");

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
	
	//檢測是否有SessionAttribute 根據結果變更顯示的選項
	var sessioncheck = $("#sessionUsername").val();
	console.log("sessioncheck="+sessioncheck);
	if(sessioncheck=="null"){//沒抓到就換回來
		console.log("隱藏");
		$(".memberOption").addClass("d-none");
		$(".loginOption").removeClass("d-none");
	}else{//當有抓到SessionAttribute時顯示會員選項	、隱藏登入選項
		console.log("顯示");
		$(".memberOption").removeClass("d-none");
		$(".loginOption").addClass("d-none");
	}

	
	//stickybar
	window.onscroll = function() { stickTop() };
	var topbar = document.getElementById("topbar");
	var distance = topbar.offsetTop; //抓topbar離頁面頂部距離
	
	//Stickytop
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

});

var ID;
var record;

//取得選擇的產品資料
function getTheProd(obj){
	ID = $(obj).parent().siblings("input").val();
	console.log("ID="+ID);
	$.ajax({
		type: "GET",
		url: "/product/selectbyid",
		data:{"id":ID},
		datatype: "JSON",
		contentType: "application/json",
		success:function(data){
			console.log("get theProd Success");
			var theProd = jQuery.parseJSON(data);
			$("#imgPreview img").attr("src",`data:image/png;base64,${theProd.pic}`);
			$("#productForm #productID").val(theProd.productID);
			$("#productForm #productName").val(theProd.productName);
			$("#productForm #category").val(theProd.category);
			$("#productForm #unitprice").val(theProd.unitprice);
			$("#productForm #totalInstore").val(theProd.totalInstore);
			$("#productForm #totalInstore").val(theProd.totalInstore);
		},
		error:function(){
			console.log("get the Prod fail");
		}
	});
}



//選取單一寵物詳細資料
//function getDetail(obj) {
//	$.ajax({
//		type: "GET",
//		url: "/pet/detaildata/" + $(obj).val(),
//		success: function() {
//			console.log("GET SUCCESS");
//		},
//		error: function() {
//			console.log("FAILED TO GET");
//		}
//	})
//};



//	$.ajax({
//		type: "GET",
//		url: "/NoXmlSpringMvcProject/petinfo.controller",
//		success:function(){
//			console.log("redirect success");
//		},
//		error:function(){
//			console.log("redirect fail");
//		}
//
//	})
