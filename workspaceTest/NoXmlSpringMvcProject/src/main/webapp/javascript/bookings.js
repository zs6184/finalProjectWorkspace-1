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
	$("#modalForm").attr("action","insertBookings.controller");
	$("#modalForm #idSection").prop("hidden","hidden");
	$("#modalForm input,textarea,select").val("");
});
	
});

//刪除資料欄(連到資料庫)
function del(obj) {
	var ID =$(obj).parent("td").siblings(".ID").text();
	console.log(ID);
			$.ajax({
			type:"GET",
			url:"/NoXmlSpringMvcProject/bookingsDeletebyid.controller",
			datatype:"JSON",
			contentType: "application/json",
			data:{"id":`${ID}`}
		})
	$(obj).parents("tr").remove();
}
//選取此筆資料
function select(obj){
	$("#modalForm").attr("action","bookingsUpdateone.controller");
	$("#modalForm #idSection").removeAttr("hidden");
	var ID =$(obj).parent("td").siblings(".ID").text();
	console.log(ID);
		$.ajax({
			type:"GET",
			url:"/NoXmlSpringMvcProject/bookingsSelectbyid.controller",
			datatype:"JSON",
			contentType: "application/json",
			data:{"id":`${ID}`},
			success:function(result){
				console.log("Success");
				var parsed = jQuery.parseJSON(result);
				$("#modalForm #bookingsID").val(ID);
				$("#modalForm #cusId").val(parsed.cusId);
				$("#modalForm #cusRealName").val(parsed.cusRealName);
				$("#modalForm #phone").val(parsed.phone);
				$("#modalForm #bookingsNum").val(parsed.bookingsNum);
				$("#modalForm #bookingsDate").val(parsed.bookingsDate);
				$("#modalForm #bookingsTime").val(parsed.bookingsTime);
				$("#modalForm #keepStatus").val(parsed.keepStatus);
				$("#modalForm #empID").val(parsed.empID);
				$("#modalForm #note").val(parsed.note);
				
			},
			error:function(){
				console.log("failed to get data");
			}
		})
		
	}
	
	

	
	

