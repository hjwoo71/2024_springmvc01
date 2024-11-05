<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result4</title>
</head>
<body>
	 <h2>결과 : result4.jsp</h2>
	 <h2> 차이름들</h2>
	 <c:forEach var="k" items="${carName}" >
	 	<h3>${k}</h3>
	 </c:forEach>
	 <hr>
</body>
</html>