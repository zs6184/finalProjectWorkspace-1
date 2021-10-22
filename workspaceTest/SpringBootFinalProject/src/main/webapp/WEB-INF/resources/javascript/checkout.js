
$(function () {
    //計算總金額
    //var row = $('tbody tr');
    //var endprice = 0;
    //$.each(row, function () {
    //    var num = $(this).find('td').eq(1).text();
    //    var price = $(this).find('td').eq(2).text();
    //    endprice += (num * price);
    //    $(this).find('td').eq(3).text(num * price);
    //});
    //$('span.orangeprice').text(`$ ${(endprice)}`);
    //$('span.endprice').text(`$ ${(endprice)}`);
    orangetotalprice();
    $('span.orangeprice').hide();
    //ajax
    $("#promo").submit(function () {
        //$('.discount').html("");
        var cvalue = $("#promoinput").val();
        console.log(`${cvalue}`);
        $.ajax({
            method: 'POST',
            url: '/NoXmlSpringMvcProject/back.checkcoupon',
            data: { ccode: cvalue },
            success: function (data) {
				console.log("test");
                //實作
                switch (data[1]) {
                    case 0:
                        $(".discount").html("")
                        $("#promoinput").attr("placeholder", "查無折扣碼");
                        $("#promoinput").val("");
                        orangetotalprice();
                        break;
                    case 1:
                        $(".discount").html("")
                        $("#promoinput").attr("placeholder", "折扣碼過期");
                        $("#promoinput").val("");
                        orangetotalprice();
                        break;
                    case 2:
                        $("#promoinput").attr("placeholder", "折扣碼正確");
                        $("#promoinput").val("");
                        $(".discount").html(`
                                <td>${data[0].couponName}</td>
                                <td>${1}</td>
                                <td>-${data[0].couponDiscount}</td>
                                <td>-${data[0].couponDiscount}</td>`
                        );
                        //取總金額
                        var row = $('tbody tr');
                        var endprice = 0;
                        $.each(row, function () {
                            var num = $(this).find('td').eq(1).text();
                            var price = $(this).find('td').eq(2).text();
                            endprice += (num * price);
                        });
                        //計算折扣金額
                        var discount = (data[0].couponDiscount);
                        var totalprice = endprice - discount;
                        $('span.endprice').text(`$ ${totalprice}`);
                        $('span.orangeprice').show();

                        break;
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(`錯誤${jqXHR} --> ${textStatus} --> ${errorThrown}`);
            }
        });
        return false;
    });//submit結束

});

function orangetotalprice() {
    var row = $('tbody tr');
    var endprice = 0;
    $.each(row, function () {
        var num = $(this).find('td').eq(1).text();
        var price = $(this).find('td').eq(2).text();
        endprice += (num * price);
        $(this).find('td').eq(3).text(num * price);
    });
    $('span.endprice').text(`$ ${(endprice)}`);
    $('span.orangeprice').text(`$ ${(endprice)}`);
    $('span.orangeprice').hide();
}

