/// <reference path="jquery-3.6.0.min.js" />
/// <reference path="jquery-ui.min.js" />

//載入變更密碼的表單
function changePasswordLoad() {
	console.log("load");
	var url = "/Users/ChangePassword.Controller";
	$('#join').load(url); //有點小問題 //如果不抓整個html的話js無法使用
	return false;
}
//載入變更信箱的表單
function changeEmailLoad() {
	console.log("load");
	var url = "/Users/ChangeEmail.Controller";
	$('#join').load(url); //有點小問題 //如果不抓整個html的話js無法使用
	return false;
}


$(function() {

	//確認權限，如果為EMPLOYEE就顯示後台按鈕
	var emp = $(".backstage").hasClass("EMPLOYEE");
	var admin = $(".backstage").hasClass("ADMIN");

	console.log(emp);
	console.log(admin);
	if (emp || admin) {
		$(".backstage").each(function() {
			$(this).find("li:first").after(`<li id="whp5"><a id="backstage" href="/Backstage/SelectCustomerAll.Controller"
											style="font-size: 1.1em;color:black"
											class="dropdown-item d-flex justify-content-center"
											target="_self">後台管理</a></li>`);
		});
	}

	//side
	$("#collapse").on("click", function() {
		if ($("#sidebar").hasClass("close")) {
			$("#sidebar").css({ "width": "" });
			$("#sidebar").removeClass("close");
			$(".center,.itemDetails").css({ "font-size": "" }).css({ "text-align": "" });
			$(".fas").css({ "font-size": "" });
			$(".items").css({ "display": "" });
		} else {
			$("#sidebar").css({ "width": 90 });
			$("#sidebar").addClass("close");
			$(".center,.itemDetails").css({ "font-size": 16 }).css({ "text-align": "center" });
			$(".fas").css({ "font-size": 30 });
			$(".items").css({ "display": "none" });
		}
	});

	$(".list-unstyled li").on("click", function() {
		//.list-unstyled li子元素下的a新增class，回到父元素，
		//遍歷父元素尋找除自己以外的同層元素，再進到子元素移除class
		$(this).find("a").addClass("sidebarLight01").parent().siblings().find("a").removeClass("sidebarLight01");
	});

	//選擇圖片時，立即瀏覽圖片
	$("#file").change(function() {
		previewImg(this.files);
	});

	function previewImg(files) {
		if (files.length == 0) {//如果沒有檔案就結束
			return;
		}

		var file = files[0];//變更的第一張圖片
		var fr = new FileReader();//FileReader()利用非同步讀取檔案

		//fr.onload檔案讀取完後執行
		fr.onload = function() {
			$("#imgPreview img").attr({ src: fr.result });//fr.result:讀入的資料內容
		}
		fr.readAsDataURL(file);//將讀入的內容轉成 data:URL 字串的資料
	}

	//進入網頁時根據資料庫回傳的性別資料勾選欄位
	if ($("#gender").hasClass("male")) {
		$("#male").prop("checked", "true");
	} else {
		$("#female").prop("checked", "true");
	}

	//判斷電話是否重複
	var myPhoneNumber = $("#phoneNumber").val();
	console.log(myPhoneNumber);
	$("#phoneNumber").blur(function() {
		var phoneNumber = $("#phoneNumber");
		var nowPhone = $(this).val();//每次失去焦點後的新電話號碼
		var num = /[0]{1}[9]{1}\d{8}/;//須符合09開頭並且10碼的數字
		var zh = /[\u4e00-\u9fa5a-zA-Z]/; //中、英文格式
		//判斷是否跟剛從資料庫載入時一致
		if (nowPhone != myPhoneNumber) {
			if (zh.test(nowPhone)) {
				$("#phoneInvalid").html("請勿填寫中、英文");
				phoneNumber.removeClass("is-valid");//移除通過
				phoneNumber.addClass("is-invalid"); //新增警告
				return false;
			} else {
				if (nowPhone.length < 10) {
					$("#phoneInvalid").html("電話號碼小於 10 碼");
					phoneNumber.removeClass("is-valid");//移除通過
					phoneNumber.addClass("is-invalid"); //新增警告
					return false;
				} else if (!num.test(nowPhone)) {
					$("#phoneInvalid").html("請填寫 09 開頭符合電話號碼格式");
					phoneNumber.removeClass("is-valid");//移除通過
					phoneNumber.addClass("is-invalid"); //新增警告
					return false;
				}
			}

			//如果不一致就查詢資料庫是否有重複電話
			var dataForm = $(this).serialize();
			$.ajax({
				method: "post",
				url: "/Users/CustomerCenterCheckPhone.controller",
				data: dataForm,
				success: function(data) {
					console.log(data);
					if (data == "pass") {
						phoneNumber.removeClass("is-invalid");//移除警告
						phoneNumber.addClass("is-valid"); //新增通過
						return false;
					} else {
						$("#phoneInvalid").html("電話號碼已被使用");
						phoneNumber.removeClass("is-valid");//移除通過
						phoneNumber.addClass("is-invalid"); //新增警告
						return false;
					}
				},
				error: function(jqXHR, textStatus, errThrown) {
					alert(`${textStatus}---${errThrown}`)
				}
			});
			return false;
		} else { //移除樣式，保持原樣
			phoneNumber.removeClass("is-invalid");
			phoneNumber.removeClass("is-valid");
		}
	});


	var myEmail = $("#email").val();
	console.log(myEmail);
	//判斷email是否重複
	$("#email").blur(function() {
		var email = $("#email");
		var nowEmail = $(this).val();//每次失去焦點後的新email
		//符合英文、數字_-. 加 @ 加英文、數字_-.及2~4位數的英文
		var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		//判斷是否跟剛從資料庫載入時一致
		if (nowEmail != myEmail) {
			if (!pattern.test(nowEmail)) {
				$("#emailInvalid").html("請符合Email格式的英文、數字、@");
				email.removeClass("is-valid");//移除通過
				email.addClass("is-invalid"); //新增警告
				return false;
			} else {
				if (nowEmail.length < 1) {
					$("#emailInvalid").html("請填寫Email");
					email.removeClass("is-valid");//移除通過
					email.addClass("is-invalid"); //新增警告
					return false;
				}
			}


			//如果不一致就查詢資料庫是否有重複email
			var dataForm = $(this).serialize();
			$.ajax({
				method: "post",
				url: "/Users/CustomerCenterCheckEmail.controller",
				data: dataForm,
				success: function(data) {
					if (data == "pass") {
						email.removeClass("is-invalid");//移除警告
						email.addClass("is-valid"); //新增通過
						return false;
					} else {
						$("#emailInvalid").html("Email 已被使用");
						email.removeClass("is-valid");//移除通過
						email.addClass("is-invalid"); //新增警告
						return false;
					}
				},
				error: function(jqXHR, textStatus, errThrown) {
					alert(`${textStatus}---${errThrown}`)
				}
			});
			return false;
		} else { //移除樣式，保持原樣
			email.removeClass("is-invalid");
			email.removeClass("is-valid");
		}
	});


	//資料送出時，檔案上傳
	$("#customerCenterForm").submit(function() {

		//判斷電話及email是否為不可使用的
		if ($("#phoneNumber").hasClass("is-invalid")) {
			$("#phoneNumber").focus();
			return false;
		}

		var formData = new FormData(this);
		$.ajax({
			method: "post",
			url: "/Users/UpdateCustomer.controller",
			data: formData,
			processData: false,//上傳圖片必須設成false，不要讓系統轉成K=V的型態，new FormData(this)已經幫我們設定好格式
			contentType: false,//上傳圖片必須設成false，default: 'application/x-www-form-urlencoded; charset=UTF-8'，但是我們不要設成這個，new FormData(this)已經幫我們設定好格式
			success: function(data) {
				location.href = '/Users/SelectCustomer.controller';
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`);
			}
		});
		return false;
	});

	//確認密碼是否正確
	$("#checkPasswordForm").submit(function() {
		var checkPassword = $("#checkPassword");
		var password = checkPassword.val();
		console.log(password);

		$.ajax({
			method: "post",
			url: "/Users/CheckPasswordBT.Controller",
			data: { "password": password },
			success: function(data) {
				console.log(data);
				if (data == "pass") {
					checkPassword.removeClass("is-invalid");//移除警告
					changePasswordLoad();
					return false;
				} else {
					$("#checkPasswordInvalid").html("密碼錯誤");
					checkPassword.removeClass("is-valid");//移除通過
					checkPassword.addClass("is-invalid"); //新增警告
					return false;
				}
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`);
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
			jqPasswordAgain.removeClass("is-valid");//移除通過
			jqPasswordAgain.addClass("is-invalid"); //新增警告
			return false;
		} else {
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
			$("#newPasswordInvalid").html("請輸入 6~20 碼的英文、數字");
			jqNewPassword.removeClass("is-valid");//移除通過
			jqNewPassword.addClass("is-invalid"); //新增警告
			jqNewPassword.focus();
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
			url: "/Users/UpdatePassword.Controller",
			data: { "newPassword": newPassword },
			success: function(data) {
				console.log(data);
				location.href = '/Users/SelectCustomer.controller#information';
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`);
			}
		});
		return false;
	});

	//確認密碼是否正確
	$("#emailCheckPasswordForm").submit(function() {
		var checkPassword = $("#checkPassword");
		var password = checkPassword.val();
		console.log(password);

		$.ajax({
			method: "post",
			url: "/Users/CheckPasswordBT.Controller",
			data: { "password": password },
			success: function(data) {
				console.log(data);
				if (data == "pass") {
					checkPassword.removeClass("is-invalid");//移除警告
					changeEmailLoad();
					return false;
				} else {
					$("#checkPasswordInvalid").html("密碼錯誤");
					checkPassword.removeClass("is-valid");//移除通過
					checkPassword.addClass("is-invalid"); //新增警告
					return false;
				}
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`);
			}
		});
		return false;
	});


	//變更email
	$("#changeEmailForm").submit(function() {
		var jqEmail = $("#email");
		var email = jqEmail.val();
		if (email == "" || email.length == 0) {
			jqEmail.focus();
			$("#emailInvalid").html("請填寫Email");
			jqEmail.removeClass("is-valid");//移除通過
			jqEmail.addClass("is-invalid"); //新增警告
			console.log("email已存在");
			return false;
		}

		//符合英文、數字_-. 加 @ 加英文、數字_-.及2~4位數的英文
		var pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		//判斷是否跟剛從資料庫載入時一致
		if (!pattern.test(email)) {
			$("#emailInvalid").html("請符合Email格式的英文、數字、@");
			jqEmail.removeClass("is-valid");//移除通過
			jqEmail.addClass("is-invalid"); //新增警告
			jqEmail.focus();
			return false;
		} else if (email.length < 1) {
			$("#emailInvalid").html("請填寫Email");
			jqEmail.removeClass("is-valid");//移除通過
			jqEmail.addClass("is-invalid"); //新增警告
			jqEmail.focus();
			return false;
		}
		jqEmail.removeClass("is-invalid");
		jqEmail.addClass("is-valid");



		console.log("email可以使用");
		//按鈕load...動畫
		$("#submit").prop("disabled", true);
		$("#submit").html(
			`<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
  			<span class="visually-hidden">Loading...</span>`);
		$.ajax({
			method: "post",
			url: "/Users/ChangeEmailSendMail.Controller",
			data: { "email": email },
			success: function(data) {
				console.log(data);
				$("#datasource").find("form").after().html(
					`<div style="text-align: center; margin: 120px" class="">
					<p class="fs-6">信件已寄出，請至信箱完成驗證。</p>
				</div>`);
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`);
			}
		});
		return false;
	});

});