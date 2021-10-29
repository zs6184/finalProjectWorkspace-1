<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員中心</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/stylesheet/index.css" />
<!--CSS在這邊 要注意放在bootstrap樣式表CDN後面 不然權重相同的部分會被bootstrap蓋過去-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0-alpha.1/jquery-ui.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
	integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<!--javaScript掛到這邊-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
	integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="/javascript/index.js"></script>

<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<!--自訂樣式表-->
<link href="/stylesheet/customerCenter.css" rel="stylesheet" />
<!--自訂js-->
<script src="/javascript/customerCenter.js"></script>

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
								<div class="dropdown absolute  backstage ${role}">
									<button class="btn btn-link dropdown-toggle " type="button"
										id="cusCenterDropdown" data-bs-toggle="dropdown">${realName}</button>
									<ul class="dropdown-menu p-0" role="button">
										<li id="whp1"><a href="/Users/SelectCustomer.controller"
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
							<li><a href="/petinfo.controller" target="_self">寵物領養</a></li>
							<li><a href="/index.html" target="_self">線上訂位</a></li>
							<li><a href="/index.html" target="_self">餐點介紹</a></li>
							<li><a href="#t1" target="_self">活動訊息</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 手風琴 -->
	<div class="containerAll">
		<!-- container left -->
		<div class="containerLeft shadow">
			<nav id="sidebar" class=" colorGray">
				<div class="">
					<div id="cursor" class="d-flex align-items-center p-2">
						<label for="#" class="bg-white"> <img
							src="/downloadTempDir/${imageName}" class="image2" />
						</label>
						<div style="text-align: center" class="ms-2">${realName}</div>
					</div>
				</div>
				<ul class="list-unstyled">
					<li><a href="#sublist01" data-bs-toggle="collapse"
						id="dropdown01" class="center sidebarLight01"> <i
							class="far fa-address-book mx-2"></i> <span class="items">我的帳戶</span>
					</a> <!-- 子連結 -->
						<ul id="sublist01" class="list-unstyled collapse show">
							<li><a href="/Users/SelectCustomer.controller#information"
								class="itemDetails">個人資料</a></li>
							<li><a href="/Users/CheckPassword.Controller#position" class="itemDetails">變更密碼</a></li>
							<li><a href="/Users/EmailCheckPassword.Controller#position" class="itemDetails  sidebarLight02">變更電子信箱</a></li>
						</ul></li>
					<li><a href="#sublist02" data-bs-toggle="collapse"
						id="dropdown02" class="center"> <i
							class="fas fa-utensils mx-2"></i> <span class="items">訂單查詢</span>
					</a> <!-- 子連結 -->
						<ul id="sublist02" class="list-unstyled collapse">
							<li><a href="/ordermanage.html" class="itemDetails">訂單管理</a>
							</li>
							<li><a href="/promo.html" class="itemDetails">優惠碼管理</a></li>
							<li><a href="#" class="itemDetails">訂位查詢</a></li>
							<li><a href="#" class="itemDetails">訂位更新</a></li>
						</ul></li>
					<li><a href="#sublist03" data-bs-toggle="collapse"
						id="dropdown03" class="center"> <i
							class="far fa-calendar-check mx-2"></i><span class="items">訂位查詢</span>
					</a> <!-- 子連結 -->
						<ul id="sublist03" class="list-unstyled collapse">
							<li><a href="#" class="itemDetails">公告總覽</a></li>
							<li><a href="/postCreate.html" class="itemDetails">新增公告</a></li>
							<li><a href="#" class="itemDetails">公告更新</a></li>
						</ul></li>
					<li><a href="#sublist04" data-bs-toggle="collapse"
						id="dropdown04" class="center"> <i class="fas fa-cat mx-2"></i>
							<span class="items">領養紀錄查詢</span>
					</a> <!-- 子連結 -->
						<ul id="sublist04" class="list-unstyled collapse">
							<li><a href="/backpetinfo.controller" class="itemDetails">寵物資訊總覽</a>
							</li>
							<li><a href="#" class="itemDetails">文章發佈</a></li>
							<li><a href="#" class="itemDetails">文章更新</a></li>
							<li><a href="#" class="itemDetails">寵物領養資訊</a></li>
							<li><a href="#" class="itemDetails">領養預約總覽</a></li>
							<li><a href="#" class="itemDetails">領養記錄查詢</a></li>
						</ul></li>
				</ul>
			</nav>
		</div>
		<!-- container right -->
		<div class="containerRight">
			<!-- 主頁內容 -->
			<div class="container-fluid">
				<div class="row" id="join">
					<div class="offset-3 col-6 bg-white my-5 box2 rounded-3 shadow p-5">
						<p class="font1 border-bottom pb-5" id="position">變更電子信箱</p>
						<!-- left -->
						<form class="mt-5" id="emailCheckPasswordForm"
							action="/Users/CheckPasswordBT.Controller" method="post"
							enctype="multipart/form-data">
							<div>
								<div class="d-flex justify-content-center" id="account">
									<div class="mb-3">
										<label for="checkPassword" class="col-form-label mb-2">如要繼續操作，請先驗證您的身分:</label>
										<div class="relative" >
											<input id="checkPassword" type="password"
												name="checkPassword"
												maxlength="20" class="form-control"
												autocomplete="off" placeholder="請輸入您的密碼">
											<div class="invalid-tooltip" id="checkPasswordInvalid">密碼錯誤</div>
										</div>
									</div>
								</div>
								<div class="text-center mt-3 mb-5">
									<button type="submit" class="btn btn-success">確認</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="copy_right">
		<div>
			<div id="lowbar">
				<div class="container h-100">
					<div class="row h-100 align-items-center lowbar">
						<div class="col-12">
							<ul>
								<li><a href="#t1" target="_self">活動訊息</a></li>
								<li><a href="/index.html" target="_self">餐點介紹</a></li>
								<li><a href="/index.html" target="_self">線上訂位</a></li>
								<li><a href="/petinfo.controller" target="_self">寵物領養</a></li>
								<li>
									<div class="dropdown absolute  backstage">
										<button class="btn btn-link dropdown-toggle text-light"
											type="button" id="cusCenterDropdown2"
											data-bs-toggle="dropdown">${realName}</button>
										<ul class="dropdown-menu p-0" role="button">
											<li id="whp3"><a href="/Users/SelectCustomer.controller"
												class="text-dark dropdown-item d-flex justify-content-center"
												target="_self">會員中心</a></li>
											<li id="whp4" class="m-0">
												<form action="/Users/logout.Controller" method="post">
													<button type="submit"
														class="dropdown-item d-flex justify-content-center"
														value="logout">登出</button>
												</form>
											</li>
										</ul>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			Copyright &copy;
			<script>
				document.write(new Date().getFullYear());
			</script>
			All rights reserved | This template is made with <i
				class="fa fa-heart-o" aria-hidden="true"></i> by <a href="#top">
				JAVA team4 </a>
		</div>
	</div>
	<div class="fix">
		<a href="#top" style="font-size: 40px;">TOP</a>
	</div>
</body>
</html>