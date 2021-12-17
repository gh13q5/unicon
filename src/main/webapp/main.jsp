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
									<c:forEach var="genre" items="${genreList}" varStatus="status">
										<a
											href="<c:url value='/category'><c:param name='category' value='${genre.genre_id }' /></c:url>"
											class="list-group-item list-group-item-action"
											aria-current="true"> ${genre.name } </a>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-8">
					<!-- search -->
					<form action="<c:url value='/search'>
            			  </c:url>">
						<div class="input-group mb-3" id="search-div">
							<div class="col-5">
								<input type="text" class="form-control" name="keyWord"
									placeholder="search" aria-label="search">
							</div>
							<div class="col-auto">
								<button type="submit" class="btn btn-info" id="search-button">search</button>
							</div>
							<div class="col" align="right">
								<button type="button" class="btn btn-warning"
									onclick="isLogin()" id="upload-button">게임 등록</button>
							</div>
						</div>
					</form>

					<!-- recommend -->
					<p class="h2" style="margin-top: 2%;">&lt; 추천 게임 &gt;</p>
					<div id="carouselExampleDark" class="carousel carousel-dark slide"
						data-bs-ride="carousel">

						<div class="carousel-inner" style="margin: 10px; padding: 10px;">
							<div class="carousel-item active">
								<div class="row" align="center">
									<div class="col">
										<div class="card" style="width: 15rem;">
											<a href="<c:url value = '/reservation/game'></c:url>"
												style="text-decoration: none"> <img
												src="images/wallR.jpg" class="card-img-top" alt="...">
												<div class="card-body">
													<h5 class="card-title">game title</h5>
													<p class="card-text">This is a wider card with
														supporting text below as a natural lead-in to additional
														content. This content is a little bit longer.</p>
													<p class="card-text">
														<small class="text-muted">Last updated 3 mins ago</small>
													</p>
												</div>
											</a>
										</div>
									</div>
									<div class="col">
										<div class="card" style="width: 15rem;">
											<img src="images/wallR.jpg" class="card-img-top" alt="...">
											<div class="card-body">
												<h5 class="card-title">game title</h5>
												<p class="card-text">This is a wider card with
													supporting text below as a natural lead-in to additional
													content. This content is a little bit longer.</p>
												<p class="card-text">
													<small class="text-muted">Last updated 3 mins ago</small>
												</p>
											</div>
										</div>
									</div>
									<div class="col">
										<div class="card" style="width: 15rem;">
											<img src="images/wallR.jpg" class="card-img-top" alt="...">
											<div class="card-body">
												<h5 class="card-title">game title</h5>
												<p class="card-text">This is a wider card with
													supporting text below as a natural lead-in to additional
													content. This content is a little bit longer.</p>
												<p class="card-text">
													<small class="text-muted">Last updated 3 mins ago</small>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="row" align="center">
									<div class="col">
										<div class="card" style="width: 15rem;">
											<a href="<c:url value = '/reservation/game'></c:url>"
												style="text-decoration: none"> <img
												src="images/wallR.jpg" class="card-img-top" alt="...">
												<div class="card-body">
													<h5 class="card-title">game title</h5>
													<p class="card-text">This is a wider card with
														supporting text below as a natural lead-in to additional
														content. This content is a little bit longer.</p>
													<p class="card-text">
														<small class="text-muted">Last updated 3 mins ago</small>
													</p>
												</div>
											</a>
										</div>
									</div>
									<div class="col">
										<div class="card" style="width: 15rem;">
											<img src="images/wallR.jpg" class="card-img-top" alt="...">
											<div class="card-body">
												<h5 class="card-title">game title</h5>
												<p class="card-text">This is a wider card with
													supporting text below as a natural lead-in to additional
													content. This content is a little bit longer.</p>
												<p class="card-text">
													<small class="text-muted">Last updated 3 mins ago</small>
												</p>
											</div>
										</div>
									</div>
									<div class="col">
										<div class="card" style="width: 15rem;">
											<img src="images/wallR.jpg" class="card-img-top" alt="...">
											<div class="card-body">
												<h5 class="card-title">game title</h5>
												<p class="card-text">This is a wider card with
													supporting text below as a natural lead-in to additional
													content. This content is a little bit longer.</p>
												<p class="card-text">
													<small class="text-muted">Last updated 3 mins ago</small>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="row" align="center">
									<div class="col">
										<div class="card" style="width: 15rem;">
											<a href="<c:url value = '/reservation/game'></c:url>"
												style="text-decoration: none"> <img
												src="images/wallR.jpg" class="card-img-top" alt="...">
												<div class="card-body">
													<h5 class="card-title">game title</h5>
													<p class="card-text">This is a wider card with
														supporting text below as a natural lead-in to additional
														content. This content is a little bit longer.</p>
													<p class="card-text">
														<small class="text-muted">Last updated 3 mins ago</small>
													</p>
												</div>
											</a>
										</div>
									</div>
									<div class="col">
										<div class="card" style="width: 15rem;">
											<img src="images/wallR.jpg" class="card-img-top" alt="...">
											<div class="card-body">
												<h5 class="card-title">game title</h5>
												<p class="card-text">This is a wider card with
													supporting text below as a natural lead-in to additional
													content. This content is a little bit longer.</p>
												<p class="card-text">
													<small class="text-muted">Last updated 3 mins ago</small>
												</p>
											</div>
										</div>
									</div>
									<div class="col">
										<div class="card" style="width: 15rem;">
											<img src="images/wallR.jpg" class="card-img-top" alt="...">
											<div class="card-body">
												<h5 class="card-title">game title</h5>
												<p class="card-text">This is a wider card with
													supporting text below as a natural lead-in to additional
													content. This content is a little bit longer.</p>
												<p class="card-text">
													<small class="text-muted">Last updated 3 mins ago</small>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleDark" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleDark" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>

					<!-- 일반 게임 -->
					<p class="h2" style="margin-top: 5%; margin-bottom: 3%;">&lt;
						전체 게임 목록 &gt;</p>
					<c:forEach var="game" items="${entireGameList}" varStatus="status">
						<c:choose>
							<c:when test="${status.index % 4  eq 0}">
								<div class="row" align="center">
							</c:when>
							<%-- <c:when test="${status.count eq 0}">
								<div class="row" align="center">
							</c:when> --%>
						</c:choose>
						<div class="col">
							<a
								href="<c:url value='/game'>
            			<c:param name='gameId' value='${game.game_id}' /></c:url>"
								class="card-link">
								<div class="card game">
									<c:set var="image" value="${fn:split(game.image_address,',')}" />
									<img
										src="<c:url value='/images/${game.company_id}/${image[0]}' />"
										class="card-img-top" alt="...">
									<div class="card-body">
										<h5 class="card-title">
											<c:out value="${game.title}"></c:out>
										</h5>
										<div class="card-body">
											<p class="card-text">
												<c:out value="${game.description}" escapeXml="false"></c:out>
											</p>
										</div>
										<p class="card-date">
											<small class="text-muted">~<c:out
													value="${game.end_date}"></c:out></small>
										</p>
									</div>
								</div>
							</a>
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
						<form>
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
						</form>
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
	<script>
		function login_() {
			if (id == "") {
				alert("사용자 아이디를 입력하십시요.");
				return false;
			}
			if (passward == "") {
				alert("비밀번호를 입력하십시요.");
				return false;
			}
			LoginController.submit();
		}
		 // 게임 예약 버튼 클릭 시
			function isLogin() {
				var user = '<%=(String) session.getAttribute(UserSessionUtils.USER_SESSION_KEY)%>';

			alert(user);
			if (user === null) {
				alert('로그인이 필요합니다!');
			} else {
				location.href = '/unicon/viewUpload';
			}
		}
	</script>
</body>
</html>