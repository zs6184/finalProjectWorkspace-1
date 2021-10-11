// JavaScript source code


$(function () {
<<<<<<< HEAD
    //stickybar
    window.onscroll = function () { stickTop() };
    var topbar = document.getElementById("topbar");
    var distance = topbar.offsetTop; //æŠ“topbaré›¢é é¢é ‚éƒ¨è·é›¢


    function stickTop() {
        if (window.pageYOffset >= distance) { //åç§»é‡>distanceæ™‚å¢žåŠ stick
=======
    
    //»s³ystickybar®ÄªG
    window.onscroll = function () { stickTop() };
    var topbar = document.getElementById("topbar");
    var distance = topbar.offsetTop; //¨ú±otopbar¤W¤è°¾²¾¶q


    function stickTop() {
        if (window.pageYOffset >= distance) { //¥H­¶­±Y¶b°¾²¾¶q¬°§PÂ_±ø¥ó
>>>>>>> 8b58d75e16e8106c9211067c0685275e26faeb5f
            topbar.classList.add("sticky");
        } else {
            topbar.classList.remove("sticky");
        }
    }


<<<<<<< HEAD
//å°‡å–å¾—backPetInfoè³‡æ–™è¼‰å…¥selecté¸é …ä¸­
    $.get("BackPetInfo.jsp", function (data) {
=======
    //±N¨ú±obackPetInfo¸ê®Æ¸ü¤Jselect¿ï¶µ¤¤
    $.get("backPetInfo.html", function (data) {
>>>>>>> 8b58d75e16e8106c9211067c0685275e26faeb5f

        //¿ï¶µÄæ

        //¯Âºé¨ú­È«á¶ñ¤Jªº°µªk¡A¨S¦³¿z­«½Æ­È(­è¦n¤]¨S¦³­«½Æ­È)
        $(".category", data).each(function () {
            $("#category").append(`<option value="${this.textContent}">${this.textContent}</option>`);
            console.log(this.textContent);
        });

<<<<<<< HEAD
//ä½¿ç”¨é™£åˆ—è£æŠ“åˆ°çš„æ‰€æœ‰é¸é …(ä¸‰å€‹F)ï¼Œå› ç‚ºæœ‰é‡è¤‡å€¼ï¼Œæ‰€ä»¥ä¸Ÿé€²Setä¸­ç¯©æŽ‰é‡è¤‡å€¼
=======
        //¨Ï¥Î°}¦C¸Ë§ì¨ìªº©Ò¦³¿ï¶µ(¤T­ÓF)¡A¦]¬°¦³­«½Æ­È¡A©Ò¥H¥á¶iSet¤¤¿z±¼­«½Æ­È
>>>>>>> 8b58d75e16e8106c9211067c0685275e26faeb5f
        var sexArr = new Array;
        $(".sex", data).each(function () {
            sexArr.push(this.textContent);
        });
        var sexArrSet = new Set(sexArr);
        var sexUnique = [...sexArrSet]; //¨Ï¥Î®i¶}¹Bºâ²Å[...]Âà´«¦¨¬°°}¦C
        console.log("uniqyeArr = " + sexUnique);

        sexUnique.forEach(function (v, i) { //forEach()¬OjsªºAPI¡AK-V­Èvalue¬O²Ä¤@­Ó°Ñ¼Æ¡Aindex¬O²Ä¤G­Ó
            $("#sex").append(`<option value="${v}">${v}</option>`)
            console.log("value = " + v);
        });

<<<<<<< HEAD
//è³‡è¨Šæ¬„
=======
        //¸ê°TÄæ
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


