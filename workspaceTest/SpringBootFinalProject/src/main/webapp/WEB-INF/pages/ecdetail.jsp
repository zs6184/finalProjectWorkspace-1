<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>綠界訂單明細</title>
</head>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/stylesheet/index.css" />
<link rel="stylesheet" href="/stylesheet/ecdetail.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.css">
<!--   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/themes/material_green.css"> -->
<!--CSS在這邊 要注意放在bootstrap樣式表CDN後面 不然權重相同的部分會被bootstrap蓋過去-->
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
<link rel="icon" type="image/png" href="/font/favicon1.png">


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.js"></script>
</head>

<body id="top" style="background-image: url(/image/背景4.jpg)";>

	<div style="background-color: rgb(6, 121, 121, .1);">
		<br>
	</div>
	<!-- 標題logo 部分 -->
	<div class="logo-area text-center">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<a href="/Users/loginIndex.Controller"><img
						src="/image/浪跡2.png" alt=""></a>
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

	<c:choose>
		<c:when test="${empty orders }">
			<div class="container" id="container" style="height: 500px;">
				<div class="emptycart">
					<h1>沒有綠界訂單</h1>
					<p>
						<a href="/product/findallproduct">商品頁面</a>
					</p>
				</div>
			</div>
		</c:when>
		<c:when test="${not empty orders }">
			<div class="item" style="height: 800px;">
				<H1>綠界訂單</H1>
				<c:forEach var="order" items="${orders}">
					<div class="card">
						<div class="ptext">
							<p>
								<span>MerchantTradeNo :</span><br>${order.MerchantTradeNo}
							</p>
							<hr>
							<p>
								<span>PaymentDate :</span><br>${order.PaymentDate}
							</p>
							<p>
								<span>TradeDate :</span><br>${order.TradeDate}
							</p>
							<p>
								<span>ItemName :</span><br>
								<c:forEach var="product" items="${order.ItemName}">
									${product}<br>
								</c:forEach>
							</p>
							<p>
								<span>TradeAmt :</span><br>${order.TradeAmt}
							</p>
							<p>
								<span>PaymentType :</span><br>${order.PaymentType}
							</p>
							<p>
								<span>MerchantID :</span><br>${order.MerchantID}
							</p>
							<p>
								<span>CheckMacValue :</span><br>${order.CheckMacValue}
							</p>
							<p>
								<span>TradeNo :</span><br>${order.TradeNo}
							</p>
							<p>
								<span>TradeStatus :</span><br>${order.TradeStatus}
							</p>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:when>

	</c:choose>



</body>
</html>