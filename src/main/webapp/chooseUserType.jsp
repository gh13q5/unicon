<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="controller.info.UserSessionUtils"%>
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
<link rel="stylesheet" href="css/main.css">

<title>찜꽁 - 회원가입</title>

</head>
<body>
	<div id="header" align="center">
		<a href="<c:url value='/main'></c:url>"> <img
			src="images/title_logo.png" id="title-logo">
		</a>
		<hr id="title-bar">
	</div>
	<div id="contents" align="center">
		<div id="chooseUserType-title" style="margin: 2%;">
			<h2>회원가입</h2>
			<p>회원 유형을 선택해주세요.</p>
		</div>
		<a href="<c:url value="/userregister"></c:url>"> <img
			class=userImg src="./images/commonUserImg.jpg"
			style="margin-right: 2%; border: 2px solid white; border-radius: 30px;"></img>
		</a> <a href="<c:url value="/companyregister"></c:url>"> <img
			class=userImg src="./images/companyUserImg.jpg"
			style="margin-left: 2%; border: 2px solid white; border-radius: 30px;"></img>
		</a>
	</div>
	<div id="footer">
		<hr class="haveMargin" id="title-bar" style="margin-top: 4%;">
		<p class="text-center" align="center">
			<small><strong>팀명</strong></small><br> <small>팀 :
				UNI-CON ㆍ 소속 : 동덕여자대학교 ㆍ 전화 : 02-123-1234</small><br> <small>Copyrightⓒ
				test.com All rights reserved.</small>
		</p>
	</div>

</body>
</html>


