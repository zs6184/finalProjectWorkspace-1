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
		$("#modalForm").attr("action", "/Backstage/reservation/addone");
		$("#modalForm #petId").removeAttr("readonly");
		$("#petInfoAdd input").attr("placeholder","");
		$("#modalForm input,textarea").val("");
		$("#imgPreview img").attr("src","");
		$("#modalForm #keepStatus").attr("disabled",false).val("未赴約");
		$("#missingBtn").addClass("btn-warning").addClass("d-none");
		$("#missingBtn").removeClass("btn-secondary").attr("disabled",false);
		$("#sendReserveBtn").attr("disabled",false).removeClass("btn-secondary").addClass("btn-danger");
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
		url: "/Backstage/pet/getAllCustomerData.controller",
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
		url: "/Backstage/pet/getAllPetAjax",
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
	
	
	//檢測是否重複預約
	checkUpdateStatus();
	
});



var ID;
var DATE;
var NAME
var record;
//跳出確認刪除對話框
function delAlert(obj) {
	record = $(obj);
	ID = $(obj).parent("td").siblings(".ID").text();
	DATE=$(obj).parent("td").siblings(".DATE").text();
	NAME = $(obj).parent("td").siblings(".NAME").text();
	console.log(ID);
	$("#alertDialog").html(`確定刪除客戶${ID} : ${NAME} 於 ${DATE}的預約 ?`);
}

//跳出確認失約確認提示框
function missAlert(obj){
	record=$(obj);
	$("#alertMissDialog").html(`確定標註客戶${ID} : ${NAME} 於 ${DATE}的預約為失約 ?`);
}


//刪除資料欄(連到資料庫)
function del() {
	console.log(ID);
	$.ajax({
		type: "GET",
		url: "/Backstage/reservation/deleteOne",
		datatype: "JSON",
		contentType: "application/json",
		data: { "cusId": `${ID}`,"reserveTime":`${DATE}` }
	})
	record.parents("tr").remove();
	
}

//使用更新按鈕選取此筆資料
function select(obj) {
	$("#modalForm").attr("action", "/Backstage/reservation/updateone");
	ID = $(obj).parent("td").siblings(".ID").text();
	DATE=$(obj).parent("td").siblings(".DATE").text();
	console.log(ID+"--"+DATE);
	$.ajax({
		type: "GET",
		url: "/Backstage/reservation/selectone",
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
			NAME=parsed.cusRealname;
			if(parsed.keepStatus=="失約"){
				$("#modalForm #keepStatus").attr("disabled","disabled");
				$("#modalForm #keepStatus").attr("disabled","disabled");
				$("#missingBtn").removeClass("btn-warning").removeClass("d-none");
				$("#missingBtn").addClass("btn-secondary");
				$("#missingBtn").attr("disabled","disabled");
				$("#sendReserveBtn").attr("disabled","disabled").removeClass("btn-danger").addClass("btn-secondary");
			}else{
				$("#modalForm #keepStatus").attr("disabled",false);
				$("#missingBtn").addClass("btn-warning");
				$("#missingBtn").removeClass("btn-secondary").removeClass("d-none");
				$("#missingBtn").attr("disabled",false);
				$("#sendReserveBtn").attr("disabled",false).removeClass("btn-secondary").addClass("btn-danger");
			}			
		},
		error: function() {
			console.log("failed to get data");
		}
	})
}


//確定送出失約請求
function confirmMiss(){
	$.ajax({
		type: "GET",
		url: "/Backstage/reservation/dealmissing",
		datatype: "JSON",
		contentType: "application/json",
		data: { "cusId": `${ID}`,"reserveTime":`${DATE}`}
	})
}

//檢測是否重複預約
function checkUpdateStatus(){
	var status = $("#status").val()
	console.log("status="+status);
		if(status=="已預約"){
		$("#statusAlert").modal("show");
		$("#status").val("");	
	}
}

