<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="/stylesheet/sweetalert.css" />
    <script src="https://cdn.staticfile.org/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="/javascript/sweetalert.min.js"></script>
    <script src="/javascript/sweetalert-dev.js"></script>
    <script src="/javascript/ShoppingCartTEST.js"></script>

    <title>購物車項目測試中2</title>
    <style type="text/css">
        .center div {
            background-color: orange;
            width: 300px;
            margin: 10px auto;
            padding: 0 5px 20px 5px;
            line-height: 100%;
            margin-bottom:2em;
           
        }
        .center h2 {
            text-align: center;
            width: 300px;
            margin: 10px auto;
        }
    </style>
</head>
<body>
    <div class="center">
        <h2>aaa123</h2>
        <div id="1">
            <h1>蘋果</h1>
            <button class="addtocart" type="button">加入購物車</button>
            <button class="pluscartitem" type="button">數量++</button>
            <button class="reducecartitem" type="button">數量--</button>
        </div>
        <div id="2">
            <h1>香蕉</h1>
            <button class="addtocart" type="button">加入購物車</button>
            <button class="pluscartitem" type="button">數量++</button>
            <button class="reducecartitem" type="button">數量--</button>
        </div>
        <div id="3">
            <h1>鳳梨</h1>
            <button class="addtocart" type="button">加入購物車</button>
            <button class="pluscartitem" type="button">數量++</button>
            <button class="reducecartitem" type="button">數量--</button>
        </div>
        <div id="4">
            <h1>土豆</h1>
            <button class="addtocart" type="button">加入購物車</button>
            <button class="pluscartitem" type="button">數量++</button>
            <button class="reducecartitem" type="button">數量--</button>
        </div>
    </div>
</body>
</html>