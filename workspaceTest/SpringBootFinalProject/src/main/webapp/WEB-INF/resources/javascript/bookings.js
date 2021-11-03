// JavaScript source code
$(function() {
//datepicker jQuery
	$("input[name='bookingsDate']").datepicker({
		dateFormat: 'yy/mm/dd',
		changeYear: true,
		changeMonth: true
	});



//變更按鈕對應表單的action

$("#insertBtn").click(function(){
	$("#modalForm").attr("action","/backstage/bookings/insert");
	$("#modalForm #idSection").prop("hidden","hidden");
	$("#modalForm input,textarea,select").val("");
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
			url:`/backstage/bookings/${id}`,
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
	$("#modalForm").attr("action","/backstage/bookings/update");
	$("#modalForm #idSection").removeAttr("hidden");
	let id =$(obj).parent("td").siblings(".ID").text();

	$("#modalForm #bookingsID").val($("#bookingsID_"+id).text());
	$("#modalForm #cusId").val($("#cusId_"+id).text());
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
	
	

	
	

