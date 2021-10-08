<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginSystem</title>
</head>
<body>
	<h3>Login System</h3>
	<form action="checkLogin.controller" method="post">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="userName"/></td>
				<td>${errors.name}</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="userPwd"/></td>
				<td>${errors.pwd}</td>
			</tr>
			<tr>
				<td><input type="submit" value="Login"/></td>
				<td>${errors.msg}</td>
			</tr>
		</table>
	</form>
</body>
</html>