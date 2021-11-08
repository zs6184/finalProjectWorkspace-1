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
	<div class="container-fluid mt-5">
		<div class="row">
			<div class="offset-4 col-4 border rounded-3 shadow mt-5 box3">
				<!-- 標題logo 部分 -->
				<div class="text-center">
					<div class="container">
						<div class="row align-items-center border-bottom mb-5">
							<div>
								<a href="/index.html"><img src="/image/浪跡.png" alt=""></a>
							</div>
						</div>
					</div>
				</div>
				<form class="" id="loginForm" action="/ForgotPasswordSendMail"
					method="post">
					<div class="row" id="account">
						<div style="text-align: center;">
							<label for="email" class="col-form-label mb-5">信件已寄出，請至信箱完成驗證。</label>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>