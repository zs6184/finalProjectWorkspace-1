// JavaScript source code
$(function () {

    //�����ܾ�
    $("input[name='adoptDate']").datepicker({
        dateFormat: 'yy/mm/dd',
        changeYear: true,
        changeMonth: true
    });


});

//�R����ƨƥ�
function del(obj) {
    $(obj).parents("tr").remove();
}

/*function openEdit() {
    window.open("petInfoAdd.html", "EditWindow", "height=600,width=800,top=200,left=600");
}*/