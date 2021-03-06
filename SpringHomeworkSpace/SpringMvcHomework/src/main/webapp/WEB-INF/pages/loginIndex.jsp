<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>浪跡</title>
</head>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>index</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="stylesheet/index.css" />
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
<script src="javascript/index.js"></script>



</head>

<body id="top" style="background-image: url(image/背景4.jpg)";>
	<div style="background-color: rgb(6, 121, 121, .1);">
		<br>
	</div>
	<!-- 標題logo 部分 -->
	<div class="logo-area text-center">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<a href="index.html"><img src="image/浪跡2.png" alt=""></a>
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
								<a href="personnelData.controller" target="_self">${realName}</a>
								<a href="logoutIndex.Controller" target="_self">登出</a>
							</li>
							<li><a href="petinfo.controller" target="_self">寵物領養</a></li>
							<li><a href="index.html" target="_self">線上訂位</a></li>
							<li><a href="index.html" target="_self">餐點介紹</a></li>
							<li><a href="#t1" target="_self">活動訊息</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

	</div>


	<!--頭部輪播  -->
	<div id="owl-one" class="owl-carousel owl-theme">
		<div class="item">
			<img src="image/1.jpg" alt="" />
		</div>
		<div class="item">
			<img src="image/4.jpg" alt="" />
		</div>
		<div class="item">
			<img style="margin-right: 0;" src="image/3.jpg" alt="" />
		</div>


		<!-- 說明訂位,訂餐圖 -->
	</div>
	<div class="container  justify-content: center align-content: center"
		style="display: flex;">
		<ul class="papper">
			<li>
				<p class="p1"></p>
				<h3>Pet Restaurant</h3>
				<h2>浪跡寵物餐廳</h2>
				<p style="font-size: 16px;">
					與毛小孩體驗最快樂、最衛生、最安全的接觸，搭配著餐廳所提供的美好餐點,感受人生的美好時光。</p>
			</li>
			<li>
				<div class="backpic">
					<div style="height: 460px; width: 332px;"
						class="row justify-content-center align-items-center">
						<div class="btn">
							<button type="button" class="btn btn-dark" id="b1">我要訂餐</button>
							<!--訂餐訂位按鈕我加了一個hover效果在CSS XDD-->
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="backpic2">
					<div style="height: 460px; width: 332px;">
						<div style="height: 460px; width: 332px;"
							class="row justify-content-center align-items-center">
							<div class="btn">
								<button type="button" class="btn btn-dark" id="b2"
									data-bs-toggle="modal" data-bs-target="#Reservation">我要訂位</button>
							</div>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
	<hr id="t1">
	<div class="modal fade" id="Reservation" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content"
				style="background-image: url(image/背景1.jpg); background-size: cover; background-position: 100px 60px;">
				<div class="modal-header">
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<form action="#" method="get">
					<div class="modal-body" style="font-family: BB; font-size: 25px;";>
						<div class="row">
							<div class="text-center" id="mainbox">
								<img src="image/浪跡.png" style="width: 25%;">
								<hr>

								<BR> <label for="Name" class=""><span>顧客姓名&nbsp&nbsp&nbsp</span></label>
								<input type="text" id="Name" name="Name" required
									style="border: 2px solid #ccc" /> <BR> <BR> <label
									for="Phone" class="">電話號碼&nbsp&nbsp&nbsp</span></label> <input
									type="text" id="Phone" name="Phone" required
									style="border: 2px solid #ccc" /> <BR> <BR> <span>訂位人數&nbsp&nbsp&nbsp</span>
								<select name="peopleNum"
									style="width: 280px; height: 40px; border: 2px solid #ccc"
									required>
									<option value="" selected>選擇人數</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
								</select> <BR> <BR> <label for="orderdate" class="form-label"
									style="margin-right: 13px;">訂位日期</label> <input
									style="width: 280px; height: 40px; display: inline-block; margin-left: 25px; border: 2px solid #ccc"
									id="orderdate" name="orderdate" type="date"
									class="form-control" required />

								<div style="overflow: hidden; height: 40px;"></div>

								<span>訂位人數&nbsp&nbsp&nbsp</span> <select name="time"
									style="width: 280px; height: 40px; border: 2px solid #ccc"
									required>
									<option value="" selected>選擇時間</option>
									<option value="1">11:00</option>
									<option value="2">12:00</option>
									<option value="3">13:00</option>
									<option value="4">14:00</option>
									<option value="5">15:00</option>
									<option value="6">16:00</option>
									<option value="7">17:00</option>
									<option value="8">18:00</option>
									<option value="9">19:00</option>
									<option value="10">20:00</option>
								</select> <BR> <BR>
								<div class="modal-footer">
									<div class="container-fluid">
										<div class="row justify-content-start">
											<div class="offset-4 col-4">
												<button type="submit" class="btn btn-primary">送出</button>
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
	<br>
	<div style="text-align: center; font-family: BB;" class="clearfix "
		id="pageChange">
		<span><button onclick="getData()">首頁</button></span> <span><button
				onclick="getData()">&nbsp&nbsp1</button></span> <span><button
				onclick="getData2()">&nbsp; 2&nbsp;</button></span> <span><button
				onclick="getData3()">&nbsp; 3&nbsp;</button></span> <span><button
				onclick="getData4()">&nbsp; 4&nbsp;</button></span> <span><button
				onclick="getData5()">&nbsp; 5&nbsp;</button></span> <span><button
				onclick="getData5()">終頁</button></span> <br> <br> <br>
		<!-- 公告 -->
		<div id="content">
			<div class="container">
				<div class="row justify-content-center">
					<div
						style="display: inline-block; height: 300px; width: 300px; margin: 25px">
						<img src="image/food1_adobespark.jfif">
					</div>
					<div
						style="display: inline-block; height: 300px; width: 700px; margin: 25px; padding-top: 10px">
						<h6 style="float: right">2021年11月15號 22:38</h6>
						<h2 style="text-align: center;">特價特價大特價</h2>

						<p style="font-size: 25px;">
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp三人同行一人免費,吃吐司配寵物,是你唯一的選擇,還等甚麼呢?
							一堆毛毛怪在等你來七逃RRR<br> <br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp四人套餐原價:1699元<br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp現在只要:1000元喔
						</p>

					</div>
				</div>
				<div class="row justify-content-center">
					<div class="justify-content-center"
						style="display: inline-block; height: 300px; width: 700px; margin: 25px 25px; padding-top: 10px">
						<h6 style="float: left">2021年11月15號 22:38</h6>
						<h2 style="text-align: center;">新餐點來享受</h2>

						<p style="font-size: 25px;">
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp人生中最美好的時光就是吃
							紅羅波佐火腿佐魚肉佐菠菜佐哈密瓜拌飯才是人間美味。: D<br>快來享受美好餐點,現在只要189元就吃的到唷。<br>
							<br> <br &nbsp>&nbsp哈密瓜魚肉紅羅波拌飯&nbsp189元!!!!!!!
						</p>

					</div>
					<div
						style="display: inline-block; height: 300px; width: 300px; margin: 25px">
						<img src="image/food2_adobespark.jfif">
					</div>
				</div>
			</div>
			<hr>
			<div class="container">
				<div class="row justify-content-center">
					<div
						style="display: inline-block; height: 300px; width: 300px; margin: 25px">
						<img src="image/c1.jpg">
					</div>
					<div
						style="display: inline-block; height: 300px; width: 700px; margin: 25px 25px; padding-top: 10px">
						<h6 style="float: right">2021年11月15號 22: 38</h6>
						<h2 style="text-align: center;">小波今天去看醫生嚕</h2>

						<p style="font-size: 25px;">
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp最近小波的眼睛好像怪怪的,於是我們請我們最美麗的小倩店長帶她去看醫生嚕!!還好醫生說沒怎麼樣,不然采臣跟我會很欲哭無淚的嗚嗚嗚
						</p>

					</div>
				</div>
				<div class="row justify-content-center">
					<div
						style="display: inline-block; height: 300px; width: 700px; margin: 25px 25px; padding-top: 10px">
						<h6 style="float: left">2021年11月15號 22: 38</h6>
						<h2 style="text-align: center;">預防大於治療</h2>

						<p style="font-size: 25px;">
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp最近小春的眼睛好像怪怪的,於是我們請我們最美麗的小倩店長帶她去看醫生嚕!!還好醫生說沒怎麼樣,不然采臣跟我會很欲哭無淚的嗚嗚嗚
						</p>

					</div>
					<div
						style="display: inline-block; height: 300px; width: 300px; margin: 25px">
						<img src="image/c2.jpg">
					</div>
				</div>
			</div>
		</div>
		<div style="text-align: center; font-family: BB;" class="clearfix "
			id="pageChange">
			<span><button onclick="getData()">首頁</button></span> <span><button
					onclick="getData()">&nbsp&nbsp1</button></span> <span><button
					onclick="getData2()">&nbsp; 2&nbsp;</button></span> <span><button
					onclick="getData3()">&nbsp; 3&nbsp;</button></span> <span><button
					onclick="getData4()">&nbsp; 4&nbsp;</button></span> <span><button
					onclick="getData5()">&nbsp; 5&nbsp;</button></span> <span><button
					onclick="getData5()">終頁</button></span>

			 <hr style="margin-bottom: 0;">

                <div style="text-align: center;background-color:rgb(6, 121, 121,.1);">
                    <br>
                    <h1 style="font-size: 68px;margin:20px 0 0 0;">地圖顯示</h1>
                    <br><br>

                    <iframe src="https://snazzymaps.com/embed/344847" width="100%" height="690px"
                        style="border:none;"></iframe>
                    <br><br><br><br><br><br>
                </div>
                <hr STYLE="margin-top:0 ;">
                <br><br><br><br><br><br>
			<hr>
			<!--網頁底部輪播  -->

			<div class=" footer">
				<div id="owl-two" class="owl-carousel owl-theme footer">
					<div class="item">
						<img src="image/m6.jfif" alt="" />
					</div>
					<div class="item">
						<img src="image/1.jfif" alt="" />
					</div>
					<div class="item">
						<img src="image/3.jfif" alt="" />
					</div>
					<div class="item">
						<img src="image/c4.jpg" alt="" />
					</div>
					<div class="item">
						<img src="image/c5.jpg" alt="" />
					</div>
					<div class="item">
						<img src="image/4.jfif" alt="" />
					</div>
					<div class="item">
						<img src="image/7.jfif" alt="" />
					</div>
					<div class="item">
						<img src="image/8.jfif" alt="" />
					</div>
				</div>
			</div>





			<div class="copy_right">
				<div>
					<div id="lowbar">
						<div class="container h-100">
							<div class="row h-100 align-items-center lowbar">
								<div class="col-12">
									<ul>
										<li><a href="#t1" target="_self">活動訊息</a></li>
										<li><a href="index.html" target="_self">餐點介紹</a></li>
										<li><a href="index.html" target="_self">線上訂位</a></li>
										<li><a href="petinfo.controller" target="_self">寵物領養</a></li>
										<li><a href="index.html" target="_self">登入註冊</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>

				</div>

				<div class="container">
					Copyright &copy;
					<script>
						document.write(new Date().getFullYear());
					</script>
					All rights reserved | This template is made with <i
						class="fa fa-heart-o" aria-hidden="true"></i> by <a href="#top">
						JAVA team4</a>
				</div>
			</div>

			<div class="fix">
				<a href="#top" style="font-size: 40px;">TOP</a>
			</div>
		</div>
	</div>
</body>
</html>