// JavaScript source code
var customers;
var pets;
var cusID = new Array();
var petID = new Array();
//DocumentReady
$(function() {

	//不同按鈕對應同一表單的action
	$("#insertBtn").click(function() {
		$("#modalForm").attr("action", "/backstage/product/insertone");
		$("#modalForm #pIdSection").prop("hidden", "hidden");
		$("#modalForm input").attr("placeholder","");
		$("#modalForm input,textarea,select").val("");
		$("#imgPreview img").attr("src","");
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

	//表單事件-提交控制
	$("#modalForm").submit(function() {
		var errs = [];
		$("#modalForm .requiredValue").each(function() {
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
	
	//生成表格分頁,呼叫DataTable套件
	dttable=
	$("#infoTable").DataTable({
		lengthChange:true,
		lengthMenu:[5,8,10],
		pageLength:10,
		paging:true,
		searching:false,
		ordering:false,
		language:{
			"lengthMenu":"顯示_MENU_ 項",
			"info":"顯示第 _START_ 至 _END_ 項 , 共 _TOTAL_ 項",
			"paginate":{
				"previous":"上一頁",
				"next":"下一頁"
			}
		}
	});
	
	//上傳檔案預覽圖片
	$("#mypic").change(function(){
		previewImg(this.files);
	});

});


//全域變數
var ID;
var DATE;
var NAME
var record;

//跳出確認刪除對話框
function delAlert(obj) {
	record = $(obj);
	ID = $(obj).parent("td").siblings(".ID").text();
	NAME = $(obj).parent("td").siblings(".NAME").text();
	console.log("ID="+ID);
	$("#alertDialog").html(`確定刪除產品${ID} - ${NAME} 的產品信息並下架 ?`);
}

//刪除資料欄(連到資料庫)
function del() {
	console.log("ID="+ID);
	$.ajax({
		type: "GET",
		url: "/backstage/product/deletebyid",
		datatype: "JSON",
		contentType: "application/json",
		data: { "id": `${ID}`}
	})
	record.parents("tr").remove();
	window.location.reload();
}

//使用更新按鈕選取此筆產品資料
function select(obj) {
	$("#modalForm").attr("action", "/backstage/product/updateone");
	$("#modalForm #pIdSection").prop("hidden", "");
	ID = $(obj).parent("td").siblings(".ID").text();
	console.log("ID="+ID);
	$.ajax({
		type: "GET",
		url: "/backstage/product/selectbyid",
		datatype: "JSON",
		contentType: "application/json",
		data: { "id": `${ID}`},
		success: function(result) {
			console.log("Success");
			var parsed = jQuery.parseJSON(result);
			$("#modalForm #productID").val(parsed.productID);
			$("#modalForm #productName").val(parsed.productName);
			$("#modalForm #category").val(parsed.category);
			$("#modalForm #unitprice").val(parsed.unitprice);			
			$("#modalForm #totalInstore").val(parsed.totalInstore);			
			$("#modalForm #totalInorder").val(parsed.totalInorder);	
			$("#modalForm #note").val(parsed.note);						
			$("#modalForm #onShelve").val(parsed.onShelve);						
			$("#imgPreview img").attr("src",`data:image/png;base64,${parsed.pic}`);				
		},
		error: function() {
			console.log("failed to get data");
		}
	})
}

//預覽上傳檔案圖片
function previewImg(files){
	if(files.length==0) 
	return;
	var file = files[0];
	var fr = new FileReader();
	fr.onload = function(){
		$("#imgPreview img").attr({src:fr.result});
	};
	fr.readAsDataURL(file);
}


////確定送出失約請求
//function confirmMiss(){
//	
//		$.ajax({
//		type: "GET",
//		url: "/backstage/reservation/dealmissing",
//		datatype: "JSON",
//		contentType: "application/json",
//		data: { "cusId": `${ID}`,"reserveTime":`${DATE}`}
//	})
//}

