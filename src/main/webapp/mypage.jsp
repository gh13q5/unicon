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
						<p id=singUpP>회원 정보</p>
						
						<%-- <c:set var="user" value="${findUserList}"/> --%>
						
						<form class="form-horizontal" role="form" method="post">
							<div id="makeItcenter" align="left">
								<div class="form-group" id="userNameID">
									<p>
										<b>${findUser.name}</b> <span style="color: gray">${findUser.id}</span>
									</p>

								</div>
							</div>
							<div class="form-group" id="email"></div>
							<div class="row justify-content-center">
								<div class="col-4">
									<p class="informaton">Email</p>
								</div>
								<div class="col-4">
									<p>${findUser.email}</p>
								</div>
							</div>
							<div class="form-group" id="phoneNumber">
								<div class="row justify-content-center">
									<div class="col-4">
										<p class="informaton">전화번호</p>
									</div>
									<div class="col-4">
										<p>${findUser.phone_number}</p>
									</div>
								</div>
							</div>
							<div class="form-group" id="birthday">
								<div class="row justify-content-center">
									<div class="col-4">
										<p class="informaton">생년월일</p>
									</div>
									<div class="col-4">
										<p>${findUser.birthDay}</p>
									</div>
								</div>
							</div>
							<div class="form-group" id="gender">
								<div class="row justify-content-center">
									<div class="col-4">
										<p>성별</p>
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
					</div>
					<!-- 게임 장르 체크박스  -->
							<div id="genre-row" class="row">
								<div id="genre-label" class="col">관심 분야</div>
								<div id="genre-input">
									<div class="checkbox-group">
									 <c:set var = "str" value = "${findUserInterestsList}"/>
										<c:forEach var="tag" items="${findGenreList}" varStatus="status">
											<c:choose>
												<c:when
													test="${status.index % 4 eq 0 and status.index ne 0}">
													<br>
													</c:when>
													</c:choose>
													
													<c:set var = "ckd" value = "${tag.genre_id}"/>
													<c:choose>
													 <c:when test = "${fn:contains(str, ckd)}">
													 	<input class="form-check-input" type="checkbox"
															value='<c:out value="${tag.genre_id}"/>' id ="'<c:out value="${tag.genre_id}"/>'"
															name="tag[]"  checked="checked">
														<label class="form-check-label" for='<c:out value="${tag.genre_id}"/>'><c:out value="${tag.name}"/></label>
													 </c:when>
													 <c:otherwise>
													 	<input class="form-check-input" type="checkbox"
															value='<c:out value="${tag.genre_id}"/>' id ="'<c:out value="${tag.genre_id}"/>'"
															name="tag[]">
														<label class="form-check-label" for='<c:out value="${tag.genre_id}"/>'><c:out value="${tag.name}"/></label>
													 </c:otherwise>
													 </c:choose>
													
										</c:forEach>
									</div>
								</div>
							</div>

					<br>
					<div align="left">
						<p>
							<b>예약 게임 목록</b>
						</p>

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
						<div class="card" style="width: 15rem;">
							<c:set var="image" value="${fn:split(Rgame.image_address,',')}" />
							<img
								src="<c:url value='/images/${Rgame.company_id}/${image[0]}' />"
								class="card-img-top" alt="...">
							<div class="card-body">
								<h5 class="card-title">
									<c:out value="${Rgame.title}"></c:out>
								</h5>
								<p class="card-text">
									<c:out value="${Rgame.description}" escapeXml="false"></c:out>
								</p>
								<p class="card-text">
									<small class="text-muted">~<c:out
											value="${Rgame.end_date}"></c:out></small>
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
					<!-- 끝 -->

					<div id="makeItcenter" align="right">
						<div class="col-auto">
							<input type="button" class="btn btn-primary mb-3" value="정보 수정" onClick="#">
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


