/// <reference path="jquery-3.6.0.min.js" />
/// <reference path="jquery-ui.min.js" />

//建立必填欄位的div函式
function createErrDiv(input) {
	var errDivId = `err_${$(input).attr("id")}`;
	//var errDiv = $(`<div id="${errDivId}" class="d-flex align-items-center errMsg col-2">必填欄位</div>`);
	var errDiv = $(`<div id="${errDivId}" class="invalid-tooltip errMsg">必填欄位</div>`);
	$(input).parent().append(errDiv);
	$(input).removeClass("is-valid");
	$(input).addClass("is-invalid");
	//$(input).parent().parent().after(errDiv);
}

//建立密碼錯誤的div函式
function createDifferentDiv(checkPwd) {
	var differentDivId = `dif_${$(checkPwd).attr("id")}`;
	//var differentDiv = $(`<div id="${differentDivId}" class="d-flex align-items-center difMsg col-2">密碼不符</div>`);
	var differentDiv = $(`<div id="${differentDivId}" class="invalid-tooltip difMsg">密碼不符</div>`);
	$(checkPwd).parent().append(differentDiv);
	$(checkPwd).addClass("is-invalid");
	$(checkPwd).removeClass("is-valid");
	console.log("涵室內" + $(checkPwd).attr("class"));
	//$(checkPwd).parent().parent().after(differentDiv);

}

//建立帳號已被使用的div函式
function createUsernameAlreadyTakenDiv(input) {
	var uATDivId = `uAT_${input}`;
	//var uATDiv = $(`<div id="${uATDivId}" class="d-flex align-items-center uATMsg col-2">已被使用</div>`);
	var uATDiv = $(`<div id="${uATDivId}" class="invalid-tooltip uATMsg">已被使用</div>`);
	$("#username").parent().append(uATDiv);
	$("#username").addClass("is-invalid");
	$("#username").removeClass("is-valid");
}

//建立電話號碼已被使用的div函式
function createPhoneNumberAlreadyTakenDiv(input) {
	console.log(input);
	var pNATDivId = `pNAT_${input}`;
	//var pNATDiv = $(`<div id="${pNATDivId}" class="d-flex align-items-center pNATMsg col-2">已被使用</div>`);
	var pNATDiv = $(`<div id="${pNATDivId}" class="invalid-tooltip pNATMsg">已被使用</div>`);
	$("#phoneNumber").parent().append(pNATDiv);
	$("#phoneNumber").addClass("is-invalid");
	$("#phoneNumber").removeClass("is-valid");
}

//建立email已被使用的div函式
function createEmailAlreadyTakenDiv(input) {
	var eATDivId = `eAT_${input}`;
	//var eATDiv = $(`<div id="${eATDivId}" class="d-flex align-items-center eATMsg col-2">已被使用</div>`);
	var eATDiv = $(`<div id="${eATDivId}" class="invalid-tooltip eATMsg">Email已被使用</div>`);
	$("#email").parent().append(eATDiv);
	$("#email").addClass("is-invalid");
	$("#email").removeClass("is-valid");
}


//確認密碼是否一致
function checkPassword() {
	$("#checkPassword").blur(function() {
		var checkPassword = $("#checkPassword");
		var differentDivId = `#dif_${$(this).attr("id")}`;
		var differentDivMsg = $(differentDivId);
		//當比對不符而且不等於空值時才建立DIV，防止跟必填欄位同時出現
		if ($(this).val() != $("#password").val() && $(this).val() != "") {
			if (differentDivMsg.length <= 0) {
				//checkPassword.addClass("is-invalid"); //新增警告
				//checkPassword.removeClass("is-valid");//移除通過
				//$("#checkPasswordInvalid").html("與密碼不符");
				createDifferentDiv("#checkPassword");
				return false;
			}
		} else {
			if (differentDivMsg.length > 0) {
				checkPassword.removeClass("is-invalid");//移除警告
				checkPassword.addClass("is-valid"); //新增通過
				differentDivMsg.remove();
				return false;
			}
		}
		return false;
	});
}


$(function() {
	//確認密碼是否一致
	$("#password").blur(function() {
		var password = $("#password");
		var nowPassword = $(this).val();//每次失去焦點後的新密碼
		if (nowPassword.length >= 1 && nowPassword.length < 6) {
			$(this).parent().append(`<div id="passwordInvalid" class="invalid-tooltip difMsg"></div>`);
			$("#passwordInvalid").html("請輸入 6~20 碼的英文、數字");
			password.removeClass("is-valid");//移除通過
			password.addClass("is-invalid"); //新增警告
			return false;
		} else {
			password.addClass("is-valid");//移除通過
			password.removeClass("is-invalid"); //新增警告
		}
	});





	//checkPassword();
	//失去焦點時如果非空值就移除必填欄位
	$("#createCusForm input").blur(function() {
		var errDivId = `#err_${$(this).attr("id")}`;
		var errDivMsg = $(errDivId);
		if ($(this).val() != "") {
			if (errDivMsg.length > 0) {
				$(this).addClass("is-valid");
				$(this).removeClass("is-invalid");
				errDivMsg.remove();
				checkPassword();
			}
		} else {
			if (errDivMsg.length <= 0) {
				if ($(this).attr("id") != "nickName" && $(this).attr("id") != "address") {//暱稱與地址除外
					$("#passwordInvalid").remove();
					$("#usernameInvalid").remove();
					$("#phoneInvalid").remove();
					console.log("remove");
					createErrDiv(this);
				}
			}
		}
	});


	$("#password").blur(function() {
		if ($("#password").val() == "") {
			$("#password").removeClass("is-valid");//移除通過
			$("#password").addClass("is-invalid"); //新增警告
			return false;
		}
		var password = $("#password");
		var nowPassword = $(this).val();//每次失去焦點後的新密碼
		//判斷是否跟剛從資料庫載入時一致
		console.log("dddd");
		if (nowPassword.length >= 1 && nowPassword.length < 6) {
			$(this).parent().append(`<div id="passwordInvalid" class="invalid-tooltip difMsg"></div>`);
			$("#passwordInvalid").html("請輸入 6~20 碼的英文、數字");
			password.removeClass("is-valid");//移除通過
			password.addClass("is-invalid"); //新增警告
			return false;
		} else {
			password.addClass("is-valid");//移除通過
			password.removeClass("is-invalid"); //新增警告
		}
	});

	//判斷帳號是否重複
	$("#username").blur(function() {
		var uATDivId = $(`#uAT_username`);
		var username = $("#username");
		var nowUsername = username.val();
		var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var zh = /[\u4e00-\u9fa5]/; //中、英文格式
		if (zh.test(nowUsername)) {
			$(this).parent().append(`<div id="usernameInvalid" class="invalid-tooltip difMsg"></div>`);
			$("#usernameInvalid").html("請勿輸入中文");
			username.removeClass("is-valid");//移除通過
			username.addClass("is-invalid"); //新增警告
			return false;
		} else if (nowUsername.length >= 1 && nowUsername.length < 6) {
			$(this).parent().append(`<div id="usernameInvalid" class="invalid-tooltip difMsg"></div>`);
			$("#usernameInvalid").html("請輸入 6~20 碼的英文、數字");
			username.removeClass("is-valid");//移除通過
			username.addClass("is-invalid"); //新增警告
			return false;
		}
		var dataForm = $(this).serialize();
		$.ajax({
			method: "post",
			url: "/CreateCusAccountCheckUsername.controller",
			data: dataForm,
			success: function(data) {
				console.log(data);
				if (data == "pass" && $("#username").val() != "") {
					console.log("進判斷");
					$(`.uATMsg`).remove();//清除已被使用
					$("#username").addClass("is-valid");
					$("#username").removeClass("is-invalid");
					return false;
				} else {
					if (uATDivId.length <= 0 && $("#username").val() != "") {
						createUsernameAlreadyTakenDiv("username");
						return false;
					} else if (uATDivId.length > 0 && $("#username").val() == "") {
						$("#username").addClass("is-valid");
						$("#username").removeClass("is-invalid");
						$(`.uATMsg`).remove();
						return false;
					}
				}
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`)
			}
		});
		return false;
	});

	//判斷電話是否重複
	$("#phoneNumber").blur(function() {
		var pNATDivId = $(`#pNAT_phoneNumber`);
		var phoneNumber = $("#phoneNumber");
		var nowPhone = $(this).val();
		var num = /[0]{1}[9]{1}\d{8}/;//須符合09開頭並且10碼的數字
		var zh = /[\u4e00-\u9fa5a-zA-Z]/; //中、英文格式
		if (zh.test(nowPhone)) {
			$(`#phoneInvalid`).remove();
			$(this).parent().append(`<div id="phoneInvalid" class="invalid-tooltip difMsg"></div>`);
			$("#phoneInvalid").html("請勿填寫中、英文");
			phoneNumber.removeClass("is-valid");//移除通過
			phoneNumber.addClass("is-invalid"); //新增警告
			return false;
			console.log("判斷1");
		} else {
			if (nowPhone.length > 0 && nowPhone.length < 10) {
				console.log("判斷2");
				$(`#phoneInvalid`).remove();
				$(this).parent().append(`<div id="phoneInvalid" class="invalid-tooltip difMsg"></div>`);
				$("#phoneInvalid").html("電話號碼小於 10 碼");
				phoneNumber.removeClass("is-valid");//移除通過
				phoneNumber.addClass("is-invalid"); //新增警告
				return false;
			} else if (nowPhone.length > 0 && !num.test(nowPhone)) {
				console.log("判斷3");
				$(`#phoneInvalid`).remove();
				$(this).parent().append(`<div id="phoneInvalid" class="invalid-tooltip difMsg"></div>`);
				$("#phoneInvalid").html("請填寫 09 開頭符合電話號碼格式");
				phoneNumber.removeClass("is-valid");//移除通過
				phoneNumber.addClass("is-invalid"); //新增警告
				return false;
			}
		}
		var dataForm = $(this).serialize();
		$.ajax({
			method: "post",
			url: "/CreateCusAccountCheckPhone.controller",
			data: dataForm,
			success: function(data) {
				console.log(data);
				if (data == "pass" && $("#phoneNumber").val() != "") {
					console.log("進判斷");
					$("#phoneNumber").addClass("is-valid");
					$("#phoneNumber").removeClass("is-invalid");
					$(`#phoneInvalid`).remove();
					$(`.pNATMsg`).remove();//清除已被使用
					return false;
				} else if (pNATDivId.length <= 0 && $("#phoneNumber").val() != "") {
					createPhoneNumberAlreadyTakenDiv("phoneNumber");
					return false;
				} else if (pNATDivId.length > 0 && $("#phoneNumber").val() == "") {
					$("#phoneNumber").addClass("is-valid");
					$("#phoneNumber").removeClass("is-invalid");
					$(`.pNATMsg`).remove();
					return false;
				} else if (pNATDivId.length > 0 && $("#phoneNumber").val() == "") {
					$("#phoneNumber").addClass("is-valid");
					$("#phoneNumber").removeClass("is-invalid");
					$(`.pNATMsg`).remove();
					return false;
				}
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`)
			}
		});
		return false;
	});

	//判斷email是否重複
	$("#email").blur(function() {

		var eATDivId = $(`#eAT_email`);
		var email = $("#email");
		var nowEmail = $(this).val();//每次失去焦點後的新email
		//符合英文、數字_-. 加 @ 加英文、數字_-.及2~4位數的英文
		var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		//判斷是否跟剛從資料庫載入時一致
		if (!pattern.test(nowEmail) && nowEmail.length > 0) {
			console.log("判斷1");
			$(`.eATMsg`).remove();
			$("#emailInvalid").html("請符合Email格式的英文、數字、@");
			email.removeClass("is-valid");//移除通過
			email.addClass("is-invalid"); //新增警告
			return false;
		} else if (nowEmail.length <= 1 && nowEmail.length > 0) {

			console.log("判斷2");
			$(`.eATMsg`).remove();
			$("#emailInvalid").html("請填寫Email");
			email.removeClass("is-valid");//移除通過
			email.addClass("is-invalid"); //新增警告
			return false;
		}

		var dataForm = $(this).serialize();
		$.ajax({
			method: "post",
			url: "/CreateCusAccountCheckEmail.controller",
			data: dataForm,
			success: function(data) {
				if (data == "pass" && $("#email").val() != "") {
					console.log("進判斷");
					$("#email").addClass("is-valid");
					$("#email").removeClass("is-invalid");
					$(`.eATMsg`).remove();//清除已被使用
					return false;
				} else {
					if (eATDivId.length <= 0 && $("#email").val() != "") {
						console.log("進判斷2");
						$("#emailInvalid").remove();
						createEmailAlreadyTakenDiv("email");

						var div = $(`<div class="invalid-tooltip" id="emailInvalid"></div>`);
						$("#email").parent().append(div);

						return false;
					} else if (eATDivId.length > 0 && $("#email").val() == "") {
						console.log("進判斷3");
						$("#email").addClass("is-valid");
						$("#email").removeClass("is-invalid");
						$(`.eATMsg`).remove();
					}
				}
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`)
			}
		});
		return false;
	});

	$("#createCusForm").submit(function() {
		$(".errMsg").remove();//清除重複登入時的必填欄位
		var errs = [];
		//確認欄位是否空白
		$("#account input").each(function() {
			if ($(this).val() == "" && ($(this).attr("id") != "nickName" && $(this).attr("id") != "address")) {
				createErrDiv(this);//呼叫新增必填欄位方法(input)
				errs.push(this);//將空白input放入陣列
			}
		});

		//當欄位有空白時，focus在第一個欄位
		if (errs.length > 0) {
			$(errs[0]).focus();
			return false; //提早結束submit
		}

		//按下註冊後重新確認密碼是否正確，如果不正確自動focus到確認密碼
		if ($("#checkPassword").val() != $("#password").val()) {
			$("#checkPassword").focus();
			return false;
		}


		$(".btn-secondary").click(function() {
			if (errs.length > 0) {
				$(errs[0]).focus();
				return false; //提早結束submit
			}
		});

		var dataForm = $(this).serialize();
		$.ajax({
			method: "post",
			url: "/CreateCusAccount.Controller",
			data: dataForm,
			success: function(data) {
				if (data == "pass") {
					console.log(data);
					location.href = "/login.Controller";
				}
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`)
			}
		});
		return false;
	});
});