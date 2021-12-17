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

<title>Hello, world!</title>
</head>
<body>
	<div id="header" align="center">
		<h1>
			<br>찜꽁
		</h1>
		<hr>
		<div class="container-fluid">
			<div class="row">
				<div class="col-2">
					<div class="accordion" id="accordionExample">
						<div class="accordion-item">
							<h2 class="accordion-header" id="headingOne">
								<button class="accordion-button" type="button"
									data-bs-toggle="collapse" data-bs-target="#collapseOne"
									aria-expanded="true" aria-controls="collapseOne">카테고리별
									보기</button>
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
						<div class="accordion-item">
							<h2 class="accordion-header" id="headingTwo">
								<button class="accordion-button collapsed" type="button"
									data-bs-toggle="collapse" data-bs-target="#collapseTwo"
									aria-expanded="false" aria-controls="collapseTwo">메뉴2</button>
							</h2>
							<div id="collapseTwo" class="accordion-collapse collapse"
								aria-labelledby="headingTwo" data-bs-parent="#accordionExample">

								<div class="list-group">
									<a href="#"
										class="list-group-item list-group-item-action active"
										aria-current="true"> 서브메뉴1 </a> <a href="#"
										class="list-group-item list-group-item-action">서브메뉴2</a> <a
										href="#" class="list-group-item list-group-item-action">서브메뉴3</a>
									<a href="#" class="list-group-item list-group-item-action">서브메뉴4</a>
								</div>
							</div>
						</div>
						<div class="accordion-item">
							<h2 class="accordion-header" id="headingThree">
								<button class="accordion-button collapsed" type="button"
									data-bs-toggle="collapse" data-bs-target="#collapseThree"
									aria-expanded="false" aria-controls="collapseThree">
									메뉴3</button>
							</h2>
							<div id="collapseThree" class="accordion-collapse collapse"
								aria-labelledby="headingThree"
								data-bs-parent="#accordionExample">

								<div class="list-group">
									<a href="#"
										class="list-group-item list-group-item-action active"
										aria-current="true"> 서브메뉴1 </a> <a href="#"
										class="list-group-item list-group-item-action">서브메뉴2</a> <a
										href="#" class="list-group-item list-group-item-action">서브메뉴3</a>
									<a href="#" class="list-group-item list-group-item-action">서브메뉴4</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-8 border">
					<!-- 일반 게임 -->
					<div class="row">
						<div class="col" align="left" style="margin-top: 10px;">
							<h1 class="display-5">Category Name</h1>
						</div>
						<div class="col" align="right" style="margin: 20px;">
							<button type="button" class="btn btn-warning" onclick="isLogin()">게임
								등록</button>
						</div>
					</div>
					<hr />
					<div class="row">
						<div class="col" align="left">
							<h4>
								<u>사전예약 진행 중</u>
							</h4>
						</div>
						<div class="col-sm-2" align="right"
							style="margin-right: 20px; margin-bottom: 10px;">
							<select class="form-select" aria-label="Default select example">
								<option selected>Open this select menu</option>
								<option value="1">One</option>
								<option value="2">Two</option>
								<option value="3">Three</option>
							</select>
						</div>
					</div>
					<c:forEach var="Cgame" items="${categoryGameList}"
						varStatus="status">
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
            			<c:param name='gameId' value='${Cgame.game_id}' /></c:url>"
								style="color: black; text-decoration: none;">
								<div class="card" style="width: 15rem; margin: 5px;">
									<c:set var="image" value="${fn:split(Cgame.image_address,',')}" />
									<img
										src="<c:url value='/images/${Cgame.company_id}/${image[0]}' />"
										class="card-img-top" alt="...">
									<div class="card-body">
										<h5 class="card-title">
											<c:out value="${Cgame.title}"></c:out>
										</h5>
										<p class="card-text">
											<c:out value="${Cgame.description}" escapeXml="false"></c:out>
										</p>
										<p class="card-text">
											<small class="text-muted">~<c:out
													value="${Cgame.end_date}"></c:out></small>
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
			<div>
				<br>
			</div>
			<div class="row">
				<div class="col" align="left" style="margin-left: 10px">
					<h4>
						<u>사전예약 종료</u>
					</h4>
				</div>
				<div class="col-sm-2" align="right"
					style="margin-right: 20px; margin-bottom: 10px;">
					<select class="form-select" aria-label="Default select example">
						<option selected>Open this select menu</option>
						<option value="1">One</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select>
				</div>

				<c:forEach var="Egame" items="${endGameList}" varStatus="status">
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
						<div class="card" style="width: 15rem;">
							<c:set var="image" value="${fn:split(Egame.image_address,',')}" />
							<img
								src="<c:url value='/images/${Egame.company_id}/${image[0]}' />"
								class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title">
									<c:out value="${Egame.title}"></c:out>
								</h5>
								<p class="card-text">
									<c:out value="${Egame.description}" escapeXml="false"></c:out>
								</p>
								<p class="card-text">
									<small class="text-muted">~<c:out
											value="${Egame.end_date}"></c:out></small>
								</p>
							</div>
						</div>
						<!-- </a> -->
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
	</div>
	<div class="col-2">
		<div class="card border-warning mb-3" style="max-width: 18rem;">
			<div class="card-body">
				<form>
					<div class="row mb-3">

						<input type="email" class="form-control" id="inputEmail3"
							placeholder="ID">
					</div>
					<div class="row mb-3">
						<input type="password" class="form-control" id="inputPassword3"
							placeholder="PW">
					</div>
					<div class="row mb-3">
						<div class="col-sm-6" align="left">
							<a href="#">regist in</a>
						</div>
						<div class="col-sm-6">
							<button type="submit" class="btn btn-primary">Sign in</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</div>
	</div>
	</div>
	<div id="footer">
		<hr class="haveMargin">
		<p class="text-center" align="center">
			<small><strong>업체명</strong></small><br> <small>대표 : 홍길동
				ㆍ 주소 : 사거리 ㆍ 사업자등록번호:123-12-12345 ㆍ 전화 : 02-123-1234</small><br> <small>Copyrightⓒ
				test.com All rights reserved.</small>
		</p>
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


