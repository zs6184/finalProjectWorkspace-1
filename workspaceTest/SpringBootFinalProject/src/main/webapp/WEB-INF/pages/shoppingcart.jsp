<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>購物車</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<!--<link rel="stylesheet" href="/stylesheet/petInfo.css" />-->
<!--CSS here-->
<link rel="stylesheet" href="/stylesheet/shoppingcart.css" />
<link rel="stylesheet" href="/stylesheet/sweetalert.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<script src="/javascript/sweetalert.min.js"></script>
<script src="/javascript/sweetalert-dev.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/javascript/jquery-3.6.0.min.js"></script>
<script src="/javascript/jquery-ui.min.js"></script>
<script src="/javascript/jquery.ui.datepicker-zh-TW.min.js"></script>
<script src="https://cdn.staticfile.org/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="/javascript/shoppingcart.js"></script>

<!--JS Here-->
<!--icon-->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />




<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>



</head>
<body style="background-image: url(image/背景4.jpg)">
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
						<h2>購物車</h2>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--內容-->
	<div class="container" id="container">
		<div class="container" style="width: 960px; clear: both">
			<div class="row">
				<table class="table-hover" style="width: 100%;">
					<thead>
						<tr>
							<th>商品名稱</th>
							<th>數量</th>
							<th>單價</th>
							<th>小計</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="catitem" items="${catitem}">
							<tr id="${catitem.id}">
								<td>${catitem.name}</td>
								<td><i class="fas fa-minus"></i> <input type="number"
									value="${catitem.num}" /> <i class="far fa-plus"></i></td>
								<td class="price">${catitem.price}</td>
								<td class="subtotal">0</td>
								<td>
									<button type="button" class="btn btn-outline-secondary">
										<svg xmlns="http://www.w3.org/2000/svg" width="22" height="22"
											fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                        <path
												d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"></path>
                                        <path fill-rule="evenodd"
												d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"></path>
                                    </svg>
									</button>
								</td>
							</tr>

						</c:forEach>


					</tbody>
				</table>
			</div>
			<hr />
			<div class="bottm row">
				<div class="btn">
					<span>合計:$<span class="totalprice">100</span></span>
					<button type="button" class="btn btn-dark">結帳</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
