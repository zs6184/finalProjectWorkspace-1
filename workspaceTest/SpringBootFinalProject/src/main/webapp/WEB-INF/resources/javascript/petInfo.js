// JavaScript source code

var indexPage = 1;

$(function() {
	//確認權限，如果為EMPLOYEE就顯示後台按鈕
	var emp = $(".backstage").hasClass("EMPLOYEE");
	var admin = $(".backstage").hasClass("ADMIN");

	console.log(emp);
	console.log(admin);
	if (emp || admin) {
		$(".backstage").each(function() {
			$(this).find("li:first").after(`<li id="whp5"><a id="backstage" href="/Backstage/SelectCustomerAll.Controller"
											style="font-size: 1.1em;color:black"
											class="dropdown-item d-flex justify-content-center"
											target="_self">後台管理</a></li>`);
		});
	}
	
	//檢測是否有SessionAttribute 根據結果變更顯示的選項
	var sessioncheck = $("#sessionUsername").val();
	console.log("sessioncheck="+sessioncheck);
	if(sessioncheck=="null"){//沒抓到就換回來
		console.log("隱藏");
		$(".memberOption").addClass("d-none");
		$(".loginOption").removeClass("d-none");
	}else{//當有抓到SessionAttribute時顯示會員選項	、隱藏登入選項
		console.log("顯示");
		$(".memberOption").removeClass("d-none");
		$(".loginOption").addClass("d-none");
	}

	
	//stickybar
	window.onscroll = function() { stickTop() };
	var topbar = document.getElementById("topbar");
	var distance = topbar.offsetTop; //抓topbar離頁面頂部距離
	
	//Stickytop
	function stickTop() {
		if (window.pageYOffset >= distance) { //偏移量>distance時增加stick
			topbar.classList.add("sticky");
		} else {
			topbar.classList.remove("sticky");
		}
	}
	

	//將不同背景色放入陣列
	const bgColor = ["bg-success", "bg-danger", "bg-warning", "bg-info","bg-primary"];
	//隨機從陣列中取詞彙
	const getBgColor = () => bgColor[
		Math.floor(Math.random() * (bgColor.length))];
	//將搜索結果加上隨機背景色
	$(".selectResult").each(function() {
		$(this).addClass(`${getBgColor()}`)
	});


});

//選取單一寵物詳細資料
//function getDetail(obj) {
//	$.ajax({
//		type: "GET",
//		url: "/pet/detaildata/" + $(obj).val(),
//		success: function() {
//			console.log("GET SUCCESS");
//		},
//		error: function() {
//			console.log("FAILED TO GET");
//		}
//	})
//};



//	$.ajax({
//		type: "GET",
//		url: "/NoXmlSpringMvcProject/petinfo.controller",
//		success:function(){
//			console.log("redirect success");
//		},
//		error:function(){
//			console.log("redirect fail");
//		}
//
//	})


    //將取得backPetInfo資料載入select選項中
//    $.get("backPetInfo.html", function (data) {
//
//        $(".category", data).each(function () {
//            $("#category").append(`<option value="${this.textContent}">${this.textContent}</option>`);
//            console.log(this.textContent);
//        });
//
//        //使用陣列裝抓到的所有選項(三個F)，因為有重複值，所以丟進Set中篩掉重複值
//        var sexArr = new Array;
//        $(".sex", data).each(function () {
//            sexArr.push(this.textContent);
//        });
//        var sexArrSet = new Set(sexArr);
//        var sexUnique = [...sexArrSet]; //Set轉回陣列
//        console.log("uniqyeArr = " + sexUnique);
//
//        sexUnique.forEach(function (v, i) { //JQ 的forEach 前面value後面index
//            $("#sex").append(`<option value="${v}">${v}</option>`)
//            console.log("value = " + v);
//        });
//		
//        var index = 0;
//		//左半區域
//        $("#infoTable tr:even:not(':first')", data).each(function () {
//            $("#infoLeft").append
//				(`<div class="row col-10 offset-1 border bg-secondary text-white" style="margin-bottom:30px;border-radius:10px;">
//					<div class="row col-6 border align-items-center" style="margin:0px;">
//						<img src="image/f5.jpg" class="col w-100 h-w"/>
//					</div>
//					<div class="col" id="${index}">
//					</div>
//				</div>`);
//            $("td:not(':last')", this).each(function () {
//                $(`#${index}`).append(`<div>${this.textContent}</div>`);
//            });
//            index++;
//        });
//		//右半區域
//        $("#infoTable tr:odd", data).each(function () {
//            console.log("RIGHT---" + this.textContent);
//            $("#infoRight").append(
//				`<div class="row col-10 offset-1 border bg-secondary text-white" style="margin-bottom:30px;border-radius:10px;">
//					<div class="row col-6 border align-items-center" style="margin:0px;">
//						<img src="image/f5.jpg" class="col w-100 h-w"/>
//					</div>
//					<div class="col" id="${index}"></div>
//				</div>`);
//            $("td:not(':last')", this).each(function () {
//                $(`#${index}`).append(`<div>${this.textContent}</div>`);
//            });
//            index++;
//        });
//
//    });

