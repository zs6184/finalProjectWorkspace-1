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
<link rel="icon" type="image/png"  href="/font/favicon1.png">
</head>
<body class="body1">
	<div class="container-fluid">
		<div class="row">
			<div class="offset-6 col-5 border rounded-3 shadow mt-5 box3">
				<!-- 標題logo 部分 -->
				<div class="text-start">
					<div class="container">
						<div class="row align-items-center">
							<div>
								<p class="my-5 border-bottom pb-5" style="font-size: 23px"
									id="position">重設密碼</p>
							</div>
						</div>
					</div>
				</div>
				<form class="" id="ForgotPasswordForm" action="/ForgotPasswordSendMail"
					method="post">
					<div class="row" id="account">
						<div class="offset-3 col-6">
							<label for="email" class="col-form-label mb-2">請輸入註冊時的電子信箱地址進行驗證:</label>
							<div class="input-group mb-3 relative">
								<span class="input-group-text" id="basic-addon2"><i
									class="fas fa-envelope"></i> </span> <input type="email" id="email"
									name="email" class="form-control" maxlength="35"
									placeholder="Email" autocomplete="off">
								<div class="invalid-tooltip" id="emailInvalid"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="text-center LoginErrMsg"></div>
						<!-- 註冊及忘記密碼  -->
						<div class="text-center mt-3 mb-5" id="status1">
							<button class="btn btn-primary btn-lg mt-3" id="submit"
								type="submit">驗證</button>
						</div>
						<div class="text-center mb-4">
							<a href="/createCusAccount.html">註冊</a> <a
								href="/login.Controller" class="ms-5">登入</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>