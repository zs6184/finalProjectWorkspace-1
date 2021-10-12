// JavaScript source code
$(function() {
	//datepicker jQuery
	$("input[name='adoptDate']").datepicker({
		dateFormat: 'yy/mm/dd',
		changeYear: true,
		changeMonth: true
	});

	//一開始從DB取得資料
	$.ajax({
		type: "GET",
		url: "/TestProjectWorkspace/Servlet/PetInfoLoad",
		dataType: "JSON",
		contentType: "application/json",

		error: function() {
			console.log("get data failed");
		},
		success: function(data) {   
			for (var i = 0; i < data.pets.length; i++) {
				$("#infoTable tbody").append(
					`<tr>
                         <td>${data.pets[i].petId}</td>
                         <td class="pname">${data.pets[i].petName}</td>
                         <td class="category">${data.pets[i].category}</td>
                         <td class="species">${data.pets[i].species}</td>
                         <td class="sex">${data.pets[i].sex}</td>
                         <td>${data.pets[i].age}</td>
                         <td>${data.pets[i].fixStatus}</td>
                         <td>${data.pets[i].adoptStatus}</td>
                         <td>${data.pets[i].cusId}</td>
                         <td>${data.pets[i].cusName}</td>
                         <td>${data.pets[i].adoptDate}</td>
                         <td>${data.pets[i].note}</td>
                         <td>
                         	<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#petInfoAdd">更新</button>
                         	<button type="button" class="btn btn-danger delete" onclick="del(this)">刪除</button>
                         </td>
                     </tr>`
				);
			}
		}
	});
	
	//提交表單
	$("form").on("submit",function(event){
		//event.preventDefault();
		console.log($(this).serialize());
	});
	
});

//刪除資料欄(僅畫面上的刪除尚未連接資料庫)
function del(obj,id) {
	console.log(id);
	//$(obj).parents("tr").remove();
	
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

//提交新增表單
//function addInfo(){
//	$.ajax({
//		type:"POST",
//		dataType:"JSON",
//		url:"/TestProjectWorkspace/Servlet/PetInfoInsert",
//		data:$(this).serialize(),
//		success:function(res){
//			console.log("POST SUCCESS")
//			
//		}
//	});
//}
