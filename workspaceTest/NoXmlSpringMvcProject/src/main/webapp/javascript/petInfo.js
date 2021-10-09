// JavaScript source code


$(function () {
    //�s�ystickybar�ĪG
    window.onscroll = function () { stickTop() };
    var topbar = document.getElementById("topbar");
    var distance = topbar.offsetTop; //���otopbar�W�谾���q


    function stickTop() {
        if (window.pageYOffset >= distance) { //�H����Y�b�����q���P�_����
            topbar.classList.add("sticky");
        } else {
            topbar.classList.remove("sticky");
        }
    }


    //將取得backPetInfo資料載入select選項中
    $.get("BackPetInfo.jsp", function (data) {

        //�ﶵ��

        //純粹取值後填入的做法，沒有篩重複值(剛好也沒有重複值)
        $(".category", data).each(function () {
            $("#category").append(`<option value="${this.textContent}">${this.textContent}</option>`);
            console.log(this.textContent);
        });

        //使用陣列裝抓到的所有選項(三個F)，因為有重複值，所以丟進Set中篩掉重複值
        var sexArr = new Array;
        $(".sex", data).each(function () {
            sexArr.push(this.textContent);
        });
        var sexArrSet = new Set(sexArr);
        var sexUnique = [...sexArrSet]; //使用展開運算符[...]轉換成為陣列
        console.log("uniqyeArr = " + sexUnique);

        sexUnique.forEach(function (v, i) { //forEach()是js的API，K-V值value是第一個參數，index是第二個
            $("#sex").append(`<option value="${v}">${v}</option>`)
            console.log("value = " + v);
        });

       //資訊欄
        var index = 0;
        $("#infoTable tr:even:not(':first')", data).each(function () {
            console.log("LEFT---" + this.textContent);
            $("#infoLeft").append(`<div class="row col-10 offset-1 border bg-secondary text-white" style="margin-bottom:30px;border-radius:10px;"><div class="row col-6 border align-items-center" style="margin:0px;"><img src="image/f5.jpg" class="col w-100 h-w"/></div><div class="col" id="${index}"></div></div>`);
            $("td:not(':last')", this).each(function () {
                $(`#${index}`).append(`<div>${this.textContent}</div>`);
            });
            index++;
        });

        $("#infoTable tr:odd", data).each(function () {
            console.log("RIGHT---" + this.textContent);
            $("#infoRight").append(`<div class="row col-10 offset-1 border bg-secondary text-white" style="margin-bottom:30px;border-radius:10px;"><div class="row col-6 border align-items-center" style="margin:0px;"><img src="image/f5.jpg" class="col w-100 h-w"/></div><div class="col" id="${index}"></div></div>`);
            $("td:not(':last')", this).each(function () {
                $(`#${index}`).append(`<div>${this.textContent}</div>`);
            });
            index++;
        });

    });

});


