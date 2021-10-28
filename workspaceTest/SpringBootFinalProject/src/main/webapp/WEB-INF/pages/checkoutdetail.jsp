<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>訂單明細</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<!--<link rel="stylesheet" href="/stylesheet/petInfo.css" />-->
<!--CSS here-->
<link rel="stylesheet" href="/stylesheet/checkoutdetail.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/javascript/jquery-3.6.0.min.js"></script>
<script src="/javascript/jquery-ui.min.js"></script>
<script src="/javascript/jquery.ui.datepicker-zh-TW.min.js"></script>
<script src="/javascript/checkoutdetail.js"></script>
<script src="/javascript/checkout.js"></script>

<!--JS Here-->
<!--icon-->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />




<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>



</head>
<body style="background-image: url(/image/背景4.jpg)">
	<hr>
	<!-- 標題logo 部分 -->
	<div class="logo-area text-center">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<a href="index.html"><img src="image/浪跡2.png" alt=""></a>
					<!--LOGO-->
				</div>
			</div>
		</div>
	</div>
	<hr>

	<!-- nav欄部分 -->
	<div id="topbar">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<ul>
						<li><a href="petInfo.html" target="_self">寵物領養</a></li>
						<li><a href="index.html" target="_self">線上訂位</a></li>
						<li><a href="index.html" target="_self">餐點介紹</a></li>
						<li><a href="index.html" target="_self">公告專區</a></li>
						<li><a href="index.html" target="_self">登入註冊</a></li>
						<li><a href="index.html" target="_self">首頁</a></li>
					</ul>
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
				<a class="home" href="">回首頁</a>
				<c:if test="${order.paymethod == '綠界付款'}">
					<a class="ecpay" href="">綠界付款</a>
				</c:if>
			</div>
		</div>
	</div>

</body>
</html>