//刪除折扣碼
function del(obj,id) {	
	$.ajax({
		method:'POST',
		url:'/back.deletecoupon',
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