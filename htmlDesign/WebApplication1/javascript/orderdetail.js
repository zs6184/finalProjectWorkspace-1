$(function () {
    $('.preloader').hide()
    console.log("sdadasdas");
    $("a[class='godetail']").on('click', function () {
        var x = $(this).parent().siblings('th').text();
        console.log(x);
        $(".orderdetail").css({ "right": "0px" });
        $(".orderdetail").addClass('show');
        $(this).siblings('.preloader').show();


    })
    //$(document).click(function (e) {
    //    console.log($(".orderdetail").hasClass('show'));
    //    if ($(".orderdetail").hasClass('show')) {
    //        $(".orderdetail").css({ "right": "0" });
    //        $(".orderdetail").removeClass('show');
    //    } else {
    //        $(".orderdetail").css({ "right": "-1000px" });
    //    }
    //})
    $('.wrap button').click(function (e) {
        console.log($(".orderdetail").hasClass('show'));
        var con = $(".orderdetail")
        if ($(".orderdetail").hasClass('show') ) {
            $(".orderdetail").css({ "right": "-1000px" })
            $(".orderdetail").removeClass('show');
            $('.preloader').hide()

        }
    });

    $('.wrap button').hover(function () {
        console.log('hihi');
        $('.wrap button i').removeClass('bi-x-circle').addClass('bi-x-circle-fill')
    }, function () {
        $('.wrap button i').removeClass('bi-x-circle-fill').addClass('bi-x-circle')
    });
    
    step(4);
});

function step(state) {
    switch (state) {
        case 0:
            $('#progress').html("<li><div class='node  grey'></div><p>準備中</p></li>"+
                "<li><div class='divider grey '></div></li>"+
                "<li><div class='node grey'></div><p>製作中</p></li>"+
                "<li><div class='divider grey'></div></li>"+
                "<li><div class='node grey'></div><p>待取餐</p></li>"+
                "<li><div class='divider grey'></div></li>"+
                "<li><div class='node grey'></div><p>取餐完成</p></li>"+
                "<li><div class='node red'></div><p>取消</p></li>");
            break;
        case 1:
            $('#progress').html("<li><div class='node  green'></div><p>準備中</p></li>" +
                "<li><div class='divider green '></div></li>" +
                "<li><div class='node grey'></div><p>製作中</p></li>" +
                "<li><div class='divider grey'></div></li>" +
                "<li><div class='node grey'></div><p>待取餐</p></li>" +
                "<li><div class='divider grey'></div></li>" +
                "<li><div class='node grey'></div><p>取餐完成</p></li>" +
                "<li><div class='node grey'></div><p>取消</p></li>");
            break;
        case 2:
            $('#progress').html("<li><div class='node  green'></div><p>準備中</p></li>" +
                "<li><div class='divider green '></div></li>" +
                "<li><div class='node green'></div><p>製作中</p></li>" +
                "<li><div class='divider green'></div></li>" +
                "<li><div class='node grey'></div><p>待取餐</p></li>" +
                "<li><div class='divider grey'></div></li>" +
                "<li><div class='node grey'></div><p>取餐完成</p></li>" +
                "<li><div class='node grey'></div><p>取消</p></li>");
            break;
        case 3:
            $('#progress').html("<li><div class='node  green'></div><p>準備中</p></li>" +
                "<li><div class='divider green '></div></li>" +
                "<li><div class='node green'></div><p>製作中</p></li>" +
                "<li><div class='divider green'></div></li>" +
                "<li><div class='node green'></div><p>待取餐</p></li>" +
                "<li><div class='divider green'></div></li>" +
                "<li><div class='node grey'></div><p>取餐完成</p></li>" +
                "<li><div class='node grey'></div><p>取消</p></li>");
            break;
        case 4:
            $('#progress').html("<li><div class='node  green'></div><p>準備中</p></li>" +
                "<li><div class='divider green '></div></li>" +
                "<li><div class='node green'></div><p>製作中</p></li>" +
                "<li><div class='divider green'></div></li>" +
                "<li><div class='node green'></div><p>待取餐</p></li>" +
                "<li><div class='divider green'></div></li>" +
                "<li><div class='node blue'></div><p>取餐完成</p></li>" +
                "<li><div class='node grey'></div><p>取消</p></li>");
            break;
    }
        


}





