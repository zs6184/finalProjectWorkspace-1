<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TestJSTL</title>
</head>
<body>
	<h1>Test for JSTL in WebAPP</h1><hr/>
	<c:choose>
		<c:when test="${param.username !=null}">
			<h1>歡迎${param.username }光臨</h1>
		</c:when>
		<c:otherwise>
			<h1>歡迎無名氏光臨</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>

<!-- user name自行輸入query String -->
<!-- 經測試MAVEN下載的JSTL套件會在這裡生效，無須再在WEB-INF的lib放入相關JAR檔 -->