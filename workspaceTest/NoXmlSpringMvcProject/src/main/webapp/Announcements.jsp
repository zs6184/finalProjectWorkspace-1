<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="tw.finalspring.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>活動資訊管理</title>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- fontawesome icon -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<!--bootstrap & jQuery-ui-->
<link href="stylesheet/bootstrap.min.css" rel="stylesheet" />
<link href="stylesheet/jquery-ui.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css"/>
<!--自訂樣式表-->
<link href="stylesheet/backstage.css" rel="stylesheet" />
<link href="stylesheet/announcements.css" rel="stylesheet" />

<!--<script src="javascript/bootstrap.min.js"></script>-->
<script src="javascript/jquery-3.6.0.min.js"></script>
<script src="javascript/jquery-ui.min.js"></script>
<script src="javascript/jquery.ui.datepicker-zh-TW.min.js"></script>
<!--datepicker-ui中文補丁-->
<!--自訂js-->
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script src="javascript/announcements.js"></script>
<script type="text/javascript">
$(document).ready( function () {
    $('#infoTable').DataTable({
    	
    	lengthChange:true,
		lengthMenu:[5,8,10],
		pageLength:10,
		paging:true,
		searching:true,
		ordering:false,
		language:{
			"lengthMenu":"顯示_MENU_ 項",
			"info":"顯示第 _START_ 至 _END_ 項 , 共 _TOTAL_ 項",
			"paginate":{
				"previous":"上一頁",
				"next":"下一頁"
			}
		}
	 
    });
} );
</script>
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
							<li><a href="#" class="itemDetails sidebarLight02">訂位查詢</a></li>
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
						class="center"> <i class="fas fa-users mx-2"></i>
							<span class="items">會員管理</span>
					</a></li>
					<li class="m-0"><a href="employees.html" class="center"> <i
							class="fas fa-address-card mx-2"></i> <span class="items">員工管理</span>
					</a></li>

				</ul>
			</nav>
		</div>
		<!-- container right -->
		<div class="containerRight">
			
			<!-- 主頁內容 -->
			<div class="container-fluid ">
				<div class="row">
					<div class="col-11 bg-white mt-3 ms-5 box rounded-3 shadow">
						<table class="table table-striped table-hover mt-4" id="infoTable">
							<thead>
								<tr>
									<th scope="col">文章編號</th>
									<th scope="col">員工ID</th>
									<th scope="col">標題</th>
									<th scope="col">發文時間</th>
									<th scope="col">圖片</th>
									<th scope="col">文章內容</th>
									<th scope="col">
										<button type="button" class="btn btn-primary btn"
											data-bs-toggle="modal" data-bs-target="#announcementsAdd" id="insertBtn">新增</button>
									</th>
								</tr>
							</thead>
							<tbody class="align-middle">
								<c:forEach var="arrAnnounce" items="${arrAnnounce}">
									<tr>
										<td class="ID">${arrAnnounce.announceID}</td>
										<td>${arrAnnounce.empID}</td>
										<td>${arrAnnounce.headline}</td>
										<td>${arrAnnounce.releaseTime}</td>
										<td>${arrAnnounce.picture}</td>
										<td>${arrAnnounce.articleCont}</td>
										<td>
											<button type="button" class="btn btn-danger updateBtn"
												data-bs-toggle="modal" data-bs-target="#announcementsAdd"
												onclick="select(this)">更新</button>
											<button type="button" class="btn btn-danger delete"
												onclick="del(this)">刪除</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>

			<!--訂位資料更新Modal-->
		<div class="modal fade" id="announcementsAdd" tabindex="-1">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title" id="announcementsModalTitle">文章資料</h3>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>
						<form class="add" action="insertAnnouncements.controller" method="POST"
							id="modalForm">
							<div class="modal-body">
								<div class="row">
									<div class="text-center" id="mainbox">
										<fieldset>
											<legend>文章資料</legend>
											<div id="idSection">
												<label for="announceID" class=""><span>文章編號</span></label> <input
													type="text" id="announceID" name="announceID" disabled />
											</div>
											<div>
												<label for="empId" class=""><span>員工ID</span></label> <input
													type="text" id="empId" name="empId"
													oninput="value=value.replace(/[^\d]/g,'')" />
											</div>
											<div>
												<label for="headline" class=""><span>標題</span></label> <input
													type="text" id="headline" name="headline" />
											</div>
											<div>
												<label for="releaseTime" class=""><span>發文時間</span></label> <input
													type="text" id="releaseTime" name="releaseTime" />
											</div>
											<hr />
											<div>
												<label for="picture" class=""><span>圖片</span></label> <input
													type="text" id="picture" name="picture" />
											</div>
											
									
										<hr />
										<fieldset>
											<legend>文章內容</legend>
											<textarea id="articleCont" name="articleCont"></textarea>
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
			

	</div>
</body>
</html>