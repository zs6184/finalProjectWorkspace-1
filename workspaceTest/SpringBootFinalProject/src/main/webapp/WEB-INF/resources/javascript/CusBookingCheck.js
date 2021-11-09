var ID;
var DATE;
var NAME
var record;

$(function() {
//	//生成表格分頁,呼叫DataTable套件
//	$("#infoTable").DataTable({
//		lengthChange:true,
//		lengthMenu:[5,8,10],
//		pageLength:10,
//		paging:true,
//		searching:false,
//		ordering:false,
//		language:{
//			"lengthMenu":"顯示_MENU_ 項",
//			"info":"顯示第 _START_ 至 _END_ 項 , 共 _TOTAL_ 項",
//			"paginate":{
//				"previous":"上一頁",
//				"next":"下一頁"
//			}
//		}
//	});

	
	//確認是否為google登入，如果是就隱藏變更密碼跟變更信箱
	var provider = $("#dropdown01").hasClass("GOOGLE");
	console.log("provider: "+provider);
	if(provider){
		$("#pwd").addClass("d-none");
		$("#mail").addClass("d-none");
	}

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

	//side
	$("#collapse").on("click", function() {
		if ($("#sidebar").hasClass("close")) {
			$("#sidebar").css({ "width": "" });
			$("#sidebar").removeClass("close");
			$(".center,.itemDetails").css({ "font-size": "" }).css({ "text-align": "" });
			$(".fas").css({ "font-size": "" });
			$(".items").css({ "display": "" });
		} else {
			$("#sidebar").css({ "width": 90 });
			$("#sidebar").addClass("close");
			$(".center,.itemDetails").css({ "font-size": 16 }).css({ "text-align": "center" });
			$(".fas").css({ "font-size": 30 });
			$(".items").css({ "display": "none" });
		}
	});

	$(".list-unstyled li").on("click", function() {
		//.list-unstyled li子元素下的a新增class，回到父元素，
		//遍歷父元素尋找除自己以外的同層元素，再進到子元素移除class
		$(this).find("a").addClass("sidebarLight01").parent().siblings().find("a").removeClass("sidebarLight01");
	});

	//選擇圖片時，立即瀏覽圖片
	$("#file").change(function() {
		previewImg(this.files);
	});

	function previewImg(files) {
		if (files.length == 0) {//如果沒有檔案就結束
			return;
		}

		var file = files[0];//變更的第一張圖片
		var fr = new FileReader();//FileReader()利用非同步讀取檔案

		//fr.onload檔案讀取完後執行
		fr.onload = function() {
			$("#imgPreview img").attr({ src: fr.result });//fr.result:讀入的資料內容
		}
		fr.readAsDataURL(file);//將讀入的內容轉成 data:URL 字串的資料
	}

	//DatePicker
	$("#reserveTime").datepicker({
		dateFormat: 'yy/mm/dd',
		changeYear: true,
		changeMonth: true
	});
	
	
	//表單事件-生成錯誤提示訊息
	function createErr(obj) {
		$(obj).attr("placeholder", "此為必填");
	}

	//表單事件-新增錯誤提示元素
	$("#modalForm .requiredValue").blur(function() {
		if ($(this).val() == "") {		//若必填的input空值
			createErr(this);
		}
	});

	//針對電話號碼的檢查
	$("#modalForm #phone").blur(function(){
		console.log("電話檢查");
		let pattern=/[0]{1}[9]{1}\d{8}$/
		let nowPhone = $("#modalForm #phone").val();
		if(!pattern.test(nowPhone)&&nowPhone.length>0){
			console.log("手機號碼格式不符");
			$("#modalForm #phone").val("");
			$("#modalForm #phone").attr("placeholder","手機號碼格式不符");
		}
	});
	
	//送出修改表單並檢測是否已預約,同時控制檢查項目
	$("#sendReserveBtn").click(function(){
		var errs = [];
		$("#modalForm .requiredValue").each(function() {
			if ($(this).val() == "") {
				createErr(this);
				errs.push(this);
			}
		});
		console.log("檢查完畢");
		if (errs.length > 0) {
			$(errs[0]).focus();
			console.log("嘗試阻止");
			return false; //阻止表單提交
			}	
		console.log("進入送出POST請求環節");
		$("#reservePet").modal("hide");
		var nowTime = $("#modalForm #reserveTime").val();
		$.ajax({
			type: "POST",
			url: "/Users/petreserve/updateone",
			datatype: "JSON",
			data:{'preTime':`${DATE}`,'nowTime':`${nowTime}`,'id':`${ID}`},
			success:function(result){
				console.log("return success");
				console.log("result= "+result);
				if(result=="1"){
					$("#statusAlert").modal("show");
				}
			},
			error:function(){
				console.log("return fail")
			}
		});
		
	});

	//檢測是否重複預約
	checkUpdateStatus();


});




//跳出確認刪除對話框
function delAlert() {
	ID = record.parent("td").siblings(".ID").text();
	DATE=record.parent("td").siblings(".DATE").text();
	NAME = record.parent("td").siblings(".NAME").text();
	console.log("ID="+ID+"DATE="+DATE+"NAME="+NAME);
	$("#alertDialog").html(`確定刪除客戶${ID} : ${NAME} 於 ${DATE}的預約 ?`);
}

//刪除資料欄(連到資料庫)
function del(obj) {
		console.log('delete obj', obj)
	let id =$(obj).parent("td").siblings(".ID").text();
	console.log(id);
	$('#loading').addClass('overlay-show').removeClass('overlay-hide')
			$.ajax({
			method:"DELETE",
			datatype: "text", // 回傳型態
			url:`/Users/bookingsRecord/${id}`,
			success: function (){
				$(obj).parents("tr").remove();
			},
			error: function (){
				console.log('刪除失敗')
			},
			complete: function (){
				$('#loading').removeClass('overlay-show').addClass('overlay-hide')
			},
		})
}




