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

    $(".list-unstyled li").on("click", function () {
        //.list-unstyled li子元素下的a新增class，回到父元素，
        //遍歷父元素尋找除自己以外的同層元素，再進到子元素移除class
        $(this).find("a").addClass("sidebarLight01").parent().siblings().find("a").removeClass("sidebarLight01");
    });

    $(".collapse li").on("click", function () {
        $(this).find("a").addClass("sidebarLight02").parent().siblings().find("a").removeClass("sidebarLight02");
        
    });

});