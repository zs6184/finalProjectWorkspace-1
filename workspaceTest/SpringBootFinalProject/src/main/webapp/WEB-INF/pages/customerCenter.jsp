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
<link rel="icon" type="image/png"  href="/font/favicon1.png">
</head>

<body id="top" style="background-image: url(/image/背景4.jpg);">
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
								<div class="dropdown absolute backstage">
									<button class="btn btn-link dropdown-toggle " type="button"
										id="cusCenterDropdown" data-bs-toggle="dropdown">${realName}</button>
									<ul class="dropdown-menu p-0" role="button">
										<li id="whp1"><a href="/Users/SelectCustomer.controller#information"
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
							<li><a href="/Users/loginIndex.Controller#Reservation" target="_self">線上訂位</a></li>
							<li><a href="/product/findallproduct" target="_self">餐點介紹</a></li>
							<li><a href="/Users/loginIndex.Controller#t1" target="_self">活動訊息</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 手風琴 -->
	<div class="containerAll" id="information">
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
						id="dropdown01" class="center sidebarLight01 ${provider}"> <i
							class="far fa-address-book mx-2"></i> <span class="items">我的帳戶</span>
					</a> <!-- 子連結 -->
						<ul id="sublist01" class="list-unstyled collapse show">
							<li><a href="/Users/SelectCustomer.controller#information"
								class="itemDetails">個人資料</a></li>
							<li id="pwd"><a href="/Users/CheckPassword.Controller#position" class="itemDetails">變更密碼</a></li>
							<li id="mail"><a href="/Users/EmailCheckPassword.Controller#position" class="itemDetails">變更信箱</a></li>
						</ul></li>
					<li><a href="#sublist02" data-bs-toggle="collapse"
						id="dropdown02" class="center"> <i
							class="fas fa-utensils mx-2"></i> <span class="items">訂單訂位查詢</span>
					</a> <!-- 子連結 -->
						<ul id="sublist02" class="list-unstyled collapse">
							<li><a href="/order/memberorders" class="itemDetails">訂單查詢</a></li>
							<li><a href="/ec/ecdetail" class="itemDetails">綠界訂單檢視</a></li>							
						</ul></li>
					<li><a href="#sublist03" data-bs-toggle="collapse"
						id="dropdown03" class="center"> <i
							class="far fa-calendar-check mx-2"></i><span class="items">訂位查詢</span>
					</a> <!-- 子連結 -->
						<ul id="sublist03" class="list-unstyled collapse">
							<li><a href="/Users/bookingsRecord/getthecusresult" class="itemDetails ">訂位總覽</a></li>
						
						</ul></li>
					<li><a href="#sublist04" data-bs-toggle="collapse"
						id="dropdown04" class="center"> <i class="fas fa-cat mx-2"></i>
							<span class="items">領養紀錄查詢</span>
					</a> <!-- 子連結 -->
						<ul id="sublist04" class="list-unstyled collapse">
							<li><a href="/pet/petinfo.controller" class="itemDetails">寵物資訊總覽</a>
							</li>
						
							<li><a href="/Users/petreserve/getthecusresult" class="itemDetails">領養預約總覽</a></li>
							
						</ul></li>
				</ul>
			</nav>
		</div>
		<!-- container right -->
		<div class="containerRight">
			<!-- 主頁內容 -->
			<div class="container-fluid">
				<div class="row">
					<div class="col-11 bg-white my-3 ms-5 box rounded-3 shadow p-5">
						<p class="font1 border-bottom pb-4">個人資料</p>
						<!-- left -->
						<form class="mt-5" id="customerCenterForm"
							action="/Users/UpdateCustomer.controller" method="post"
							enctype="multipart/form-data">
							<div class="row">
								<div class="col-8">
									<div class=" row" id="account">
										<div class="row my-2 offset-1 col-10">
											<input hidden="true" name="cusId" id="cusId"
												value="${id}" /> <label for="username"
												class="col-3 col-form-label">帳號:</label>
											<div class="col-9 d-flex align-items-center">
												<span id="username">${username}</span>
												<!--<input id="username" type="text" name="cusUsername" class="form-control" maxlength="20" placeholder="Username" autocomplete="off" autofocus>-->
											</div>
										</div>
										<div class="row my-2 offset-1 col-10">
											<label for="cusRealname" class="col-3 col-form-label">姓名:</label>
											<div class="col-9">
												<input id="cusRealname" type="text"
													value="${realName}" name="cusRealname"
													class="form-control" maxlength="10" placeholder="Name"
													autocomplete="off">
											</div>
										</div>
										<div class="row my-3 offset-1 col-10">
											<label for="aka" class="col-3 col-form-label">暱稱:</label>
											<div class="col-9">
												<input id="aka" type="text" name="aka" value="${aka}"
													class="form-control" maxlength="10" placeholder="Nickname"
													autocomplete="off">
											</div>
										</div>
										<div class="row my-2 offset-1 col-10">
											<label for="male" class="col-3 ${gender}" id="gender">性別:</label>
											<div class="col-9">
												<label for="male" class="form-check-label">男</label> <input
													id="male" type="radio" value="male" name="gender"
													class="form-check-input"> <label for="female"
													class="form-check-label ms-3">女</label> <input id="female"
													type="radio" value="female" name="gender"
													class="form-check-input">
											</div>
										</div>
										<div class="row my-2 offset-1 col-10">
											<label for="birthdate" class="col-3 col-form-label">生日:</label>
											<div class="col-9">
												<input id="birthdate" type="date"
													value="${birthdate}" name="birthdate"
													class="form-control" autocomplete="off">
											</div>
										</div>
										<div class="row my-3 offset-1 col-10">
											<label for="phoneNumber" class="col-3 col-form-label">聯絡電話:</label>
											<div class="col-9 relative">
												<input id="phoneNumber" type="tel"
													value="${phoneNumber}" name="phoneNumber"
													maxlength="10" class="form-control"
													placeholder="Phone Number" autocomplete="off">
												<div class="invalid-tooltip" id="phoneInvalid">電話號碼已被使用</div>
											</div>
										</div>
										<div class="row my-3 offset-1 col-10">
											<label for="email" class="col-3 col-form-label">E-mail:</label>
											<div class="col-9 align-items-center my-2">
												<input id="email" hidden="true" name="email" value="${email}">
												<span>${email}</span>
											</div>
										</div>
										<div class="row my-3 offset-1 col-10">
											<label for="address" class="col-3 col-form-label">地址:</label>
											<div class="col-9">
												<input id="address" type="text" value="${address}"
													name="address" class="form-control" maxlength="50"
													placeholder="Address" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="text-center mt-3 mb-5">
										<button type="submit" class="btn btn-success">儲存</button>
									</div>
								</div>
								<!-- right -->
								<div class="col-4 mt-5">
									<div class="border-start d-flex justify-content-center">
										<div>
											<div class="mx-5 my-2">
												<div id="cursor">
													<label for="file" id="imgPreview" class="bg-white"
														style="border: hidden"> <img
														src="/downloadTempDir/${imageName}" class="image" />
													</label>
													<!-- image/husky.jpg -->
												</div>
												<div class="d-flex justify-content-center">
													<label for="file" class="btn btn-outline-success mt-3">選擇圖片</label>
													<input type="file" name="image" id="file"
														accept=".jpg,.jpeg,.png" style="display: none" />
												</div>
											</div>
											<div class="mt-2 text-center">檔案限制: .JPG, .JPEG, .PNG</div>
										</div>
									</div>
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
								<li><a href="/Users/loginIndex.Controller#t1" target="_self">活動訊息</a></li>
								<li><a href="/product/findallproduct" target="_self">餐點介紹</a></li>
								<li><a href="/Users/loginIndex.Controller#Reservation" target="_self">線上訂位</a></li>
								<li><a href="/pet/petinfo.controller" target="_self">寵物領養</a></li>
								<li>
									<div class="dropdown absolute backstage ${role}">
										<button class="btn btn-link dropdown-toggle text-light" type="button"
											id="cusCenterDropdown2" data-bs-toggle="dropdown">${realName}</button>
										<ul class="dropdown-menu p-0" role="button">
											<li id="whp3"><a href="/Users/SelectCustomer.controller#information"
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