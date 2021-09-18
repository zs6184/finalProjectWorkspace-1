$(function () {
    //電話新增時自動寫入密碼欄位
    $("#phoneNumber").on("keyup", function () {
        num = $(this).val();
        $("#password").val(num);

    });


});