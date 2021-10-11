/// <reference path="jquery-3.6.0.min.js" />
/// <reference path="jquery-ui.min.js" />


//搜尋指定會員資料
function selectOneCus(id) {
	$.ajax({
		method: "get",
		url: "http://localhost:8080/NoXmlSpringMvcProject/SelectCustomerById.Controller",
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
		},
		error: function(jqXHR, textStatus, errThrown) {
			alert(`${textStatus}---${errThrown}`)
		}
	});
}

//刪除指定會員資料
function deleteOneCus() {
	var id = $("#cusUpdateId").val();
	console.log(id);

	$.ajax({
		method: "get",
		url: "http://localhost:8080/NoXmlSpringMvcProject/DeleteCustomerById.Controller",
		dataType: "json",
		contentType: "application/json",
		data: { "id": `${id}` },
		success: function(data) {
			console.log(data);
			if (data) {
				console.log("成功");
				location.href = "SelectCustomerAll.Controller";
			} else {
				console.log("失敗");
			}
		},
		error: function(jqXHR, textStatus, errThrown) {
			alert(`${textStatus}---${errThrown}`)
		}
	});
}
//更新指定會員資料
/*function updateOneCus(){
	var formData = $("#updateForm").serialize();
	$.ajax({
		method:"post",
		url:"http://localhost:8080/NoXmlSpringMvcProject/UpdateCustomerById.Controller",
		data:formData,
		success:function(data){
			console.log(date);
		},
		error:function(jqXHR, textStatus, errThrown) {
			alert(`${textStatus}---${errThrown}`)
		}
	});
}*/


//function updateOneCus(){
$("#updateForm").submit(function() {

	var formData = $(this).serialize();
	console.log(formData);
	$.ajax({
		method: "post",
		url: "http://localhost:8080/NoXmlSpringMvcProject/UpdateCustomerById.Controller",
		data: formData,
		success: function(data) {
			console.log(date);
		},
		error: function(jqXHR, textStatus, errThrown) {
			alert(`${textStatus}---${errThrown}`)
		}
	});
	return false;

});

//}












$(function() {
	/*$("#updateForm").submit(function(id){
		
		//更新指定會員資料
	var formData = $(this).serialize();
	$.ajax({
		method:"post",
		url:"http://localhost:8080/NoXmlSpringMvcProject/UpdateCustomerById.Controller",
		data:formData,
		success:function(data){
			console.log(date);
		},
		error:function(jqXHR, textStatus, errThrown) {
			alert(`${textStatus}---${errThrown}`)
		}
		
	});*/
$("#updateForm").submit(function() {
$("#updateForm :input").prop("disabled",false);//disabled會造成伺服器無法接收資料
	var formData = $(this).serialize();
	console.log(formData+"測試");
	$.ajax({
		method: "post",
		url: "http://localhost:8080/NoXmlSpringMvcProject/UpdateCustomerById.Controller",
		data: formData,
		success: function(data) {
			console.log(data);
			location.href = "SelectCustomerAll.Controller";
		},
		error: function(jqXHR, textStatus, errThrown) {
			alert(`${textStatus}---${errThrown}`)
		}
	});
	return false;

});

});