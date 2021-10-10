<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pet Insert</title>
</head>
<body>
	<div class="modal fade" id="petInfoAdd">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title" id="petModalTitle">寵物資料</h3>
				</div>
				<form class="add" action="insertPetInfo.controller" method="POST">
					<div class="modal-body">
						<div class="row">
							<div class="text-center" id="mainbox">
								<fieldset>
									<legend>基本資料</legend>
									<div>
										<label for="petName" class=""><span>寵物名</span></label> <input
											type="text" id="petName" name="petName" />
									</div>
									<div>
										<label for="category" class=""><span>類別</span></label> <input
											type="text" id="category" name="category" />
									</div>
									<div>
										<label for="species" class=""><span>品種</span></label> <input
											type="text" id="species" name="species" />
									</div>
									<div>
										<label for="sex" class=""><span>性別</span></label> <select
											id="sex" name="sex">
											<option value="M">Male</option>
											<option value="F">Female</option>
										</select>
									</div>
									<div>
										<label for="age" class=""><span>年齡</span></label> <select
											id="age" name="age">
											<option value="幼年">幼年</option>
											<option value="成年">成年</option>
											<option value="老年">老年</option>
										</select>
									</div>
									<div>
										<label for="fixStatus" class=""><span>絕育狀態</span></label> <select
											id="fixStatus" name="fixStatus">
											<option value="已結紮">已結紮</option>
											<option value="未結紮">未結紮</option>
										</select>
									</div>
									<div>
										<label for="adoptStatus" class=""><span>領養狀態</span></label> <select
											id="adoptStatus" name="adoptStatus">
											<option value="已領養">已領養</option>
											<option value="未領養">未領養</option>
										</select>
									</div>
								</fieldset>
								<hr />
								<fieldset>
									<legend>領養資料</legend>
									<div>
										<label for="cusId" class=""><span>領養會員ID</span></label> <input
											type="text" id="cusId" name="cusId"
											oninput="value=value.replace(/[^\d]/g,'')" />
									</div>
									<div>
										<label for="cusName" class=""><span>領養會員姓名</span></label> <input
											type="text" id="cusName" name="cusName" />
									</div>
									<div>
										<label for="adoptDate" class=""><span>領養日期</span></label> <input
											type="date" id="adoptDate" name="adoptDate"
											data-provide="datepicker" />
									</div>
								</fieldset>
								<hr />
								<fieldset>
									<legend>備註</legend>
									<textarea placeholder="請輸入備註..." id="note" name="note"></textarea>
								</fieldset>
								<div class="modal-footer">
									<div class="container-fluid">
										<div class="row justify-content-start">
											<div class="offset-4 col-4">
												<button type="submit" class="btn btn-primary"
													name="tempInfo">確認</button>
												<button type="reset" class="btn btn-secondary"
													data-bs-dismiss="modal">取消</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>