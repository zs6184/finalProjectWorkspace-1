// JavaScript source code
$(function () {
    //�s�ystickybar�ĪG
    window.onscroll = function () { stickTop() };
    var topbar = document.getElementById("topbar");
    var distance = topbar.offsetTop; //���otopbar�W�谾���q

    function stickTop() {
        if (window.pageYOffset >= distance) { //�H����Y�b�����q���P�_����
            topbar.classList.add("sticky");
        } else {
            topbar.classList.remove("sticky");
        }
    }
});


