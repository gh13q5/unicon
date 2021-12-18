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
<title>예약자 목록 조회</title>
<style>
@font-face {
	font-family: 'CookieRun-Regular';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

body {
	padding: 3%;
	font-family: 'CookieRun-Regular';
}
</style>
</head>
<body>
	<div id="header" align="center">
		<a href="<c:url value='/main'></c:url>"> <img
			src="images/title_logo.png" id="title-logo"
			style="width: 20%; height: 20%; margin-top: 2%;">
		</a>
		<hr id="title-bar" style="width:90%;">
		<div id="user-table" style="margin:5%;">
			<c:forEach var="user" items="${userList }" varStatus="status">
				<div class="row" style="margin-bottom:2%;">${status.count }&nbsp;&nbsp;&nbsp;${user.name }&nbsp;/&nbsp;${user.email }</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>


