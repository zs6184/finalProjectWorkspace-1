// JavaScript source code
$(function () {

    //�����ܾ�
    $("input[name='adoptDate']").datepicker({
        dateFormat: 'yy/mm/dd',
        changeYear: true,
        changeMonth: true
    });

<<<<<<< HEAD

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
=======
    $("#renew").click(function () {
        del(this);
    });

});

//�R����ƨƥ�
function del(obj) {
    $(obj).parents("tr").remove();
}


function renewData() {
    $.ajax({
        type: "GET",
        url: "/PetInfoLoad",
        dataType: "text",
        success: function (data, textStatus) {
            alert(data);
        },
    });
}
>>>>>>> 8b58d75e16e8106c9211067c0685275e26faeb5f

/*function openEdit() {
    window.open("petInfoAdd.html", "EditWindow", "height=600,width=800,top=200,left=600");
}*/