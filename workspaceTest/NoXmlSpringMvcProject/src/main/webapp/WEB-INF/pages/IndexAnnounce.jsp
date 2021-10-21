<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>index</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="stylesheet/index.css" />
    <!--CSS在這邊 要注意放在bootstrap樣式表CDN後面 不然權重相同的部分會被bootstrap蓋過去-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0-alpha.1/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
            integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <!--javaScript掛到這邊-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css"
          integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="javascript/index.js"></script>


</head>

<body id="top" style="background-image:url(image/背景4.jpg)" ;>
    <div style="background-color: rgb(6, 121, 121,.1);"><br></div>
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

                            <li><a href="login.Controller" target="_self">登入註冊</a></li>
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
    <div class="container  justify-content: center align-content: center" style="display: flex; ">
        <ul class="papper">
            <li>
                <p class="p1"></p>
                <h3>Pet Restaurant</h3>
                <h2>浪跡寵物餐廳</h2>
                <p style="font-size: 16px;"> 與毛小孩體驗最快樂、最衛生、最安全的接觸，搭配著餐廳所提供的美好餐點,感受人生的美好時光。 </p>
            </li>
            <li>
                <div class="backpic">
                    <div style="height: 460px;width:332px;" class="row justify-content-center align-items-center">
                        <div class="btn">
                            <button type="button" class="btn btn-dark" id="b1">我要訂餐</button>
                            <!--訂餐訂位按鈕我加了一個hover效果在CSS XDD-->
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="backpic2">
                    <div style="height: 460px;width:332px;">
                        <div style="height: 460px;width:332px;" class="row justify-content-center align-items-center">
                            <div class="btn">
                                <button type="button" class="btn btn-dark" id="b2" data-bs-toggle="modal"
                                        data-bs-target="#Reservation">
                                    我要訂位
                                </button>
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
                 style="background-image: url(image/背景1.jpg);background-size: cover;background-position:100px 60px;">
                <div class="modal-header">
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="#" method="get">
                    <div class="modal-body" style="font-family:BB;font-size: 25px;" ;>
                        <div class="row">
                            <div class="text-center" id="mainbox">
                                <img src="image/浪跡.png" style="width: 25%;">
                                <hr>

                                <BR>
                                <label for="Name" class=""><span>顧客姓名&nbsp&nbsp&nbsp</span></label>
                                <input type="text" id="Name" name="Name" required style="border:2px solid #ccc" />
                                <BR><BR>
                                <label for="Phone" class="">電話號碼&nbsp&nbsp&nbsp</span></label>
                                <input type="text" id="Phone" name="Phone" required style="border:2px solid #ccc" />
                                <BR><BR>
                                <span>訂位人數&nbsp&nbsp&nbsp</span>
                                <select name="peopleNum" style="width: 280px;height:40px;border:2px solid #ccc" required>
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

                                <label for="orderdate" class="form-label" style="margin-right: 13px;">訂位日期</label>
                                <input style="width:280px;height: 40px;display: inline-block;margin-left: 25px;border:2px solid #ccc"
                                       id="orderdate" name="orderdate" type="date" class="form-control" required />

                                <div style="overflow: hidden;height:40px;"></div>

                                <span>訂位人數&nbsp&nbsp&nbsp</span>
                                <select name="time" style="width: 280px;height:40px;border:2px solid #ccc" required>
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
                                </select>




                                <BR><BR>
                                <div class="modal-footer">
                                    <div class="container-fluid">
                                        <div class="row justify-content-start">
                                            <div class="offset-4 col-4">
                                                <button type="submit" class="btn btn-primary">送出</button>
                                                <button type="reset" class="btn btn-secondary"
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
   <c:forEach var="arrAnnounce" items="${arrAnnounce}" varStatus="status">
            <c:if test="${status.count%2!=0}">

                <div class="row justify-content-center">
                    <div style="display: inline-block; height: 300px;width: 300px;margin:25px" id="imgPreview">
                        <img src="data:image/png;base64,${baseStr[arrAnnounce.announceID]} " style="width:100%;height:100%;" alt=""/>
                    </div>
                    <div style="display: inline-block; height: 300px;width: 700px;margin: 25px;padding-top:10px">
                        <h6 style="float:right;font-family: AA">${arrAnnounce.releaseTime}</h6>
                        <h2 style="text-align: center;font-family: AA">${arrAnnounce.headline}</h2>

                        <p style="font-size: 25px;font-family: AA">
                            ${arrAnnounce.articleCont}
                        </p>

                    </div>

                </div>

            </c:if>
            <c:if test="${status.count%2==0}">
                <div class="row justify-content-center">
                    <div class="justify-content-center"
                        style="display: inline-block; height: 300px;width: 700px;margin: 25px 25px;padding-top:10px">
                        <h6 style="float:left;font-family: AA">${arrAnnounce.releaseTime}</h6>
                        <h2 style="text-align: center;font-family: AA">${arrAnnounce.headline}</h2>

                        <p style="font-size: 25px;font-family: AA">
                            ${arrAnnounce.articleCont}
                        </p>

                    </div>
                    <div style="display: inline-block; height: 300px;width: 300px;margin:25px">
                         <img src="data:image/png;base64,${baseStr[arrAnnounce.announceID]} " style="width:100%;height:100%;" alt=""/>
                    </div>
                </div>

            </c:if>

            <hr>
        </c:forEach>

  

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
                    <script>document.write(new Date().getFullYear());</script> All rights reserved | This template
                    is made with
                    <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="#top">
                        JAVA team4
                    </a>
                </div>
            </div>

            <div class="fix"><a href="#top" style="font-size: 40px;">TOP</a></div>
        </div>
    </div>
</body>


</html>
