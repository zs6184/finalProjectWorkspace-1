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

//�q��L����������
/*function getDataPage() {
    $("#divTest").load("backPetInfo.html #petModalTitle", function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "success") {
            alert("completed !");
        } else {
            alert("failed !" + xhr.status + xhr.statusText);
        }
    });
}*/


