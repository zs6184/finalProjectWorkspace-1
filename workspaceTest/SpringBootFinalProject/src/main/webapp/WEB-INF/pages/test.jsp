<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>無訪問權限</title>
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

</head>
<body>
	<div class="container-fluid mt-5">
		<div class="row">
			<div class="offset-4 col-4 border rounded-3 shadow mt-5 bg-white">
				<!-- 標題logo 部分 -->
				<div class="text-center">
					<div class="container">
						<div class="row align-items-center border-bottom mb-5">
							<div>
								<a href="/Users/loginIndex.Controller"><img src="/image/浪跡.png" alt=""></a>
							</div>
						</div>
					</div>
				</div>
				<form class="" id="loginForm" action="/ForgotPasswordSendMail"
					method="post">
					<div class="row" id="account">
						<div class="offset-4 mb-5">
							<div>
								<label class="">您無此頁面訪問權限。</label>
								<p>將自動跳轉回首頁<span id="time"></span></p>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>