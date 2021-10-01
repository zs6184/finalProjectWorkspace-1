<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Test</title>
</head>
<body>
	<h1>歡迎<% out.print(request.getParameter("username"));%>光臨</h1>
	<!-- 歡迎null 光臨/自行輸入query String -->
</body>
</html>