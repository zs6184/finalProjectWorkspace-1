// JavaScript source code
var customers;
var pets;
var cusID = new Array();
var petID = new Array();
//DocumentReady
$(function() {

	//DatePicker
	$("input[name='reserveTime']").datepicker({
		dateFormat: 'yy/mm/dd',
		changeYear: true,
		changeMonth: true
	});


	//不同按鈕對應同一表單的action
	$("#insertBtn").click(function() {
		$("#modalForm").attr("action", "/backstage/reservation/addorupdate");
		$("#modalForm #petId").removeAttr("readonly");
		$("#petInfoAdd input").attr("placeholder","");
		$("#modalForm input,textarea").val("");
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
	
	//抓取cusData後續檢測使用
	$.ajax({
		type: "GET",
		url: "/backstage/pet/getAllCustomerData.controller",
		datatype: "JSON",
		contentType: "application/json",
		success: function(data) {
			console.log("getCusData Success")
			customers=jQuery.parseJSON(data).cusData; //客戶的資料JSON陣列
			//console.log("cusId=1="+customers.find(i=>i.cusId=="1").cusRealname); //JOE
			
			for(let i=0;i<customers.length;i++){
				cusID.push(customers[i].cusId);	
			}
			console.log("cusID members ="+cusID);
		},
		error:function(){
			console.log("get cusData failed");
		}
	})
	
	//更新寵物資料-輸入客戶ID後檢測客戶是否存在
	$("#cusId").blur(function(){
		var check = parseInt($(this).val()); //確定轉為整數值

		if(cusID.includes(check)){
			console.log("find cus success");
			$("#cusRealname").val(customers.find(i=>i.cusId==`${check}`).cusRealname);
			$("#phone").val(customers.find(i=>i.cusId==`${check}`).phoneNumber);
		}else{
			console.log("Who is it ?");
			$("#cusId,#cusName").val("");
			$("#cusId,#cusName").attr("placeholder","查無此會員");
		}
	});
	
	//抓取petData後續檢測使用
	$.ajax({
		type: "GET",
		url: "/backstage/pet/getAllPetAjax",
		datatype: "JSON",
		contentType: "application/json",
		success:function(data){
			console.log("get petData success");
			pets = jQuery.parseJSON(data).petData;
			for(let i=0;i<pets.length;i++){
				petID.push(pets[i].petId);
			}
			console.log("petID members ="+petID);
		},
		error:function(){
			console.log("get petData failed");
		}
	});
	
	//檢測寵物是否存在並自動填值
	$("#petId").blur(function(){
		var tempId = parseInt($(this).val());
		if(petID.includes(tempId)){
			console.log("find pet success");
			$("#petName").val(pets.find(i=>i.petId==`${tempId}`).petName);
		}else{
			console.log("can't find the pet");
			$("#petId,#petName").val("");
			$("#petId,#petName").attr("placeholder","查無此寵物");
		}
	});
	
	
	
//上傳檔案預覽圖片
//	$("#mypic").change(function(){
//		previewImg(this.files);
//	});
//當使用者返回前頁時，需重新預覽前回點選擬上傳的圖片
//previewImg($("#mypic")[0].files);

});

//跳出確認刪除對話框
var ID;
var DATE;
var record;

function delAlert(obj) {
	record = $(obj);
	ID = $(obj).parent("td").siblings(".ID").text();
	DATE=$(obj).parent("td").siblings(".DATE").text();
	var NAME = $(obj).parent("td").siblings(".NAME").text();
	console.log(ID);
	$("#alertDialog").html(`確定刪除客戶${ID}:${NAME} 於 ${DATE}的預約 ?`);
}


//刪除資料欄(連到資料庫)
function del() {
	console.log(ID);
	$.ajax({
		type: "GET",
		url: "/backstage/reservation/deleteOne",
		datatype: "JSON",
		contentType: "application/json",
		data: { "cusId": `${ID}`,"reserveTime":`${DATE}` }
	})
	record.parents("tr").remove();
	window.location.reload()
}

//使用更新按鈕選取此筆資料
function select(obj) {
	$("#modalForm").attr("action", "/backstage/reservation/addorupdate");
	ID = $(obj).parent("td").siblings(".ID").text();
	DATE=$(obj).parent("td").siblings(".DATE").text();
	console.log(ID+"--"+DATE);
	$.ajax({
		type: "GET",
		url: "/backstage/reservation/selectone",
		datatype: "JSON",
		contentType: "application/json",
		data: { "cusId": `${ID}`,"reserveTime":`${DATE}` },
		success: function(result) {
			console.log("Success");
			var parsed = jQuery.parseJSON(result);
			$("#modalForm #petId").val(parsed.petId);
			$("#modalForm #petName").val(parsed.petName);
			$("#modalForm #cusId").val(parsed.cusId);
			$("#modalForm #cusRealname").val(parsed.cusRealname);			
			$("#modalForm #phone").val(parsed.phone);			
			$("#modalForm #reserveTime").val(parsed.reserveTime);			
			$("#modalForm #keepStatus").val(parsed.keepStatus);					
		},
		error: function() {
			console.log("failed to get data");
		}
	})
}

//預覽上傳檔案圖片
//function previewImg(files){
//	if(files.length==0) 
//	return;
//	var file = files[0];
//	var fr = new FileReader();
//	fr.onload = function(){
//		$("#imgPreview img").attr({src:fr.result});
//	};
//	fr.readAsDataURL(file);
//}

