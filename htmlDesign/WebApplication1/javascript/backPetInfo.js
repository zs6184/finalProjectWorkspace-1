// JavaScript source code
$(function () {
    //日期選擇器
    $("input[name='adoptDate']").datepicker({
        dateFormat: 'yy/mm/dd',
        changeYear: true,
        changeMonth: true
    });
});

/*function openEdit() {
    window.open("petInfoAdd.html", "EditWindow", "height=600,width=800,top=200,left=600");
}*/