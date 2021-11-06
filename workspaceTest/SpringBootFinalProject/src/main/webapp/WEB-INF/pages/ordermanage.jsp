<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>訂單管理</title>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- fontawesome icon -->
<!--<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />-->
<!--bootstrap & jQuery-ui-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link href="stylesheet/jquery-ui.min.css" rel="stylesheet" />
<!--自訂樣式表-->
<link href="/stylesheet/backstage.css" rel="stylesheet" />
<link href="/stylesheet/ordermanage.css" rel="stylesheet" />
<!--<script src="javascript/bootstrap.min.js"></script>-->
<script src="/javascript/jquery-3.6.0.min.js"></script>
<script src="/javascript/jquery-dateFormat.min.js"></script>
<!--自訂js-->
<script src="/javascript/backstage.js"></script>
<script src="/javascript/ordermanage.js"></script>
<!-- font awesome  -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!--jquery-->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>
<!-- sweetalert -->
<link rel="stylesheet" href="/stylesheet/sweetalert.css" />
<script src="/javascript/sweetalert.min.js"></script>
<script src="/javascript/sweetalert-dev.js"></script>
<link rel="icon" type="image/png"  href="/font/favicon1.png">
</head>
<body>
	<!-- 導覽列 -->
	<header>
		<div class="container-fluid border shadow w-100">
			<nav class="navbar navbar-light bg-white m-0 ">
				<div class="container-fluid">
					<!-- 超連結到主頁 -->
					<a class="navbar-brand row" href="backstage.html">後臺管理系統</a>
					<!-- 搜尋欄及按鈕 -->
					<form class="d-flex offset-5 col-3">
						<input class="form-control me-2 ms-5 " type="search"
							placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form>
					<div class="">
						<!-- 帳號頭像及功能 -->
						<div class="dropdown">
							<button class="btn-transparent" type="button"
								id="dropdownButton01" data-bs-toggle="dropdown">
								<img src="/image/husky.jpg" class="image shadow" />
							</button>
							<ul class="dropdown-menu  dropdown-menu-end shadow"
								aria-labelledby="dropdownMenuButton01">
								<li><a class="dropdown-item" href="#">Username</a></li>
								<li><a class="dropdown-item" href="#">Settings</a></li>
								<li><a class="dropdown-item" href="#">登出</a></li>
							</ul>
						</div>
						<!-- 訊息資訊 -->
						<div class="dropdown">
							<button class="btn-transparent" type="button"
								id="dropdownButton02" data-bs-toggle="dropdown">
								<i class="far fa-envelope me-5 navIcon mt-3" id="navIcon"></i>
							</button>
							<ul class="dropdown-menu  dropdown-menu-end shadow"
								aria-labelledby="dropdownMenuButton02">
								<li><a class="dropdown-item me-3" href="#"> <img
										src="/image/husky.jpg" class="imageMessage shadow" /> message
								</a></li>
								<li><a class="dropdown-item" href="#"> <img
										src="/image/husky.jpg" class="imageMessage shadow" /> message
								</a></li>
								<li><a class="dropdown-item" href="#"> <img
										src="/image/husky.jpg" class="imageMessage shadow" /> message
								</a></li>
							</ul>
						</div>
						<!-- 訊息通知 -->
						<div class="dropdown floatDown">
							<button class="btn-transparent" type="button"
								id="dropdownButton03" data-bs-toggle="dropdown">
								<i class="far fa-bell me-5 navIcon mt-3" id="navIcon"></i>
							</button>
							<ul class="dropdown-menu  dropdown-menu-end shadow"
								aria-labelledby="dropdownMenuButton03">
								<li><a class="dropdown-item" href="#">notify</a></li>
								<li><a class="dropdown-item" href="#">notify</a></li>
								<li><a class="dropdown-item" href="#">notify</a></li>
							</ul>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</header>
	<!-- 手風琴 -->
	<div class="containerAll">
		<!-- container left -->
		<div class="containerLeft shadow">
			<nav id="sidebar" class=" colorGray">
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<button type="button" id="collapse" class="collapseBtn ">
						<i class="fas fa-align-left"></i>
					</button>
				</div>
				<ul class="list-unstyled">
					<li><a href="#sublist01" data-bs-toggle="collapse"
						id="dropdown01" class="center"> <i
							class="fas fa-pizza-slice mx-2"></i> <span class="items">餐點管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist01" class="list-unstyled collapse">
							<li><a href="#" class="itemDetails">餐點總覽</a></li>
							<li><a href="#" class="itemDetails">新品上架</a></li>
							<li><a href="#" class="itemDetails">餐點更新</a></li>
						</ul></li>
					<li><a href="#sublist02" data-bs-toggle="collapse"
						id="dropdown02" class="center"> <i
							class="fas fa-utensils mx-2"></i> <span class="items">訂單及訂位管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist02" class="list-unstyled collapse">
							<li><a href="/order/back.ordermanage.controller"
								class="itemDetails">訂單管理</a></li>
							<li><a href="/back.coupons" class="itemDetails">優惠碼管理</a></li>
							<li><a href="#" class="itemDetails">訂位查詢</a></li>
							<li><a href="#" class="itemDetails">訂位更新</a></li>
						</ul></li>
					<li><a href="#sublist03" data-bs-toggle="collapse"
						id="dropdown03" class="center"> <i
							class="fas fa-bullhorn mx-2"></i> <span class="items">公告管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist03" class="list-unstyled collapse">
							<li><a href="#" class="itemDetails">公告總覽</a></li>
							<li><a href="postCreate.html" class="itemDetails">新增公告</a></li>
							<li><a href="#" class="itemDetails">公告更新</a></li>
						</ul></li>
					<li><a href="#sublist04" data-bs-toggle="collapse"
						id="dropdown04" class="center"> <i class="fas fa-cat mx-2"></i>
							<span class="items">寵物管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist04" class="list-unstyled collapse">
							<li><a href="backPetInfo.html" class="itemDetails">寵物資訊總覽</a>
							</li>
							<li><a href="#" class="itemDetails">文章發佈</a></li>
							<li><a href="#" class="itemDetails">文章更新</a></li>
							<li><a href="#" class="itemDetails">寵物領養資訊</a></li>
							<li><a href="#" class="itemDetails">領養預約總覽</a></li>
							<li><a href="#" class="itemDetails">領養記錄查詢</a></li>
						</ul></li>
					<li class="m-0"><a href="#" class="center"> <i
							class="fas fa-comments-dollar mx-2"></i> <span class="items">客服管理</span>
					</a></li>
					<li class="m-0"><a href="members.html"
						class="center sidebarLight02"> <i class="fas fa-users mx-2"></i>
							<span class="items">會員管理</span>
					</a></li>
					<li class="m-0"><a href="/Backstage/EmployeesAll.Controller"
						class="center"> <i class="fas fa-address-card mx-2"></i> <span
							class="items">員工管理</span>
					</a></li>

				</ul>
			</nav>
		</div>
		<!-- 主頁內容-->
		<div class="container  bg-light  p-4 h-75" id="containerbox">
			<div class="row ">
				<div class="col-sm-12 d-flex">
					<div style="width: 800%; height: 36px; overflow: auto;">
						<ul class="nav nav-tabs" id="tab_menu_initial" role="tablist">
							<li class="nav-item" role="presentation"><a
								class="nav-link active" id="home-tab" data-toggle="tab"
								href="#home" role="tab" aria-controls="home"
								aria-selected="true">新訂單</a></li>
							<li class="nav-item" role="presentation"><a
								class="nav-link " id="profile-tab" data-toggle="tab"
								href="#profile" role="tab" aria-controls="profile"
								aria-selected="false">製作中</a></li>
							<li class="nav-item" role="presentation"><a class="nav-link"
								id="contact-tab" data-toggle="tab" href="#contact" role="tab"
								aria-controls="contact" aria-selected="false">未取餐</a></li>
							<li class="nav-item" role="presentation"><a class="nav-link"
								id="contact-tab" data-toggle="tab" href="#service_01" role="tab"
								aria-controls="contact" aria-selected="false">已取餐</a></li>
							<li class="nav-item" role="presentation"><a class="nav-link"
								id="contact-tab" data-toggle="tab" href="#service_02" role="tab"
								aria-controls="contact" aria-selected="false">取消</a></li>
							<li class="nav-item" role="presentation"><a
								class="nav-link " id="contact-tab" data-toggle="tab"
								href="#service_03" role="tab" aria-controls="contact"
								aria-selected="false">查詢</a></li>
						</ul>
					</div>

					<!--<ol class="more_menu_outer" style="width: 20%;">
                    <li class="">
                        <div class="c_dropdown">
                            <span>more <i class="fas fa-angle-down ml-1"></i></span>
                            <div class="dropdown-content">
                                <ul class="" id="more_menu">
                                </ul>
                            </div>
                        </div>
                    </li>
                </ol>-->

				</div>

				<div class="col-sm-12 d-flex">
					<div class="tab-content" id="myTabContent">
						<!--新訂單顯示區塊-->
						<div class="tab-pane fade col-sm-12 show active " id="home"
							role="tabpanel">
							<div class="neworderright">
								<table class="table table table-hover">
									<thead class="thead-light">
										<tr>
											<th scope="col">電話號碼</th>
											<th scope="col">預計取餐時間</th>
											<th scope="col">合計</th>
											<th scope="col">付款狀態</th>
										</tr>
									</thead>
									<tbody id="newordertbody">
										<tr onclick="neworDerShowDetail(event, 'order01')"
											class="tablinks" id="open">
											<th scope="row" colspan="4" style="text-align: center;">尚無訂單</th>
											<!--<td colspan="3"></td> -->
											<!--<td colspan="3"></td> -->
											<!--<td colspan="3"></td> -->
										</tr>
									</tbody>
								</table>
							</div>
							<!-- 內容 -->
							<!-- 結束 -->
						</div>
						<!-- 新訂單結束 -->
						<!--製作中顯示區塊-->
						<div class="tab-pane fade" id="profile" role="tabpanel"
							aria-labelledby="contact-tab">
							<div class="tab-pane fade show active col-sm-12" id="profile"
								role="tabpanel" aria-labelledby="home-tab">
								<div class="neworderright">
									<table class="table table table-hover">
										<thead class="thead-light">
											<tr>
												<th scope="col">電話號碼</th>
												<th scope="col">預計取餐時間</th>
												<th scope="col">合計</th>
												<th scope="col">付款狀態</th>
											</tr>
										</thead>
										<tbody id="making">
											<tr onclick="makingShowDetail(event, 'order011')"
												class="tablinks" id="open2">
												<th scope="row" colspan="4" style="text-align: center;">尚無訂單</th>
												<!-- <td colspan="3"></td> -->
												<!-- <td colspan="3"></td> -->
												<!-- <td colspan="3"></td> -->
											</tr>
										</tbody>
									</table>
								</div>
								<div id="divmaking">
								<!-- 內容 -->
								<!-- 內容結束 -->
								</div>
							</div>
						</div>
						<!--未取餐顯示區塊-->
						<div class="tab-pane fade" id="contact" role="tabpanel"
							aria-labelledby="contact-tab">
							<div class="tab-pane fade show active col-sm-12" id="contact"
								role="tabpanel" aria-labelledby="home-tab">
								<div class="neworderright">
									<table class="table table table-hover">
										<thead class="thead-light">
											<tr>
												<th scope="col">電話號碼</th>
												<th scope="col">預計取餐時間</th>
												<th scope="col">合計</th>
												<th scope="col">付款狀態</th>
											</tr>
										</thead>
										<tbody id="untaken">
											<tr onclick="untakeShowDetail(event, 'order021')"
												class="tablinks" id="open2">
												<th scope="row" colspan="4" style="text-align: center;">尚無訂單</th>
												<!--<td>09-15 15:20</td> -->
												<!--<td>100</td> -->
												<!--<td>未付款</td> -->
											</tr>
										</tbody>
									</table>
								</div>
								<div id="divuntaken">
									<!-- 內容 -->
									<!-- 內容結束 -->
								</div>								
							</div>
						</div>
						
						<!--已取餐顯示區塊-->
						<div class="tab-pane fade" id="service_01" role="tabpanel"
							aria-labelledby="contact-tab">
							<div class="tab-pane fade show active col-sm-12" id="service_01"
								role="tabpanel" aria-labelledby="home-tab">
								<div class="neworderright">
									<table class="table table table-hover">
										<thead class="thead-light">
											<tr>
												<th scope="col">電話號碼</th>
												<th scope="col">預計取餐時間</th>
												<th scope="col">合計</th>
												<th scope="col">付款狀態</th>
											</tr>
										</thead>
										<tbody id="havebeentaken">
											<tr onclick="untakeShowDetail(event, 'order031')"
												class="tablinks" id="open4">
												<th scope="row" colspan="4" style="text-align: center;">尚無訂單</th>
												<!--<td>09-15 15:20</td> -->
												<!--<td>100</td> -->
												<!--<td>已付款</td> -->
											</tr>

										</tbody>
									</table>
								</div>
								<div id="divhavebeentaken">
								<!-- 內容 -->
								<!-- 內容結束 -->
								</div>							
							</div>
						</div>
						
						<!--取消顯示區塊-->
						<div class="tab-pane fade" id="service_02" role="tabpanel"
							aria-labelledby="contact-tab">
							<div class="tab-pane fade show active col-sm-12" id="service_02"
								role="tabpanel" aria-labelledby="home-tab">
								<div class="neworderright">
									<table class="table table table-hover">
										<thead class="thead-light">
											<tr>
												<th scope="col">電話號碼</th>
												<th scope="col">預計取餐時間</th>
												<th scope="col">合計</th>
											</tr>
										</thead>
										<tbody id="cancel">
											<tr onclick="cancelShowDetail(event, 'order041')" class="tablinks" id="open5">
												<th scope="row" colspan="4" style="text-align: center;">尚無訂單</th>
											</tr>
										</tbody>
									</table>
								</div>
								
								<div id="divcancel">
								<!-- 內容-->
								<!-- 內容結束 -->
								</div>
							</div>
						</div>
						<!--查詢顯示區塊-->
						<div class="tab-pane fade  " id="service_03" role="tabpanel"
							aria-labelledby="contact-tab">
							<div class="searchdiv">
								<form class="example" action="#">
									<input type="text" placeholder="Search.." name="search">
									<button type="submit">
										<i class="fa fa-search"></i>
									</button>
								</form>
							</div>
							<!--以上是搜尋框-->
							<div class="tab-pane fade show active col-sm-12" id="service_03"
								role="tabpanel" aria-labelledby="home-tab">
								<div class="neworderright">
									<table class="table table table-hover">
										<thead class="thead-light">
											<tr>
												<th scope="col">電話號碼</th>
												<th scope="col">預計取餐時間</th>
												<th scope="col">合計</th>
												<th scope="col">付款狀態</th>
											</tr>
										</thead>
										<tbody id="search">
											<tr onclick="cancelShowDetail(event, 'order041')" class="tablinks" id="open5">
												<th scope="row" colspan="4" style="text-align: center;">尚無搜結果</th>
											</tr>
	
										</tbody>
									</table>
								</div>
								<div id="divsearch">
									<!-- 內容 -->
									<!-- 內容結束 -->
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<!--主頁內容結束 -->
	</div>
</body>
</html>