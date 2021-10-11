<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺管理系統</title>
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <!-- fontawesome icon -->
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />

    <link href="stylesheet/bootstrap.min.css" rel="stylesheet" />
    <link href="stylesheet/jquery-ui.min.css" rel="stylesheet" />
    <!--自訂樣式表-->
    <link href="stylesheet/backstage.css" rel="stylesheet" />
    <link href="stylesheet/employees.css" rel="stylesheet" />
    <!--<script src="javascript/bootstrap.min.js"></script>-->
    <script src="javascript/jquery-3.6.0.min.js"></script>
    <!--自訂js-->
    <script src="javascript/backstage.js"></script>
    <script src="javascript/employees.js"></script>

</head>
<body>
    <!-- 導覽列 -->
    <header>
        <div class="container-fluid border shadow w-100">
            <nav class="navbar navbar-light bg-white m-0 ">
                <div class="container-fluid">
                    <!-- 超連結到主頁 -->
                    <a class="navbar-brand row" href="backstage.html">後臺管理系統</a>
                    <!-- 搜尋欄及按鈕 -->
                    <form class="d-flex offset-5 col-3">
                        <input class="form-control me-2 ms-5 " type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                    <div class="">
                        <!-- 帳號頭像及功能 -->
                        <div class="dropdown">
                            <button class="btn-transparent" type="button" id="dropdownButton01" data-bs-toggle="dropdown">
                                <img src="image/husky.jpg" class="image shadow" />
                            </button>
                            <ul class="dropdown-menu  dropdown-menu-end shadow" aria-labelledby="dropdownMenuButton01">
                                <li><a class="dropdown-item" href="#">Username</a></li>
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><a class="dropdown-item" href="#">登出</a></li>
                            </ul>
                        </div>
                        <!-- 訊息資訊 -->
                        <div class="dropdown">
                            <button class="btn-transparent" type="button" id="dropdownButton02" data-bs-toggle="dropdown">
                                <i class="far fa-envelope me-5 navIcon mt-3" id="navIcon"></i>
                            </button>
                            <ul class="dropdown-menu  dropdown-menu-end shadow" aria-labelledby="dropdownMenuButton02">
                                <li>
                                    <a class="dropdown-item me-3" href="#">
                                        <img src="image/husky.jpg" class="imageMessage shadow" />
                                        message
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">
                                        <img src="image/husky.jpg" class="imageMessage shadow" />
                                        message
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">
                                        <img src="image/husky.jpg" class="imageMessage shadow" />
                                        message
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <!-- 訊息通知 -->
                        <div class="dropdown floatDown">
                            <button class="btn-transparent" type="button" id="dropdownButton03" data-bs-toggle="dropdown">
                                <i class="far fa-bell me-5 navIcon mt-3" id="navIcon"></i>
                            </button>
                            <ul class="dropdown-menu  dropdown-menu-end shadow" aria-labelledby="dropdownMenuButton03">
                                <li><a class="dropdown-item" href="#">notify</a></li>
                                <li><a class="dropdown-item" href="#">notify</a></li>
                                <li><a class="dropdown-item" href="#">notify</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </header>
        <!-- 手風琴 -->
        <div class="containerAll">
            <!-- container left -->
            <div class="containerLeft shadow">
                <nav id="sidebar" class=" colorGray">
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button type="button" id="collapse" class="collapseBtn ">
                            <i class="fas fa-align-left"></i>
                        </button>
                    </div>
                    <ul class="list-unstyled">
                        <li>
                            <a href="#sublist01" data-bs-toggle="collapse" id="dropdown01" class="center">
                                <i class="fas fa-pizza-slice mx-2"></i>
                                <span class="items">餐點管理</span>
                            </a>
                            <!-- 子連結 -->
                            <ul id="sublist01" class="list-unstyled collapse" >
                                <li>
                                    <a href="#" class="itemDetails">餐點總覽</a>
                                </li>
                                <li>
                                    <a href="#" class="itemDetails">新品上架</a>
                                </li>
                                <li>
                                    <a href="#" class="itemDetails">餐點更新</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#sublist02" data-bs-toggle="collapse" id="dropdown02" class="center">
                                <i class="fas fa-utensils mx-2"></i>
                                <span class="items">訂單及訂位管理</span>
                            </a>
                            <!-- 子連結 -->
                            <ul id="sublist02" class="list-unstyled collapse">
                                <li>
                                    <a href="ordermanage.html" class="itemDetails">訂單管理</a>
                                </li>
                                <li>
                                    <a href="promo.html" class="itemDetails">優惠碼管理</a>
                                </li>
                                <li>
                                    <a href="#" class="itemDetails">訂位查詢</a>
                                </li>
                                <li>
                                    <a href="#" class="itemDetails">訂位更新</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#sublist03" data-bs-toggle="collapse" id="dropdown03" class="center">
                                <i class="fas fa-bullhorn mx-2"></i>
                                <span class="items">公告管理</span>
                            </a>
                            <!-- 子連結 -->
                            <ul id="sublist03" class="list-unstyled collapse">
                                <li>
                                    <a href="#" class="itemDetails">公告總覽</a>
                                </li>
                                <li>
                                    <a href="postCreate.html" class="itemDetails">新增公告</a>
                                </li>
                                <li>
                                    <a href="#" class="itemDetails">公告更新</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#sublist04" data-bs-toggle="collapse" id="dropdown04" class="center">
                                <i class="fas fa-cat mx-2"></i>
                                <span class="items">寵物管理</span>
                            </a>
                            <!-- 子連結 -->
                            <ul id="sublist04" class="list-unstyled collapse">
                                <li>
                                    <a href="backPetInfo.html" class="itemDetails">寵物資訊總覽</a>
                                </li>
                                <li>
                                    <a href="#" class="itemDetails">文章發佈</a>
                                </li>
                                <li>
                                    <a href="#" class="itemDetails">文章更新</a>
                                </li>
                                <li>
                                    <a href="#" class="itemDetails">寵物領養資訊</a>
                                </li>
                                <li>
                                    <a href="#" class="itemDetails">領養預約總覽</a>
                                </li>
                                <li>
                                    <a href="#" class="itemDetails">領養記錄查詢</a>
                                </li>
                            </ul>
                        </li>
                        <li class="m-0">
                            <a href="message.html" class="center">
                                <i class="fas fa-comments-dollar mx-2"></i>
                                <span class="items">客服管理</span>
                            </a>
                        </li>
                        <li class="m-0">
                            <a href="SelectCustomerAll.Controller" class="center">
                                <i class="fas fa-users mx-2"></i>
                                <span class="items">會員管理</span>
                            </a>
                        </li>
                        <li class="m-0">
                            <a href="Employees.Controller" class="center sidebarLight02">
                                <i class="fas fa-address-card mx-2"></i>
                                <span class="items">員工管理</span>
                            </a>
                        </li>

                    </ul>
                </nav>
            </div>
            <!-- container right -->
            <div class="containerRight">
                <!-- 分頁按鈕 -->
                <div class="page container">
                    <div class="row">
                        <div class="col-2">
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary btn-lg ms-5" data-bs-toggle="modal" data-bs-target="#empModal">
                                新增
                            </button>
                            <!-- Modal:員工帳號建立頁面 --><!--fade為動畫-->
                            <div class="modal fade" id="empModal" tabindex="-1">
                                <div class="modal-dialog modal-dialog-centered modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h3 class="modal-title ms-3" id="ModalLabel">建立帳號</h3>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                        </div>
                                        <form action="#" method="post">
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="offset-1 col-4">
                                                        <label for="account" class="form-label">帳號:</label>
                                                        <input id="account" name="account" type="text" value="EMP2021091801" class="form-control mb-3" disabled />
                                                    </div>
                                                    <div class="offset-2 col-4">
                                                        <label for="password" class="form-label">密碼:</label>
                                                        <input id="password" name="password" type="password" class="form-control mb-3" disabled />
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="offset-1 col-4">
                                                        <label for="empName" class="form-label">姓名:</label>
                                                        <input id="empName" name="empName" type="text" class="form-control  mb-3" autocomplete="off" autofocus required />

                                                    </div>
                                                    <div class="offset-2 col-4 d-flex align-items-center">
                                                        <label for="male" class="form-check-label">性別:</label>
                                                        <input id="male" name="gender" type="radio" class="form-check-input mx-2" required />
                                                        <label for="male">男</label>
                                                        <input id="female" name="gender" type="radio" class="form-check-input mx-2" />
                                                        <label for="female">女</label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="offset-1 col-4">
                                                        <label for="phoneNumber" class="form-label">連絡電話:</label>
                                                        <input id="phoneNumber" name="phoneNumber" max="10" pattern="[0]{1}[9]{1}\d{8}" type="tel" class="form-control  mb-3" autocomplete="off" required />

                                                    </div>
                                                    <div class="offset-2 col-4">
                                                        <label for="birthdate" class="form-label">生日:</label>
                                                        <input id="birthdate" name="birthdate" type="date" class="form-control  mb-3" required />
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="offset-1 col-4">
                                                        <label for="" class="form-label">職稱:</label>
                                                        <select class="form-select" required>
                                                            <option selected></option>
                                                            <option value="waiter">服務生</option>
                                                            <option value="senior waiter">資深服務生</option>
                                                            <option value="assistant chef">廚房助手</option>
                                                            <option value="chef">廚師</option>
                                                            <option value="store manager">店長</option>
                                                        </select>

                                                    </div>
                                                    <div class="offset-2 col-4">
                                                        <label for="hiredate" class="form-label">到職日:</label>
                                                        <input id="hiredate" name="hiredate" type="date" class="form-control  mb-3" required />
                                                    </div>
                                                    <div class="offset-1 col-7">
                                                        <label for="address" class="form-label">地址:</label>
                                                        <input id="address" name="address" type="text" class="form-control  mb-3" autocomplete="off" required />
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                                <button type="submit" class="btn btn-primary">建立</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-8 ">
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item "><a class="page-link" href="history.back()">上一頁</a></li>
                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item"><a class="page-link" href="#">下一頁</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <!-- 主頁內容 -->
                <div class="container-fluid ">
                    <div class="row">
                        <div class="col-11 bg-white mt-3 ms-5 box rounded-3 shadow">
                            <table class="table table-striped table-hover mt-4">
                                <thead>
                                    <tr>
                                        <th scope="col">員工ID</th>
                                        <th scope="col">員工姓名</th>
                                        <th scope="col">職稱</th>
                                        <th scope="col">性別</th>
                                        <th scope="col">連絡電話</th>
                                        <th scope="col">生日</th>
                                        <th scope="col">到職日期</th>
                                        <th scope="col">地址</th>
                                        <th scope="col">備註</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody class="align-middle">
                                    <tr>
                                        <th scope="row">1</th>
                                        <td>賈斯汀</td>
                                        <td>廚師</td>
                                        <td>男</td>
                                        <td>0987654321</td>
                                        <td>1990/12/31</td>
                                        <td>2020/09/09</td>
                                        <td>高雄市苓雅區四維三路2號高雄市苓雅區四維三路2號</td>
                                        <td>長期曠職</td>
                                        <td><button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#empUpdataModal">更新</button></td>
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>賈斯汀</td>
                                        <td>廚師</td>
                                        <td>男</td>
                                        <td>0987654321</td>
                                        <td>1990/12/31</td>
                                        <td>2020/09/09</td>
                                        <td>高雄市苓雅區四維三路2號</td>
                                        <td></td>
                                        <td><button type="button" class="btn btn-danger">更新</button></td>

                                    </tr>
                                    <tr>
                                        <th scope="row">3</th>
                                        <td>賈斯汀</td>
                                        <td>廚師</td>
                                        <td>男</td>
                                        <td>0987654321</td>
                                        <td>1990/12/31</td>
                                        <td>2020/09/09</td>
                                        <td>高雄市苓雅區四維三路2號</td>
                                        <td></td>
                                        <td><button type="button" class="btn btn-danger">更新</button></td>

                                    </tr>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
                <!-- 員工資料更新modal -->
                <div class="modal fade" id="empUpdataModal" tabindex="-1">
                    <div class="modal-dialog modal-lg ">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title" id="empModalLabel">員工資料</h3>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <form action="#" method="post">
                                <div class="modal-body">
                                    <div class="row">
                                        <!-- left -->
                                        <div class="offset-1 col-4">
                                            <div class="ratio ratio-1x1 mt-1 mb-3">
                                                <img src="image/m2.jpg" class="rounded-3 shadow" />
                                            </div>
                                            <label for="empNameUpdata" class="form-label">姓名:</label>
                                            <input id="empNameUpdata" name="empNameUpdata" type="text" class="form-control mb-3" value="賈斯汀" disabled />
                                            <label for="hiredateUpdata" class="form-label">到職日:</label>
                                            <input id="hiredateUpdata" name="hiredateUpdata" type="date" class="form-control mb-3" value="2020-09-09" disabled />
                                            <label for="" class="form-label">職稱:</label>
                                            <select class="form-select mb-3" required>
                                                <option value="waiter">服務生</option>
                                                <option value="senior waiter">資深服務生</option>
                                                <option value="assistant chef">廚房助手</option>
                                                <option value="chef">廚師</option>
                                                <option value="store manager">店長</option>
                                            </select>
                                        </div>
                                        <!-- right -->
                                        <div class="offset-1 col-5">
                                            <label for="genderUpdata" class="form-label">性別:</label>
                                            <input id="genderUpdata" name="genderUpdata" type="text" class="form-control mb-3" value="男" disabled />
                                            <label for="phoneNumberUpdata" class="form-label">連絡電話:</label>
                                            <input id="phoneNumberUpdata" name="phoneNumberUpdata" type="tel" class="form-control mb-3" value="0987654321" disabled />
                                            <label for="birthdateUpdata" class="form-label">生日:</label>
                                            <input id="birthdateUpdata" name="birthdateUpdata" type="date" class="form-control mb-3" value="1990-12-31" disabled />
                                            <label for="addressUpdata" class="form-label">地址:</label>
                                            <input id="addressUpdata" name="addressUpdata" type="text" class="form-control mb-3" value="高雄市苓雅區四維三路2號高雄市苓雅區四維三路2號" disabled />
                                            <label for="notesUpdata" class="form-label">備註:</label>
                                            <textarea id="notesUpdata" name="notesUpdata" class="form-control mb-3">長期曠職</textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="offset-5 col">
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                                <button type="submit" class="btn btn-primary">完成</button>
                                            </div>
                                            <div class="offset-2 col-2">
                                                <button type="button" class="btn btn-danger">刪除帳號</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- 分頁按鈕 -->
                <div class="page">
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <li class="page-item"><a class="page-link" href="history.back()">上一頁</a></li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">下一頁</a></li>
                        </ul>
                    </nav>
                </div>
            </div>

        </div>
</body>
</html>