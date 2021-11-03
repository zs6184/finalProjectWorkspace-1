<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>	
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
<link rel="stylesheet" href="/stylesheet/index.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.css">
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


 <script src="https://cdnjs.cloudflare.com/ajax/libs/flatpickr/4.2.3/flatpickr.js"></script>
<script>
$(function(){
	 $("#orderDate").flatpickr({
		    enableTime: true,
		    dateFormat: "Y-m-d",
		    "disable": [
		      function (date) {
		        return (date.getDay() === 0 || date.getDay() === 6);  // disable weekends
		      }
		    ],
		    "locale": {
		      "firstDayOfWeek": 1 // set start day of week to Monday
		    }
		  });
	
})
 
</script>
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


	<!--頭部輪播  -->
	<div id="owl-one" class="owl-carousel owl-theme">
		<div class="item">
			<img src="/image/1.jpg" alt="" />
		</div>
		<div class="item">
			<img src="/image/4.jpg" alt="" />
		</div>
		<div class="item">
			<img style="margin-right: 0;" src="/image/3.jpg" alt="" />
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
     <div id="loading"  class="overlay-hide">
      <div class="d-flex justify-content-center">
            <div class="spinner-border" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>
    </div>
    <div class="modal fade " id="reservationMsg" tabindex="-1">
        <div class="modal-dialog modal-sm"">

            <div class="modal-content" style="background-position:100px 170px;">
                <div class="modal-header">
                    <button type="button" class="btn-close"  data-bs-dismiss="modal"></button>
                </div>
                <div id="responseMessage"  class="modal-body" style="font-family:AA;font-size: 30px;" ;>
                   <p style="text-align:center"> success</p>
                </div>
            </div>
        </div>

    </div>
	
	<div class="modal fade" id="Reservation" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content"
                 style="background-image: url(../image/背景1.jpg);background-size: cover;background-position:100px 60px;">
                <div class="modal-header">
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                  <form id="bookingForm" action="/bookings" method="post" >
                    <div class="modal-body" style="font-family:BB;font-size: 25px;" ;>
                        <div class="row">
                            <div class="text-center" id="mainbox">
                                <img src="../image/浪跡.png" style="width: 25%;">
                                <hr>

                                <BR>
                                 <label for="name" class=""><span>顧客姓名&nbsp&nbsp&nbsp</span></label>
                                <input type="text" id="name" name="name" required style="border:2px solid #ccc" />
                               
                                <BR><BR>
                                <label for="phone" class="">電話號碼&nbsp&nbsp&nbsp</span></label>
                                <input type="text" id="phone" name="phone" required style="border:2px solid #ccc" />
                                <BR><BR>
                                <span>訂位人數&nbsp&nbsp&nbsp</span>
                               <select id="peopleNum" name="peopleNum" style="width: 280px;height:40px;border:2px solid #ccc" required>
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
                                </select>
                                <BR><BR>

                                <label for="orderDate" class="form-label" style="margin-right: 13px;">訂位日期</label>
                                <input style="width:280px;height: 40px;display: inline-block;margin-left: 25px;border:2px solid #ccc"
                                       id="orderDate" name="orderDate" class="form-control" data-input required placeholder="點擊選擇您的日期" />

                                <div style="overflow: hidden;height:40px;"></div>

                                <span>訂位時間&nbsp&nbsp&nbsp</span>
                                <select id="time" name="time" style="width: 280px;height:40px;border:2px solid #ccc" required>
                                    <option value="" selected>選擇時間</option>
                                    <option value="1">11:00</option>
                                    <option value="2">12:00</option>
                                    <option value="3">13:00</option>
                                    <option value="4">14:00</option>
                                    <option value="5">15:00</option>
                                    <option value="6">16:00</option>
                                    <option value="7">17:00</option>
                                    <option value="8">18:00</option>
                              
                                </select>




                                <BR><BR>
                                <div class="modal-footer">
                                    <div class="container-fluid">
                                        <div class="row justify-content-start">
                                            <div class="offset-4 col-4">
                                               <button id="submitBtn" type="submit" onclick="booking()" class="btn btn-success">送出</button>
                                                <button type="reset" class="btn btn-success"
                                                        data-bs-dismiss="modal">
                                                    取消
                                                </button>
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
   
		<!-- 公告 -->
	<br>
   <c:set var="startIndex" value="${fn:length(arrAnnounce)-1}"></c:set>
   <c:forEach var="arrAnnoun" items="${arrAnnounce}" varStatus="status">
            <c:if test="${status.count%2!=0}">

                <div class="row justify-content-center">
                    <div style="display: inline-block; height: 300px;width: 300px;margin:25px" id="imgPreview">
                        <img src="data:image/png;base64,${baseStr[arrAnnounce[startIndex-status.index].announceID]} " style="width:100%;height:100%;" alt=""/>
                    </div>
                    <div style="display: inline-block; height: 300px;width: 700px;margin: 25px;padding-top:10px">
                        <h6 style="float:right;font-family: AA">${arrAnnounce[startIndex-status.index].releaseTime}</h6>
                        <h2 style="text-align: center;font-family: AA">${arrAnnounce[startIndex-status.index].headline}</h2>

                        <p style="font-size: 25px;font-family: AA">
                            ${arrAnnounce[startIndex-status.index].articleCont}
                        </p>

                    </div>

                </div>

            </c:if>
            
            <c:if test="${status.count%2==0}">
            	<hr>
                <div class="row justify-content-center">
                    <div class="justify-content-center"
                        style="display: inline-block; height: 300px;width: 700px;margin: 25px 25px;padding-top:10px">
                        <h6 style="float:left;font-family: AA">${arrAnnounce[startIndex-status.index].releaseTime}</h6>
                        <h2 style="text-align: center;font-family: AA">${arrAnnounce[startIndex-status.index].headline}</h2>

                        <p style="font-size: 25px;font-family: AA">
                            ${arrAnnounce[startIndex-status.index].articleCont}
                        </p>

                    </div>
                    <div style="display: inline-block; height: 300px;width: 300px;margin:25px">
                         <img src="data:image/png;base64,${baseStr[arrAnnounce[startIndex-status.index].announceID]} " style="width:100%;height:100%;" alt=""/>
                    </div>
                </div>

            </c:if>

            
        </c:forEach>

  <hr>

        <br> <br> <br>
     
       
          <div style="width:1100px;margin:0 auto">


        <ul>
            <li style="float:left;margin-right: 30px;">
                <iframe src="https://snazzymaps.com/embed/344847" width="500px" height="500px"
                    style="border:none;"></iframe>
            </li>
            <li style="float:left;height: 500px;width: 500px; background-color:  rgba(6, 121, 121,.8);">
                <ul>
                    <li style="height:50px;">
                        <h2 style="margin-top:20px;color: ivory;">浪跡寵物餐廳</h2>
                    </li>
                    <li>
                        <p style="margin-top:0px;color: ivory;font-size: 20px;">地址:嘉義市嘉義市西區博愛路二段467號</p>
                    </li>
                    <hr style="width: 430px;color: white;height: 8px;margin-bottom:5px ;">
                    <li>
                        <h4 style="color: white;">&lt交通資訊&gt - 步行 </h4>
                        <p style="font-size: 20px;color: white;">從後火車站＞中興路左轉＞沿博愛路一段＞沿博愛路二段</p>
                        <hr style="width: 430px;color: white;height: 5px;">
                        <h4 style="color: white;">&lt交通資訊&gt - 公車 </h4>
                        <p style="font-size: 20px;color: white;">可以搭乘嘉義客運或公車，往朴子.布袋方向，在家樂福站下車</p>
                        <hr style="width: 430px;color: white;height: 5px;">
                        <span style="color: white;font-size: 20px;">&lt預約電話&gt </span>
                        <span style="font-size: 20px;color: white;">0987-654-321</span><br>
                        <span style="color: white;font-size: 20px;">&lt營業時間&gt </span>
                        <span style="font-size: 20px;color: white;">週一到週五早上11:00-下午7:00</span>
                    </li>
                </ul>
            </li>
        </ul>
 <div style="clear: both;"></div>
    <br><br><br>

</div>


			<!--網頁底部輪播  -->

			<div class=" footer">
				<div id="owl-two" class="owl-carousel owl-theme footer">
					<div class="item">
						<img src="/image/m6.jfif" alt="" />
					</div>
					<div class="item">
						<img src="/image/1.jfif" alt="" />
					</div>
					<div class="item">
						<img src="/image/3.jfif" alt="" />
					</div>
					<div class="item">
						<img src="/image/c4.jpg" alt="" />
					</div>
					<div class="item">
						<img src="/image/c5.jpg" alt="" />
					</div>
					<div class="item">
						<img src="/image/4.jfif" alt="" />
					</div>
					<div class="item">
						<img src="/image/7.jfif" alt="" />
					</div>
					<div class="item">
						<img src="/image/8.jfif" alt="" />
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
										<li><a href="/product/findallproduct" target="_self">餐點介紹</a></li>
										  <li><a href="#Reservation" role="button" data-bs-toggle="modal" target="_self">線上訂位</a></li>
										<li><a href="/pet/petinfo.controller" target="_self">寵物領養</a></li>
										<li><div class="dropdown absolute backstage">
												<button class="btn btn-link dropdown-toggle text-light"
													type="button" id="cusCenterDropdown2"
													data-bs-toggle="dropdown">${realName}</button>
												<ul class="dropdown-menu p-0" role="button">
													<li id="whp3"><a
														href="/Users/SelectCustomer.controller#information"
														class="text-dark dropdown-item d-flex justify-content-center"
														target="_self">會員中心</a></li>
													<li id="whp4" class="m-0">
														<form action="/Users/logout.Controller" method="post">
															<button type="submit"
																class="dropdown-item d-flex justify-content-center"
																value="logout">登出</button>
														</form>
													</li>
												</ul>
											</div></li>
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