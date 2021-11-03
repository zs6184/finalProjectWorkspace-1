// JavaScript source code
$(function() {
//datepicker jQuery
	


//變更按鈕對應表單的action

$("#insertBtn").click(function(){
	$("#modalForm").attr("action","/backstage/announcements/insertAnnouncements.controller");
	$("#modalForm #idSection").prop("hidden","hidden");
	$("#announcementsAdd input").attr("placeholder","");
	$("#imgPreview img").attr("src","");
	$("#modalForm input,textarea,select").val("");

	
});
	//上傳檔案預覽圖片
	$("#mypic").change(function(){
		previewImg(this.files);
	});

	
	//如果是admin權限，就取消隱藏的員工管理
	var role = $("#role").hasClass("ADMIN");
		console.log(role);
	if(role){
		console.log("成功");
		$("#employeeManagement").removeClass("d-none");
	}
	
	
	
});
	
	

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


//刪除資料欄(連到資料庫)
function del(obj) {
	var ID =$(obj).parent("td").siblings(".ID").text();
	console.log(ID);
			$.ajax({
			type:"GET",
			url:"/backstage/announcements/deletebyid.controller",
			datatype:"JSON",
			contentType: "application/json",
			data:{"id":`${ID}`}
		})
	$(obj).parents("tr").remove();
	window.location.reload()
}
//選取此筆資料
function select(obj){
	$("#modalForm").attr("action","/backstage/announcements/updateone.controller");
	$("#modalForm #idSection").removeAttr("hidden");
	var ID =$(obj).parent("td").siblings(".ID").text();
	console.log(ID);
		$.ajax({
			type:"GET",
			url:"/backstage/announcements/selectbyid.controller",
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
				$("#imgPreview img").attr("src",`data:image/png;base64,${parsed.pic}`);
				$("#modalForm #articleCont").val(parsed.articleCont);
		
				
			
				
			},
			error:function(){
				console.log("failed to get data");
			}
		})
		
	}
	
	

	

