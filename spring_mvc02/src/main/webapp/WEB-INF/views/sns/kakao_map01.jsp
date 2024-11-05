<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카카오 지도 연습-1</title>
</head>
<body>
	<!--  
	카카오 디벨로퍼 로그인 후 내 애플리케이션에서 애플리케이션 선택 후 javascript키 복사

-->
	<div id="map" style="width: 100%; height: 350px;"></div>
	<p>
		<button onclick="setDraggable(false)">지도 드래그 이동 끄기</button>
		<button onclick="setDraggable(true)">지도 드래그 이동 켜기</button>
	</p>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=59b6018633a06c096011fd8e47dc1417"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			// draggable: false, // 지도를 생성할때 지도 이동 및 확대/축소를 막으려면 draggable: false 옵션을 추가하세요
			level : 3
		// 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// 버튼 클릭에 따라 지도 이동 기능을 막거나 풀고 싶은 경우에는 map.setDraggable 함수를 사용합니다
		function setDraggable(draggable) {
			// 마우스 드래그로 지도 이동 가능여부를 설정합니다
			map.setDraggable(draggable);
		}
	</script>

</body>
</html>