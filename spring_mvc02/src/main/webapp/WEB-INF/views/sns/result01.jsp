<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js" crossorigin="anonymous"></script>
</head>
<body>
	<h2>카카오 로그인 성공</h2>
	<div id="result"></div>
	
	<!-- 계정과 함께 로그 아웃 -->
	<a href="https://kauth.kakao.com/oauth/logout?client_id=d6088e3d9ffeb3d60a2bb45793e040d9&logout_redirect_uri=http://localhost:8080/kakaologout">로그아웃</a>
	
	<script type="text/javascript">
	// $(document).ready(function(){  });
	$(function(){
		$("#result").empty();
		$.ajax({
			url : "/kakaoUserInfo",
			method : "post",
			dayaType : "json",
			success : function(data){
			//	console.log(data);
						
				let str = "<li>닉네임 : ${nickname} </li> ";
				    str += "<li>이미지 : <img src= '${profileImage}' ></li> ";
				    
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