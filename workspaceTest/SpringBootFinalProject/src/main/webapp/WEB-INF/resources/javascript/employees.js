//新增時即時顯示最新無人使用員工帳號
function selectAccount() {
	$.ajax({
		method: "get",
		url: "http://localhost:8080/NoXmlSpringMvcProject/CreateEmpUsername.Controller",
		success: function(data) {
			$("#empUsername").val(data);
			console.log(data);
		},
		error: function(jqXHR, textStatus, errThrown) {
			alert(`${textStatus}---${errThrown}`)
		}
	});
	return false;
}

//搜尋指定員工資料
function selectOneEmp(id) {
	$.ajax({
		method: "get",
		url: "http://localhost:8080/NoXmlSpringMvcProject/SelectEmployeeById.Controller",
		dataType: "json",
		contentType: "application/json",
		data: { "id": `${id}` },
		success: function(data) {
			$("#empUpdateId").val(data.empId);
			$("#empNameUpdate").val(data.empRealname);
			$("#hiredateUpdate").val(data.hiredate);
			$("#titleUpdate").val(data.title);
			$("#genderUpdate").val(data.gender);
			$("#phoneNumberUpdate").val(data.phoneNumber);
			$("#birthdateUpdate").val(data.birthdate);
			$("#addressUpdate").val(data.address);
			$("#notesUpdate").val(data.note);
		},
		error: function(jqXHR, textStatus, errThrown) {
			alert(`${textStatus}---${errThrown}`)
		}
	});
	return false;
}

//刪除指定會員資料
function deleteOneEmp() {
	var id = $("#empUpdateId").val();
	console.log(id);

	$.ajax({
		method: "get",
		url: "http://localhost:8080/NoXmlSpringMvcProject/DeleteEmployeeById.Controller",
		dataType: "json",
		contentType: "application/json",
		data: { "id": `${id}` },
		success: function(data) {
			console.log(data);
			if (data) {
				console.log("成功");
				location.href = "EmployeesAll.Controller";
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
	//電話新增時自動寫入密碼欄位
	$("#phoneNumber").on("keyup", function() {
		num = $(this).val();
		$("#empPassword").val(num);
	});

	//新增員工帳號
	$("#empForm").submit(function() {
		$("#empForm :input").prop("disabled", false);//disabled會造成伺服器無法接收資料
		var formData = $(this).serialize();
		console.log(formData + "測試");
		$.ajax({
			method: "post",
			url: "http://localhost:8080/NoXmlSpringMvcProject/CreateEmpAccount.Controller",
			data: formData,
			success: function(data) {
				console.log(data);
				location.href = "EmployeesAll.Controller";
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`)
			}
		});
		return false;
	});

	//更新員工資料
	$("#updateEmpForm").submit(function() {
		$("#updateEmpForm :input").prop("disabled", false);//disabled會造成伺服器無法接收資料
		var formData = $(this).serialize();
		console.log(formData + "測試");
		$.ajax({
			method: "post",
			url: "http://localhost:8080/NoXmlSpringMvcProject/UpdateEmployeeById.Controller",
			data: formData,
			success: function(data) {
				console.log(data);
				location.href = "EmployeesAll.Controller";
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`)
			}
		});
		return false;
	});


	//判斷電話是否重複
	$("#phoneNumber").blur(function() {
		var dataForm = $(this).serialize();
		var number = $(this).val();
		var numberSize = number.length;
		if (numberSize < 10) { //電話號碼小於10時，增加警告效果
			$("#phoneNumber").addClass("is-invalid");
			return false;
		}
		$.ajax({
			method: "get",
			url: "http://localhost:8080/NoXmlSpringMvcProject/SelectPhone.Controller",
			data: dataForm,
			success: function(data) {
				console.log(data);
				if (data == "pass" && data != "") {
					$("#phoneNumber").removeClass("is-invalid");
				} else {
					$("#phoneNumber").addClass("is-invalid");
				}
				return false;
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`)
			}
		});
		return false;
	});






});