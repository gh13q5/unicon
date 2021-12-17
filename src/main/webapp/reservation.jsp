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
<link rel="stylesheet" href="css/reservation.css">

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
				<div class="col-8" align="left" style="padding: 50px;">
					<!-- 작성 다 하면 메뉴랑 아이디창은 없애두기 -->
					<h1 id="title">${game.title}</h1>
					<h5 id="company-title">${company.name}</h5>
					<hr>
					<!-- 이미지 슬라이드 -->
					<div id="game-image-carousel" class="carousel slide"
						data-bs-ride="carousel">
						<div class="carousel-indicators">
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="0" class="active" aria-current="true"
								aria-label="Slide 1"></button>
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="1" aria-label="Slide 2"></button>
							<button type="button" data-bs-target="#carouselExampleIndicators"
								data-bs-slide-to="2" aria-label="Slide 3"></button>
						</div>
						<div class="carousel-inner">
							<c:forEach var="image" items="${imageList}" varStatus="status">
								<c:choose>
									<c:when test="${status.first }">
										<div class="carousel-item active">
											<img
												src="<c:url value='/images/${game.company_id}/${image}' />"
												class="d-block w-100" alt="...">
										</div>
									</c:when>
									<c:otherwise>
										<div class="carousel-item">
											<img
												src="<c:url value='/images/${game.company_id}/${image}' />"
												class="d-block w-100" alt="...">
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
					<div id="game-tag">
						<c:forEach var="tag" items="${genreList}">
							<button type="button" class="btn btn-warning">#${tag.name}</button>
						</c:forEach>
					</div>
					<!-- 예약자 통계 및 예약 버튼 -->
					<div id="reservation-status">
						<div id="total-reservate" class="container">
							<div class="row">
								<div id="total-reservate-text" class="col-9">예약 현황</div>
								<div id="total-reservate-num" class="col-3">${game.total_reservations}명</div>
							</div>
						</div>
						<c:choose>
							<c:when test="${reservate eq true}">
								<button id="reservate" type="button" class="btn btn-warning"
									onClick="deleteReservation()">예약취소</button>
							</c:when>
							<c:otherwise>
								<button id="reservate" type="button" class="btn btn-warning"
									onClick="isLogin()">사전예약</button>
							</c:otherwise>
						</c:choose>
						<button id="reservate" type="button" class="btn btn-warning">사전예약</button>
					</div>
					<!-- 게임 소개 등 추가 설명 -->
					<div id="game-more-inform" class="card text-center">
						<div class="card-header">
							<ul id="myTab" class="nav nav-tabs card-header-tabs"
								role="tablist">
								<li id="description-title" class="nav-item" role="presentation"><button
										id="description-tab" class="nav-link active"
										data-bs-toggle="tab" data-bs-target="#description"
										type="button" role="tab" aria-controls="description"
										aria-selected="true">게임 소개</button></li>
								<li id="reward-title" class="nav-item" role="presentation"><button
										id="reward-tab" class="nav-link" data-bs-toggle="tab"
										data-bs-target="#reward" type="button" role="tab"
										aria-controls="reward" aria-selected="false">사전 예약 보상</button></li>
								<li id="statistics-title" class="nav-item" role="presentation"><button
										id="statistics-tab" class="nav-link" data-bs-toggle="tab"
										data-bs-target="#statistics" type="button" role="tab"
										aria-controls="statistics" aria-selected="false">예약자
										통계</button></li>
							</ul>
						</div>
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="description"
								role="tabpannel" aria-labelledby="description-tab">
								<h5 class="card-title">${game.title }</h5>
								<br>
								<p class="card-text">
									<c:out value="${game.description}" escapeXml="false"></c:out>
								</p>
							</div>
							<div class="tab-pane fade" id="reward" role="tabpannel"
								aria-labelledby="reward-tab">
								<c:forEach var="rewardImage" items="${rewardImageList}">
									<img
										src="<c:url value='/images/${game.company_id}/${rewardImage}' />"
										class="d-block w-100">
									<br>
								</c:forEach>
								<br>
								<c:out value="${game.reward_text}" escapeXml="false"></c:out>
							</div>
							<div class="tab-pane fade" id="statistics" role="tabpannel"
								aria-labelledby="statistics-tab">
								<img src="images/sample04.jpg" class="d-block w-100" alt="...">
							</div>
						</div>
					</div>
				</div>
						<!-- 로그인 창 -->
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
									<button class="btn btn-warning" id="mypage-button">MY PAGE</button>
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<!-- navbar 탭 스크립트 -->
	<script>
		// 게임 소개 - 보상 - 예약자 통계 탭 클릭 시
		var triggerTabList = [].slice.call(document
				.querySelectorAll('#myTab button'))
		triggerTabList.forEach(function(triggerEl) {
			var tabTrigger = new bootstrap.Tab(triggerEl)

			triggerEl.addEventListener('click', function(event) {
				event.preventDefault()
				tabTrigger.show()
			})
		})
	</script>
	<script>
		// 게임 예약 버튼 클릭 시
		function isLogin() {
			var isLogin = '<%=(String)request.getAttribute("isLogin")%>';

			if (isLogin === 'true') {
				alert('예약되었습니다!');
				const gameId = '${game.game_id}';
				location.href = '/unicon/reservate?gameId=' + gameId;
			} else { // 로그인 되어있지 않은 경우
				alert('로그인이 필요합니다!');
			}
		}

		function deleteReservation() {
			// 로그인 구현 전 임시로 뺴둠
			alert('예약이 취소되었습니다!');
			const gameId = '${game.game_id}';
			location.href = '/unicon/cancle?gameId=' + gameId;
		}
	</script>
</body>
</html>


