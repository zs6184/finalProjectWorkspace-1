<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="tw.springbootfinal.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link href="/stylesheet/bootstrap.min.css" rel="stylesheet" />
<link href="/stylesheet/jquery-ui.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" />
<!--自訂樣式表-->
<link href="/stylesheet/backstage.css" rel="stylesheet" />
<link href="/stylesheet/announcements.css" rel="stylesheet" />

<!--<script src="javascript/bootstrap.min.js"></script>-->
<script src="/javascript/jquery-3.6.0.min.js"></script>
<script src="/javascript/jquery-ui.min.js"></script>
 <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
 <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<!--datepicker-ui中文補丁-->
<!--自訂js-->
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script src="/javascript/announcements.js"></script>
<script src="/javascript/backstage.js"></script>
<link rel="icon" type="image/png"  href="/font/favicon1.png">
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
    
    
    $('#articleCont').summernote({
    	height: 300,        
    	 toolbar: [
    		    // [groupName, [list of button]]
    		    ['style', ['bold', 'italic', 'underline', 'clear']],
    		    ['font', ['strikethrough', 'superscript', 'subscript']],
    		    ['fontsize', ['fontsize']],
    		    ['color', ['color']],
    		    ['para', ['ul', 'ol', 'paragraph']],
    		    ['height', ['height']],
    		    ['insert', ['link', 'picture', 'video']],
    		    ['view', ['fullscreen', 'codeview', 'help']]
    		  ]
    });
    
    $('#articleCont').summernote({
    	  fontNames: ['AA', 'Arial Black', 'Comic Sans MS', 'Courier New']
    	});
    $('#articleCont').summernote('fontSize', 25);

    
    $('#articleCont').summernote({
    height: 600
    });
    
    $('#articleCont').summernote('fontSize', 25);

} );

function t(){
	var now= new Date();
	var y = now.getFullYear();
	var M =(now.getMonth()+1);
	var d = now.getDate();
	var h=now.getHours();
	var m=now.getMinutes();
	var s=now.getSeconds(); 
	var tt=y+"-"+M+"-"+d+" "+h+":"+m+":"+s;
	document.getElementById("releaseTime").value=tt;
	}
	setInterval('t()',500);
</script>
<style>
	img[src=""],img:not([src]){
	display:none;
	}
	
	@font-face {
    font-family: AA;
    src: url(../resource/font/HanyiSentyBubbleTea.ttf);
}


	iframe{
		display:block;
		border:none;
		height:30%;
		width:30%;
	}
</style>
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
							<li><a href="#" class="/backstage/product/findAll">餐點總覽</a></li>
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
						id="dropdown03" class="center  sidebarLight01"> <i
							class="fas fa-bullhorn mx-2 "></i> <span class="items ">公告管理</span>
					</a> <!-- 子連結 -->
						<ul id="sublist03" class="list-unstyled collapse">
							<li><a href="/backstage/announcements/backannouncements.controller" class="itemDetails  sidebarLight02">公告總覽</a></li>
							<li><a href="/backstage/announcements/backannouncements.controller" class="itemDetails">新增公告</a></li>
							<li><a href="/backstage/announcements/backannouncements.controller" class="itemDetails">公告更新</a></li>
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
<!-- 									<th scope="col">員工ID</th> -->
									<th scope="col">標題</th>		
									<th scope="col">發文時間</th>
									
									<th scope="col">
										<button type="button" class="btn btn-primary btn"
											data-bs-toggle="modal" data-bs-target="#announcementsAdd" id="insertBtn">新增</button>
									</th>
								</tr>
							</thead>
							<tbody class="align-middle">
							<c:set var="startIndex" value="${fn:length(arrAnnounce)-1}"></c:set>
								<c:forEach var="arrAnnounc" items="${arrAnnounce}"  varStatus="status">
									<tr>
										<td class="ID">${arrAnnounce[startIndex-status.index].announceID}</td>
<%-- 										<td>${arrAnnounce[startIndex-status.index].empID}</td> --%>
										<td>${arrAnnounce[startIndex-status.index].headline}</td>
										<td>${arrAnnounce[startIndex-status.index].releaseTime}</td>
										
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
						<form class="add" action="/backstage/announcements/insertAnnouncements.controller" method="POST" 
							id="modalForm" enctype="multipart/form-data">
							<div class="modal-body">
								<div class="row">
									<div class="text-center" id="mainbox">
										<fieldset>
											<legend>文章資料</legend>
											<div id="idSection">
												<label for="announceID" class=""><span>文章編號</span></label> <input
													type="text" id="announceID" name="announceID"  readonly />
											</div>
<!-- 											<div> -->
<!-- 												<label for="empId" class=""><span>員工ID</span></label> <input -->
<!-- 													type="text" id="empId" name="empId" -->
<!-- 													oninput="value=value.replace(/[^\d]/g,'')" /> -->
<!-- 											</div> -->
											<div>
												<label for="headline" class=""><span>標題</span></label> <input
													type="text" id="headline" name="headline" required/>
											</div>
											<div>
												<label for="releaseTime" class=""><span>發文時間</span></label> <input
													type="text" id="releaseTime" name="releaseTime" />
											</div>
											<div class="row">
											<div class="col-6 justify-content-center mt-6">
												<label for="mypic" class="btn btn-outline-info text-center">選擇圖片</label>
												<input type="file" name="mypic" id="mypic" accept="image/*" style="display:none"/><br>
											</div>
											<div class="col-6 justify-content-center">
												<div id="imgPreview" class="border" style="width:200px; height:200px;">
													<img src="" style="width:100%;height:100%;" alt="請上傳圖片" ; />
												</div>
											</div>
											
											

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