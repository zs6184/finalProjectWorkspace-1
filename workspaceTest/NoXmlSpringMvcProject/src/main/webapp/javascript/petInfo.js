// JavaScript source code


$(function () {
<<<<<<< HEAD
    //stickybar
    window.onscroll = function () { stickTop() };
    var topbar = document.getElementById("topbar");
    var distance = topbar.offsetTop; //抓topbar離頁面頂部距離


    function stickTop() {
        if (window.pageYOffset >= distance) { //偏移量>distance時增加stick
=======
    
    //�s�ystickybar�ĪG
    window.onscroll = function () { stickTop() };
    var topbar = document.getElementById("topbar");
    var distance = topbar.offsetTop; //���otopbar�W�谾���q


    function stickTop() {
        if (window.pageYOffset >= distance) { //�H����Y�b�����q���P�_����
>>>>>>> 8b58d75e16e8106c9211067c0685275e26faeb5f
            topbar.classList.add("sticky");
        } else {
            topbar.classList.remove("sticky");
        }
    }


<<<<<<< HEAD
//將取得backPetInfo資料載入select選項中
    $.get("BackPetInfo.jsp", function (data) {
=======
    //�N���obackPetInfo��Ƹ��Jselect�ﶵ��
    $.get("backPetInfo.html", function (data) {
>>>>>>> 8b58d75e16e8106c9211067c0685275e26faeb5f

        //�ﶵ��

        //�º���ȫ��J�����k�A�S���z���ƭ�(��n�]�S�����ƭ�)
        $(".category", data).each(function () {
            $("#category").append(`<option value="${this.textContent}">${this.textContent}</option>`);
            console.log(this.textContent);
        });

<<<<<<< HEAD
//使用陣列裝抓到的所有選項(三個F)，因為有重複值，所以丟進Set中篩掉重複值
=======
        //�ϥΰ}�C�˧�쪺�Ҧ��ﶵ(�T��F)�A�]�������ƭȡA�ҥH��iSet���z�����ƭ�
>>>>>>> 8b58d75e16e8106c9211067c0685275e26faeb5f
        var sexArr = new Array;
        $(".sex", data).each(function () {
            sexArr.push(this.textContent);
        });
        var sexArrSet = new Set(sexArr);
        var sexUnique = [...sexArrSet]; //�ϥήi�}�B���[...]�ഫ�����}�C
        console.log("uniqyeArr = " + sexUnique);

        sexUnique.forEach(function (v, i) { //forEach()�Ojs��API�AK-V��value�O�Ĥ@�ӰѼơAindex�O�ĤG��
            $("#sex").append(`<option value="${v}">${v}</option>`)
            console.log("value = " + v);
        });

<<<<<<< HEAD
//資訊欄
=======
        //��T��
>>>>>>> 8b58d75e16e8106c9211067c0685275e26faeb5f
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


