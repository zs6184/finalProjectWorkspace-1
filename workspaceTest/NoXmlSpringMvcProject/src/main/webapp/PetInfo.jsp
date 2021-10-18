<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>PetInfo</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="stylesheet/petInfo.css" />
<!--CSS here-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0-alpha.1/jquery-ui.min.js"></script>
<script src="javascript/petInfo.js"></script>
<!--JS Here-->


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
		style="background-image: url(image/img/bg-img/cat1.jpeg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="banner-content text-center">
						<h2>寵物領養</h2>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--內容-->
	<div class="container" id="container">
		<!--查詢欄位-->
		<form action="searchdatafront.controller" method="POST" id="srhForm">
			<div class="row justify-content-start" style="margin: 30px;">
				<div class="col-1 text-center">
					<select name="category" id="srhCategory">
						<option value="">不限類別</option>
						<c:forEach var="cateSet" items="${cateSet}">
							<option value="${cateSet}" name="${cateSet}">${cateSet}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-1 text-center">
					<select name="sex" id="srhSex">
						<option value="">不限性別</option>
						<c:forEach var="sexSet" items="${sexSet}">
							<option value="${sexSet}" name="${sexSet}">${sexSet}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-2">
					<button type="submit" class="btn-info text-white btn-sm"
						id="srhBtn">搜尋</button>
				</div>
			</div>
		</form>

		<!--查詢結果概覽-->
		<div class="container" style="margin-bottom: 30px;">
			<div class="row" id="infoContent">
				<c:forEach var="arrPet" items="${arrPet}">
					<div class="row offset-1 col-5 border bg-secondary text-white" id="${arrPet.petId}" style="margin-bottom:20px;border-radius:10px;height:280px; padding:0px;">
						<div class="col-6 h-100  align-items-center">
							<img src="image/f5.jpg" class="col w-100 h-100" />
						</div>
						<div class="row col-6 align-items-center">
							<ul>
								<li><span><i class="fa fa-id-card" aria-hidden="true"></i></span>寵物姓名:${arrPet.petName}</li>	
								<li><span><i class="fa fa-venus-mars" aria-hidden="true"></i></span>性別:${arrPet.sex}</li>	
								<li><span><i class="fa fa-paw" aria-hidden="true"></i></span>類別:${arrPet.category}</li>	
								<li><span><i class="fa fa-tag" aria-hidden="true"></i></span>品種:${arrPet.species}</li>	
							</ul>
							<button type="button" class="btn-sm btn-light">詳細資料</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>

</body>
</html>