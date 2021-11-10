<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單明細</title>
</head>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/stylesheet/checkoutdetail.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/stylesheet/index.css" />
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
<script src="/javascript/checkoutdetail.js"></script>
<script src="/javascript/checkout.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
	integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="/javascript/index.js"></script>
<script src="/javascript/customerCenter.js"></script>
<link rel="icon" type="image/png" href="/font/favicon1.png">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.js"></script>
	<link rel="icon" type="image/png"  href="/font/favicon1.png">
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

	<input type="hidden" id="sessionUsername"
		value="<%=session.getAttribute("username")%>">
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
	<!--橫幅圖片區域-->
	<div class="banner-area bg-img"
		style="background-image: url(/image/img/bg-img/cat1.jpeg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="banner-content text-center">
						<h2>訂單明細</h2>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--內容-->
	<div class="container" id="container" style="height: 700px">
		<div class="container ">
			<div class="row step ">
				<div class="col-sm-12 widget ">訂單狀態:新訂單</div>
			</div>
			<div class="row justify-content-around">
				<!--左邊資訊-->
				<div class="col-sm-6 widget  bg-light">
					<table class="table-hover" style="width: 100%">
						<thead>
							<tr>
								<th>商品名稱</th>
								<th>數量</th>
								<th>單價</th>
								<th>小計</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="catitem" items="${catitem}">
								<tr id="${catitem.id}">
									<td>${catitem.name}</td>
									<td>${catitem.num}</td>
									<td class="price">${catitem.price}</td>
									<td class="subtotal">0</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<c:if test="${ not empty coupon}">
								<tr>
									<td>${coupon.couponName}</td>
									<td>1</td>
									<td>- ${coupon.couponDiscount}</td>
									<td>- ${coupon.couponDiscount}</td>
								</tr>
							</c:if>
						</tfoot>
					</table>

				</div>
				<!--右邊資訊-->
				<div class="col-sm-6 widget">
					<p>訂單編號:${order.id}</p>
					<p>會員編號:${order.customer.cusId}</p>
					<p>
						訂購時間:
						<fmt:formatDate value="${order.ordertime}"
							pattern="yyyy-MM-dd hh:mm" />
					</p>
					<p>
						取餐時間:
						<fmt:formatDate value="${order.pickuptime}"
							pattern="yyyy-MM-dd hh:mm" />
					</p>
					<p>付款方式:${order.paymethod}</p>
					<p>訂單金額:${order.total}</p>
					<p>姓名:${order.customer.cusRealname}</p>
					<p>行動電話:${order.customer.phoneNumber}</p>
				</div>
			</div>
			<!-- 回首頁&付款 -->
			<div class="foot">
				<a class="home" href="/Users/loginIndex.Controller">回首頁</a>
				<c:if test="${order.paymethod == '綠界付款'}">
					<a class="ecpay" href="/ec/ECPayServer/${order.id}">信用卡付款</a>
				</c:if>
			</div>
		</div>
	</div>



</body>
</html>