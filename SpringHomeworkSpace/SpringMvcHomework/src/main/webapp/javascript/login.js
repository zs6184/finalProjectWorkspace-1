/// <reference path="jquery-3.6.0.min.js" />
/// <reference path="jquery-ui.min.js" />

$(function () {

        //ajax非同步
 /*       var formData = $(this).serialize();
        $.ajax({
            method: "post",
            url: "http://localhost:8080/NoXmlSpringMvcProject/checkloginaccount.controller",
            data: formData,
            success: function (data) {
				console.log(data);
                if (data == "pass") {

                        location.href = 'loginIndex.Controller';
                    //}
                } else if (data == "fail") {
                    if ($("#loginStatus").length == 0) {
                        $(".LoginErrMsg").append(`<span id="loginStatus">帳號或密碼錯誤</span>`);
                        $("#loginForm input:first").focus();
                    }
                }
            },
            error: function (jqXHR, textStatus, errThrown) {
                alert(`${textStatus}---${errThrown}`)
            }
        });
        return false;
    });*/
});