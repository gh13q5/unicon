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
<link rel="stylesheet" href="css/companyMypage.css">

<title>찜꽁</title>
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
										class="list-group-item list-group-item-action">FPS</a>

								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="col-8 border">


					<div id=contents align="center">
						<p id=singUpP>게임사 정보</p>

						<%-- <c:set var="user" value="${findUserList}"/> --%>

						<form class="form-horizontal" role="form" method="post">
							<div id="makeItcenter" align="left">
								<div class="form-group" id="userNameID">
									<p>
										<b>${company.name}</b> <span style="color: gray">${company.id}</span>
									</p>

								</div>
							</div>
							<div class="form-group" id="email"></div>
							<div class="row justify-content-center">
								<div class="col-4">
									<p class="informaton">Email</p>
								</div>
								<div class="col-4">
									<p>${company.email}</p>
								</div>
							</div>
							<div class="form-group" id="phoneNumber">
								<div class="row justify-content-center">
									<div class="col-4">
										<p class="informaton">전화번호</p>
									</div>
									<div class="col-4">
										<p>${company.phone_number}</p>
									</div>
								</div>
							</div>
						</form>
					</div>
					<br> <br>
					<div id="upload-box" style="padding: 5%;">
						<div align="left" style="margin-left: 2%; margin-bottom: 5%;">
							<h4>
								<b>등록한 게임 목록</b>
							</h4>
						</div>
						<!-- 등록한 게임 -->
						<c:forEach var="game" items="${uploadGameList}" varStatus="status">
							<div class="col upload">
								<a
									href="<c:url value='/game'>
							<c:param name='gameId' value='${game.game_id}' /></c:url>"
									class="card-link">
									<div class="card upload" style="width: 18rem;">
										<c:set var="image" value="${fn:split(game.image_address,',')}" />
										<img
											src="<c:url value='/images/${game.company_id}/${image[0]}' />"
											class="card-img-top" alt="...">
										<div class="card-body">
											<h5 class="card-title">
												<c:out value="${game.title}"></c:out>
											</h5>
											<p class="card-text">
												<c:out value="${game.description}" escapeXml="false"></c:out>
											</p>
											<p class="card-text">
												<small class="text-muted">~<c:out
														value="${game.end_date}"></c:out></small>
											</p>
										</div>
									</div>
								</a>
								<div class="row" style="padding: 5%;">
									<div class="row upload" style="width: 100%;">
										<h3 style="width: 100%;">예약 현황 :
											${game.total_reservations }명</h3>
									</div>
									<div class="row" style="margin-top: 5%;">
										<button class="btn btn-info">예약자 정보 보기</button>
									</div>
									<div class="row" style="margin-top: 5%;">
										<button class="btn btn-info"
											onclick="location.href='<c:url value='/viewEdit'>
											<c:param name='gameId' value='${game.game_id}' /></c:url>'">게임
											정보 수정</button>
									</div>
									<div class="row" style="margin-top: 5%;">
										<button class="btn btn-danger">게임 삭제</button>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- 끝 -->

					<div id="makeItcenter" align="right">
						<div class="col-auto">
							<input type="button" class="btn btn-primary mb-3"
								value="회원 정보 수정" onClick="#">
						</div>
					</div>
					<br>

					</form>



				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<hr class="haveMargin">
		<p class="text-center" align="center">
			<small><strong>팀명</strong></small><br> <small>팀 :
				UNI-CON ㆍ 소속 : 동덕여자대학교 ㆍ 전화 : 02-123-1234</small><br> <small>Copyrightⓒ
				test.com All rights reserved.</small>
		</p>
	</div>
	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>

	<!-- Option 2: Separate Popper and Bootstrap JS -->
	<!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
</body>
</html>


