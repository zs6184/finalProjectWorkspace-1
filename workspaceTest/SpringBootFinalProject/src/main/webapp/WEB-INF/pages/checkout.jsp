<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>結帳</title>
</head>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>index</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/stylesheet/index.css" />
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.css">
 <link rel="stylesheet" href="/stylesheet/checkout.css" />
<!--   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/themes/material_green.css"> -->
<!--CSS在這邊 要注意放在bootstrap樣式表CDN後面 不然權重相同的部分會被bootstrap蓋過去-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0-alpha.1/jquery-ui.min.js"></script>
<script src="https://accounts.google.com/gsi/client" async defer></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
	integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link href="/stylesheet/customerCenter.css" rel="stylesheet" />
<!--javaScript掛到這邊-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
	integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="/javascript/index.js"></script>
<script src="/javascript/customerCenter.js"></script>
<link rel="icon" type="image/png"  href="/font/favicon1.png">

<script src="/javascript/checkout.js"></script>
<!--JS Here-->

<!--icon-->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />

 <script src="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.js"></script>
</head>

<body id="top" style="background-image: url(/image/背景4.jpg)";>

	<div style="background-color: rgb(6, 121, 121, .1);">
		<br>
	</div>
	<!-- 標題logo 部分 -->
	<div class="logo-area text-center">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<a href="/Users/loginIndex.Controller"><img
						src="/image/浪跡2.png" alt=""></a>
				</div>
			</div>
		</div>
	</div>
	<hr>

	<!-- nav欄部分 -->
	<div>
		<div id="topbar">
			<div class="container h-100">
				<div class="row h-100 align-items-center topbar">
					<div class="col-12">

						<ul>
							<li>
								<div class="dropdown absolute backstage ${role}">
									<button class="btn btn-link dropdown-toggle " type="button"
										id="cusCenterDropdown" data-bs-toggle="dropdown">${realName}</button>
									<ul class="dropdown-menu p-0" role="button">
										<li id="whp1"><a href="/Users/SelectCustomer.controller#information"
											style="font-size: 1.1em"
											class="dropdown-item d-flex justify-content-center"
											target="_self">會員中心</a>
										</li>
										<li id="whp2" class="m-0">
											<form action="/Users/logout.Controller" method="post">
												<button type="submit"
													class="dropdown-item d-flex justify-content-center"
													value="logout">登出</button>
											</form>
										</li>
									</ul>
								</div>
							</li>
							<li><a href="/pet/petinfo.controller" target="_self">寵物領養</a></li>
							<li><a href="#Reservation" role="button" data-bs-toggle="modal" target="_self">線上訂位</a></li>
							<li><a href="/product/findallproduct" target="_self">餐點介紹</a></li>
							<li><a href="#t1" target="_self">活動訊息</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

	</div>
	 <!--橫幅圖片區域-->
    <div class="banner-area bg-img" style="background-image:url(/image/img/bg-img/cat1.jpeg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="banner-content text-center">
                        <h2>結帳</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<div class="container" id="container">
        <!--餐點內容-->
        <div class="Meals row justify-content-around">
            <div class="col-sm-8 widget">
                <h4 class="mb-3">餐點內容:</h4>
                <table class="table-hover" style="width:100%">
                    <thead>
                        <tr>
                            <th>商品名稱</th>
                            <th>數量</th>
                            <th>單價</th>
                            <th>小計</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="catitem" items="${catitem}">
							<tr id="${catitem.id}">
								<td class="productname">${catitem.name}</td>
								<td>${catitem.num}</td>
								<td class="price">${catitem.price}</td>
								<td class="subtotal">0</td>
							</tr>

						</c:forEach>
                    </tbody>
                    <tfoot>
                        <tr class="discount">

                        </tr>
                    </tfoot>
                </table>
                <hr />
                <form id="promo" class="needs-validation novalidate p-2" style=" width: 200px;">
                    <div class="input-group">
                        <input id="promoinput" type="text" class="form-control" placeholder="折扣碼">
                        <button  type="submit" class="btn">確認</button>
                    </div>
                </form>
                <div class="promocode">
                    <spanc class="promocode_text">合計:</spanc><span class="orangeprice">$100</span><span class="endprice">$100</span>
                </div>
            </div>
        </div>
        <!--餐點內容結束-->
        <!--備註-->
        <form class="needs-validation novalidate" action="/order/saveorder" enctype="multipart/form-data" method="POST">
            <div class="note row justify-content-around">
                <div class="col-sm-8 widget">
                    <h4 class="mb-3">備註:</h4>
                    <textarea name="note" rows="4" cols="40"></textarea>
                </div>
            </div>
            <!--備註結束-->
            <!--訂購資料-->
            <div class="orderinfo row justify-content-around">
                <div class="col-sm-8 widget">
                    <h4 class="mb-3">訂購資料:</h4>
                    <div class="col-sm-5">
                        <label for="name" class="form-label">姓名:</label>
                        <input type="text" class="form-control" id="name" placeholder="" value="${user.cusRealname}" required>
                        <div class="invalid-feedback">
                            *必填
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <label for="mail" class="form-label">信箱:</label>
                        <input type="email" class="form-control" id="mail" placeholder="" value="${user.email}" name="mail" required>
                        <div class="invalid-feedback">
                            *必填
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <label for="phone" class="form-label">手機:</label>
                        <input type="tel" class="form-control" id="phone" placeholder="" value="${user.phoneNumber}" name="phone" required>
                        <div class="invalid-feedback">
                            *必填
                        </div>
                    </div>
                </div>
            </div>
            <!--訂購資料結束-->
            <!--取餐時間-->
            <div class="note row justify-content-around">
                <div class="col-sm-8 widget">
                    <h4 class="mb-3">取餐時間:</h4>
                    <input id="datePicker" type="datetime-local"step="600" name="pickuptime1" path="starDate"pattern="MM-DD-YYYY HH:mm"/>
                    
                </div>
            </div>
            <!--取餐時間結束-->
            <!--付款方式-->
            <div class="note row justify-content-around">
                <div class="col-sm-8 widget">
                    <h4 class="mb-3">付款方式:</h4>
                    <div class="card">
                        <div class=" form-check">
                            <input id="credit" name="paymethod" type="radio" class="form-check-input" checked required value="取餐付款">
                            <label class="form-check-label" for="credit">
                                取餐付款
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-currency-exchange" viewBox="0 0 16 16">
                                    <path d="M0 5a5.002 5.002 0 0 0 4.027 4.905 6.46 6.46 0 0 1 .544-2.073C3.695 7.536 3.132 6.864 3 5.91h-.5v-.426h.466V5.05c0-.046 0-.093.004-.135H2.5v-.427h.511C3.236 3.24 4.213 2.5 5.681 2.5c.316 0 .59.031.819.085v.733a3.46 3.46 0 0 0-.815-.082c-.919 0-1.538.466-1.734 1.252h1.917v.427h-1.98c-.003.046-.003.097-.003.147v.422h1.983v.427H3.93c.118.602.468 1.03 1.005 1.229a6.5 6.5 0 0 1 4.97-3.113A5.002 5.002 0 0 0 0 5zm16 5.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0zm-7.75 1.322c.069.835.746 1.485 1.964 1.562V14h.54v-.62c1.259-.086 1.996-.74 1.996-1.69 0-.865-.563-1.31-1.57-1.54l-.426-.1V8.374c.54.06.884.347.966.745h.948c-.07-.804-.779-1.433-1.914-1.502V7h-.54v.629c-1.076.103-1.808.732-1.808 1.622 0 .787.544 1.288 1.45 1.493l.358.085v1.78c-.554-.08-.92-.376-1.003-.787H8.25zm1.96-1.895c-.532-.12-.82-.364-.82-.732 0-.41.311-.719.824-.809v1.54h-.005zm.622 1.044c.645.145.943.38.943.796 0 .474-.37.8-1.02.86v-1.674l.077.018z" />
                                </svg>
                            </label>
                        </div>
                    </div>
                    <div class="card">
                        <div class=" form-check">
                            <input id="debit" name="paymethod" type="radio" class="form-check-input" required value="綠界付款">
                            <label class="form-check-label" for="debit">
                                綠界
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-credit-card-2-back" viewBox="0 0 16 16">
                                    <path d="M11 5.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1z" />
                                    <path d="M2 2a2 2 0 0 0-2 2v8a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V4a2 2 0 0 0-2-2H2zm13 2v5H1V4a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1zm-1 9H2a1 1 0 0 1-1-1v-1h14v1a1 1 0 0 1-1 1z" />
                                </svg>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <!--付款方式結束-->
            <!--優惠碼id-->
            <input id="couponsId" name="couponsId" type="hidden"/>
            <!--優惠碼id結束-->
            <!--送出-->
            <div class="note row justify-content-around">
                <div class="col-sm-8 widget">
                    <button class="w-100 btn btn-lg" type="submit">送出訂單</button>
                </div>
            </div>
            <!--送出結束-->
        </form>
    </div>
	

</body>
</html>