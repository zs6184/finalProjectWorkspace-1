<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺管理系統</title>
<script src="/javascript/jquery-3.6.0.min.js"></script>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- DataTable js -->
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<!-- fontawesome icon -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<!-- DataTable css -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">

<link href="/stylesheet/bootstrap.min.css" rel="stylesheet" />
<link href="/stylesheet/jquery-ui.min.css" rel="stylesheet" />
<!--自訂樣式表-->
<link href="/stylesheet/backstage.css" rel="stylesheet" />
<link href="/stylesheet/members.css" rel="stylesheet" />
<!--<script src="javascript/bootstrap.min.js"></script>-->

<!--自訂js-->
<script src="/javascript/backstage.js"></script>
<script src="/javascript/customer.js"></script>
<link rel="icon" type="image/png"  href="/font/favicon1.png">

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
									<li><a class="dropdown-item" href="/Users/SelectCustomer.controller#information">${realName}</a></li>
									<li><a class="dropdown-item" href="/Users/loginIndex.Controller">浪跡</a></li>
									<li><button type="submit" class="dropdown-item"
											value="logout">Logout</button></li>
								</ul>
							</div>
							<!-- 訊息資訊 -->
							<div class="dropdown" >
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
							<li><a href="/order/back.ordermanage.controller" class="itemDetails">訂單管理</a>
							</li>
							<li><a href="/back.coupons" class="itemDetails">優惠碼管理</a></li>
							<li><a href="/backstage/bookings" class="itemDetails">訂位查詢</a></li>
							<li><a href="/backstage/bookings" class="itemDetails">訂位更新</a></li>
						</ul></li>
					<li><a href="#sublist03" data-bs-toggle="collapse"
						id="dropdown03" class="center"> <i
							class="fas fa-bullhorn mx-2"></i> <span class="items">公告管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist03" class="list-unstyled collapse">
							<li><a href="/backstage/announcements/backannouncements.controller" class="itemDetails">公告總覽</a></li>
							<li><a href="/backstage/announcements/backannouncements.controller" class="itemDetails">新增公告</a></li>
							<li><a href="/backstage/announcements/backannouncements.controller" class="itemDetails">公告更新</a></li>
						</ul></li>
					<li><a href="#sublist04" data-bs-toggle="collapse"
						id="dropdown04" class="center"> <i class="fas fa-cat mx-2"></i>
							<span class="items">寵物管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist04" class="list-unstyled collapse">
							<li><a href="/Backstage/pet/backpetinfo.controller" class="itemDetails">寵物資訊總覽</a>
							</li>
							<li><a href="/Backstage/reservation/getAll" class="itemDetails">領養預約總覽</a></li>
							<li><a href="#" class="itemDetails">領養記錄查詢</a></li>
						</ul></li>
					<li class="m-0"><a href="/Backstage/MessagePage.Controller" class="center"> <i
							class="fas fa-comments-dollar mx-2"></i> <span class="items">客服管理</span>
					</a></li>
					<li class="m-0"><a href="/Backstage/SelectCustomerAll.Controller"
						class="center sidebarLight02"> <i class="fas fa-users mx-2"></i>
							<span class="items ${role}" id="role">會員管理</span>
					</a></li>
					<li class="m-0 d-none" id="employeeManagement"><a href="/Backstage/EmployeesAll.Controller"
						class="center"> <i class="fas fa-address-card mx-2"></i> <span
							class="items">員工管理</span>
					</a></li>

				</ul>
			</nav>
		</div>
		<!-- container right -->
		<div class="containerRight">
			<!-- 主頁內容 -->
			<div class="container-fluid mt-4">
				<div class="row">
					<div class="col-11 bg-white mt-3 ms-5 box rounded-3 shadow p-1 mb-4">
						<table class="table table-striped table-hover mt-4 p-4" id="customerTable">
							<thead>
								<tr>
									<th scope="col">會員ID</th>
									<th scope="col">員工姓名</th>
									<th scope="col">暱稱</th>
									<th scope="col">性別</th>
									<th scope="col">連絡電話</th>
									<th scope="col">E-mail</th>
									<th scope="col">生日</th>
<!-- 									<th scope="col">地址</th> -->
									<th scope="col">失約次數</th>
									<th scope="col">備註</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody class="align-middle" id="tbody">

								<c:forEach var="cus" items="${cus}">
									<tr>
										<th scope="row">${cus.cusId}</th>
										<td>${cus.cusRealname}</td>
										<td>${cus.aka}</td>
										<td>${cus.gender}</td>
										<td>${cus.phoneNumber}</td>
										<td>${cus.email}</td>
										<td>${cus.birthdate}</td>
<%-- 										<td class="textOver">${cus.address}</td> --%>
										<td>${cus.noShow}</td>
										<td>${cus.note}</td>
										<td><button type="button" class="btn btn-danger"
												onclick="selectOneCus(${cus.cusId})" data-bs-toggle="modal"
												data-bs-target="#memberUpdataModal">更新</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>

			<!-- 會員資料更新modal -->
			<div class="modal fade" id="memberUpdataModal" tabindex="-1">
				<div class="modal-dialog modal-lg ">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="memberModalLabel">會員資料</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>
						<form action="/Backstage/UpdateCustomerById.Controller"
							id="updateForm" method="post">
							<div class="modal-body">
								<div class="row">
									<!-- left -->
									<div class="offset-1 col-4">
										<div class="ratio ratio-1x1 mt-1 mb-3"><!--  mb-3 -->
											<img id="image" src="/downloadTempDir/${imageName}" class="rounded-3 shadow" />
										</div>
<!-- 										<div class="row"> -->
<!-- 											<div class="col text-center"> -->
<!-- 												<h5> -->
<!-- 													<span class="badge bg-warning text-dark">VIP</span> -->
<!-- 												</h5> -->
<!-- 											</div> -->
<!-- 											<div class="col text-center"> -->
<!-- 												<h5> -->
<!-- 													<span class="badge bg-warning text-dark">超級有錢人</span> -->
<!-- 												</h5> -->
<!-- 											</div> -->
<!-- 											<div class="col text-center"> -->
<!-- 												<h5> -->
<!-- 													<span class="badge bg-warning text-dark">富二代</span> -->
<!-- 												</h5> -->
<!-- 											</div> -->
<!-- 											<div class="col text-center"> -->
<!-- 												<h5> -->
<!-- 													<span class="badge bg-warning text-dark">花花公子</span> -->
<!-- 												</h5> -->
<!-- 											</div> -->
<!-- 											<div class="col text-center"> -->
<!-- 												<h5> -->
<!-- 													<span class="badge bg-warning text-dark">帥</span> -->
<!-- 												</h5> -->
<!-- 											</div> -->
<!-- 										</div> -->
										<input type="hidden" name="cusUpdateId" id="cusUpdateId"
											value=""> <label for="cusNameUpdate"
											class="form-label">姓名:</label> <input id="cusNameUpdate"
											name="cusNameUpdate" type="text" class="form-control mb-3"
											value="" disabled /> <label for="phoneNumberUpdate"
											class="form-label">連絡電話:</label> <input
											id="phoneNumberUpdate" name="phoneNumberUpdate" type="tel"
											class="form-control mb-3" value="" disabled />
											<label
											for="emailUpdate" class="form-label">E-mail:</label> <input
											id="emailUpdate" name="emailUpdate" type="email"
											class="form-control mb-3" value="" disabled /> 
									</div>
									<!-- right -->
									<div class="offset-1 col-5">

										<label for="userNameUpdate" class="form-label">暱稱:</label> <input
											id="userNameUpdate" name="userNameUpdate" type="text"
											class="form-control mb-3" value="" disabled /> <label
											for="genderUpdate" class="form-label">性別:</label> <input
											id="genderUpdate" name="genderUpdate" type="text"
											class="form-control mb-3" value="" disabled /> <label
											for="birthdateUpdate" class="form-label">生日:</label> <input
											id="birthdateUpdate" name="birthdateUpdate" type="date"
											class="form-control mb-3" value="" disabled /> <label
											for="addressUpdate" class="form-label">地址:</label> <input
											id="addressUpdate" name="addressUpdate" type="text"
											class="form-control mb-3" value="" disabled /> <label
											for="notesUpdate" class="form-label">備註:</label>
										<textarea id="notesUpdate" name="notesUpdate"
											class="form-control mb-3"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="container-fluid">
									<div class="row">
<!-- 										<div class="col-2"> -->
<!-- 											<button type="button" class="btn btn-primary">新增標籤</button> -->
<!-- 										</div> -->
										<div class="offset-5 col">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">取消</button>
											<button type="submit" class="btn btn-primary">完成</button>
										</div>
										<div class="offset-2 col-2">
											<button type="button" class="btn btn-danger d-none" id="deleteAccount"
												data-bs-toggle="modal" data-bs-target="#deleteButton">刪除帳號</button>
										</div>
									</div>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
			<!-- modal確認視窗 -->
			<div class="modal fade" id="deleteButton" tabindex="-1">
				<div class="modal-dialog modal-dialog-centered modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">溫馨提示</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>
						<div class="modal-body">刪除後無法復原，請確定是否要刪除?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">取消</button>
							<button type="button" onclick="deleteOneCus()"
								class="btn btn-primary">送出</button>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>