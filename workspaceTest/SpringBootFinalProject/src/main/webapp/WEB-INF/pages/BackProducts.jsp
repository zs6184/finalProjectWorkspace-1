<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="tw.springbootfinal.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>商品管理</title>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- fontawesome icon -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<!--bootstrap & jQuery-ui-->
<link href="/stylesheet/bootstrap.min.css" rel="stylesheet" />
<link href="/stylesheet/jquery-ui.min.css" rel="stylesheet" />
<!-- DataTable套件 -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" />
<!--自訂樣式表-->
<link href="/stylesheet/backstage.css" rel="stylesheet" />
<link href="/stylesheet/BackProducts.css" rel="stylesheet" />
<!--<script src="/javascript/bootstrap.min.js"></script>-->
<script src="/javascript/jquery-3.6.0.min.js"></script>
<script src="/javascript/jquery-ui.min.js"></script>
<!--datepicker-ui中文補丁-->
<script src="/javascript/jquery.ui.datepicker-zh-TW.min.js"></script>
<!-- DataTable套件 -->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<!--自訂js-->
<script src="/javascript/BackProducts.js"></script>
<script src="/javascript/backstage.js"></script>
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
							<li><a href="/backstage/product/findAll" class="itemDetails">餐點總覽</a></li>
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
							<li><a href="/bookings" class="itemDetails">訂位查詢</a></li>
							<li><a href="#" class="itemDetails">訂位更新</a></li>
						</ul></li>
					<li><a href="#sublist03" data-bs-toggle="collapse"
						id="dropdown03" class="center"> <i
							class="fas fa-bullhorn mx-2"></i> <span class="items">公告管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist03" class="list-unstyled collapse">
							<li><a href="#" class="itemDetails">公告總覽</a></li>
							<li><a href="/backstage/announcements/backannouncements.controller" class="itemDetails">新增公告</a></li>
							<li><a href="#" class="itemDetails">公告更新</a></li>
						</ul></li>
					<li><a href="#sublist04" data-bs-toggle="collapse"
						id="dropdown04" class="center"> <i class="fas fa-cat mx-2"></i>
							<span class="items">寵物管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist04" class="list-unstyled collapse">
							<li><a href="/backstage/pet/backpetinfo.controller" class="itemDetails">寵物資訊總覽</a></li>
							<li><a href="/backstage/reservation/getAll" class="itemDetails">領養預約總覽</a></li>
							<li><a href="#" class="itemDetails">領養記錄查詢</a></li>
						</ul></li>
					<li class="m-0"><a href="#" class="center"> <i
							class="fas fa-comments-dollar mx-2"></i> <span class="items">客服管理</span>
					</a></li>
					<li class="m-0"><a href="/Backstage/SelectCustomerAll.Controller"
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
		<!-- container right -->
		<div class="containerRight">
<!-- 			<div class="container-fluid"> -->
<!-- 				查詢欄位 -->
<!-- 				<div style="margin-top: 20px;"> -->
<!-- 					<form action="/Backstage/pet/searchdata.controller" method="POST" id="srhForm"> -->
<!-- 						<div class="row justify-content-start"> -->
<!-- 							<div class="col-1 text-center"> -->
<!-- 								<select name="category" id="srhCategory"> -->
<!-- 									<option value="">不限類別</option> -->
<%-- 									<c:forEach var="cateSet" items="${cateSet}"> --%>
<%-- 										<option value="${cateSet}" name="${cateSet}">${cateSet}</option> --%>
<%-- 									</c:forEach> --%>
<!-- 								</select> -->
<!-- 							</div> -->
<!-- 							<div class="col-1 text-center"> -->
<!-- 								<select name="sex" id="srhSex"> -->
<!-- 									<option value="">不限性別</option> -->
<%-- 									<c:forEach var="sexSet" items="${sexSet}"> --%>
<%-- 										<option value="${sexSet}" name="${sexSet}">${sexSet}</option> --%>
<%-- 									</c:forEach> --%>

<!-- 								</select> -->
<!-- 							</div> -->
<!-- 							<div class="col-1 text-center"> -->
<!-- 								<button type="submit" class="btn btn-info text-white btn-sm" -->
<!-- 									id="srhBtn">搜尋</button> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</form> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<!-- 主頁內容 -->
			<div class="container-fluid ">
				<div class="row">
					<div class="col-11 bg-white mt-3 ms-5 box rounded-3 shadow">
						<table class="table table-striped table-hover mt-4" id="infoTable">
							<thead>
								<tr>
									<th scope="col">商品編號</th>
									<th scope="col">商品名稱</th>
									<th scope="col">類別</th>
									<th scope="col">單價</th>
									<th scope="col">庫存剩餘量</th>
									<th scope="col">在訂總量</th>
									<th scope="col">上架狀態</th>
									<th scope="col">
										<button type="button" class="btn btn-primary btn"
											data-bs-toggle="modal" data-bs-target="#ProductModal"
											id="insertBtn">新增</button>
									</th>
								</tr>
							</thead>
							<tbody class="align-middle">
								<c:forEach var="arrRes" items="${arrRes}">
									<tr>
										<td class="ID">${arrRes.productID}</td>
										<td class="NAME">${arrRes.productName}</td>
										<td>${arrRes.category}</td>
										<td>${arrRes.unitprice}</td>
										<td>${arrRes.totalInstore}</td>
										<td>${arrRes.totalInorder}</td>
										<td>${arrRes.onShelve}</td>
										<td>
											<button type="button" class="btn btn-danger updateBtn"
												data-bs-toggle="modal" data-bs-target="#ProductModal"
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

			<!--商品資料Modal-->
			<div class="modal fade" id="ProductModal" tabindex="-1">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title">商品資料</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>
					<form action="/Backstage/product/insertone" method="POST" 
							id="modalForm" enctype="multipart/form-data">
						<div class="row">
							<div class="text-center" id="mainbox">
								<fieldset>
									<legend>商品資訊</legend>
										<div id="pIdSection">
											<label for="productID"><span>商品編號</span></label> 
											<input type="text" id="productID" name="productID" value="${theProd.productID}" readonly />
										</div>
										<div>
											<label for="productName"><span>*商品名稱</span></label> 
											<input type="text" id="productName" class="requiredValue" name="productName" value="${theProd.productName}"/>
										</div>
										<div>
											<label for="category"><span>*類別</span></label>
											<select id="category" name="category" required>
												<option value="" disabled selected></option>
												<option value="飯">飯</option>
												<option value="麵">麵</option>
												<option value="飲料" >飲料</option>
												<option value="甜點" >甜點</option>
											</select>
										</div>
										<div>
											<label for="unitprice"><span>*單價</span></label> 
											<input type="text" id="unitprice" name="unitprice" value="${theProd.unitprice}" 
												oninput="value=value.replace(/[^\d]/g,'')" class="requiredValue"/>
										</div>
										<div>
											<label for="totalInstore"><span>*庫存剩餘量</span></label> 
											<input type="text" id="totalInstore" name="totalInstore" value="${theProd.totalInstore}" 
												oninput="value=value.replace(/[^\d]/g,'')" class="requiredValue"/>
										</div>
										<div>
											<label for="totalInorder"><span>在訂總量</span></label> 
											<input type="text" id="totalInorder" name="totalInorder" value="${theProd.totalInorder}" disabled/>
										</div>
										<div>
											<label for="onShelve"><span>*上架狀態</span></label> 
											<select id="onShelve" name="onShelve" required>
												<option value="" disabled selected></option>
												<option value="已上架">已上架</option>
												<option value="已下架">已下架</option>
											</select>
										</div>
								</fieldset>
								<hr/>
								<fieldset>
									<legend>相片</legend>
									<div class="row">
										<div class="col-6 justify-content-center">
											<label for="mypic" class="btn btn-outline-success text-center">選擇圖片</label>
											<input type="file" name="mypic" id="mypic" accept="image/*" style="display:none"/><br>
										</div>
										<div class="col-6 justify-content-center">
											<div id="imgPreview" class="border" style="width:300px; height:200px;">
												<img src="" style="width:100%;height:100%;" alt="圖片預覽區域"/>
											</div>
										</div>
									</div>
								</fieldset>
								<hr/>
								<fieldset>
									<legend>商品介紹</legend>
									<textarea id="note" name="note"></textarea>
								</fieldset>
								<hr/>
								<div class="row justify-content-start">
									<button type="submit" class="offset-4 col-2 me-3 btn btn-danger">確認</button>
									<button type="button" class="col-2 btn btn-secondary" data-bs-dismiss="modal">取消</button>
								</div>
							</div>
						</div>	
					</form>
					</div>
				</div>
			</div>
			<!-- End Of 商品資料 Modal -->


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
			<!-- End Of 確認刪除提示框 Modal-->
						
		</div>
	</div>
</body>
</html>