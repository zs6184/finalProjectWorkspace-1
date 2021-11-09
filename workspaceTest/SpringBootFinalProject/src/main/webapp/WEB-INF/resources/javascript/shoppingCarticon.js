$(function(){
	$('.shoppingcartnum').hide();
	let userName = $('#sessionUsername').val();
	let iconNum = $('.shoppingcartnum p').text();
	let totalnum = testCartNum(userName);
	if(totalnum > 0 ){
		$('.shoppingcartnum').show();
		$('.shoppingcartnum p').text(totalnum);
	}
	
});

//購物車數量
function testCartNum(username) {
    var cookie = $.cookie(`cart${username}`);
    var testcarJSON = JSON.parse(cookie);
	let itemnum=0;
    for (let j in testcarJSON) {
        console.log('====');
        console.log(testcarJSON[j].id + ":" + testcarJSON[j].num);
		num = parseInt(testcarJSON[j].num);
		itemnum +=num
	}	
	return itemnum;
		
}