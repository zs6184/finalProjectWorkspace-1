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
$("#mypic").change(function(){
		previewImg(this.files);
	});

function previewImg(files){
	if(files.length==0) 
	return;
	var file = files[0];
	var fr = new FileReader();
	fr.onload = function(){
		$("#imgPreview img").attr({src:fr.result});
	};
	fr.readAsDataURL(file);
}
