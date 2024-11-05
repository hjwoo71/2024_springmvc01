<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 명 록 II error 페이지</title>
</head>
<body>
	<h1>공 사 중</h1>
	<form action="${pageContext.request.contextPath}/GuestBook2Controller" method="post">
		<input type="submit" value="GuestBook2">
		<input type="hidden" name="cmd" value="/gb2_list">
	</form>	
</body>
</html>