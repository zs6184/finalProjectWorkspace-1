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


$(document).ready(function () {
    $('#bookingForm').submit(function (event){
        event.preventDefault(); // 避免執行預設
        $('#loading').addClass('overlay-show').removeClass('overlay-hide')
        $('#submitBtn').prop('disabled', true)
        let json = JSON.stringify({
            name: $('#name').val(),
            phone: $('#phone').val(),
            peopleNum: $('#peopleNum').val(),
            orderDate: $('#orderDate').val(),
            time: $('#time').val()
        })
        $.ajax({
            url: '/backstage/bookings',
            method: 'POST',
            dataType: 'text', // 預期回傳的型態 [html, text, json]
            contentType: 'application/json;charset="utf-8"',
            data: json,
            success: function (data){
                console.log('success', data)
                bootstrap.Modal.getInstance($('#Reservation')).hide()
                openResponseMsg(data)
                // 清除表單
                $('#bookingForm').trigger('reset')
            },
            error: function (xhr, ajaxOptions, thrownError){
                openResponseMsg(xhr.responseText)
            },
            complete: function (){
                // 解鎖按鈕
                $('#submitBtn').prop('disabled', false)
                // close loading
                $('#loading').removeClass('overlay').addClass('overlay-hide')
            }
        })
    })

    function openResponseMsg(msg){

        $('#responseMessage').text(msg)
        $('#reservationMsg').modal('show')

    }
    function closeSuccess(){
        bootstrap.Modal.getInstance($('#reservationMsg')).hide()
    }

})




function booking() {
    console.log('booking ')
}
