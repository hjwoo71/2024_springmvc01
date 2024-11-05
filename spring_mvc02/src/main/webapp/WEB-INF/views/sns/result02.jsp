<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	<h2>네이버 로그인 성공</h2>
	<div id="result"></div>
	
	<!-- 로그 아웃 -->
	<a href="/naverLogout">로그아웃</a>
	
	<script type="text/javascript">
	// $(document).ready(function(){  });
	$(function(){
		$("#result").empty();
		$.ajax({
			url : "/naverUserInfo",
			method : "post",
			dayaType : "json",
			success : function(data){
				// json 이용
				let name = data.response.name;
				let nickname = data.response.nickname;
				let email = data.response.email;
				let profile_image = data.response.profile_image;
				
				let str = "<li>" + name + "</li>";
				    str +=  "<li>" + nickname + "</li>";
				    str +=  "<li>" + email + "</li>";
				    str +=  "<img src="+profile_image + " style = 'width:150px'>";
				    
				$('#result').append(str);
			},
			error :function(){
				alert("읽기실패");
			}
		});
	});
	
	
	</script>
</body>
</html>