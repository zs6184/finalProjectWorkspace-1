// JavaScript source code
$(function() {
//datepicker jQuery
	$("input[name='adoptDate']").datepicker({
		dateFormat: 'yy/mm/dd',
		changeYear: true,
		changeMonth: true
	});

//一開始從DB取得資料
//	$.ajax({
//		type: "GET",
//		url: "/TestProjectWorkspace/Servlet/PetInfoLoad",
//		dataType: "JSON",
//		contentType: "application/json",
//
//		error: function() {
//			console.log("get data failed");
//		},
//		success: function(data) {   
//			for (var i = 0; i < data.pets.length; i++) {
//				$("#infoTable tbody").append(
//					`<tr>
//                         <td>${data.pets[i].petId}</td>
//                         <td class="pname">${data.pets[i].petName}</td>
//                         <td class="category">${data.pets[i].category}</td>
//                         <td class="species">${data.pets[i].species}</td>
//                         <td class="sex">${data.pets[i].sex}</td>
//                         <td>${data.pets[i].age}</td>
//                         <td>${data.pets[i].fixStatus}</td>
//                         <td>${data.pets[i].adoptStatus}</td>
//                         <td>${data.pets[i].cusId}</td>
//                         <td>${data.pets[i].cusName}</td>
//                         <td>${data.pets[i].adoptDate}</td>
//                         <td>${data.pets[i].note}</td>
//                         <td>
//                         	<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#petInfoAdd">更新</button>
//                         	<button type="button" class="btn btn-danger delete" onclick="del(this)">刪除</button>
//                         </td>
//                     </tr>`
//				);
//			}
//		}
//	});
	
//阻止提交表單並顯示於console中
//	$("form").on("submit",function(event){
//		event.preventDefault();
//		console.log($(this).serialize());
//	});


//變更按鈕對應表單的action
$("#insertBtn").click(function(){
	$("#modalForm").attr("action","insertPetInfo.controller");
	$("#modalForm #idSection").prop("hidden","hidden");
	$("#modalForm input,textarea,select").val("");
});


});

//跳出確認刪除對話框

var ID; 
var record;

function delAlert(obj){
	record=$(obj);
	ID =$(obj).parent("td").siblings(".ID").text();
	var NAME = $(obj).parent("td").siblings(".NAME").text();
	console.log(ID);
	$("#alertDialog").html(`確認刪除${ID} : ${NAME} ?`);
}


//刪除資料欄(連到資料庫)
function del() {
	console.log(ID);
			$.ajax({
			type:"GET",
			url:"/NoXmlSpringMvcProject/deletebyid.controller",
			datatype:"JSON",
			contentType: "application/json",
			data:{"id":`${ID}`}
		})
	record.parents("tr").remove();
}

//使用更新按鈕選取此筆資料
function select(obj){
	$("#modalForm").attr("action","updateone.controller");
	$("#modalForm #idSection").removeAttr("hidden");
	ID =$(obj).parent("td").siblings(".ID").text();
	console.log(ID);
		$.ajax({
			type:"GET",
			url:"/NoXmlSpringMvcProject/selectbyid.controller",
			datatype:"JSON",
			contentType: "application/json",
			data:{"id":`${ID}`},
			success:function(result){
				console.log("Success");
				var parsed = jQuery.parseJSON(result);
				$("#modalForm #petId").val(ID);
				$("#modalForm #petName").val(parsed.petName);
				$("#modalForm #category").val(parsed.category);
				$("#modalForm #species").val(parsed.species);
				$("#modalForm #sex").val(parsed.sex);
				$("#modalForm #age").val(parsed.age);
				$("#modalForm #fixStatus").val(parsed.fixStatus);
				$("#modalForm #adoptStatus").val(parsed.adoptStatus);
				$("#modalForm #cusId").val(parsed.cusId);
				$("#modalForm #cusName").val(parsed.cusName);
				$("#modalForm #adoptDate").val(parsed.adoptDate);
				$("#modalForm #note").val(parsed.note);
			},
			error:function(){
				console.log("failed to get data");
			}
		})
	}

