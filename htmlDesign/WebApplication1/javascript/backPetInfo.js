// JavaScript source code
$(function () {

    //日期選擇器
    $("input[name='adoptDate']").datepicker({
        dateFormat: 'yy/mm/dd',
        changeYear: true,
        changeMonth: true
    });


});

//刪除資料事件
function del(obj) {
    $(obj).parents("tr").remove();
}

/*function openEdit() {
    window.open("petInfoAdd.html", "EditWindow", "height=600,width=800,top=200,left=600");
}*/