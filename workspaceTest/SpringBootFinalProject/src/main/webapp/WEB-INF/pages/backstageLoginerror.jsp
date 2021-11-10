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
<!-- reCAPTCHA -->
<!-- <script src="https://www.google.com/recaptcha/api.js"></script> -->
<script
	src="https://www.google.com/recaptcha/api.js?render=6Lcnq94cAAAAAJNYUP_oO0M2EDZ53BrQguAAKfqq"></script>
<!--自訂樣式表-->
<link href="/stylesheet/login.css" rel="stylesheet" />
<!--自訂js-->
<script src="/javascript/login.js"></script>
<link rel="icon" type="image/png" href="/font/favicon1.png">
</head>
<body class="body2">
	<div class="container-fluid">
		<div class="row">

			<!-- 			<div class="offset-6 col-5 border rounded-3 shadow mt-5 bg-white"> -->
			<ul style="padding-left: 40%;">
				<li class="box1"></li>
				<li class="box2">
					<!-- 								框框出現處 on/off -->
					<div class="btn btn-lg me-5 col-2" style="float: left;">
						<!-- 								框框出現處 on/off -->
						<div class="onoffswitch">
							<input type="checkbox" name="onoffswitch"
								class="onoffswitch-checkbox member" id="myonoffswitch"
								tabindex="0" checked> <label class="onoffswitch-label"
								for="myonoffswitch"> <span class="onoffswitch-inner"></span>
								<span class="onoffswitch-switch"></span>
							</label>
						</div>
					</div> <!-- 標題logo 部分 -->
					<div class="text-center mb-1">
						<div class="container">
							<div class="row align-items-center">
								<div id="logo">
									<a href="/index.html"><img src="/image/浪跡.png" alt=""></a>
								</div>
							</div>
						</div>
					</div> <!-- 第三方登入 -->
					<div class="container buttonHidden" id="googleButton">
						<div class="row justify-content-center">
							<button class="btn btn-danger btn-lg col-10">GOOGLE登入</button>
						</div>
					</div>
					<hr class="mx-5">
					<form class="mt-5" id="loginForm"
						action="/BK/backstageLogin.Controller" method="post">
						<div class="row" id="account">
							<!-- 帳號 -->
							<div class="offset-2 col-8">
								<div class="input-group mb-3 relative">
									<span class="input-group-text" id="basic-addon1"> <i
										class="fas fa-user"></i>
									</span> <input type="text" id="username" name="username"
										class="form-control" maxlength="20" placeholder="Username"
										autocomplete="off">
								</div>
							</div>
							<!--密碼-->
							<div class="offset-2 col-8">
								<div class="input-group mb-3 relative">
									<span class="input-group-text" id="basic-addon2"> <i
										class="fas fa-lock"></i>
									</span> <input type="password" id="password" name="password"
										class="form-control" maxlength="20" placeholder="Password"
										autocomplete="off">
								</div>
							</div>
							<span id="loginStatus"
								class="offset-4 col-4 d-flex justify-content-center">帳號或密碼錯誤</span>
						</div>
						<div class="row">
							<div class="text-center LoginErrMsg"></div>
							<!-- checkBox -->
							<div class="offset-2 col-8">
								<div class="form-check">
									<input class="form-check-input" type="checkbox"
										id="remember-me" name="remember-me"> <label
										class="form-check-label" for="autoLogin"> 記住我 </label>
								</div>
							</div>
							<!-- 註冊及忘記密碼 -->
							<div class="text-center mt-3 mb-5" id="status1">
								<button type="submit" class="btn btn-primary btn-lg" id="submit"
									value="login">登入</button>
							</div>

							<div class="text-center mb-4">
								<a href="/createCusAccount.html">註冊</a> <a
									href="/forget.Controller" class="ms-5">忘記您的密碼?</a>
							</div>
						</div>
					</form>
				</li>
			</ul>
		</div>

		<!-- 		</div> -->
	</div>
</body>
</html>