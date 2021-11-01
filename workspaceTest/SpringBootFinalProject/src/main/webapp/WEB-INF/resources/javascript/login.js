/// <reference path="jquery-3.6.0.min.js" />
/// <reference path="jquery-ui.min.js" />

//在空白的欄位旁邊建立必填欄位的div
function createErrDiv(input) {
	var errDivId = `err_${$(input).attr("id")}`;//err_加上取得input下的id名稱
	var errDiv = $(`<div id="${errDivId}" class="errMsg col-2">必填欄位</div>`);
	$(input).parent().parent().after(errDiv);//在兩個父元素之後加入div
}
$(function() {

	/*$("#submit").click(function(e){
		e.preventDefault();
		console.log("ccc");
		grecaptcha.ready(function() {
			grecaptcha.execute('6Lcnq94cAAAAAJNYUP_oO0M2EDZ53BrQguAAKfqq', { action: 'submit' }).then(function(token) {
				$.ajax({
				method : "post",
				url : "/reCAPTCHA.controller",
				data : {"token" : token},
				success : function(data) {
					if(data.score>0.7){
						console.log("大於0.7");
						//location.href = '/Users/loginIndex.Controller';
						$("#loginForm").submit();
					}
					console.log("repa");
					console.log(data.score);
				},
				error : function(jqXHR, textStatus, errThrown) {
					alert(`${textStatus}---${errThrown}`)
				}

			});


				
				
				
			});
		});
	});*/

	//recaptcha
	/*function onClick(e) {
		console.log("onclick");
		e.preventDefault();
		grecaptcha.ready(function() {
			grecaptcha.execute('6Lcnq94cAAAAAJNYUP_oO0M2EDZ53BrQguAAKfqq', { action: 'submit' }).then(function(token) {
				$.ajax({
				method : "post",
				url : "/reCAPTCHA.controller",
				data : {"token" : token},
				success : function(data) {
					if(data.score>0.7){
						console.log("大於0.7");
						//location.href = '/Users/loginIndex.Controller';
						//$("#loginForm").submit();
					}
					console.log("repa");
					console.log(data.score);
				},
				error : function(jqXHR, textStatus, errThrown) {
					alert(`${textStatus}---${errThrown}`)
				}

			});


				
				
				
			});
		});
	}*/




	$("#loginForm :text,#loginForm :password").blur(function() {
		var errDivId = `#err_${$(this).attr("id")}`;//#err_加上取得input下的id名稱
		var errDivMsg = $(errDivId);
		if ($(this).val() != '') {//當value不為空字串時，且
			if (errDivMsg.length > 0) {
				errDivMsg.remove();
			}
		}
		else {//#err小於等於0時呼叫方法新增元素
			if (errDivMsg.length <= 0) {
				createErrDiv(this);
			}
		}
	});


	$("#loginForm").submit(function(e) {

		$(".errMsg").remove();//重複按登入時清除必填欄位
		var errs = [];
		//確認欄位是否空白
		$("#account input").each(function() {
			if ($(this).val() == "") {
				createErrDiv(this);//呼叫新增必填欄位方法(input)
				errs.push(this);//將空白input放入陣列
			}
		});
		//當欄位有空白時，focus在第一個空白欄位
		if (errs.length > 0) {
			$(errs[0]).focus();
			return false;//如果欄位有空直接提早結束submit
		}
	});

	//忘記密碼功能判斷email是否存在
	$("#email").blur(function() {
		var email = $("#email");
		var nowEmail = $(this).val();//每次失去焦點後的新email
		//符合英文、數字_-. 加 @ 加英文、數字_-.及2~4位數的英文
		var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		if (nowEmail.length < 1) {
			$("#emailInvalid").html("請填寫Email");
			email.removeClass("is-valid");//移除通過
			email.addClass("is-invalid"); //新增警告
			return false;
		} else if (!pattern.test(nowEmail)) {
			$("#emailInvalid").html("請符合Email格式的英文、數字、@");
			email.removeClass("is-valid");//移除通過
			email.addClass("is-invalid"); //新增警告
			return false;
		}
		email.removeClass("is-invalid");//移除警告

	});

	//忘記密碼功能寄信驗證
	$("#ForgotPasswordForm").submit(function() {

		var email = $("#email");
		var nowEmail = email.val();//每次失去焦點後的新email
		//符合英文、數字_-. 加 @ 加英文、數字_-.及2~4位數的英文
		var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		if (nowEmail.length < 1) {
			$("#emailInvalid").html("請填寫Email");
			email.removeClass("is-valid");//移除通過
			email.addClass("is-invalid"); //新增警告
			return false;
		} else if (!pattern.test(nowEmail)) {
			$("#emailInvalid").html("請符合Email格式的英文、數字、@");
			email.removeClass("is-valid");//移除通過
			email.addClass("is-invalid"); //新增警告
			return false;
		}
		email.removeClass("is-invalid");//移除警告


		//按鈕load...動畫
		$("#submit").prop("disabled", true);
		$("#submit").html(
			`<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
  			<span class="visually-hidden">Loading...</span>`);

		//如果不一致就查詢資料庫是否有重複email
		var dataForm = $(this).serialize();
		$.ajax({
			method: "post",
			url: "/ForgotPasswordSendMail",
			data: dataForm,
			success: function(data) {
				console.log("success");
				if (data == "pass") {//此處pass代表資料庫沒資料
					console.log("沒資料");
					$("#submit").prop("disabled", false);
					$("#submit").html(`驗證`);
					$("#emailInvalid").html("Email 不存在或尚未驗證");
					email.removeClass("is-valid");//移除通過
					email.addClass("is-invalid"); //新增警告
					return false;
				} else if (data == "fail") {
					console.log("有資料");
					email.removeClass("is-invalid");//移除警告
					location.href = "/forgetWait.Controller";
					return false;
				}
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`)
			}
		});
		return false;
	});





	//確認密碼是否符合長度
	$("#newPassword").blur(function() {
		console.log("blur");
		var jqNewPassword = $("#newPassword");
		var jqPasswordAgain = $("#passwordAgain");
		var newPassword = jqNewPassword.val();
		var newPasswordAgain = jqPasswordAgain.val();
		console.log(newPassword.length);

		if (newPassword.length == 0) {
			console.log("等於0");
			$("#newPasswordInvalid").html("請輸入 6~20 碼的英文、數字");
			jqNewPassword.removeClass("is-valid");//移除通過
			jqNewPassword.addClass("is-invalid"); //新增警告
			return false;
		} else if (newPassword.length >= 1 && newPassword.length < 6) {
			console.log("小於6");
			$("#newPasswordInvalid").html("請輸入 6~20 碼的英文、數字");
			jqNewPassword.removeClass("is-valid");//移除通過
			jqNewPassword.addClass("is-invalid"); //新增警告
			return false;
		} else {
			console.log("其他");
			jqNewPassword.removeClass("is-invalid");//移除警告
			jqNewPassword.addClass("is-valid"); //新增通過
		}
	});

	//確認密碼是否符合長度
	$("#passwordAgain").blur(function() {
		console.log("blur");
		var jqNewPassword = $("#newPassword");
		var jqPasswordAgain = $("#passwordAgain");
		var newPassword = jqNewPassword.val();
		var newPasswordAgain = jqPasswordAgain.val();
		console.log(newPassword.length);
		if (newPassword != newPasswordAgain && newPassword != "") {
			console.log("第一");
			$("#passwordAgainInvalid").html("密碼不符");
			jqPasswordAgain.removeClass("is-valid");//移除通過
			jqPasswordAgain.addClass("is-invalid"); //新增警告
			return false;
		} else if (newPassword == newPasswordAgain && newPasswordAgain == "") {
			console.log("第二");
			$("#passwordAgainInvalid").html("請輸入 6~20 碼的英文、數字");
			jqPasswordAgain.removeClass("is-valid");//移除通過
			jqPasswordAgain.addClass("is-invalid"); //新增警告
			return false;
		} else {
			console.log("第三");
			jqPasswordAgain.removeClass("is-invalid");//移除警告
			jqPasswordAgain.addClass("is-valid"); //新增通過

		}

	});





	//密碼更新
	$("#changePasswordForm").submit(function() {
		var jqNewPassword = $("#newPassword");
		var jqPasswordAgain = $("#passwordAgain");
		var newPassword = jqNewPassword.val();
		var newPasswordAgain = jqPasswordAgain.val();
		//確認密碼是否一致
		if (newPassword != newPasswordAgain && newPassword != "") {
			jqPasswordAgain.removeClass("is-valid");//移除通過
			jqPasswordAgain.addClass("is-invalid"); //新增警告
			jqPasswordAgain.focus();
			return false;
		} else if (newPassword.length == 0) {
			jqPasswordAgain.removeClass("is-valid");//移除通過
			jqPasswordAgain.addClass("is-invalid"); //新增警告
			jqPasswordAgain.focus();
			return false;
		} else if (newPassword.length >= 1 && newPassword.length < 6) {
			console.log("小於6");
			$("#newPasswordInvalid").html("請輸入 6~20 碼的英文、數字");
			jqNewPassword.removeClass("is-valid");//移除通過
			jqNewPassword.addClass("is-invalid"); //新增警告
			return false;
		}

		$.ajax({
			method: "post",
			url: "/UpdateEmailPassword.Controller",
			data: { "newPassword": newPassword },
			success: function(data) {
				console.log(data);
				location.href = '/login.Controller';
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`);
			}
		});
		return false;
	});



});