<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>付款成功</title>
<link rel="stylesheet" href="/stylesheet/ecpaysuccess.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/stylesheet/index.css" />
<script src="/javascript/sweetalert.min.js"></script>
<script src="/javascript/sweetalert-dev.js"></script>
<!-- icon -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.css" />

<!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/themes/material_green.css"> -->
<!--CSS在這邊 要注意放在bootstrap樣式表CDN後面 不然權重相同的部分會被bootstrap蓋過去-->
<link rel="stylesheet" href="/stylesheet/sweetalert.css" />

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0-alpha.1/jquery-ui.min.js"></script>
<script src="https://accounts.google.com/gsi/client" async defer></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
	integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link href="/stylesheet/customerCenter.css" rel="stylesheet" />
<!--javaScript掛到這邊-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
	integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="/javascript/index.js"></script>
<script src="/javascript/customerCenter.js"></script>
<script src="/javascript/shoppingcart.js"></script>

<script
	src="https://cdn.staticfile.org/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<link rel="icon" type="image/png" href="/font/favicon1.png">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100&display=swap" rel="stylesheet">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.js"></script>
</head>

<body id="top" style="background-image: url(/image/背景4.jpg)" />
<!-- 使用者 -->
<input type="hidden" id="sessionUsername"
	value="<%=session.getAttribute("username")%>">

<div style="background-color: rgb(6, 121, 121, .1)">
	<br>
</div>
<!-- 標題logo 部分 -->
<div class="logo-area text-center">
	<div class="container h-100">
		<div class="row h-100 align-items-center">
			<div class="col-12">
				<a href="/Users/loginIndex.Controller"><img src="/image/浪跡2.png"
					alt=""></a>
			</div>
		</div>
	</div>
</div>
<hr>


<!-- nav欄部分 -->
<div>
	<div id="topbar">
		<div class="container h-100">
			<div class="row h-100 align-items-center topbar">
				<div class="col-12">

					<ul>
						<li>
							<div class="dropdown absolute backstage ${role}">
								<button class="btn btn-link dropdown-toggle " type="button"
									id="cusCenterDropdown" data-bs-toggle="dropdown">${realName}</button>
								<ul class="dropdown-menu p-0" role="button">
									<li id="whp1"><a
										href="/Users/SelectCustomer.controller#information"
										style="font-size: 1.1em"
										class="dropdown-item d-flex justify-content-center"
										target="_self">會員中心</a></li>
									<li id="whp2" class="m-0">
										<form action="/Users/logout.Controller" method="post">
											<button type="submit"
												class="dropdown-item d-flex justify-content-center"
												value="logout">登出</button>
										</form>
									</li>
								</ul>
							</div>
						</li>
						<li><a href="/pet/petinfo.controller" target="_self">寵物領養</a></li>
						<li><a href="#Reservation" role="button"
							data-bs-toggle="modal" target="_self">線上訂位</a></li>
						<li><a href="/product/findallproduct" target="_self">餐點介紹</a></li>
						<li><a href="#t1" target="_self">活動訊息</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

</div>
<div class="container" id="container" style="height: 600px;">
	<div class="emptycart">
		<h1>付款成功!</h1>
		<p>親愛的 ${user} 您好</p>
		<p>訂單編號:${orderid}</p>
		<p>綠界編號:${merchantTradeNo}</p>
		<p>付款日期:${paymentDate}</p>
		<p>付款金額:${tradeAmt}</p>
	</div>
</div>

</body>
</html>