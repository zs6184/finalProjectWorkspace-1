// JavaScript source code
var customers;
var cusID = new Array();
//DocumentReady
$(function() {

	//DatePicker
	$("input[name='adoptDate']").datepicker({
		dateFormat: 'yy/mm/dd',
		changeYear: true,
		changeMonth: true
	});

	//不同按鈕對應同一表單的action
	$("#insertBtn").click(function() {
		$("#modalForm").attr("action", "/Backstage/pet/insertPetInfo.controller");
		$("#modalForm #idSection").prop("hidden", "hidden");
		$("#petInfoAdd input").attr("placeholder","");
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
			console.log("cusRealname="+customers.find(i=>i.cusId==`${check}`).cusRealname);
			$("#cusName").val(customers.find(i=>i.cusId==`${check}`).cusRealname);
		}else{
			console.log("Who is it ?");
			$("#cusId,#cusName").val("");
			$("#cusId,#cusName").attr("placeholder","查無此會員");
		}
	});
	
	
	//上傳檔案預覽圖片
	$("#mypic").change(function(){
		previewImg(this.files);
	});
	
	
	//當使用者返回前頁時，需重新預覽前回點選擬上傳的圖片
	//previewImg($("#mypic")[0].files);

});

//跳出確認刪除對話框
var ID;
var record;


function delAlert(obj) {
	record = $(obj);
	ID = $(obj).parent("td").siblings(".ID").text();
	var NAME = $(obj).parent("td").siblings(".NAME").text();
	console.log(ID);
	$("#alertDialog").html(`確定刪除資料 ${ID} : ${NAME} ?`);
}


//刪除資料欄(連到資料庫)
function del() {
	console.log(ID);
	$.ajax({
		type: "GET",
		url: "/Backstage/pet/deletebyid.controller",
		datatype: "JSON",
		contentType: "application/json",
		data: { "id": `${ID}`}
	})
	record.parents("tr").remove();
	window.location.reload()
}

//使用更新按鈕選取此筆資料
function select(obj) {
	$("#modalForm").attr("action", "/Backstage/pet/updateone.controller");
	$("#modalForm #idSection").removeAttr("hidden");
	ID = $(obj).parent("td").siblings(".ID").text();
	console.log(ID);
	$.ajax({
		type: "GET",
		url: "/Backstage/pet/selectbyid.controller",
		datatype: "JSON",
		contentType: "application/json",
		data: { "id": `${ID}` },
		success: function(result) {
			console.log("Success");
			var parsed = jQuery.parseJSON(result);
			$("#modalForm #petId").val(ID);
			$("#modalForm #petName").val(parsed.petName);
			$("#modalForm #category").val(parsed.category);
			$("#modalForm #species").val(parsed.species);
			$("#modalForm #sex").val(parsed.sex);
			$("#modalForm #age").val(parsed.age);
			$("#modalForm #fixStatus").val(parsed.fixStatus);
			$("#modalForm #adoptStatus").val(parsed.adoptStatus);
			$("#modalForm #cusId").val(parsed.cusId);
			$("#modalForm #cusName").val(parsed.cusName);
			$("#modalForm #adoptDate").val(parsed.adoptDate);
			$("#modalForm #note").val(parsed.note);
			$("#imgPreview img").attr("src",`data:image/*;base64,${parsed.pic}`);
			//console.log(parsed.pic);
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

