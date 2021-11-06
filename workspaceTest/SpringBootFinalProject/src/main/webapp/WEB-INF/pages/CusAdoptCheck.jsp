<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員中心-領養預約紀錄</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link href="/stylesheet/jquery-ui.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/stylesheet/index.css" />
<!--CSS在這邊 要注意放在bootstrap樣式表CDN後面 不然權重相同的部分會被bootstrap蓋過去-->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="/javascript/jquery-ui.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.0-alpha.1/jquery-ui.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- DataTable套件 -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" />

<!--datepicker-ui中文補丁-->
<script src="/javascript/jquery.ui.datepicker-zh-TW.min.js"></script>
<!--javaScript掛到這邊-->
<script src="/javascript/index.js"></script>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<!-- DataTable套件 -->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<!--自訂樣式表-->
<link href="/stylesheet/CusAdoptCheck.css" rel="stylesheet" />
<!--自訂js-->
<script src="/javascript/CusAdoptCheck.js"></script>

</head>

<body id="top" style="background-image: url(/image/背景4.jpg)";>
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
	<input type="hidden" id="sessionUsername" value="<%=session.getAttribute("username") %>">
	<!-- 抓取sessionAttribute -->
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
										<li id="whp1"><a
											href="/Users/SelectCustomer.controller#information"
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
							<li><a href="/petinfo.controller" target="_self">寵物領養</a></li>
							<li><a href="/index.html" target="_self">線上訂位</a></li>
							<li><a href="/index.html" target="_self">餐點介紹</a></li>
							<li><a href="#t1" target="_self">活動訊息</a></li>
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
						id="dropdown01" class="center"> <i
							class="far fa-address-book mx-2"></i> <span class="items">我的帳戶</span>
					</a> <!-- 子連結 -->
						<ul id="sublist01" class="list-unstyled collapse">
							<li><a href="/Users/SelectCustomer.controller#information"
								class="itemDetails">個人資料</a></li>
							<li><a href="/Users/CheckPassword.Controller#position"
								class="itemDetails">變更密碼</a></li>
							<li><a href="/Users/EmailCheckPassword.Controller#position"
								class="itemDetails">變更信箱</a></li>
						</ul></li>
					<li><a href="#sublist02" data-bs-toggle="collapse"
						id="dropdown02" class="center"> <i
							class="fas fa-utensils mx-2"></i> <span class="items">訂單查詢</span>
					</a> <!-- 子連結 -->
						<ul id="sublist02" class="list-unstyled collapse">
							<li><a href="/ordermanage.html" class="itemDetails">訂單管理</a>
							</li>
							<li><a href="/promo.html" class="itemDetails">優惠碼管理</a></li>
							<li><a href="#" class="itemDetails">訂位查詢</a></li>
							<li><a href="#" class="itemDetails">訂位更新</a></li>
						</ul></li>
					<li><a href="#sublist03" data-bs-toggle="collapse"
						id="dropdown03" class="center"> <i
							class="far fa-calendar-check mx-2"></i><span class="items">訂位查詢</span>
					</a> <!-- 子連結 -->
						<ul id="sublist03" class="list-unstyled collapse">
							<li><a href="#" class="itemDetails">公告總覽</a></li>
							<li><a href="/postCreate.html" class="itemDetails">新增公告</a></li>
							<li><a href="#" class="itemDetails">公告更新</a></li>
						</ul></li>
					<li><a href="#sublist04" data-bs-toggle="collapse"
						id="dropdown04" class="center sidebarLight01"> <i
							class="fas fa-cat mx-2"></i> <span class="items">領養紀錄查詢</span>
					</a> <!-- 子連結 -->
						<ul id="sublist04" class="list-unstyled collapse show">
							<li><a href="/pet/petinfo.controller" class="itemDetails">寵物資訊總覽</a>
							</li>
							<li><a href="#" class="itemDetails">文章發佈</a></li>
							<li><a href="#" class="itemDetails">文章更新</a></li>
							<li><a href="#" class="itemDetails">寵物領養資訊</a></li>
							<li><a href="/users/petreserve/getthecusresult"
								class="itemDetails">領養預約總覽</a></li>
							<li><a href="#" class="itemDetails  sidebarLight02">領養記錄查詢</a></li>
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
						<p class="font1 border-bottom pb-4">領養預約紀錄</p>
						<div class="row align-item-center justify-content-center h-75">
							<div class="col-10 rounded-3 shadow p-5 overflow">
								<div class="row justify-content-center text-center">
									<div class="col-4">到訪次數：${arrive}次</div>
									<div class="col-4">尚未赴約：${notyet}筆</div>
									<div class="col-4">失約次數：${miss}次</div>
								</div>
								<hr/>
								<table class="table table-striped table-hover mt-4 text-center"
									id="infoTable">
									<thead>
										<tr>
											<th scope="col">寵物編號</th>
											<th scope="col">寵物名</th>
											<th scope="col">預約日期</th>
											<th scope="col">赴約狀態</th>
											<th scope="col">修改預約</th>
										</tr>
									</thead>
									<tbody class="align-middle">
										<c:forEach var="arrRes" items="${arrRes}">
											<tr>
												<td class="ID d-none">${arrRes.cusId}</td>
												<td class="NAME d-none">${arrRes.cusRealname}</td>
												<td><a href="javascript:void(0)" onclick="checkIfAdopted(this)">${arrRes.petId}</a></td>
												<td>${arrRes.petName}</td>
												<td class="DATE">${arrRes.reserveTime}</td>
												<td>${arrRes.keepStatus}</td>
												<td>
													<button type="button" class="btn btn-danger updateBtn"
														data-bs-toggle="modal" data-bs-target="#reservePet"
														onclick="select(this)">修改</button>
												</td>
											</tr>
										</c:forEach>	
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--預約紀錄Modal-->
	<input type="hidden" id="Dualstatus" value="${status}" />
	<div class="modal fade" id="reservePet" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="petModalTitle">預約看寵資料</h3>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<form action="#" method="POST"
					id="modalForm">
					<div class="row">
						<div class="text-center" id="mainbox">
							<fieldset>
								<legend>寵物資訊</legend>
								<div>
									<label for="petId"><span>*寵物編號</span></label> <input
										type="text" id="petId" name="petId" value="${thePet.petId}"
										oninput="value=value.replace(/[^\d]/g,'')"
										class="requiredValue" readonly />
								</div>
								<div>
									<label for="petName"><span>寵物名</span></label> <input
										type="text" id="petName" name="petName"
										value="${thePet.petName}" readonly />
								</div>
							</fieldset>
							<hr />
							<fieldset>
								<legend>預約資訊</legend>
								<div>
									<label for="cusId"><span>*會員編號</span></label> <input
										type="text" id="cusId" name="cusId" class="requiredValue"
										oninput="value=value.replace(/[^\d]/g,'')" readonly/>
								</div>
								<div>
									<label for="cusRealname"><span>會員姓名</span></label> <input
										type="text" id="cusRealname" name="cusRealname" readonly />
								</div>
								<div>
									<label for="phone"><span>聯絡電話</span></label> <input type="text"
										id="phone" name="phone" pattern="[0]{1}[9]{1}\d{8}"
										oninput="value=value.replace(/[^\d]/g,'')"
										class="requiredValue" />
								</div>
								<div id="datetimepicker" class="mb-4">
									<label for="reserveTime"><span>預約看寵日期</span></label> <input
										type="text" id="reserveTime" name="reserveTime"
										class="requiredValue" />
								</div>
								<div>
									<label for="keepStatus"><span>赴約狀態</span></label> 
									<input
										type="text" id="keepStatus" name="keepStatus"
										class="requiredValue" readonly/>
								</div>
							</fieldset>
							<hr/>
							<div class="row justify-content-start">
									<button type="button" id="sendReserveBtn" class="offset-4 col-2 me-3 btn btn-danger">送出修改</button>
									<button type="button" class="col-2 btn btn-secondary" data-bs-dismiss="modal">取消</button>
									<button type="button" id="missingBtn" class="offset-1 col-2 btn btn-warning text-white"
											 data-bs-toggle="modal" data-bs-target="#delAlert" onclick="delAlert(this)">刪除預約</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- End Of 預約紀錄Modal -->
	<!-- 刪除提示框 Modal-->
	<div class="modal fade text-center" id="delAlert" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">提示信息</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body items-align-center">
					<h4 style="margin-top: 10px; margin-bottom: 50px;" id="alertDialog">確認刪除此筆預約?</h4>
				</div>
				<hr />
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal" onclick="del()">確定</button>
			</div>
		</div>
	</div>
	<!-- 刪除提示框 Modal-->
	<!-- 預約狀態提示框 Modal-->
	<div class="modal fade text-center" id="statusAlert" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">提示信息</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body items-align-center">
					<h4 style="margin-top: 10px; margin-bottom: 50px;" id="statusAlertDialog">當日已有其他預約，無須重複預約</h4>
				</div>
				<hr />
				<button type="button" class="btn btn-danger" data-bs-dismiss="modal">確定</button>
			</div>
		</div>
	</div>
	<!-- End Of 預約狀態提示框 Modal-->

	<div class="copy_right">
		<div>
			<div id="lowbar">
				<div class="container h-100">
					<div class="row h-100 align-items-center lowbar">
						<div class="col-12">
							<ul>
								<li><a href="#t1" target="_self">活動訊息</a></li>
								<li><a href="/index.html" target="_self">餐點介紹</a></li>
								<li><a href="/index.html" target="_self">線上訂位</a></li>
								<li><a href="/petinfo.controller" target="_self">寵物領養</a></li>
								<li>
									<div class="dropdown absolute backstage ${role}">
										<button class="btn btn-link dropdown-toggle text-light"
											type="button" id="cusCenterDropdown2"
											data-bs-toggle="dropdown">${realName}</button>
										<ul class="dropdown-menu p-0" role="button">
											<li id="whp3"><a
												href="/Users/SelectCustomer.controller#information"
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