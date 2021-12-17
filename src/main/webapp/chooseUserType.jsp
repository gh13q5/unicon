<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- CSS -->
<link rel="stylesheet" href="css/chooseUserType.css">

<title>찜꽁 - 이용자 선택</title>

</head>
<body>
	<div id="header" align="center">
		<h1>
			<br>찜꽁
		</h1>
		<hr>
	</div>
	<div id="contents" align="center">
		<div id=chooseUserType-title>회원가입</div>
		<a href ="<c:url value="/userregister"></c:url>">
			<img class=userImg src="./images/commonUserImg.png"></img> 
		</a>	
		<a href="<c:url value="/companyregister"></c:url>"> 
			<img class=userImg src="./images/companyUserImg.png"></img>
		</a>
	</div>
	<div id="footer">
		<hr>
		<p class="text-center" align="center">
			<small><strong>업체명</strong></small><br> <small>대표 : 홍길동
				ㆍ 주소 : 사거리 ㆍ 사업자등록번호:123-12-12345 ㆍ 전화 : 02-123-1234</small><br> <small>Copyrightⓒ
				test.com All rights reserved.</small>
		</p>
	</div>

</body>
</html>


