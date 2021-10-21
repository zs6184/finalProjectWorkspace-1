<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員登入</title>
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
<!--自訂js-->
<script src="/javascript/login.js"></script>

</head>
<body>
	<div class="container-fluid">
		<div class="row">

			<div class="offset-6 col-5 border rounded-3 shadow mt-5 bg-white">
				<!-- 標題logo 部分 -->
				<div class="text-center">
					<div class="container">
						<div class="row align-items-center">
							<div>
								<a href="/index.html"><img src="/image/浪跡.png" alt=""></a>
							</div>
						</div>
					</div>
				</div>
				<hr class="mx-5">
				<form class="mt-5" id="loginForm"
					action="/users/checkloginaccount.controller" method="post">
					<div class="row" id="account">
						<!-- 帳號 -->
						<div class="offset-2 col-8">
							<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon1"> <i
									class="fas fa-user"></i>
								</span> <input type="text" id="username" name="username"
									class="form-control" placeholder="Username" autocomplete="off"  autofocus>
							</div>
						</div>
							<div class="col-1" style="color:red"></div>
						<!--密碼-->
						<div class="offset-2 col-8">
							<div class="input-group mb-3">
								<span class="input-group-text" id="basic-addon2"> <i
									class="fas fa-lock"></i>
								</span> <input type="password" id="password" name="password"
									class="form-control" placeholder="Password" autocomplete="off">
							</div>
						</div>
					</div>
					<div style="color:red;text-align:center;">帳號或密碼錯誤</div>
					<div class="row">
						<!-- 註冊及忘記密碼 -->
						<div class="text-center mt-3 mb-5" id="status1">
							<button type="submit" class="btn btn-primary btn-lg">登入</button>
						</div>

						<div class="text-center mb-4">
							<a href="/createCusAccount.html">註冊</a>
						</div>
					</div>
				</form>

			</div>

		</div>
	</div>
</body>
</html>