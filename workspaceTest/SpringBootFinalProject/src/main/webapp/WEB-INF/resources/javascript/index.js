
// JavaScript source code
var customers;
var cusID = new Array();
var sessioncheck = $("#sessionUsername").val();
var sessionRole = $("#sessionRole").val();
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


	//抓客戶資料
$.ajax({
		type: "GET",
		url: "/getAllCustomerData.controller",
		datatype: "JSON",
		contentType: "application/json",
		success: function(data) {
			console.log("getCusData Success")
			customers=jQuery.parseJSON(data).cusData; //客戶的資料JSON陣列
			//console.log("cusId=1="+customers.find(i=>i.cusId=="1").cusRealname); //JOE
			
			for(let i=0;i<customers.length;i++){
				cusID.push(customers[i].cusId);	
			}
			console.log("cusID members ="+cusID);
		},
		error:function(){
			console.log("get cusData failed");
		}
	})
	
	

//更新資料-輸入客戶ID後檢測客戶是否存在
	$("#cusid").blur(function(){
		var check = parseInt($(this).val()); //確定轉為整數值

		if(cusID.includes(check)){
			console.log("find cus success");
			$("#name").val(customers.find(i=>i.cusId==`${check}`).cusRealname);
			$("#phone").val(customers.find(i=>i.cusId==`${check}`).phoneNumber);
		}else{
			console.log("Who is it ?");
			$("#cusID,#cusRealName").val("");
			$("#cusID,#cusRealName").attr("placeholder","查無此會員");
		}
	});





//按下領養預約按鈕時清空表單欄位並檢測是否有登入，未登入則跳轉登入頁面
	$("#b2").click(function(){
		$("#cusid,#name,#phone").val("");
		$("#cusid,#name,#phone").attr("placeholder","");
		    console.log("session username="+$("#sessionUsername").val());
            console.log("session role="+$("#sessionRole").val());
		 if($("#sessionRole").val()=="MEMBER"){
				$.ajax({
				type: "GET",
				url: "/users/petreserve/checktheCus",
				datatype: "JSON",
				contentType: "application/json",
				success:function(data){
					console.log('get theCus successfully');
					var theCus = jQuery.parseJSON(data).theCus;
					console.log("cusId="+theCus.cusId)
					$("#cusid").val(`${theCus.cusId}`);
					$("#name").val(`${theCus.cusRealname}`);
					if(theCus.phoneNumber!=null){
					$("#phone").val(`${theCus.phoneNumber}`);
					}
					$("#reservePet").modal("show");					
				},
				error:function(){
					console.log('get the cus fail');
					window.location.replace("http://localhost:8080/login.Controller");
				}
			});
			}else{
				$.ajax({
				type: "GET",
				url: "/users/petreserve/checktheEmp",
				datatype: "JSON",
				contentType: "application/json",
				success:function(data){
					console.log('get theCus successfully');
					var theCus = jQuery.parseJSON(data).theCus;
					console.log("cusId="+theCus.cusId)
					$("#cusid").val(`${theCus.cusId}`);
					$("#name").val(`${theCus.cusRealname}`);
					if(theCus.phoneNumber!=null){
					$("#phone").val(`${theCus.phoneNumber}`);
					}
					$("#reservePet").modal("show");					
				},
				error:function(){
					console.log('get the emp fail');
					window.location.replace("http://localhost:8080/login.Controller");
				}
			});
				
			}
			
	});





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
			cusid: $('#cusid').val(),
            name: $('#name').val(),
            phone: $('#phone').val(),
            peopleNum: $('#peopleNum').val(),
            orderDate: $('#orderDate').val(),
            time: $('#time').val()
        })
		console.log("我進來了");
        $.ajax({
            url: '/users/bookingsRecord',
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
    };



	

})




function booking() {
    console.log('booking ')
}



