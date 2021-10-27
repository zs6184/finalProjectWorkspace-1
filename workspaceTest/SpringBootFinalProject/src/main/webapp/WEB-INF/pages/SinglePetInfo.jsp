<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>SinglePetInfo</title>
<!--CSS here-->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<!--bootstrap & jQuery-ui-->
<link href="/stylesheet/bootstrap.min.css" rel="stylesheet" />
<link href="/stylesheet/jquery-ui.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/stylesheet/singlePetInfo.css" />
<!--JS Here-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"></script>
<script src="/javascript/jquery-3.6.0.min.js"></script>
<script src="/javascript/jquery-ui.min.js"></script>
<!--datepicker-ui中文補丁-->
<script src="/javascript/jquery.ui.datepicker-zh-TW.min.js"></script>
<script src="/javascript/SinglePetInfo.js"></script>
</head>
<body style="background-image: url(/image/背景4.jpg)">
	<hr>
	<!-- 標題logo 部分 -->
	<div class="logo-area text-center">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<a href="/index.html"><img src="/image/浪跡2.png" alt=""></a>
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
						<li><a href="/pet/petinfo.controller" target="_self">寵物領養</a></li>
						<li><a href="/index.html" target="_self">線上訂位</a></li>
						<li><a href="/index.html" target="_self">餐點介紹</a></li>
						<li><a href="/index.html" target="_self">公告專區</a></li>
						<li><a href="/index.html" target="_self">登入註冊</a></li>
						<li><a href="/index.html" target="_self">首頁</a></li>
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
						<h2> <a href="/pet/petinfo.controller" style="text-decoration: none; color:honeydew;">寵物領養</a> >> ${thePet.petName} </h2>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--內容-->
	<div class="container" id="container">
		<!--寵物詳細資訊-->
		<div class="container" style="margin-top: 30px;margin-bottom:30px;">
			<div class="row justify-content-center" id="infoContent">
				<div class="row border text-white align-items-center selectResult" id="${thePet.petId}"
					style="background-image: url(/image/img/bg-img/b11.jpg);font-size:1.25em;">
					<div class="col-6 h-100  align-items-center">
						<img src="data:image/png;base64,${baseStr}"
							class="w-100 h-100" />
					</div>
					<div class="col-6">
						<div style="margin-bottom:50px;">
							<ul>
								<li><span><i class="fa fa-id-card" aria-hidden="true"></i>&nbsp</span><span>寵物姓名:${thePet.petName}</span></li>
								<li><span><i class="fa fa-venus-mars" aria-hidden="true">&nbsp</i></span><span>性別:${thePet.sex}</span></li>
								<li><span><i class="fa fa-paw" aria-hidden="true"></i>&nbsp</span><span>類別:${thePet.category}</span></li>
								<li><span><i class="fa fa-tag" aria-hidden="true"></i>&nbsp</span><span>品種:${thePet.species}</span></li>
								<li><span><i class="fa fa-tag" aria-hidden="true"></i>&nbsp</span><span>年紀:${thePet.age}</span></li>
								<li><span><i class="fa fa-tag" aria-hidden="true"></i>&nbsp</span><span>結紮:${thePet.fixStatus}</span></li>
							</ul>
						</div>
						<div class="row justify-content-center">
							<div>備註:</div>							
							<textarea class="col-10" style="resize:none;padding:0px;margin-bottom:30px;height:200px;overflow:true" 
									 readonly>${thePet.note}</textarea>
						</div>
						<div class="row justify-content-end">
							<button type="button" class="col-3 btn btn-danger text-white" id="reserveBtn"
									data-bs-toggle="modal" data-bs-target="#reservePet">預約領養</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--預約看寵Modal-->
	<div class="modal fade" id="reservePet" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="petModalTitle">寵物資料</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form action="#" method="POST" id="reserveForm">
						<div class="row">
							<div class="text-center" id="mainbox">
								<fieldset>
									<legend>寵物資訊</legend>
										<div>
											<label for="petId"><span>寵物編號</span></label> 
											<input type="text" id="petId" name="petId" value="${thePet.petId}" readonly />
										</div>
										<div>
											<label for="petName"><span>寵物名</span></label> 
											<input type="text" id="petName" name="petName" value="${thePet.petName}" readonly />
										</div>
								</fieldset>
								<hr/>
								<fieldset>
									<legend>預約資訊</legend>
										<div>
											<label for="cusId"><span>會員編號</span></label> 
											<input type="text" id="cusId" name="cusId" class="requiredValue"/>
										</div>
										<div>
											<label for="cusRealname"><span>會員姓名</span></label> 
											<input type="text" id="cusRealname" name="cusRealname" readonly/>
										</div>
										<div>
											<label for="phone"><span>聯絡電話</span></label> 
											<input type="text" id="phone" name="phone" readonly/>
										</div>
										<div id="datetimepicker" class="mb-4">
											<label for="reserveTime"><span>預約看寵日期</span></label> 
											<input type="text" id="reserveTime" name="reserveTime" class="requiredValue"/>
										</div>
										<div>
											<input type="hidden" id="keepStatus" name="keepStatus" value="未赴約"/>
										</div>
								</fieldset>
								<div class="row justify-content-center modal-footer">
									<button type="button" class="col-2 me-3 btn btn-danger" id="sendReserveBtn" data-bs-dismiss="modal">送出預約</button>
									<button type="button" class="col-2 btn btn-secondary" data-bs-dismiss="modal">取消</button>
								</div>
							</div>
						</div>	
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- End of 預約看寵Modal -->
	<!-- 預約狀態提示框 Modal-->
	<div class="modal fade text-center" id="statusAlert" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">提示信息</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body items-align-center">
						<h4 style="margin-top: 10px; margin-bottom: 50px;"
							id="alertDialog"></h4>
				</div>
				<hr/>
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">確定</button>
			</div>
		</div>
	</div>
<!-- End Of deleteAlert Modal-->
</body>
</html>