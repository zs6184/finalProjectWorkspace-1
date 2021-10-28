/// <reference path="jquery-3.6.0.min.js" />
/// <reference path="jquery-ui.min.js" />


//搜尋指定會員資料
function selectOneCus(id) {
	$.ajax({
		method: "get",
		url: "/Backstage/SelectCustomerImageById.Controller",
		dataType: "json",
		contentType: "application/json",
		data: { "id": `${id}` },
		success: function(data) {
			$("#cusUpdateId").val(data.cusId);
			$("#cusNameUpdate").val(data.cusRealname);
			$("#phoneNumberUpdate").val(data.phoneNumber);
			$("#userNameUpdate").val(data.aka);
			$("#genderUpdate").val(data.gender);
			$("#birthdateUpdate").val(data.birthdate);
			$("#emailUpdate").val(data.email);
			$("#addressUpdate").val(data.address);
			$("#notesUpdate").val(data.note);
			$("#image").prop("src","/downloadTempDir/"+data.imageName);
		},
		error: function(jqXHR, textStatus, errThrown) {
			alert(`${textStatus}---${errThrown}`)
		}
	});
	return false;
}

//刪除指定會員資料
function deleteOneCus() {
	var id = $("#cusUpdateId").val();
	console.log(id);

	$.ajax({
		method: "get",
		url: "/Backstage/DeleteCustomerById.Controller",
		dataType: "json",
		contentType: "application/json",
		data: { "id": `${id}` },
		success: function(data) {
			console.log(data);
			if (data) {
				console.log("成功");
				location.href = "/Backstage/SelectCustomerAll.Controller";
			} else {
				console.log("失敗");
			}
		},
		error: function(jqXHR, textStatus, errThrown) {
			alert(`${textStatus}---${errThrown}`)
		}
	});
	return false;
}





$(function() {
	
	//更新指定會員資料
	$("#updateForm").submit(function() {
		$("#updateForm :input").prop("disabled", false);//disabled會造成伺服器無法接收資料
		var formData = $(this).serialize();
		console.log(formData + "測試");
		$.ajax({
			method: "post",
			url: "/Backstage/UpdateCustomerById.Controller",
			data: formData,
			success: function(data) {
				console.log(data);
				location.href = "/Backstage/SelectCustomerAll.Controller";
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`)
			}
		});
		return false;
	});
		$('#customerTable').DataTable({
		lengthChange: true, //呈現選單
		lengthMenu: [5, 10], //選單值
		pageLength: 10, //固定頁數
		paging: true, //建立分頁
		searching: false, //搜尋
		ordering: false, //取消排序
		language: { //語言
			"lengthMenu": "顯示_MENU_ 項",
			"info": "顯示第 _START_ 至 _END_ 項 , 共 _TOTAL_ 項",
			"paginate": {
				"previous": "上一頁",
				"next": "下一頁"
			}
		}
	});
	

});