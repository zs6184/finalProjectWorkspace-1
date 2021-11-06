<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺管理系統</title>
<script src="/javascript/jquery-3.6.0.min.js"></script>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- DataTable js -->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<!-- fontawesome icon -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<!-- DataTable css -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">

<link href="/stylesheet/bootstrap.min.css" rel="stylesheet" />
<link href="/stylesheet/jquery-ui.min.css" rel="stylesheet" />
<!--自訂樣式表-->
<link href="/stylesheet/backstage.css" rel="stylesheet" />
<link href="/stylesheet/members.css" rel="stylesheet" />
<link href="/stylesheet/message.css" rel="stylesheet" />
<!--<script src="javascript/bootstrap.min.js"></script>-->

<!--自訂js-->
<script src="/javascript/backstage.js"></script>
<script src="/javascript/customer.js"></script>
<link rel="icon" type="image/png" href="/font/favicon1.png">

</head>
<body>
	<!-- 導覽列 -->
	<header>
		<div class="container-fluid border shadow w-100">
			<nav class="navbar navbar-light bg-white m-0 ">
				<div class="container-fluid">
					<!-- 超連結到主頁 -->
					<a class="navbar-brand row" href="#">後臺管理系統</a>
					<!-- 搜尋欄及按鈕 -->
					<form class="d-flex offset-5 col-3">
						<input class="form-control me-2 ms-5 " type="search"
							placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-success" type="submit">Search</button>
					</form>
					<form action="/Users/logout.Controller" method="post">
						<div class="">
							<!-- 帳號頭像及功能 -->
							<div class="dropdown">
								<button class="btn-transparent" type="button"
									id="dropdownButton01" data-bs-toggle="dropdown">
									<img src="/downloadTempDir/${imageName}" class="image shadow" />
								</button>
								<ul class="dropdown-menu  dropdown-menu-end shadow"
									aria-labelledby="dropdownMenuButton01">
									<li><a class="dropdown-item"
										href="/Users/SelectCustomer.controller#information">${realName}</a></li>
									<li><a class="dropdown-item"
										href="/Users/loginIndex.Controller">浪跡</a></li>
									<li><button type="submit" class="dropdown-item"
											value="logout">Logout</button></li>
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
											src="/image/husky.jpg" class="imageMessage shadow" />
											message
									</a></li>
									<li><a class="dropdown-item" href="#"> <img
											src="/image/husky.jpg" class="imageMessage shadow" />
											message
									</a></li>
									<li><a class="dropdown-item" href="#"> <img
											src="/image/husky.jpg" class="imageMessage shadow" />
											message
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
					</form>
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
							<li><a href="/Backstage/product/findAll" class="itemDetails">餐點總覽</a></li>
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
							<li><a href="/Backstage/bookings" class="itemDetails">訂位查詢</a></li>
							<li><a href="#" class="itemDetails">訂位更新</a></li>
						</ul></li>
					<li><a href="#sublist03" data-bs-toggle="collapse"
						id="dropdown03" class="center"> <i
							class="fas fa-bullhorn mx-2"></i> <span class="items">公告管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist03" class="list-unstyled collapse">
							<li><a href="#" class="itemDetails">公告總覽</a></li>
							<li><a
								href="/Backstage/announcements/backannouncements.controller"
								class="itemDetails">新增公告</a></li>
							<li><a href="#" class="itemDetails">公告更新</a></li>
						</ul></li>
					<li><a href="#sublist04" data-bs-toggle="collapse"
						id="dropdown04" class="center"> <i class="fas fa-cat mx-2"></i>
							<span class="items">寵物管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist04" class="list-unstyled collapse">
							<li><a href="/Backstage/pet/backpetinfo.controller"
								class="itemDetails">寵物資訊總覽</a></li>
							<li><a href="/Backstage/reservation/getAll"
								class="itemDetails">領養預約總覽</a></li>
							<li><a href="#" class="itemDetails">領養記錄查詢</a></li>
						</ul></li>
					<li class="m-0"><a href="/message.html" class="center"> <i
							class="fas fa-comments-dollar mx-2"></i> <span class="items">客服管理</span>
					</a></li>
					<li class="m-0"><a
						href="/Backstage/SelectCustomerAll.Controller"
						class="center sidebarLight02"> <i class="fas fa-users mx-2"></i>
							<span class="items ${role}" id="role">會員管理</span>
					</a></li>
					<li class="m-0 d-none" id="employeeManagement"><a
						href="/Backstage/EmployeesAll.Controller" class="center"> <i
							class="fas fa-address-card mx-2"></i> <span class="items">員工管理</span>
					</a></li>

				</ul>
			</nav>
		</div>

		<!-- container right -->
		<div class="containerRight">
			<div class="container-fluid ">
				<div class="row">
					<div
						class="col-4 bg-white my-5 ms-5 box2 rounded-3 shadow  overflow-auto">
						<!-- message left -->
						<div class="row">
							<div class="col-4">
								<div>
									<img src="/image/m6.jpg"
										class="messageImage mx-2 my-4 text-center shadow" />
								</div>
							</div>
							<div class="col p-3">
								<h5>王小明</h5>
								<p class="text m-1">我想把你們店買下來，請老闆跟我聯繫，找時間跟老闆喝杯咖啡，商談購買事宜</p>
								<p class="text-end m-0 p-0">time</p>
							</div>
							<hr />
							<div class="col-4">
								<div>
									<img src="/image/m6.jpg"
										class="messageImage mx-2 my-4 text-center shadow" />
								</div>
							</div>
							<div class="col p-3">
								<h5>王小明</h5>
								<p class="text m-1">我想把你們店買下來，請老闆跟我聯繫，找時間跟老闆喝杯咖啡，商談購買事宜</p>
								<p class="text-end m-0 p-0">time</p>
							</div>
							<hr />
							<div class="col-4">
								<div>
									<img src="/image/m6.jpg"
										class="messageImage mx-2 my-4 text-center shadow" />
								</div>
							</div>
							<div class="col p-3">
								<h5>王小明</h5>
								<p class="text m-1">我想把你們店買下來，請老闆跟我聯繫，找時間跟老闆喝杯咖啡，商談購買事宜</p>
								<p class="text-end m-0 p-0">time</p>
							</div>
							<hr />
							<div class="col-4">
								<div>
									<img src="/image/m6.jpg"
										class="messageImage mx-2 my-4 text-center shadow" />
								</div>
							</div>
							<div class="col p-3">
								<h5>王小明</h5>
								<p class="text m-1">我想把你們店買下來，請老闆跟我聯繫，找時間跟老闆喝杯咖啡，商談購買事宜</p>
								<p class="text-end m-0 p-0">time</p>
							</div>
							<hr />
							<div class="col-4">
								<div>
									<img src="/image/m6.jpg"
										class="messageImage mx-2 my-4 text-center shadow" />
								</div>
							</div>
							<div class="col p-3">
								<h5>王小明</h5>
								<p class="text m-1">我想把你們店買下來，請老闆跟我聯繫，找時間跟老闆喝杯咖啡，商談購買事宜</p>
								<p class="text-end m-0 p-0">time</p>
							</div>
							<hr />
						</div>
					</div>
					<!-- right container -->
					<div class="col my-5 mx-5 bg-white box2 rounded-3 shadow">
						<div class="row">
							<!-- messageHead -->
							<div class="col-2">
								<div>
									<img src="/image/m6.jpg"
										class="messageImage mx-2 my-4 text-center shadow" />
								</div>
							</div>
							<div class="col-10 p-3 d-flex align-items-center">
								<div>
									<h5>王小明</h5>
								</div>
							</div>
							<hr />
							<!-- messageBody -->
							<div id="messageBody">
								訊息內容
								訊息內容
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
								<p>訊息內容</p>
							</div>
							<hr />
							<!-- messageFoot -->
							<div class="d-flex align-items-end mt-2">
								<div class="row col-12">
									<div class="col-11">
										<input type="text" id="send" class="form-control"> 
									</div>
									<label class="col-1"
										for="send" class="col-form-label"><button type="button" class="btn btn-primary">Send</button></label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>