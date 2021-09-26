// JavaScript source code
$(function () {
    //製造stickybar效果
    window.onscroll = function () { stickTop() };
    var topbar = document.getElementById("topbar");
    var distance = topbar.offsetTop; //取得topbar上方偏移量

    function stickTop() {
        if (window.pageYOffset >= distance) { //以頁面Y軸偏移量為判斷條件
            topbar.classList.add("sticky");
        } else {
            topbar.classList.remove("sticky");
        }
    }
});


