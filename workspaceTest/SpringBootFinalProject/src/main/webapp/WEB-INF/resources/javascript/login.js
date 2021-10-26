/// <reference path="jquery-3.6.0.min.js" />
/// <reference path="jquery-ui.min.js" />

//在空白的欄位旁邊建立必填欄位的div
function createErrDiv(input) {
	var errDivId = `err_${$(input).attr("id")}`;//err_加上取得input下的id名稱
	var errDiv = $(`<div id="${errDivId}" class="errMsg col-2">必填欄位</div>`);
	$(input).parent().parent().after(errDiv);//在兩個父元素之後加入div
}
$(function() {


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
		
		
		//reCAPTCHA
		/*e.preventDefault();
		grecaptcha.ready(function() {
		console.log("reCAPTCHA進函式");
			grecaptcha.execute(
				'6Lcnq94cAAAAAJNYUP_oO0M2EDZ53BrQguAAKfqq',
				{ action: 'test' }).then(function(token) {
					console.log("reCAPTCHA進函式");
					console.log(token);
					$.ajax({
						method: "post",
						url: "reCAPTCHA.controller",
						data: { "token": token },
						success: function(data) {
							if(data>0.5){
								console.log("humen");
							}else{
								console.log("robot");
								return false;
							}
						},
						error: function(jqXHR, textStatus, errThrown) {
							alert(`${textStatus}---${errThrown}`)
						}
					});
					
				});
		});*/
		
		
		
		
		
		
		
		
		
		
		
		
		

		//ajax非同步
		/*var formData = $(this).serialize();
		$.ajax({
			method: "post",
			url: "/checkloginaccount.controller",
			data: formData,
			success: function(data) {
				console.log(data);
				if (data == "pass") {
					location.href = '/loginIndex.Controller';
				
				} else if (data == "fail") {
					if ($("#loginStatus").length == 0) {
						$(".LoginErrMsg").append(`<span id="loginStatus">帳號或密碼錯誤</span>`);
						$("#loginForm input:first").focus();
						return false;
					}
						return false;
				}
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`);
				return false;
			}
		});
		return false;*/
	});

	//reCAPTCHA
	//var btn = $("#loginForm");
	function onClick() {
		//e.preventDefault();
		grecaptcha.ready(function() {
		console.log("reCAPTCHA進函式");
			grecaptcha.execute(
				'6Lcnq94cAAAAAJNYUP_oO0M2EDZ53BrQguAAKfqq',
				{ action: 'test' }).then(function(token) {
					console.log("reCAPTCHA進函式");
					$.ajax({
						method: "post",
						url: "reCAPTCHA.controller",
						data: { "token": token },
						success: function(data) {
							if(data>0.5){
								console.log("humen")
							}else{
								console.log("robot")
							}
						},
						error: function(jqXHR, textStatus, errThrown) {
							alert(`${textStatus}---${errThrown}`)
						}
					});

				});
		});
	}
});