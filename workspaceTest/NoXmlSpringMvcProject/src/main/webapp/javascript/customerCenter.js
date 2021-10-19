/// <reference path="jquery-3.6.0.min.js" />
/// <reference path="jquery-ui.min.js" />

$(function() {
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

	//資料送出時，檔案上傳
	$("#customerCenterForm").submit(function() {
		var formData = new FormData(this);
		$.ajax({
			method: "post",
			url: "UpdateCustomer.controller",
			data: formData,
			processData:false,//上傳圖片必須設成false，不要讓系統轉成K=V的型態，new FormData(this)已經幫我們設定好格式
			contentType:false,//上傳圖片必須設成false，default: 'application/x-www-form-urlencoded; charset=UTF-8'，但是我們不要設成這個，new FormData(this)已經幫我們設定好格式
			success: function(data) {
				location.href = 'SelectCustomer.controller';
			},
			error: function(jqXHR, textStatus, errThrown) {
				alert(`${textStatus}---${errThrown}`);
			}
		});
		return false;
	});

});