// JavaScript source code
$(function () {
    //日期選擇器
    $("input[name='adoptDate']").datepicker({
        dateFormat: 'yy/mm/dd',
        changeYear: true,
        changeMonth: true
    });

});
