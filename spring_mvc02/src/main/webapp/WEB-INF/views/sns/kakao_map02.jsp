<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	navigator.geolocation.getCurrentPosition(function(position) {
		const lat = position.coords.latitude;
		const lng = position.coords.longitude;
		geo_map(lat, lng)
	});
	console.log("1 : "+lat+","+ lng)
</script>
</head>
<body>

    <!-- 지도를 표시할 div 입니다 -->
    <div id="map" style="width: 100%; height: 350px;"></div>

    <script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=59b6018633a06c096011fd8e47dc1417"></script>
	<script>
	    function geo_map(lat, lng){
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = {
				center : new kakao.maps.LatLng(lat, lng), // 지도의 중심좌표
				// draggable: false, // 지도를 생성할때 지도 이동 및 확대/축소를 막으려면 draggable: false 옵션을 추가하세요
				level : 2
			// 지도의 확대 레벨
			};

			var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		}   
	</script>
</body>
</html>