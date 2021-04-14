<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<c:set var="root" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>Home</title>
<style>
@keyframes bomb {
  from { color : white;}
  to { color : black;}
}
#intro {
	animation-name : bomb;
	animation-duration : 5s;
}
</style>
</head>
<body>
	<table style="margin: 0 auto;">
		<tr>
			<td style="width: 280px; text-align: center;">
				<img id="intro-img" alt="kingdom" src="${root}/images/logo.png">
			</td>
			<td id="intro">마녀의 오븐에서 탈출한 쿠키들이 도착한 곳은 알고보니 저 먼 고대에 부흥했던 왕국! <br> 
				사라진 고대영웅 쿠키들의 흔적과 지금은 아득히 잊혀진 과거의 이야기를 찾아 떠나보세요!
			</td>
		</tr>
	</table>

	<div id="carousel" class="carousel slide" data-ride="carousel"
		style="width: 846px; margin: 0 auto; margin-top: 30px;">
		<ol class="carousel-indicators">
			<li data-target="#carousel" data-slide-to="0" class="active"></li>
			<li data-target="#carousel" data-slide-to="1"></li>
			<li data-target="#carousel" data-slide-to="2"></li>
			<li data-target="#carousel" data-slide-to="3"></li>
			<li data-target="#carousel" data-slide-to="4"></li>
			<li data-target="#carousel" data-slide-to="5"></li>
			<li data-target="#carousel" data-slide-to="6"></li>
			<li data-target="#carousel" data-slide-to="7"></li>
		</ol>
		<div class="carousel-inner" id="carousel-inner">
			<div class="item active">
				<iframe width="846" height="528"
					src="https://www.youtube.com/embed/OwbEBrA63JM"
					title="YouTube video player" frameborder="0"
					allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
					allowfullscreen></iframe>
			</div>
			<div class="item">
				<img alt="kingdom1" id="crsl-img"
					src="https://play-lh.googleusercontent.com/jTDZjM4mEelmxQBXG7IEk94nv7jOFWOmuSNtcVPxdasa-2GDrd_ajAhIHpUUNEIC5eM=w1536-h754-rw">
			</div>
			<div class="item">
				<img alt="kingdom2" id="crsl-img"
					src="https://play-lh.googleusercontent.com/jNRntVHZV6CzQPH4iBCJue5tME4NyzykVPJxKoezcKyh_BgAsGOkwr7TVEFan9kdrhE=w1536-h754-rw">
			</div>
			<div class="item">
				<img alt="kingdom3" id="crsl-img"
					src="https://play-lh.googleusercontent.com/e-0FQjTWqKFjYe9cLjugNTp2XFq_hIkdW3p2W6wQmBrDdqokWZm-fcPnJskpZ_Q4KHY=w1536-h754-rw">
			</div>
			<div class="item">
				<img alt="kingdom4" id="crsl-img"
					src="https://play-lh.googleusercontent.com/URedl6p1GPUlqS7TCcqeXLPEYYFAbRHCJQrKq9SGQrBuM2jo3ZTDsb68AB0gESfNPwjh=w1536-h754-rw">
			</div>
			<div class="item">
				<img alt="kingdom5" id="crsl-img"
					src="https://play-lh.googleusercontent.com/eSq5jmbCsIKlIOzBGH1Wu3b8w_p3u6zvGQEYqeWc0Po47lwBCPXB9e7OCh4jxgNflJg=w1536-h754-rw">
			</div>
			<div class="item">
				<img alt="kingdom6" id="crsl-img"
					src="https://play-lh.googleusercontent.com/3bAxGhlno95kJTnOh34IHfCRXOiOYxAEq4UEgTPtOFBQCwsc3SAfzHIff5WiDueff5A=w1536-h754-rw">
			</div>
		</div>
		<a class="left carousel-control" href="#carousel" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#carousel" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>

	<table style="margin: 0 auto; margin-top: 30px;">
		<tr>
			<td style="width: 100px; text-align: center;" colspan="2">제작</td>
			<td style="width: 100px;" colspan="2">데브시스터즈</td>
		</tr>
		<tr>
			<td style="width: 100px; text-align: center;" colspan="2">배급</td>
			<td colspan="2">데브시스터즈</td>
		</tr>
		<tr>
			<td style="width: 100px; text-align: center;" colspan="2">등급</td>
			<td colspan="2">전체 이용가</td>
		</tr>
		<tr>
			<td style="width: 100px; text-align: center;" colspan="2">출시</td>
			<td colspan="2">2021.1.21</td>
		</tr>
		<tr style="height: 100px; text-align: center;">
			<td><a href="https://www.cookierun-kingdom.com/ko/"> <img
					src="${root}/images/cookierun.jpg" style="width: 50px;">
			</a></td>
			<td><a href="https://www.youtube.com/channel/UCAOTbNctDD0Hg5MG0enQpMQ">
					<img src="${root}/images/youtube.png" style="width: 50px;">
			</a></td>
			<td><a href="https://twitter.com/CRKingdomKR"> 
					<img src="${root}/images/twitter.jpg" style="width: 50px;">
			</a></td>
			<td><a href="https://www.facebook.com/CRKingdomKR/"> 
					<img src="${root}/images/facebook.jpg" style="width: 50px;">
			</a></td>
		</tr>
	</table>
</body>
</html>
