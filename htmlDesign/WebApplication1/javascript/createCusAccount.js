/// <reference path="jquery-3.6.0.min.js" />
/// <reference path="jquery-ui.min.js" />

//建立必填欄位的div函式
function createErrDiv(input) {
    var errDivId = `err_${$(input).attr("id")}`;
    var errDiv = $(`<div id="${errDivId}" class="d-flex align-items-center errMsg col-2">必填欄位</div>`);
    $(input).parent().parent().after(errDiv);
}

//建立密碼錯誤的div函式
function createDifferentDiv(checkPwd) {
    var differentDivId = `dif_${$(checkPwd).attr("id")}`;
    var differentDiv = $(`<div id="${differentDivId}" class="d-flex align-items-center difMsg col-2">密碼不符</div>`);
    $(checkPwd).parent().parent().after(differentDiv);
}

//建立帳號已被使用的div函式
function createUsernameAlreadyTakenDiv(input) {
    console.log(input);
    var uATDivId = `uAT_${input}`;
    var uATDiv = $(`<div id="${uATDivId}" class="d-flex align-items-center uATMsg col-2">已被使用</div>`);
    $("#username").parent().parent().after(uATDiv);
}

//建立電話號碼已被使用的div函式
function createPhoneNumberAlreadyTakenDiv(input) {
    console.log(input);
    var pNATDivId = `pNAT_${input}`;
    var pNATDiv = $(`<div id="${pNATDivId}" class="d-flex align-items-center pNATMsg col-2">已被使用</div>`);
    $("#phoneNumber").parent().parent().after(pNATDiv);
}

function createEmailAlreadyTakenDiv(input) {
    console.log(input);
    var eATDivId = `eAT_${input}`;
    var eATDiv = $(`<div id="${eATDivId}" class="d-flex align-items-center eATMsg col-2">已被使用</div>`);
    $("#email").parent().parent().after(eATDiv);
}

//失去焦點時，比對密碼與再次輸入密碼是否相同
function checkPassword() {
    $("#checkPassword").blur(function () {
        var differentDivId = `#dif_${$(this).attr("id")}`;
        var differentDivMsg = $(differentDivId);
        //當比對不符而且不等於空值時才建立DIV，防止跟必填欄位同時出現
        if ($(this).val() != $("#password").val() && $(this).val()!="") {
            if (differentDivMsg.length <= 0) {
                createDifferentDiv(this);
            }
        } else {
            if (differentDivMsg.length > 0) {
                differentDivMsg.remove();
            }
        }
    });
}

$(function () {
    checkPassword();
    //失去焦點時如果非空值就移除必填欄位
    $("#createCusForm input").blur(function () {
        var errDivId = `#err_${$(this).attr("id")}`;
        var errDivMsg = $(errDivId);
        if ($(this).val() != "") {
                if (errDivMsg.length > 0) {
                    errDivMsg.remove();
                }
            } else {
                if (errDivMsg.length <= 0) {
                    if ($(this).attr("id") != "nickName" && $(this).attr("id") != "address") {//暱稱與地址除外
                        createErrDiv(this);
                    }
                }
            }
    });

    //判斷帳號是否重複
    $("#username").blur(function () {
        var uATDivId = $(`#uAT_username`);
        var dataForm = $(this).serialize();
        $.ajax({
            method: "post",
            url: "http://localhost:8080/Hibernate_Web/CreateCusAccountCheckUsername",
            data: dataForm,
            success: function (data) {
                console.log(data);
                if (data == "pass") {
                    console.log("進判斷");
                    $(`.uATMsg`).remove();//清除已被使用
                    return false;
                }else {
                    if (uATDivId.length <= 0 && $("#username").val() != "") {
                        createUsernameAlreadyTakenDiv("username");
                        return false;
                    } else if (uATDivId.length > 0 && $("#username").val() == "") {
                        $(`.uATMsg`).remove();
                    }
                }
            },
            error: function (jqXHR, textStatus, errThrown) {
                alert(`${textStatus}---${errThrown}`)
            }
        });
        return false;
    });

    //判斷電話是否重複
    $("#phoneNumber").blur(function () {
        var pNATDivId = $(`#pNAT_phoneNumber`);
        var dataForm = $(this).serialize();
        $.ajax({
            method: "post",
            url: "http://localhost:8080/Hibernate_Web/CreateCusAccountCheckPhone",
            data: dataForm,
            success: function (data) {
                console.log(data);
                if (data == "pass") {
                    console.log("進判斷");
                    $(`.pNATMsg`).remove();//清除已被使用
                    return false;
                } else {
                    if (pNATDivId.length <= 0 && $("#phoneNumber").val() != "") {
                        createPhoneNumberAlreadyTakenDiv("phoneNumber");
                        return false;
                    } else if (pNATDivId.length > 0 && $("#phoneNumber").val() == "") {
                        $(`.pNATMsg`).remove();
                    }
                }
            },
            error: function (jqXHR, textStatus, errThrown) {
                alert(`${textStatus}---${errThrown}`)
            }
        });
        return false;
    });

    //判斷email是否重複
    $("#email").blur(function () {
        var eATDivId = $(`#eAT_email`);
        var dataForm = $(this).serialize();
        $.ajax({
            method: "post",
            url: "http://localhost:8080/Hibernate_Web/CreateCusAccountCheckEmail",
            data: dataForm,
            success: function (data) {
                if (data == "pass") {
                    console.log("進判斷");
                    $(`.eATMsg`).remove();//清除已被使用
                    return false;
                } else {
                    if (eATDivId.length <= 0 && $("#email").val() != "") {
                        createEmailAlreadyTakenDiv("email");
                        return false;
                    } else if (eATDivId.length > 0 && $("#email").val() == "") {
                        $(`.eATMsg`).remove();
                    }
                }
            },
            error: function (jqXHR, textStatus, errThrown) {
                alert(`${textStatus}---${errThrown}`)
            }
        });
        return false;
    });

    $("#createCusForm").submit(function () {
        $(".errMsg").remove();//清除重複登入時的必填欄位
        var errs = [];
        //確認欄位是否空白
        $("#account input").each(function () {
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


        $(".btn-secondary").click(function () {
            if (errs.length > 0) {
                $(errs[0]).focus();
                return false; //提早結束submit
            }
        });

        var dataForm = $(this).serialize();
        $.ajax({
            method: "post",
            url: "http://localhost:8080/Hibernate_Web/CreateCusAccount",
            data: dataForm,
            success: function (data) {
                if (data == "pass") {
                    console.log(data);
                    location.href = "login.html";
                }
            },
            error: function (jqXHR, textStatus, errThrown) {
                alert(`${textStatus}---${errThrown}`)
            }
        });
        return false;
    });
});