<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>PetInfo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/stylesheet/petInfo.css" /><!--CSS here-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.0-alpha.1/jquery-ui.min.js"></script>
    <script src="/javascript/petInfo.js"></script> <!--JS Here-->


</head>
<body>
    <hr>
    <!-- 標題logo 部分 -->
    <div class="logo-area text-center">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <a href="index.html"><img src="image/浪跡.png" alt=""></a> <!--LOGO-->
                </div>
            </div>
        </div>
    </div>
    <hr>

    <!-- nav欄部分 -->
    <div id="topbar">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <ul>
                        <li><a href="petInfo.html" target="_self">寵物領養</a></li>
                        <li><a href="index.html" target="_self">線上訂位</a></li>
                        <li><a href="index.html" target="_self">餐點介紹</a></li>
                        <li><a href="index.html" target="_self">公告專區</a></li>
                        <li><a href="index.html" target="_self">登入註冊</a></li>
                        <li><a href="index.html" target="_self">首頁</a></li>
                    </ul>
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
                        <h2>寵物領養</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--內容-->
    <div class="container" id="container" style="margin:30px;">
        <!--查詢欄位-->
        <form action="#">
            <div class="row justify-content-start" style="margin:30px;">
                <div class="col-1"><select name="category" id="category"><option value="">不限類別</option></select></div>
                <div class="col-1"><select name="sex" id="sex"><option value="">不限性別</option></select></div>
                <div class="col-2"><button type="button" class="btn btn-info text-white btn-sm">搜尋</button></div>
            </div>
        </form>

        <!--TEST-->
        <div id="divTest">
            
        </div>
    </div>

</body>
</html>