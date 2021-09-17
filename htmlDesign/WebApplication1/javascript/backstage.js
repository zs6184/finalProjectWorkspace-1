$(function () {
    //side
    $("#collapse").on("click", function () {
        if ($("#sidebar").hasClass("close")) {
            $("#sidebar").css({ "width": "" });
            $("#sidebar").removeClass("close");
            $(".center,.itemDetails").css({ "font-size": "" }).css({ "text-align": "" });
            $(".fas").css({ "font-size": "" });
            $(".items").css({ "display": "" });
        } else {
            $("#sidebar").css({ "width": 90 });
            $("#sidebar").addClass("close");
            $(".center,.itemDetails").css({ "font-size": 16 }).css({"text-align":"center"});
            $(".fas").css({ "font-size": 30 });
            $(".items").css({ "display": "none" });
        }
    });

    //$("ul li a").on("click", function () {
        //if ($(this).hasClass("background-c")) {
        //    $(this).css({ "background-color": "" }).css({ "color": "" });
        //    $(this).removeClass("background-c");
        //} else {
        //    $(this).addClass("background-c").siblings().removeClass("background-c");
        //    $(this).css({ "background-color": "#9999CC" }).css({ "color": "white" });
        //    $(this).addClass("background-c").siblings().removeClass("background-c");
        //$(this).addClass("clickA").siblings().removeClass("clickA");

        //}

    //});



});