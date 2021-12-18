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
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/registerForm.css">

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
<title>찜꽁 - 게임사 정보 수정</title>
</head>
<body>
	<div id="header" align="center">
		<a href="<c:url value='/main'></c:url>"> <img
			src="images/title_logo.png" id="title-logo">
		</a>
		<hr id="title-bar">
		<div class="container-fluid">
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
				<div class="col-8">
					<div id=contents align="center"
						style="padding-top: 3%; padding-left: 9%; padding-right: 9%;">
						<div id="info-title" style="margin-bottom: 5%;">게임사 정보 수정</div>
						<form id="updateForm" method="post"
							action="<c:url value='/updateRegister/company'/>">
							<div id="form-container" class="container">
								<div class="row">
									<div id="name-label" class="col-5">회사명</div>
									<div id="name-input" class="col-6" style="padding: 0px;">
										<input name="name" type="text"
											class="form-control onlyAlphabetAndNumber" id="nickname"
											data-rule-required="true" value="${company.name }"
											maxlength="10">
									</div>
								</div>
								<div class="row">
									<div id="id-label" class="col-5">아이디</div>
									<div id="id-input" class="col-6" style="padding: 0px;">
										<input name="id" type="text"
											class="form-control onlyAlphabetAndNumber" id="id"
											data-rule-required="true" value="${company.id }">
									</div>
								</div>
								<div class="row">
									<div id="pwd-label" class="col-5">비밀번호</div>
									<div id="pwd-input" class="col-6" style="padding: 0px;">
										<input name="password" type="password"
											class="form-control onlyAlphabetAndNumber" id="password"
											data-rule-required="true" value="${company.password }">
									</div>
								</div>
								<div class="row">
									<div id="email-label" class="col-5">이메일</div>
									<div id="email-input" class="col-6" style="padding: 0px;">
										<input name="email" type="email"
											class="form-control onlyAlphabetAndNumber" id="email"
											data-rule-required="true" value="${company.email }">
									</div>
								</div>
								<div class="row">
									<div id="phone-label" class="col-5">전화번호</div>
									<div id="phone-input" class="col-6" style="padding: 0px;">
										<input name="phone_number" type="tel"
											class="form-control onlyAlphabetAndNumber" id="phone"
											data-rule-required="true" value="${company.phone_number }">
									</div>
								</div>
							</div>
							<button id="submit-btn" type="submit" class="btn btn-warning"
								style="width: 50%;">수정 완료</button>
						</form>
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
											<button type="submit" class="btn btn-warning"
												id="login-button">로그인</button>
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


