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
<link rel="stylesheet" href="css/uploadGame.css">
<link rel="stylesheet" href="css/main.css">

<title>찜꽁 - 게임 수정</title>
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
				<div class="col-8" align="center" style="padding: 50px;">
					<div id="upload-title">게임 등록</div>
					<!-- 게임 등록 form -->
					<form action="/unicon/edit?gameId=${game.game_id }"
						id="upload-form" role="form" enctype="multipart/form-data"
						method="POST">
						<div id="form-container" class="container">
							<!-- 게임 이름 입력 -->
							<div class="row">
								<div id="name-label" class="col-4">게임 이름</div>
								<div id="name-input" class="col-6">
									<input type="text" class="form-control" id="name" name="title"
										value="${game.title }">
								</div>
							</div>
							<!-- 예약 기간 입력 -->
							<div class="row">
								<div id="period-label" class="col-4">예약 기간</div>
								<div id="period-input" class="col-6">
									<input type="date" class="form-control" id="start-period"
										name="start_date" value="${game.start_date }"> ~ <input
										type="date" class="form-control" id="end-period"
										name="end_date" value="${game.end_date }">
								</div>
							</div>
							<!-- 홍보 이미지 업로드 -->
							<div class="row">
								<div id="image-label" class="col">홍보 이미지 (최대 4개)</div>
								<div id="image-input">
									<c:forEach var="image" items="${imageList}" varStatus="status">
										<c:choose>
											<c:when test="${(status.last) and (status.index < 3) }">
												<c:forEach var="idx" begin="${status.index + 2 }" end="4">
													<label for="image0${idx }"> <img
														src="images/image_upload.jpg" class="image-upload-icon"
														alt="...">
													</label>
													<input type="file" class="form-control" id="image0${idx }"
														name="image0${idx }">
												</c:forEach>
											</c:when>
											<c:otherwise>
												<label for="image0${status.index + 1}"> <img
													src="<c:url value='/images/${game.company_id}/${image}' />"
													class="image-upload-icon" alt="...">
												</label>
												<input type="file" class="form-control"
													id="image0${status.index + 1 }"
													name="image0${status.index + 1 }"
													value="<c:url value='/images/${game.company_id}/${image}' />">
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</div>
							</div>
							<!-- 게임 소개글 입력 -->
							<div id="description-row" class="row">
								<div id="description-label" class="col">게임 소개글</div>
								<div id="description-input">
									<textarea class="form-control" id="description" rows="8"
										name="description">${game.description }</textarea>
								</div>
							</div>
							<!-- 게임 장르 체크박스  -->
							<div id="genre-row" class="row">
								<div id="genre-label" class="col">게임 장르 (최소 1개 이상)</div>
								<div id="genre-input">
									<div class="checkbox-group">
										<c:forEach var="tag" items="${genreList}" varStatus="status">
											<c:choose>
												<c:when
													test="${(status.index % 4) eq 0 and status.index ne 0}">
													<br>
													<input class="form-check-input" type="checkbox"
														value="${tag.genre_id }" id="${tag.genre_id }"
														name="tag[]">
													<label class="form-check-label" for="${tag.genre_id }">${tag.name }</label>
												</c:when>
												<c:otherwise>
													<input class="form-check-input" type="checkbox"
														value="${tag.genre_id }" id="${tag.genre_id }"
														name="tag[]">
													<label class="form-check-label" for="${tag.genre_id }">${tag.name }</label>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</div>
								</div>
							</div>
							<!-- 게임 보상 입력 -->
							<hr id="reward-divide">
							<div id="reward-description-row" class="row">
								<div id="reward-description-label" class="col">사전예약 보상 목록</div>
								<div id="reward-description-input">
									<textarea class="form-control" id="reward-description" rows="8"
										name="reward_text">${game.reward_text }</textarea>
									<!-- 게임 보상 이미지 업로드 -->
									<div id="reward-image-input" style="margin-top: 10px;">
										<c:forEach var="rewardImage" items="${rewardImageList}"
											varStatus="status">
											<c:choose>
												<c:when test="${(status.last) and (status.index < 3) }">
													<c:forEach var="idx" begin="${status.index + 2 }" end="4">
														<label for="reward-image0${idx }"> <img
															src="images/image_upload.jpg" class="image-upload-icon"
															alt="...">
														</label>
														<input type="file" class="form-control"
															id="reward-image0${idx }" name="reward-image0${idx }">
													</c:forEach>
												</c:when>
												<c:otherwise>
													<label for="reward-image0${status.index + 1}"> <img
														src="<c:url value='/images/${game.company_id}/${rewardImage}' />"
														class="image-upload-icon" alt="...">
													</label>
													<input type="file" class="form-control"
														id="reward-image0${status.index + 1 }"
														name="reward-image0${status.index + 1 }"
														value="<c:url value='/images/${game.company_id}/${image}' />">
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</div>
								</div>
							</div>
							<button id="upload-btn" type="submit" class="btn btn-warning">게임
								수정</button>
						</div>
					</form>
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
	<div id="footer">
		<hr class="haveMargin" id="title-bar" style="margin-top:2%;">
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
	<!-- 게임 이미지 업로드 스크립트 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script>
		function readURL(input) {
			var id = $(input).attr("id");

			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('label[for="' + id + '"] .image-upload-icon').attr('src',
							e.target.result).show();
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$("input[id^='image']").change(function() {
			readURL(this);
		});

		$("input[id^='reward-image']").change(function() {
			readURL(this);
		});
	</script>
</body>
</html>


