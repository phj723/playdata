<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>🍪쿠키런 킹덤⚔</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <!-- 구글폰트 -->
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link
		href="https://fonts.googleapis.com/css2?family=Poor+Story&display=swap"
		rel="stylesheet">
<style type="text/css">
.header {
/* 	position: fixed; */
	top: 0px;
	left: 0px;
	width: 100%;
	height: 250px;
	background-position: center;
	background-size: cover;
}
.navbar{
	top: -51px;
	left: 0px;
	z-index: 10;
	color: black;
	background-color: rgba(245, 245, 245, 0.6);
	border: 0px;
}
* {
	font-family: 'Poor Story', cursive;
	font-size: 12pt;
	/* 	background-color: #fdf2c0; */
}
</style>
<script>
$(document).ready(function () {
    showBgImage();
});

function showBgImage() {
    var imgArray = new Array();
    imgArray[0] = "${root}/images/bg1.png";
    imgArray[1] = "${root}/images/bg2.png";
    imgArray[2] = "${root}/images/bg3.jpg";
    imgArray[3] = "${root}/images/bg4.jpg";
    
    var imgNum = Math.round(Math.random() * 4);
    var bgImg = document.getElementById('header');
    bgImg.style.backgroundImage = 'url(' + imgArray[imgNum] + ')';
}

function list(grade){
    var url = "${root}/cookies/list";
    url += "?grade="+grade;
    
    location.href=url;  
 }
</script>
</head>

<body>
<header class="header" id="header"></header>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${root}" style="font-size:15pt; font-weight:bold;">🍪 쿠키런 킹덤</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="${root}/cookies/list">쿠키 소개<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="${root}/cookies/list">모든 쿠키</a></li>
          <li><a href="javascript:list('에픽')">에픽 쿠키</a></li>
          <li><a href="javascript:list('레어')">레어 쿠키</a></li>
          <li><a href="javascript:list('커먼')">커먼 쿠키</a></li>
        </ul>
      </li>
      <li><a href="#">커뮤니티</a></li>
      <li><a href="https://game.devplay.com/coupon/ck/ko">쿠폰 입력</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>
</body>
</html>