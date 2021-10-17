
$(function () {
    unirowprice();
    totalprice();
    //減數量
    $('.fa-minus').click(function () {
        var num = $(this).siblings('input').val();
        if (num == 1) {
            $(this).siblings('input').val(1);
        } else {
            var price = parseInt($(this).parent().parent().children().filter('td.price').text());
            var numm = parseInt($(this).parent().parent().children().filter('td.subtotal').text((num - 1) * price));
            $(this).siblings('input').val(num - 1);
        }
        totalprice();
    });
    //加數量
    $('.fa-plus').click(function () {
        var num = parseInt($(this).siblings('input').val());
        var price = parseInt($(this).parent().parent().children().filter('td.price').text());
        var numm = parseInt($(this).parent().parent().children().filter('td.subtotal').text((num + 1) * price));
        $(this).siblings('input').val(num + 1);
        totalprice();
    });
    //直接使用輸入框變更數量
    $('td input').change(function () {
        var num = $(this).val();
        var price = parseInt($(this).parent().parent().children().filter('td.price').text());
        var numm = parseInt($(this).parent().parent().children().filter('td.subtotal').text(num * price));
        totalprice();
    });
    //更新總計
    function totalprice() {
        var row = $('tbody tr');
        var totalprice = 0;
        $.each(row, function (index, value) {
            totalprice += parseInt($(this).find('.subtotal').text());
        });
        $('.totalprice').text(totalprice);
    }
    //初始化面計算所有小計
    function unirowprice() {
        var row = $('tbody tr');
        $.each(row, function (index, value) {
            var price = parseInt($(this).find('.price').text());
            var num = parseInt($(this).children().find('input').val());
            $(this).find('.subtotal').text(price * num);
        });
    }
    //刪除
    $('td button[type="button"]').click(function () {
        $(this).closest('tr').remove();
        totalprice();
    });



});

