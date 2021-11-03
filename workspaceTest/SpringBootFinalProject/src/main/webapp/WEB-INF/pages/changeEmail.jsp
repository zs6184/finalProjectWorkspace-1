<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員中心</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/stylesheet/index.css" />
<!--CSS在這邊 要注意放在bootstrap樣式表CDN後面 不然權重相同的部分會被bootstrap蓋過去-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0-alpha.1/jquery-ui.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
	integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<!--javaScript掛到這邊-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
	integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="/javascript/index.js"></script>

<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

<!--自訂樣式表-->
<link href="/stylesheet/customerCenter.css" rel="stylesheet" />
<!--自訂js-->
<script src="/javascript/customerCenter.js"></script>
<link rel="icon" type="image/png"  href="/font/favicon1.png">
</head>
<body>
	<div class="offset-3 col-6 bg-white my-5 box2 rounded-3 shadow p-5"
		id="datasource">
		<p class="font1 border-bottom pb-5" id="position">變更電子信箱</p>
		<!-- left -->
		<form class="mt-5" id="changeEmailForm"
			action="/Users/ChangeEmailSendMail" method="post"
			enctype="multipart/form-data">
			<div>
				<div class="d-flex justify-content-center" id="account">
					<div class="mb-3">
						<label for="email" class="col-form-label mb-2">請輸入新的電子信箱地址:</label>
						<div class="relative">
							<input id="email" type=text name="email"
								maxlength="35" size="30" class="form-control"  placeholder="Email" autocomplete="off">
							<div class="invalid-tooltip" id="emailInvalid"></div>
						</div>
					</div>
				</div>
				<div class="text-center mt-3 mb-5">
					<button type="submit" class="btn btn-success" id="submit">驗證</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>