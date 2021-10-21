<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員資料修改</title>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- fontawesome icon -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<link href="/stylesheet/bootstrap.min.css" rel="stylesheet" />
<link href="/stylesheet/jquery-ui.min.css" rel="stylesheet" />
<script src="/javascript/jquery-3.6.0.min.js"></script>
<!--自訂樣式表-->
<link href="/stylesheet/login.css" rel="stylesheet" />
</head>
<body>
<script>
	$(function(){
		console.log("JS READY")
		
		$("#deleteBtn").click(function(){
			$("#cusDataForm").attr("action","/users/deleteData.controller");
			alert("成功刪除");
		});
		
		$("#updateBtn").click(function(){
			$("#cusDataForm").attr("action","/users/updateData.controller");
			console.log("修改完成");
		});
	});
</script>
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-5 border rounded-3 shadow mt-5 bg-white">
				<!-- 標題logo 部分 -->
				<div class="text-center">
					<div class="container">
						<div class="row align-items-center">
							<div>
								<a href="/index.html"><img src="/image/浪跡.png" alt=""></a>
							</div>
							<div style="margin-top: 30px; margin-bottom: 0px;">
								<h4>會員資料修改</h4>
							</div>
						</div>
					</div>
				</div>
				<hr class="mx-5">
				<form class="mt-5" id="cusDataForm"
					action="/users/deleteData.controller" method="POST">
					<div class="row" id="account">
						<!-- ID -->
						<div class="row offset-2 col-8">
							<div class="input-group mb-3">
								<span class="input-group-text col-3" id="basic-addon2"> <i
									class="fa fa-id-badge" aria-hidden="true"></i>會員ID
								</span> <input type="text" id="id" name="id" class="form-control"
									value="${theCus.id}" autocomplete="off" readonly>
							</div>
						</div>
						<!-- 姓名 -->
						<div class="row offset-2 col-8">
							<div class="input-group mb-3">
								<span class="input-group-text col-3" id="basic-addon2"> <i
									class="fa fa-tag" aria-hidden="true"></i>會員姓名
								</span> <input type="text" id="realname" name="realname"
									class="form-control" value="${theCus.realname}"
									autocomplete="off">
							</div>
						</div>
						<!-- 帳號 -->
						<div class="row offset-2 col-8">
							<div class="input-group mb-3">
								<span class="input-group-text col-3" id="basic-addon1"> <i
									class="fas fa-user"></i>會員帳號
								</span> <input type="text" id="username" name="username"
									class="form-control" value="${theCus.username}"
									autocomplete="off">
							</div>
						</div>
						<!--密碼-->
						<div class="row offset-2 col-8">
							<div class="input-group mb-3">
								<span class="input-group-text col-3" id="basic-addon2"> <i
									class="fas fa-lock"></i>會員密碼
								</span> <input type="text" id="password" name="password"
									class="form-control" value="${theCus.password}"
									autocomplete="off">
							</div>
						</div>
					</div>
					<div style="color: red; text-align: center;">${msg}</div>
					<div class="row justify-content-center text-center">
						<div class="text-center mt-3 mb-5 col-2" id="updateBtn">
							<button type="submit" class="btn btn-primary btn-lg">修改</button>
						</div>
						<div class="text-center mt-3 mb-5 col-2" id="deleteBtn">
							<button type="submit" class="btn btn-danger btn-lg">刪除</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>