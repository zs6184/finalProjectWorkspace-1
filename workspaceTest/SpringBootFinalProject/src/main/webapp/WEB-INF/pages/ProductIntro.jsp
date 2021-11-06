<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>菜單介紹</title>

<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/stylesheet/ProductIntro.css" />
<link rel="stylesheet" href="/stylesheet/sweetalert.css" />
<!--CSS here-->
<script src="/javascript/sweetalert.min.js"></script>
<script src="/javascript/sweetalert-dev.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="/javascript/jquery-ui.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0-alpha.1/jquery-ui.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
	integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="/javascript/ProductIntro.js"></script>
<script src="/javascript/addtoshoppingcart.js"></script>
<script src="https://cdn.staticfile.org/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!--JS Here-->
<link rel="icon" type="image/png"  href="/font/favicon1.png">
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
							<li><a href="/product/findallproduct" target="_self">餐點介紹</a></li>
							<li><a href="/indexAnn.controller" target="_self">活動訊息</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--橫幅圖片區域-->
	<div class="banner-area bg-img"
		style="background-image: url(/image/img/bg-img/food_bg.jpeg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="banner-content text-center">
						<h2>菜單介紹</h2>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--內容-->
	<div class="container" id="container">
<!-- 		<!--查詢欄位-->
<!-- 		<form action="/pet/searchdatafront.controller" method="POST" -->
<!-- 			id="srhForm"> -->
<!-- 			<div class="row justify-content-start" style="margin: 30px;"> -->
<!-- 				<div class="col-1 text-center"> -->
<!-- 					<select name="category" id="srhCategory"> -->
<!-- 						<option value="">不限類別</option> -->
<%-- 						<c:forEach var="cateSet" items="${cateSet}"> --%>
<%-- 							<option value="${cateSet}" name="${cateSet}">${cateSet}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<div class="col-1 text-center"> -->
<!-- 					<select name="sex" id="srhSex"> -->
<!-- 						<option value="">不限性別</option> -->
<%-- 						<c:forEach var="sexSet" items="${sexSet}"> --%>
<%-- 							<option value="${sexSet}" name="${sexSet}">${sexSet}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<div class="col-2"> -->
<!-- 					<button type="submit" class="btn-info text-white btn-sm" -->
<!-- 						id="srhBtn">搜尋</button> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</form> -->

		<!--查詢結果概覽-->
		<div id="tabs">
			<div>
				<div class="align-items-center mt-5">
					<ul id="menuNav" class="row justify-content-center">
						<li class="row col-2 border justify-content-center"><a href="#rice" class="text-white text-center">飯</a></li>
						<li class="row col-2 border justify-content-center"><a href="#noodle" class="text-white text-center">麵</a></li>
						<li class="row col-2 border justify-content-center"><a href="#beverage" class="text-white text-center">飲料</a></li>
						<li class="row col-2 border justify-content-center"><a href="#dessert" class="text-white text-center">點心</a></li>
					</ul>
				</div>
			</div>
				
			<div id="rice">
				<div class="main-box">
					<c:forEach var="arrRice" items="${arrRice}">
						<div class="row mb-3 justify-content-start border rounded" 
							style="height:300px;background-image: url(/image/img/bg-img/b10.jpg);">
							<input type="hidden" value="${arrRice.productID}" id="productid"/>
							<div class="col-4 h-100 align-items-center">
								<img src="data:image/png;base64,${baseStr[arrRice.productID]}" class="col w-100 h-100" />
							</div>
							<div class="row col-6 justify-content-center align-items-center">
								<div class="text-start fs-2">${arrRice.productName}</div>
								<div class="text-center fs-4" style="margin-bottom:75px;">${arrRice.note}</div>
							</div>
							<div class="row col-2 justify-content-center align-items-center">
								<button type="button" class="btn btn-dark detailBtn fs-4" style="height:50%;"
										data-bs-toggle="modal" data-bs-target="#productDetail" onclick="getTheProd(this)">我要點餐</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div id="noodle">
				<div class="main-box">
					<c:forEach var="arrNoodle" items="${arrNoodle}">
						<div class="row mb-3 justify-content-start border rounded" 
							style="height:300px;background-image: url(/image/img/bg-img/b10.jpg);">
							<input type="hidden" value="${arrNoodle.productID}"/>
							<div class="col-4 h-100 align-items-center">
								<img src="data:image/png;base64,${baseStr[arrNoodle.productID]}" class="col w-100 h-100" />
							</div>
							<div class="row col-6 justify-content-center align-items-center">
								<div class="text-start fs-2 prodName">${arrNoodle.productName}</div>
								<div class="text-center fs-4" style="margin-bottom:75px;">${arrNoodle.note}</div>
							</div>
							<div class="row col-2 justify-content-center align-items-center">
								<button type="button" class="btn btn-dark detailBtn" style="height:50%;"
										data-bs-toggle="modal" data-bs-target="#productDetail" onclick="getTheProd(this)">我要點餐</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div id="beverage">
				<div class="main-box">
					<c:forEach var="arrBeverage" items="${arrBeverage}">
						<div class="row mb-3 justify-content-start border rounded" 
							style="height:300px;background-image: url(/image/img/bg-img/b10.jpg);">
							<input type="hidden" value="${arrBeverage.productID}"/>
							<div class="col-4 h-100 align-items-center">
								<img src="data:image/png;base64,${baseStr[arrBeverage.productID]}" class="col w-100 h-100" />
							</div>
							<div class="row col-6 justify-content-center align-items-center">
								<div class="text-start fs-2">${arrBeverage.productName}</div>
								<div class="text-center fs-4" style="margin-bottom:75px;">${arrBeverage.note}</div>
							</div>
							<div class="row col-2 justify-content-center align-items-center">
								<button type="button" class="btn btn-dark detailBtn" style="height:50%;"
										data-bs-toggle="modal" data-bs-target="#productDetail" onclick="getTheProd(this)">我要點餐</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div id="dessert">
				<div class="main-box">
					<c:forEach var="arrDessert" items="${arrDessert}">
						<div class="row mb-3 justify-content-start border rounded" 
							style="height:300px;background-image: url(/image/img/bg-img/b10.jpg);">
							<input type="hidden" value="${arrDessert.productID}"/>
							<div class="col-4 h-100 align-items-center">
								<img src="data:image/png;base64,${baseStr[arrDessert.productID]}" class="col w-100 h-100" />
							</div>
							<div class="row col-6 justify-content-center align-items-center">
								<ul>
								<li class="text-start fs-2">${arrDessert.productName}</li>
								<li class="text-center fs-4">${arrDessert.note}</li>
								</ul>
							</div>
							<div class="row col-2 justify-content-center align-items-center">
								<button type="button" class="btn btn-dark detailBtn" style="height:50%;"
										data-bs-toggle="modal" data-bs-target="#productDetail" onclick="getTheProd(this)">我要點餐</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>			
		</div>
	</div>
	
	<!-- Product Detail Modal -->
	<div class="modal fade" id="productDetail" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">產品資料</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form action="" method="GET" id="productForm">
						<div class="row">
							<div class="text-center" id="mainbox">
								<fieldset>
								<legend>餐點外觀</legend>
									<div class="row justify-content-center">
										<div class="row justify-content-center">
											<div id="imgPreview" class="border" style="width:400px; height:300px;">
												<img src="" style="width:100%;height:100%;" alt="圖片預覽區域"/>
											</div>
										</div>
									</div>
								</fieldset>
								<hr/>
								<fieldset>
									<legend>訂購資訊</legend>
										<input type="hidden" name="hiddenProdID" id="productID"/>
										<div>
											<label for="productName"><span>餐點名稱</span></label> 
											<input type="text" id="productName" name="productName" disabled/>
										</div>
										<div>
											<label for="category"><span>類別</span></label> 
											<input type="text" id="category" name="category" disabled/>
										</div>
										<div>
											<label for="unitprice"><span>單價</span></label> 
											<input type="text" id="unitprice" name="unitprice" readonly/>
										</div>
										<div>
											<label for="totalInstore"><span>庫存剩餘量</span></label> 
											<input type="text" id="totalInstore" name="totalInstore" disabled/>
										</div>
										<div>
											<label for="num" class=""><span>欲訂購數量</span></label>
											<input type="text" id="num" name="num" class="requiredValue"
													oninput="value=value.replace(/[^\d]/g,'')" />
										</div>
										<div>
											<label for="subtotal"><span>小計</span></label> 
											<input type="text" id="subtotal" name="subtotal" readonly/>
										</div>
								</fieldset>
								<hr/>
								<div class="row justify-content-center modal-footer">
									<button type="submit" class="col-2 me-3 btn btn-danger" id="sendReserveBtn">加入購物車</button>
									<button type="button" class="col-2 btn btn-secondary" data-bs-dismiss="modal">取消</button>
								</div>
							</div>
						</div>	
					</form>
				</div>
			</div>
		</div>
	</div>
	

	<!--End of Product Detail Modal -->
	
            <div class="copy_right">
                <div>
                    <div id="lowbar">
                        <div class="container h-100">
                            <div class="row h-100 align-items-center lowbar">
                                <div class="col-12 ">
                                    <ul>
                                        <li><a href="#t1" target="_self">活動訊息</a></li>
                                        <li><a href="/product/findallproduct" target="_self">餐點介紹</a></li>
                                     	 <li><a href="/Users/loginIndex.Controller" target="_self">線上訂位</a></li>
                                        <li><a href="/pet/petinfo.controller" target="_self">寵物領養</a></li>
                                        <li class="loginOption"><a href="/login.Controller" target="_self">登入註冊</a></li>
										<li class="memberOption"><div class="dropdown absolute backstage">
												<button class="btn btn-link dropdown-toggle text-light"
													type="button" id="cusCenterDropdown2"
													data-bs-toggle="dropdown">${realName}</button>
												<ul class="dropdown-menu p-0" role="button">
													<li id="whp3"><a
														href="/Users/SelectCustomer.controller"
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
											</div></li>
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