// JavaScript source code
$(function () {

    //日期選擇器
    $("input[name='adoptDate']").datepicker({
        dateFormat: 'yy/mm/dd',
        changeYear: true,
        changeMonth: true
    });

    $("#renew").click(function () {
        del(this);
    });

});

//刪除資料事件
function del(obj) {
    $(obj).parents("tr").remove();
}


function renewData() {
    $.ajax({
        type: "GET",
        url: "/PetInfoLoad",
        dataType: "text",
        success: function (data, textStatus) {
            alert(data);
        },
    });
}

/*function openEdit() {
    window.open("petInfoAdd.html", "EditWindow", "height=600,width=800,top=200,left=600");
}*/