// JavaScript source code
$(function() {
	//datepicker jQuery
//	$("input[name='adoptDate']").datepicker({
//		dateFormat: 'yy/mm/dd',
//		changeYear: true,
//		changeMonth: true
//	});
	//提交表單
//	$("form").on("submit",function(event){
//		//event.preventDefault();
//		console.log($(this).serialize());
//	});
	
});

//刪除折扣碼
function del(obj,id) {	
	$.ajax({
		method:'POST',
		url:'/NoXmlSpringMvcProject/back.deletecoupon',
		data:{couponId:id},
		success:function(){
		$(obj).parents("tr").remove();
		console.log('優惠碼刪除成功');
		},
		error:function(){
		console.log('優惠碼刪除失敗');
		}
	});
}

