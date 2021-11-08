// JavaScript source code
var customers;
var cusID = new Array();

$(function() {
//datepicker jQuery
	$("input[name='bookingsDate']").datepicker({
		dateFormat: 'yy/mm/dd',
		changeYear: true,
		changeMonth: true
	});

	

//變更按鈕對應表單的action

$("#insertBtn").click(function(){
	$("#modalForm").attr("action","/Backstage/bookings/insert");
	$("#modalForm #idSection").prop("hidden","hidden");
	$("#modalForm input,textarea,select").val("");
});
//抓客戶資料
$.ajax({
		type: "GET",
		url: "/Backstage/bookings/getAllCustomerData.controller",
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
	
	

//更新資料-輸入客戶ID後檢測客戶是否存在
	$("#cusID").blur(function(){
		var check = parseInt($(this).val()); //確定轉為整數值

		if(cusID.includes(check)){
			console.log("find cus success");
			$("#cusRealName").val(customers.find(i=>i.cusId==`${check}`).cusRealname);
			$("#phone").val(customers.find(i=>i.cusId==`${check}`).phoneNumber);
		}else{
			console.log("Who is it ?");
			$("#cusID,#cusRealName").val("");
			$("#cusID,#cusRealName").attr("placeholder","查無此會員");
		}
	});




	
});

//刪除資料欄(連到資料庫)
function del(obj) {
		console.log('delete obj', obj)
	let id =$(obj).parent("td").siblings(".ID").text();
	console.log(id);
	$('#loading').addClass('overlay-show').removeClass('overlay-hide')
			$.ajax({
			method:"DELETE",
			datatype: "text", // 回傳型態
			url:`/Backstage/bookings/${id}`,
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
//選取此筆資料
function select(obj){
		console.log('select obj', obj)
	$("#modalForm").attr("action","/Backstage/bookings/update");
	$("#modalForm #idSection").removeAttr("hidden");
	let id =$(obj).parent("td").siblings(".ID").text();

	$("#modalForm #bookingsID").val($("#bookingsID_"+id).text());
	$("#modalForm #cusID").val($("#cusID_"+id).text());
	$("#modalForm #cusRealName").val($("#cusRealName_"+id).text());
	$("#modalForm #phone").val($("#phone_"+id).text());
	$("#modalForm #bookingsNum").val($("#bookingsNum_"+id).text());
	$("#modalForm #bookingsDate").val($("#bookingsDate_"+id).text());
	console.log('$("#bookingsTime_"+id).val()',$("#bookingsTime_"+id).val())
	$("#modalForm #bookingsTime").val($("#bookingsTime_"+id).val());
	$("#modalForm #keepStatus").val($("#keepStatus_"+id).text());
	$("#modalForm #empID").val($("#empID_"+id).text());
	$("#modalForm #note").val($("#note_"+id).text());
	}
	
	


	