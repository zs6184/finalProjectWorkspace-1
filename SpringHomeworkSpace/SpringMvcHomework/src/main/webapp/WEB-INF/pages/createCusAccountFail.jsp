<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊</title>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- fontawesome icon -->
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<link href="stylesheet/bootstrap.min.css" rel="stylesheet" />
<link href="stylesheet/jquery-ui.min.css" rel="stylesheet" />
<script src="javascript/jquery-3.6.0.min.js"></script>
<!--自訂樣式表-->
<link href="stylesheet/createCusAccount.css" rel="stylesheet" />
<!--自訂js-->
<script src="javascript/createCusAccount.js"></script>
</head>
<body>
	<hr>
	<!-- 標題logo 部分 -->
	<div class="logo-area text-center">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<a href="index.html"><img src="image/浪跡.png" alt=""></a>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<div class="container-fluid">
		<div class="row">
			<div class="offset-3 col-6 border rounded-3 shadow my-5 bg-white">
				<div class="row">
					<div class="mt-4 ms-4 col">
						<h3>加入會員</h3>
					</div>
				</div>
				<form class="mt-5" id="createCusForm"
					action="CreateCusAccount.controller" method="post">
					<div class=" row" id="account">
						<div class="row my-2 offset-2 col-8">

							<label for="username" class="col-3 col-form-label">帳號:</label>
							<div class="col-9">
								<input id="username" type="text" name="cusUsername"
									class="form-control" placeholder="Username" autocomplete="off"
									autofocus>
							</div>
						</div>
						<div class="row my-2 offset-2 col-8">
							<label for="password" class="col-3 col-form-label">密碼:</label>
							<div class="col-9">
								<input id="password" type="password" name="cusPassword"
									class="form-control" placeholder="Password" autocomplete="off">
							</div>
						</div>
						<div class="row my-2 offset-2 col-8">
							<label for="name" class="col-3 col-form-label">姓名:</label>
							<div class="col-9">
								<input id="name" type="text" name="cusRealname"
									class="form-control" placeholder="Name" autocomplete="off">
							</div>
						</div>
					</div>
					<div style="color: red; text-align: center;">帳號已被使用</div>
					<div class="text-center mt-3 mb-5">
						<button type="submit" class="btn btn-primary btn-lg">註冊</button>
					</div>
					<div class="text-center mb-4">
						<span>已有帳號?</span> <a href="login.Controller">登入</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>