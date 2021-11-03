<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="tw.springbootfinal.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>領養預約紀錄管理</title>
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
<link href="/stylesheet/backReservation.css" rel="stylesheet" />
<!--<script src="/javascript/bootstrap.min.js"></script>-->
<script src="/javascript/jquery-3.6.0.min.js"></script>
<script src="/javascript/jquery-ui.min.js"></script>
<!--datepicker-ui中文補丁-->
<script src="/javascript/jquery.ui.datepicker-zh-TW.min.js"></script>
<!-- DataTable套件 -->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<!--自訂js-->
<script src="/javascript/BackReservation.js"></script>
<script src="/javascript/backstage.js"></script>
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
							<li><a href="#" class="/backstage/reservation/getAll">領養預約總覽</a></li>
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
<!-- 					<form action="/backstage/pet/searchdata.controller" method="POST" id="srhForm"> -->
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
									<th scope="col">寵物編號</th>
									<th scope="col">寵物名</th>
									<th scope="col">會員編號</th>
									<th scope="col">會員姓名</th>
									<th scope="col">會員連絡電話</th>
									<th scope="col">預約日期</th>
									<th scope="col">赴約狀態</th>
									<th scope="col">
										<button type="button" class="btn btn-primary btn"
											data-bs-toggle="modal" data-bs-target="#ReserveAdd"
											id="insertBtn">新增</button>
									</th>
								</tr>
							</thead>
							<tbody class="align-middle">
								<c:forEach var="arrRes" items="${arrRes}">
									<tr>
										<td>${arrRes.petId}</td>
										<td>${arrRes.petName}</td>
										<td class="ID">${arrRes.cusId}</td>
										<td class="NAME">${arrRes.cusRealname}</td>
										<td>${arrRes.phone}</td>
										<td class="DATE">${arrRes.reserveTime}</td>
										<td>${arrRes.keepStatus}</td>
										<td>
											<button type="button" class="btn btn-danger updateBtn"
												data-bs-toggle="modal" data-bs-target="#ReserveAdd"
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

			<!--新增預約紀錄Modal-->
			<input type="hidden" id="status" value="${status}"/>
			<div class="modal fade" id="ReserveAdd" tabindex="-1">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="petModalTitle">預約看寵資料</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>
					<form action="/backstage/reservation/addone" method="POST" id="modalForm">
						<div class="row">
							<div class="text-center" id="mainbox">
								<fieldset>
									<legend>寵物資訊</legend>
										<div>
											<label for="petId"><span>*寵物編號</span></label> 
											<input type="text" id="petId" name="petId" value="${thePet.petId}" 
												oninput="value=value.replace(/[^\d]/g,'')" class="requiredValue" readonly />
										</div>
										<div>
											<label for="petName"><span>寵物名</span></label> 
											<input type="text" id="petName" name="petName" value="${thePet.petName}" readonly />
										</div>
								</fieldset>
								<hr/>
								<fieldset>
									<legend>預約資訊</legend>
										<div>
											<label for="cusId"><span>*會員編號</span></label> 
											<input type="text" id="cusId" name="cusId" class="requiredValue" oninput="value=value.replace(/[^\d]/g,'')"/>
										</div>
										<div>
											<label for="cusRealname"><span>會員姓名</span></label> 
											<input type="text" id="cusRealname" name="cusRealname" readonly/>
										</div>
										<div>
											<label for="phone"><span>聯絡電話</span></label> 
											<input type="text" id="phone" name="phone" readonly/>
										</div>
										<div id="datetimepicker" class="mb-4">
											<label for="reserveTime"><span>預約看寵日期</span></label> 
											<input type="text" id="reserveTime" name="reserveTime" class="requiredValue"/>
										</div>
										<div>
											<label for="keepStatus"><span>赴約狀態</span></label>
											<select id="keepStatus" name="keepStatus">
												<option value="未赴約" selected>未赴約</option>
												<option value="已赴約">已赴約</option>
												<option hidden value="失約" >失約</option>
											</select>
										</div>
								</fieldset>
								<hr/>
								<div class="row justify-content-start">
									<button type="submit" id="sendReserveBtn" class="offset-4 col-2 me-3 btn btn-danger">送出預約</button>
									<button type="button" class="col-2 btn btn-secondary" data-bs-dismiss="modal">取消</button>
									<button type="button" id="missingBtn" class="offset-2 col-1 btn btn-warning text-white"
											 data-bs-toggle="modal" data-bs-target="#missingAlert" onclick="missAlert(this)">失約</button>
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
			
			<!-- 確認失約提示框 Modal-->			
			<div class="modal fade text-center" id="missingAlert" tabindex="-1">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">提示信息</h4>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>
						<div class="modal-body items-align-center">
							<h4 style="margin-top: 10px; margin-bottom: 50px;"
								id="alertMissDialog">確認標註此預約為客戶失約?</h4>
							<button type="button" class="btn btn-danger"
								data-bs-dismiss="modal" onclick="confirmMiss()">確定</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>		
			<!-- End Of 確認失約提示框 Modal-->
			
			<!-- 預約狀態提示框 Modal-->
			<div class="modal fade text-center" id="statusAlert" tabindex="-1">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">提示信息</h4>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>
						<div class="modal-body items-align-center">
							<h4 style="margin-top: 10px; margin-bottom: 50px;"
								id="alertDialog">修改失敗，該會員當日已有其他預約，無須重複預約</h4>
						</div>
						<hr/>
						<button type="button" class="btn btn-danger" data-bs-dismiss="modal">確定</button>
					</div>
				</div>
			</div>
			<!-- End of 預約狀態提示框 Modal-->
			
		</div>
	</div>
</body>
</html>