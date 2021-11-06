<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺管理系統</title>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- fontawesome icon -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<link href="/stylesheet/bootstrap.min.css" rel="stylesheet" />
<link href="/stylesheet/jquery-ui.min.css" rel="stylesheet" />
<!--自訂樣式表-->
<link href="/stylesheet/backstage.css" rel="stylesheet" />
<link href="/stylesheet/employees.css" rel="stylesheet" />
<!--<script src="javascript/bootstrap.min.js"></script>-->
<script src="/javascript/jquery-3.6.0.min.js"></script>
<!-- DataTable css -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
<!--自訂js-->
<script src="/javascript/backstage.js"></script>
<script src="/javascript/employees.js"></script>
<!-- DataTable js -->
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<link rel="icon" type="image/png"  href="/font/favicon1.png">
</head>
<body>
	<!-- 導覽列 -->
	<header>
		<div class="container-fluid border shadow w-100">
			<nav class="navbar navbar-light bg-white m-0 ">
				<div class="container-fluid">
					<!-- 超連結到主頁 -->
					<a class="navbar-brand row" href="/backstage.html">後臺管理系統</a>
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
							<li><a href="/order/back.ordermanage.controller" class="itemDetails">訂單管理</a>
							</li>
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
							<li><a href="/Backstage/announcements/backannouncements.controller" class="itemDetails">新增公告</a></li>
							<li><a href="#" class="itemDetails">公告更新</a></li>
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
					<li class="m-0"><a
						href="/Backstage/SelectCustomerAll.Controller" class="center">
							<i class="fas fa-users mx-2"></i> <span class="items">會員管理</span>
					</a></li>
					<li class="m-0"><a href="/Backstage/EmployeesAll.Controller"
						class="center sidebarLight02"> <i
							class="fas fa-address-card mx-2"></i> <span class="items">員工管理</span>
					</a></li>

				</ul>
			</nav>
		</div>
		<!-- container right -->
		<div class="containerRight">
			<!-- 分頁按鈕 -->
			<div class="page container">
				<div class="row d-flex justify-content-end">
					<div class="col-2">
						<!-- Button trigger modal      -->
						<div class="">
						<button type="button" onclick="selectAccount()"
							class="btn btn-primary btn-lg ms-5" data-bs-toggle="modal"
							data-bs-target="#empModal">新增</button></div>
						<!-- Modal:員工帳號建立頁面 -->
						<!--fade為動畫-->
						<div class="modal fade" id="empModal" tabindex="-1">
							<div class="modal-dialog modal-dialog-centered modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h3 class="modal-title ms-3" id="ModalLabel">建立帳號</h3>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal"></button>
									</div>
									<form action="/Backstage/CreateEmpAccount.Controller"
										method="post" id="empForm">
										<div class="modal-body">
											<div class="row">
												<div class="offset-1 col-4 my-3">
													<label for="empUsername" class="form-label">帳號:</label> <input
														id="empUsername" name="empUsername" type="text" value=""
														class="form-control" disabled />
												</div>
												<div class="offset-2 col-4 my-3">
													<label for="empPassword" class="form-label">密碼:</label> <input
														id="empPassword" name="empPassword" type="password"
														class="form-control" disabled />
												</div>
											</div>
											<div class="row">
												<div class="offset-1 col-4 my-3">
													<label for="empRealname" class="form-label">姓名:</label> <input
														id="empRealname" name="empRealname" type="text"
														class="form-control" maxlength="10" autocomplete="off"
														autofocus required />

												</div>
												<div class="offset-2 col-4 d-flex align-items-center my-3">
													<label for="male" class="form-check-label mt-4">性別:</label>
													<input id="male" name="gender" value="male" type="radio"
														class="form-check-input mx-2 mt-4" required checked /> <label
														for="male" class="mt-4">男</label> <input id="female"
														name="gender" value="female" type="radio"
														class="form-check-input mx-2 mt-4" /> <label for="female"
														class="mt-4">女</label>
												</div>
											</div>
											<div class="row">
												<div class="offset-1 col-4 my-3 relative">
													<label for="phoneNumber" class="form-label">連絡電話:</label> <input
														id="phoneNumber" name="phoneNumber" max="10"
														pattern="[0]{1}[9]{1}\d{8}" type="tel"
														class="form-control" maxlength="10" autocomplete="off"
														required />
													<div class="invalid-tooltip" id="phoneInvalid">電話號碼已被使用</div>
												</div>
												<div class="offset-2 col-4 my-3">
													<label for="birthdate" class="form-label">生日:</label> <input
														id="birthdate" name="birthdate" type="date"
														class="form-control" required />
												</div>
											</div>
											<div class="row">
												<div class="offset-1 col-4 my-3">
													<label class="form-label">職稱:</label> <select name="title"
														class="form-select" required>
														<option selected></option>
														<option value="waiter">服務生</option>
														<option value="senior waiter">資深服務生</option>
														<option value="assistant chef">廚房助手</option>
														<option value="chef">廚師</option>
														<option value="store manager">店長</option>
													</select>

												</div>
												<div class="offset-2 col-4 my-3">
													<label for="hiredate" class="form-label">到職日:</label> <input
														id="hiredate" name="hiredate" type="date"
														class="form-control" required />
												</div>
												<div class="offset-1 col-7 my-3">
													<label for="address" class="form-label">地址:</label> <input
														id="address" name="address" type="text"
														class="form-control" maxlength="50" autocomplete="off"
														required />
												</div>
											</div>
										</div>

										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">取消</button>
											<button type="submit" class="btn btn-primary">建立</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
			<!-- 主頁內容 -->
			<div class="container-fluid ">
				<div class="row">
					<div class="col-11 bg-white mt-3 ms-5 box rounded-3 shadow p-1 mb-5">
						<table class="table table-striped table-hover mt-4 display p-4"
							id="myTable">
							<thead>
								<tr>
									<th scope="col">員工ID</th>
									<th scope="col">員工姓名</th>
									<th scope="col">職稱</th>
									<th scope="col">性別</th>
									<th scope="col">連絡電話</th>
									<th scope="col">生日</th>
									<th scope="col">到職日期</th>
									<th scope="col">地址</th>
									<th scope="col">備註</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody class="align-middle">
								<c:forEach var="emp" items="${emp}">
									<tr>
										<th scope="row">${emp.empId}</th>
										<td>${emp.empRealname}</td>
										<td>${emp.title}</td>
										<td>${emp.gender}</td>
										<td>${emp.phoneNumber}</td>
										<td>${emp.birthdate}</td>
										<td>${emp.hiredate}</td>
										<td>${emp.address}</td>
										<td>${emp.note}</td>
										<td><button type="button" class="btn btn-danger"
												onclick="selectOneEmp(${emp.empId})" data-bs-toggle="modal"
												data-bs-target="#empUpdataModal">更新</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>
			<!-- 員工資料更新modal -->
			<div class="modal fade" id="empUpdataModal" tabindex="-1">
				<div class="modal-dialog modal-lg ">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="empModalLabel">員工資料</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>
						<form action="/Backstage/UpdateEmployeeById.Controller"
							id="updateEmpForm" method="post">
							<div class="modal-body">
								<div class="row">
									<!-- left -->
									<div class="offset-1 col-4">
										<div class="ratio ratio-1x1 mt-1 mb-3">
											<img src="/image/m2.jpg" class="rounded-3 shadow" />
										</div>
										<input type="hidden" id="empUpdateId" name="empUpdateId"
											value="" /> <label for="empNameUpdate" class="form-label">姓名:</label>
										<input id="empNameUpdate" name="empNameUpdate" type="text"
											class="form-control mb-3" value="" disabled /> <label
											for="hiredateUpdate" class="form-label">到職日:</label> <input
											id="hiredateUpdate" name="hiredateUpdate" type="date"
											class="form-control mb-3" value="" disabled /> <label for=""
											class="form-label">職稱:</label> <select id="titleUpdate"
											name="titleUpdate" class="form-select mb-3" required>
											<option value="waiter">服務生</option>
											<option value="senior waiter">資深服務生</option>
											<option value="assistant chef">廚房助手</option>
											<option value="chef">廚師</option>
											<option value="store manager">店長</option>
										</select>
									</div>
									<!-- right -->
									<div class="offset-1 col-5">
										<label for="genderUpdate" class="form-label">性別:</label> <input
											id="genderUpdate" name="genderUpdate" type="text"
											class="form-control mb-3" value="" disabled /> <label
											for="phoneNumberUpdate" class="form-label">連絡電話:</label> <input
											id="phoneNumberUpdate" name="phoneNumberUpdate" type="tel"
											class="form-control mb-3" value="" disabled /> <label
											for="birthdateUpdate" class="form-label">生日:</label> <input
											id="birthdateUpdate" name="birthdateUpdate" type="date"
											class="form-control mb-3" value="" disabled /> <label
											for="addressUpdate" class="form-label">地址:</label> <input
											id="addressUpdate" name="addressUpdate" type="text"
											class="form-control mb-3" value="" disabled /> <label
											for="notesUpdate" class="form-label">備註:</label>
										<textarea id="notesUpdate" name="notesUpdate"
											class="form-control mb-3" maxlength="200">長期曠職</textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<div class="container-fluid">
									<div class="row">
										<div class="offset-5 col">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">取消</button>
											<button type="submit" class="btn btn-primary">完成</button>
										</div>
										<div class="offset-2 col-2">
											<button type="button" class="btn btn-danger"
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
							<button type="button" onclick="deleteOneEmp()"
								class="btn btn-primary">送出</button>
						</div>
					</div>
				</div>
			</div>


		</div>

	</div>
</body>
</html>