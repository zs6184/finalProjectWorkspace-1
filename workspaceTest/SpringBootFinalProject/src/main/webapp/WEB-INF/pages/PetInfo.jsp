<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>PetInfo</title>

<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/stylesheet/petInfo.css" />
<!--CSS here-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0-alpha.1/jquery-ui.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
	integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script><script src="/javascript/petInfo.js"></script>
<!--JS Here-->

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
	<input type="hidden" id="sessionUsername" value="<%=session.getAttribute("username") %>">
	<!-- 檢查SessionAttribute是否存在用 -->
	<div>
		<div id="topbar">
			<div class="container h-100">
				<div class="row h-100 align-items-center topbar">
					<div class="col-12">
						<ul>
							<li id="memberOption" class="memberOption d-none">
								<div class="dropdown absolute backstage ${role}">
									<button class="btn btn-link dropdown-toggle " type="button"
										id="cusCenterDropdown" data-bs-toggle="dropdown">${realName}</button>
									<ul class="dropdown-menu p-0" role="button">
										<li id="whp1"><a href="/Users/SelectCustomer.controller"
											style="font-size: 1.1em"
											class="dropdown-item d-flex justify-content-center"
											target="_self">會員中心</a>
										</li>
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
							
							<li id="loginOption" class="loginOption"><a href="/login.Controller" target="_self">登入註冊</a></li>
							<li><a href="/pet/petinfo.controller" target="_self">寵物領養</a></li>
							<li><a href="/Users/loginIndex.Controller" target="_self">線上訂位</a></li>
							<li><a href="index.html" target="_self">餐點介紹</a></li>
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
						<h2>寵物領養</h2>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--內容-->
	<div class="container" id="container">
		<!--查詢欄位-->
		<form action="/pet/searchdatafront.controller" method="POST"
			id="srhForm">
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
					<div class="row offset-1 col-5 border text-white selectResult"
						id="${arrPet.petId}"
						style="margin-bottom: 20px; border-radius: 10px; height: 280px; padding: 0px;">
						<div class="col-6 h-100  align-items-center">
							<img src="data:image/png;base64,${baseStr[arrPet.petId]}"
								class="col w-100 h-100" />
						</div>
						<div class="row col-6 align-items-center">
							<ul>
								<li><span><i class="fa fa-id-card"
										aria-hidden="true"></i>&nbsp</span><span>寵物姓名:${arrPet.petName}</span></li>
								<li><span><i class="fa fa-venus-mars"
										aria-hidden="true">&nbsp</i></span><span>性別:${arrPet.sex}</span></li>
								<li><span><i class="fa fa-paw" aria-hidden="true"></i>&nbsp</span><span>類別:${arrPet.category}</span></li>
								<li><span><i class="fa fa-tag" aria-hidden="true"></i>&nbsp</span><span>品種:${arrPet.species}</span></li>
							</ul>
							<a href="/pet/detaildata/${arrPet.petId}">
								<button class="btn-sm btn-light detailBtn text-center"
									value="${arrPet.petId}" onclick="getDetail(this)">詳細資料</button>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
            <div class="copy_right">
                <div>
                    <div id="lowbar">
                        <div class="container h-100">
                            <div class="row h-100 align-items-center lowbar">
                                <div class="col-12 ">
                                    <ul>
                                        <li><a href="#t1" target="_self">活動訊息</a></li>
                                        <li><a href="index.html" target="_self">餐點介紹</a></li>
                                     	 <li><a href="/Users/loginIndex.Controller" target="_self">線上訂位</a></li>
                                        <li><a href="/pet/petinfo.controller" target="_self">寵物領養</a></li>
                                        <li><a href="/login.Controller" target="_self">登入註冊</a></li>
                                        <li id="memberOption" class="memberOption d-none">
											<div class="dropdown absolute backstage ${role}">
											<button class="btn btn-link dropdown-toggle " type="button"
													id="cusCenterDropdown" data-bs-toggle="dropdown">${realName}</button>
											<ul class="dropdown-menu p-0" role="button">
												<li id="whp1"><a href="/Users/SelectCustomer.controller"
													style="font-size: 1.1em"
													class="dropdown-item d-flex justify-content-center"
													target="_self">會員中心</a>
												</li>
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
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="container">
                    Copyright &copy;
                    <script>document.write(new Date().getFullYear());</script> All rights reserved | This template
                    is made with
                    <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="#top">
                        JAVA team4
                    </a>
                </div>
            </div>

	<div class="fix"><a href="#top" style="font-size: 40px;">TOP</a></div>

</body>
</html>