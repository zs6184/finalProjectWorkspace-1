// JavaScript source code
$(function() {
//datepicker jQuery
	$("input[name='releaseTime']").datepicker({
		dateFormat: 'yy/mm/dd',
		changeYear: true,
		changeMonth: true
	});



//變更按鈕對應表單的action

$("#insertBtn").click(function(){
	$("#modalForm").attr("action","insertAnnouncements.controller");
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
			url:"/NoXmlSpringMvcProject/announcementsDeletebyid.controller",
			datatype:"JSON",
			contentType: "application/json",
			data:{"id":`${ID}`}
		})
	$(obj).parents("tr").remove();
}
//選取此筆資料
function select(obj){
	$("#modalForm").attr("action","announcementsUpdateone.controller");
	$("#modalForm #idSection").removeAttr("hidden");
	var ID =$(obj).parent("td").siblings(".ID").text();
	console.log(ID);
		$.ajax({
			type:"GET",
			url:"/NoXmlSpringMvcProject/announcementsSelectbyid.controller",
			datatype:"JSON",
			contentType: "application/json",
			data:{"id":`${ID}`},
			success:function(result){
				console.log("Success");
				var parsed = jQuery.parseJSON(result);
				$("#modalForm #announceID").val(ID);
				$("#modalForm #empID").val(parsed.empID);
				$("#modalForm #headline").val(parsed.headline);
				$("#modalForm #releaseTime").val(parsed.releaseTime);
				$("#modalForm #picture").val(parsed.picture);
				$("#modalForm #articleCont").val(parsed.articleCont);
				
			
				
			},
			error:function(){
				console.log("failed to get data");
			}
		})
		
	}
	
	

	
	

