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
<link rel="stylesheet" href="css/mypage.css">
<link rel="stylesheet" href="css/main.css">
<style>
div#info-title {
	width: 40%;
	height: 80px;
	margin: auto;
	padding-top: 1.5%;
	border: 3px solid #5fc8d7;
	border-radius: 10px;
	text-align: center;
	color: #5fc8d7;
	font-weight: bolder;
	text-align: center;
	color: #5fc8d7;
	font-weight: bolder;
	font-size: 200%;
}
</style>

<title>찜꽁 - 마이 페이지</title>
</head>
<body>
	<div id="header" align="center">
		<a href="<c:url value='/main'></c:url>"> <img
			src="images/title_logo.png" id="title-logo">
		</a>
		<hr id="title-bar">
		<div class="container-fluid" style="margin-top: 2%;">
			<div class="row">
				<div class="col-2">
					<div class="accordion" id="accordionExample">
						<div class="accordion-item">
							<h2 class="accordion-header" id="headingOne">
								<button class="accordion-button" type="button"
									data-bs-toggle="collapse" data-bs-target="#collapseOne"
									aria-expanded="true" aria-controls="collapseOne">카테고리</button>
							</h2>
							<div id="collapseOne" class="accordion-collapse collapse show"
								aria-labelledby="headingOne" data-bs-parent="#accordionExample">

								<div class="list-group">

									<a
										href="<c:url value='/category'><c:param name='category' value='0' /></c:url>"
										class="list-group-item list-group-item-action"
										aria-current="true"> 스포츠 </a> <a
										href="<c:url value='/category'><c:param name='category' value='1' /></c:url>"
										class="list-group-item list-group-item-action">퍼즐</a> <a
										href="<c:url value='/category'><c:param name='category' value='2' /></c:url>"
										class="list-group-item list-group-item-action">롤플레잉</a> <a
										href="<c:url value='/category'><c:param name='category' value='3' /></c:url>"
										class="list-group-item list-group-item-action">시뮬레이션</a> <a
										href="<c:url value='/category'><c:param name='category' value='4' /></c:url>"
										class="list-group-item list-group-item-action">액션</a> <a
										href="<c:url value='/category'><c:param name='category' value='5' /></c:url>"
										class="list-group-item list-group-item-action">음악</a> <a
										href="<c:url value='/category'><c:param name='category' value='6' /></c:url>"
										class="list-group-item list-group-item-action">보드</a> <a
										href="<c:url value='/category'><c:param name='category' value='7' /></c:url>"
										class="list-group-item list-group-item-action">FPS</a> <a
										href="<c:url value='/category'><c:param name='category' value='8' /></c:url>"
										class="list-group-item list-group-item-action">멀티플레이어</a> <a
										href="<c:url value='/category'><c:param name='category' value='9' /></c:url>"
										class="list-group-item list-group-item-action">솔로플레이어</a>

								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="col-8" style="padding: 3%;">
					<div id=contents align="center">
						<div id="info-title" style="margin-bottom: 5%;">회원 정보</div>
						<form class="form-horizontal" role="form" method="post">
							<div id="makeItcenter" style="margin-bottom: 5%;">
								<div class="form-group" id="userNameID">
									<h2>
										<b>${findUser.name} &nbsp;</b><span style="color: gray">${findUser.id}</span>
									</h2>
								</div>
							</div>
							<div class="row justify-content-center">
								<div class="col-4">
									<h5 class="informaton">Email</h5>
								</div>
								<div class="col-4">
									<p>${findUser.email}</p>
								</div>
							</div>
							<div class="form-group" id="phoneNumber">
								<div class="row justify-content-center">
									<div class="col-4">
										<h5 class="informaton">전화번호</h5>
									</div>
									<div class="col-4">
										<p>${findUser.phone_number}</p>
									</div>
								</div>
							</div>
							<div class="form-group" id="birthday">
								<div class="row justify-content-center">
									<div class="col-4">
										<h5 class="informaton">생년월일</h5>
									</div>
									<div class="col-4">
										<p>${findUser.birthDay}</p>
									</div>
								</div>
							</div>
							<div class="form-group" id="gender">
								<div class="row justify-content-center">
									<div class="col-4">
										<h5>성별</h5>
									</div>
									<div class="col-4">
										<p>
											<c:if test="${findUser.gender eq 0}">
												<c:out value="남성" />
											</c:if>
											<c:if test="${findUser.gender eq 1}">
												<c:out value="여성" />
											</c:if>
											<c:if test="${findUser.gender eq 2}">
												<c:out value="기타" />
											</c:if>
										</p>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div id="row" align="right"
						style="margin-right: 13%; margin-top: 5%;">
						<input type="button" class="btn btn-warning" value="회원 정보 수정"
							onClick="#">
					</div>
					<div id="reservation-box" style="padding: 5%;">
						<div align="left" style="margin-left: 2%; margin-bottom: 5%;">
							<h4>
								<b>예약 게임 목록</b>
							</h4>
						</div>
						<!-- 예약 게임 -->
						<c:forEach var="Rgame" items="${findrv}" varStatus="status">
							<c:choose>
								<c:when test="${status.index % 4  eq 0}">
									<div class="row" align="center">
								</c:when>
								<%-- <c:when test="${status.count eq 0}">
								<div class="row" align="center">
							</c:when> --%>
							</c:choose>
							<%-- <a href="<c:url value='/game'>
            			<c:param name='game_id' value='${Cgame.id}' /></c:url>"> --%>
							<div class="col">
								<a
									href="<c:url value='/game'>
							<c:param name='gameId' value='${Rgame.game_id}' /></c:url>"
									class="card-link">
									<div class="card reservate" style="width: 15rem;">
										<c:set var="image"
											value="${fn:split(Rgame.image_address,',')}" />
										<img
											src="<c:url value='/images/${Rgame.company_id}/${image[0]}' />"
											class="card-img-top" alt="...">
										<div class="card-body">
											<h5 class="card-title">
												<c:out value="${Rgame.title}"></c:out>
											</h5>
											<div class="card-body">
												<p class="card-text">
													<c:out value="${Rgame.description}" escapeXml="false"></c:out>
												</p>
											</div>
											<p class="card-date">
												<small class="text-muted">~<c:out
														value="${Rgame.end_date}"></c:out></small>
											</p>
										</div>
									</div> </a>
							</div>
							<c:choose>
								<c:when test="${status.count % 4 eq 0}">
					</div>
					</c:when>
					<c:when test="${status.last}">
				</div>
				</c:when>
				</c:choose>
				</c:forEach>
				<!-- 끝 -->
			</div>
		</div>
		<div class="col-2">
			<div class="card border-warning mb-3" style="max-width: 18rem;">
				<div class="card-body" id="login-body">
					<!-- 로그인 전 -->
					<c:if test="${loginFailed}">
							${exception}
					</c:if>
					<c:if test="${empty userId}">
						<form action="<c:url value='/login'/>" method="POST">
							<div class="row mb-3">

								<input name=id type="text" class="form-control" id="id"
									placeholder="ID">
							</div>
							<div class="row mb-3">
								<input name=password type="password" class="form-control"
									id="password" placeholder="PW">
							</div>
							<div class="row mb-3">
								<div class="col-sm-6" align="left">
									<a href="chooseUserType.jsp" id="register-link">회원가입</a>
								</div>
								<div class="col-sm-6">
									<button type="submit" class="btn btn-warning" id="login-button">로그인</button>
								</div>
							</div>
						</form>
					</c:if>
					<!-- 로그인 후 -->
					<c:if test="${!empty userId}">
						<div class="row mb-3">
							<p class="h4" align="left">
								<u>${userObj.name}</u> 님
							</p>
							<p align="left">안녕하세요! o(^^)o</p>
						</div>
						<div class="row mb-3">
							<a href="<c:url value = '/mypage'/>">
								<button class="btn btn-warning" id="mypage-button">MY
									PAGE</button>
							</a>
						</div>
						<div class="col-sm-4" align="right">
							<a href=" <c:url value= '/logout'/>" id="logout-link"> 로그아웃</a>
						</div>
					</c:if>

				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	<div id="footer">
		<hr class="haveMargin" id="title-bar" style="margin-top: 2%;">
		<p class="text-center" align="center">
			<small><strong>팀명</strong></small><br> <small>팀 :
				UNI-CON ㆍ 소속 : 동덕여자대학교 ㆍ 전화 : 02-123-1234</small><br> <small>Copyrightⓒ
				test.com All rights reserved.</small>
		</p>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>

</body>
</html>


