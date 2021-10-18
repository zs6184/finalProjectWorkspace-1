<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="tw.finalspring.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>寵物資訊管理</title>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- fontawesome icon -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<!--bootstrap & jQuery-ui-->
<link href="stylesheet/bootstrap.min.css" rel="stylesheet" />
<link href="stylesheet/jquery-ui.min.css" rel="stylesheet" />
<!-- DataTable套件 -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" />
<!--自訂樣式表-->
<link href="stylesheet/backstage.css" rel="stylesheet" />
<link href="stylesheet/backPetInfo.css" rel="stylesheet" />
<!--<script src="javascript/bootstrap.min.js"></script>-->
<script src="javascript/jquery-3.6.0.min.js"></script>
<script src="javascript/jquery-ui.min.js"></script>
<!--datepicker-ui中文補丁-->
<script src="javascript/jquery.ui.datepicker-zh-TW.min.js"></script>
<!-- DataTable套件 -->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<!--自訂js-->
<script src="javascript/backPetInfo.js"></script>
<script src="javascript/backstage.js"></script>
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
								<img src="image/husky.jpg" class="image shadow" />
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
										src="image/husky.jpg" class="imageMessage shadow" /> message
								</a></li>
								<li><a class="dropdown-item" href="#"> <img
										src="image/husky.jpg" class="imageMessage shadow" /> message
								</a></li>
								<li><a class="dropdown-item" href="#"> <img
										src="image/husky.jpg" class="imageMessage shadow" /> message
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
							<li><a href="ordermanage.html" class="itemDetails">訂單管理</a>
							</li>
							<li><a href="promo.html" class="itemDetails">優惠碼管理</a></li>
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
					<li class="m-0"><a href="SelectCustomerAll.Controller"
						class="center sidebarLight02"> <i class="fas fa-users mx-2"></i>
							<span class="items">會員管理</span>
					</a></li>
					<li class="m-0"><a href="EmployeesAll.Controller"
						class="center"> <i class="fas fa-address-card mx-2"></i> <span
							class="items">員工管理</span>
					</a></li>

				</ul>
			</nav>
		</div>
		<!-- container right -->
		<div class="containerRight">

			<div class="container-fluid">
				<!-- 查詢欄位 -->
				<div style="margin-top: 20px;">
					<form action="searchdata.controller" method="POST" id="srhForm">
						<div class="row justify-content-start">
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
							<div class="col-1 text-center">
								<button type="submit" class="btn btn-info text-white btn-sm"
									id="srhBtn">搜尋</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- 主頁內容 -->
			<div class="container-fluid ">
				<div class="row">
					<div class="col-11 bg-white mt-3 ms-5 box rounded-3 shadow">
						<table class="table table-striped table-hover mt-4" id="infoTable">
							<thead>
								<tr>
									<th scope="col">寵物編號</th>
									<th scope="col">寵物名</th>
									<th scope="col">類別</th>
									<th scope="col">品種</th>
									<th scope="col">性別</th>
									<th scope="col">年紀</th>
									<th scope="col">絕育狀態</th>
									<th scope="col">領養狀態</th>
									<th scope="col">領養會員ID</th>
									<th scope="col">領養會員姓名</th>
									<th scope="col">領養日期</th>
									<th scope="col" class="notetable">備註</th>
									<th scope="col">
										<button type="button" class="btn btn-primary btn"
											data-bs-toggle="modal" data-bs-target="#petInfoAdd"
											id="insertBtn">新增</button>
									</th>
								</tr>
							</thead>
							<tbody class="align-middle">
								<c:forEach var="arrPet" items="${arrPet}">
									<tr>
										<td class="ID">${arrPet.petId}</td>
										<td class="NAME">${arrPet.petName}</td>
										<td>${arrPet.category}</td>
										<td>${arrPet.species}</td>
										<td>${arrPet.sex}</td>
										<td>${arrPet.age}</td>
										<td>${arrPet.fixStatus}</td>
										<td>${arrPet.adoptStatus}</td>
										<td>${arrPet.cusId}</td>
										<td>${arrPet.cusName}</td>
										<td>${arrPet.adoptDate}</td>
										<td class="notetable">${arrPet.note}</td>
										<td>
											<button type="button" class="btn btn-danger updateBtn"
												data-bs-toggle="modal" data-bs-target="#petInfoAdd"
												onclick="select(this)">更新</button>
											<button type="button" class="btn btn-danger deleteBtn"
												data-bs-toggle="modal" data-bs-target="#deleteAlert"
												onclick="delAlert(this)">刪除</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>

			<!--寵物資料更新Modal-->
			<div class="modal fade" id="petInfoAdd" tabindex="-1">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="petModalTitle">寵物資料</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>
						<form class="add" action="insertPetInfo.controller" method="POST"
							id="modalForm" enctype="multipart/form-data">
							<div class="modal-body">
								<div class="row">
									<div class="text-center" id="mainbox">
										<fieldset>
											<legend>基本資料</legend>
											<div id="idSection">
												<label for="petId"><span>寵物編號</span></label> <input
													type="text" id="petId" name="petId" disabled />
											</div>
											<div>
												<label for="petName"><span>*寵物名</span></label> <input
													type="text" id="petName" name="petName"
													class="requiredValue" />
											</div>
											<div>
												<label for="category"><span>*類別</span></label> <input
													type="text" id="category" name="category"
													class="requiredValue" />
											</div>
											<div>
												<label for="species"><span>*品種</span></label> <input
													type="text" id="species" name="species"
													class="requiredValue" />
											</div>
											<div>
												<label for="sex"><span>*性別</span></label> <select id="sex"
													name="sex" class="requiredValue" required>
													<option value="" disabled selected></option>
													<option value="M">Male</option>
													<option value="F">Female</option>
												</select>
											</div>
											<div>
												<label for="age"><span>*年齡</span></label> <select id="age"
													name="age" class="requiredValue" required>
													<option value="" disabled selected></option>
													<option value="幼年">幼年</option>
													<option value="成年">成年</option>
													<option value="老年">老年</option>
												</select>
											</div>
											<div>
												<label for="fixStatus"><span>*絕育狀態</span></label> <select
													id="fixStatus" name="fixStatus" class="requiredValue"
													required>
													<option value="" disabled selected></option>
													<option value="已結紮">已結紮</option>
													<option value="未結紮">未結紮</option>
												</select>
											</div>
											<div>
												<label for="adoptStatus"><span>*領養狀態</span></label> <select
													id="adoptStatus" name="adoptStatus" class="requiredValue"
													required>
													<option value="" disabled selected></option>
													<option value="已領養">已領養</option>
													<option value="未領養">未領養</option>
												</select>
											</div>
										</fieldset>
										<hr />
										<fieldset>
											<legend>領養資料</legend>
											<div>
												<label for="cusId" class=""><span>領養會員ID</span></label> <input
													type="text" id="cusId" name="cusId"
													oninput="value=value.replace(/[^\d]/g,'')" />
											</div>
											<div>
												<label for="cusName" class=""><span>領養會員姓名</span></label> <input
													type="text" id="cusName" name="cusName" />
											</div>
											<div>
												<label for="adoptDate" class=""><span>領養日期</span></label> <input
													type="text" id="adoptDate" name="adoptDate"
													data-provide="datepicker" />
											</div>
										</fieldset>
										<hr />
										<fieldset></fieldset>
										<legend>相片</legend>
										<div class="row">
											<div class="col-6 justify-content-center">
												<input type="file" name="mypic" id="mypic" /><br>
											</div>
											<div class="col-6 justify-content-center">
												<div id="imgPreview">
													<img src="" style="width:100%;height:100%;" alt="圖片預覽區域"/>
												</div>
											</div>
										</div>
										<hr />
										<fieldset>
											<legend>備註</legend>
											<textarea id="note" name="note"></textarea>
										</fieldset>
										<div class="modal-footer">
											<div class="container-fluid">
												<div class="row justify-content-start">
													<div class="offset-4 col-4">
														<button type="submit" class="btn btn-primary">確認</button>
														<button type="reset" class="btn btn-secondary"
															data-bs-dismiss="modal">取消</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- End Of PetInfoAdd Modal -->


			<!-- 確認刪除提示框 Modal-->
			<div class="modal fade text-center" id="deleteAlert" tabindex="-1">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">提示信息</h4>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>
						<div class="modal-body items-align-center">
							<h4 style="margin-top: 10px; margin-bottom: 50px;"
								id="alertDialog">確認刪除此筆資料?</h4>
							<button type="button" class="btn btn-danger"
								data-bs-dismiss="modal" id="delConfirm" onclick="del()">確定</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
			<!-- End Of deleteAlert Modal-->

		</div>
	</div>
</body>
</html>