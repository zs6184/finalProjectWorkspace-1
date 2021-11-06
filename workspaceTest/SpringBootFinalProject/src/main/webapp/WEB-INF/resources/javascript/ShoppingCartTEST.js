$(function () {
    //取出使用者帳號名稱
    var username = $('h2').text();
    //取出Cookie
    //判斷是否為空
    //var jsonstring = [{'id':'1','num':'2'},{'id':'2','num':'3'}];
    //var str = JSON.stringify(jsonstring);
    
    //var cookie = $.cookie('key');
    //var jsoncookie = JSON.parse(cookie);
    //console.log(jsoncookie);
    $('.center div').on('click', 'button', function () {
        var pid = $(this).closest('.center div').attr('id');
        if ($(this).hasClass('addtocart')) {
            //如果購物車為空
            var cookie = $.cookie(`cart${username}`);
            if (!cookie) {
                let item = [{ 'id': pid, 'num': 1 }];
                $.cookie(`cart${username}`, JSON.stringify(item));
                swal('已加入購物車', '數量:1', "success");
            } else {
                var cookie = $.cookie(`cart${username}`);
                var carJSONpart = JSON.parse(cookie);
                var same = false;
                //如果有加入過購物車 則數量加一
                for (let i in carJSONpart) {
                    if (carJSONpart[i].id == pid) {
                        carJSONpart[i].num++;
                        var num = carJSONpart[i].num;
                        same = true;
                        swal('已加入購物車', '數量:' + num++, "success");
                        break;
                    };
                }
                //如果沒有加入過購物車則加入
                if (!same) {
                    let item = { 'id': pid, 'num': 1 };
                    carJSONpart.push(item);
                    swal('已加入購物車', '數量:1', "success");
                }
                $.cookie(`cart${username}`, JSON.stringify(carJSONpart));
            }
            testCartNum(username);
        };
		
        if ($(this).hasClass('pluscartitem')) {
            carjsonpart(username, pid, '+');
            testCartNum(username);

        };
        if ($(this).hasClass('reducecartitem')) {
            carjsonpart(username, pid, '-');
            testCartNum(username);
        };
            

    });//事件結束

});
//控制台印出目前購物車狀況
function testCartNum(username) {
    var cookie = $.cookie(`cart${username}`);
    var testcarJSON = JSON.parse(cookie);
    for (let j in testcarJSON) {
        console.log('====');
        console.log(testcarJSON[j].id + ":" + testcarJSON[j].num);
    }
}
//加減方法
function carjsonpart(username, pid, state) {
    var cookie = $.cookie(`cart${username}`);
    var carJSONpart = JSON.parse(cookie)
    //遍歷所有cooki進行加減
    for (var i in carJSONpart) {
        if (carJSONpart[i].id == pid) {
            switch (state) {
                case '+':
                    let pluseNum = carJSONpart[i].num++;
                    let pluseNum1 = parseInt(pluseNum)+1;
                    swal('數量增加', '數量:' + pluseNum1, "success");
                    $.cookie(`cart${username}`, JSON.stringify(carJSONpart));
                    break;
                case '-':
                    //購物車數量減少
                    let reduceNum0 = carJSONpart[i].num;
                    if (reduceNum0 > 1) {
                        let reduceNum = carJSONpart[i].num--;
                        let reduceNum1 = parseInt(reduceNum) - 1;
                        swal('數量減少', '數量:' + reduceNum1, "success");
                        $.cookie(`cart${username}`, JSON.stringify(carJSONpart));
                        break;
                    } else {
                        //當數量為一時刪除購物項
                        swal({
                            title: "數量已經是1",
                            text: "不買可惜",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            cancelButtonText: "明智選擇！",
                            confirmButtonText: "就讓它可惜！",
                            closeOnConfirm: false
                        },
                            function () {
                                carJSONpart.splice(i,1);
                                swal("已移除！", "從購物車移除。", "success");
                                $.cookie(`cart${username}`, JSON.stringify(carJSONpart));
                            });
                    }
                    break;
            }
            break;
        };
    }
}