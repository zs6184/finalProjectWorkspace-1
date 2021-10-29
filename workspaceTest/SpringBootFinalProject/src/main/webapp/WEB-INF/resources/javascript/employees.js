//新增時即時顯示最新無人使用員工帳號
function selectAccount() {
	$.ajax({
		method: "get",
		url: "/Backstage/CreateEmpUsername.Controller",
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
		url: "/Backstage/SelectEmployeeById.Controller",
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
		url: "/Backstage/DeleteEmployeeById.Controller",
		dataType: "json",
		contentType: "application/json",
		data: { "id": `${id}` },
		success: function(data) {
			console.log(data);
			if (data) {
				console.log("成功");
				location.href = "/Backstage/EmployeesAll.Controller";
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
			url: "/Backstage/CreateEmpAccount.Controller",
			data: formData,
			success: function(data) {
				console.log(data);
				location.href = "/Backstage/EmployeesAll.Controller";
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
			url: "/Backstage/UpdateEmployeeById.Controller",
			data: formData,
			success: function(data) {
				console.log(data);
				location.href = "/Backstage/EmployeesAll.Controller";
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
		var nowPhone = $(this).val();//每次失去焦點後的新電話號碼
		var num = /[0]{1}[9]{1}\d{8}/;//須符合09開頭並且10碼的數字
		var zh = /[\u4e00-\u9fa5a-zA-Z]/; //中、英文格式
		//判斷是否跟剛從資料庫載入時一致
		if (zh.test(nowPhone)) {
			$("#phoneInvalid").html("請勿填寫中、英文");
			$("#phoneNumber").removeClass("is-valid");//移除通過
			$("#phoneNumber").addClass("is-invalid"); //新增警告
			return false;
		} else {
			if (nowPhone.length < 10) {
				$("#phoneInvalid").html("電話號碼小於 10 碼");
				$("#phoneNumber").removeClass("is-valid");//移除通過
				$("#phoneNumber").addClass("is-invalid"); //新增警告
				return false;
			} else if (!num.test(nowPhone)) {
				$("#phoneInvalid").html("請填寫 09 開頭符合電話號碼格式");
				$("#phoneNumber").removeClass("is-valid");//移除通過
				$("#phoneNumber").addClass("is-invalid"); //新增警告
				return false;
			}
		}
		$.ajax({
			method: "get",
			url: "/Backstage/SelectPhone.Controller",
			data: dataForm,
			success: function(data) {
				console.log(data);
				if (data == "pass" && data != "") {
					$("#phoneNumber").removeClass("is-invalid");//移除警告
					$("#phoneNumber").addClass("is-valid"); //新增通過
					return false;
				} else {
					$("#phoneInvalid").html("電話號碼已被使用");
					$("#phoneNumber").removeClass("is-valid");//移除通過
					$("#phoneNumber").addClass("is-invalid"); //新增警告
					return false;
				}
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`)
			}
		});
		return false;
	});

	$('#myTable').DataTable({
		lengthChange: true, //呈現選單
		lengthMenu: [5, 10], //選單值
		pageLength: 10, //固定頁數
		paging: true, //建立分頁
		searching: true, //搜尋
		ordering: true, //取消排序
		language: { //語言
			"lengthMenu": "顯示_MENU_ 項",
			"info": "顯示第 _START_ 至 _END_ 項 , 共 _TOTAL_ 項",
			"paginate": {
				"previous": "上一頁",
				"next": "下一頁"
			},
			"search":`<i class="fas fa-search"></i>`
		}
	});





});