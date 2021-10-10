// JavaScript source code

$(function () {
    $('#owl-one').owlCarousel({
        items: 2,
        margin: 30,
        loop: true,
        center: true,
        autoplay: true,
        nav: true,
        autoplayTimeout: 5000,
        smartSpeed: 2000
    });

    $('#owl-two').owlCarousel({
        items: 7,
        margin: 0,
        loop: true,
        autoplay: true,
        autoplayHoverPause: true,
        autoplayTimeout: 2000, // Autoplay Timeout 1s = 1000ms
        smartSpeed: 2000,
        responsive: {
            0: {
                items: 2
            },
            480: {
                items: 3
            },
            576: {
                items: 4
            },
            992: {
                items: 5
            },
            1500: {
                items: 7
            }

        }
    })
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


function getData() {
    var req = new XMLHttpRequest();
    req.open("get", "http://localhost:8080/indexContent/page1.html");
    req.onload = function () {
        var content = document.getElementById("content");
        $('#content').empty();
        content.innerHTML = this.responseText;

    };
    req.send();
}


function getData2() {
    var req = new XMLHttpRequest();
    req.open("get", "http://localhost:8080/indexContent/page2.html");
    req.onload = function () {
        var content = document.getElementById("content");
        $('#content').empty();
        content.innerHTML = this.responseText;

    };
    req.send();
}
function getData3() {
    var req = new XMLHttpRequest();
    req.open("get", "http://localhost:8080/indexContent/page3.html");
    req.onload = function () {
        var content = document.getElementById("content");
        $('#content').empty();
        content.innerHTML = this.responseText;

    };
    req.send();
}

function getData4() {
    var req = new XMLHttpRequest();
    req.open("get", "http://localhost:8080/indexContent/page4.html");
    req.onload = function () {
        var content = document.getElementById("content");
        $('#content').empty();
        content.innerHTML = this.responseText;

    };
    req.send();
}

function getData5() {
    var req = new XMLHttpRequest();
    req.open("get", "http://localhost:8080/indexContent/page5.html");
    req.onload = function () {
        var content = document.getElementById("content");
        $('#content').empty();
        content.innerHTML = this.responseText;

    };
    req.send();
}
