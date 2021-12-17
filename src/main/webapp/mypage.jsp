<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<!-- CSS -->
<link rel="stylesheet" href="css/mypage.css">

<title>myPage</title>
</head>
<body>
	<div id="header" align="center">
		<h1>
			<br>���
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
									aria-expanded="true" aria-controls="collapseOne">�޴�1</button>
							</h2>
							<div id="collapseOne" class="accordion-collapse collapse show"
								aria-labelledby="headingOne" data-bs-parent="#accordionExample">

								<div class="list-group">
									<a href="#"
										class="list-group-item list-group-item-action active"
										aria-current="true"> ����޴�1 </a> <a href="#"
										class="list-group-item list-group-item-action">����޴�2</a> <a
										href="#" class="list-group-item list-group-item-action">����޴�3</a>
									<a href="#" class="list-group-item list-group-item-action">����޴�4</a>
								</div>

							</div>
						</div>
						<div class="accordion-item">
							<h2 class="accordion-header" id="headingTwo">
								<button class="accordion-button collapsed" type="button"
									data-bs-toggle="collapse" data-bs-target="#collapseTwo"
									aria-expanded="false" aria-controls="collapseTwo">�޴�2</button>
							</h2>
							<div id="collapseTwo" class="accordion-collapse collapse"
								aria-labelledby="headingTwo" data-bs-parent="#accordionExample">

								<div class="list-group">
									<a href="#"
										class="list-group-item list-group-item-action active"
										aria-current="true"> ����޴�1 </a> <a href="#"
										class="list-group-item list-group-item-action">����޴�2</a> <a
										href="#" class="list-group-item list-group-item-action">����޴�3</a>
									<a href="#" class="list-group-item list-group-item-action">����޴�4</a>
								</div>
							</div>
						</div>
						<div class="accordion-item">
							<h2 class="accordion-header" id="headingThree">
								<button class="accordion-button collapsed" type="button"
									data-bs-toggle="collapse" data-bs-target="#collapseThree"
									aria-expanded="false" aria-controls="collapseThree">
									�޴�3</button>
							</h2>
							<div id="collapseThree" class="accordion-collapse collapse"
								aria-labelledby="headingThree"
								data-bs-parent="#accordionExample">

								<div class="list-group">
									<a href="#"
										class="list-group-item list-group-item-action active"
										aria-current="true"> ����޴�1 </a> <a href="#"
										class="list-group-item list-group-item-action">����޴�2</a> <a
										href="#" class="list-group-item list-group-item-action">����޴�3</a>
									<a href="#" class="list-group-item list-group-item-action">����޴�4</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-8 border">


					<div id=contents align="center">
						<p id=singUpP>ȸ�� ����</p>
						
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
										<p class="informaton">��ȭ��ȣ</p>
									</div>
									<div class="col-4">
										<p>${findUser.phone_number}</p>
									</div>
								</div>
							</div>
							<div class="form-group" id="birthday">
								<div class="row justify-content-center">
									<div class="col-4">
										<p class="informaton">�������</p>
									</div>
									<div class="col-4">
										<p>${findUser.birthDay}</p>
									</div>
								</div>
							</div>
							<div class="form-group" id="gender">
								<div class="row justify-content-center">
									<div class="col-4">
										<p>����</p>
									</div>
									<div class="col-4">
										<p>
										<c:if test="${findUser.gender eq 0}">
											<c:out value="����" />
										</c:if>
										<c:if test="${findUser.gender eq 1}">
											<c:out value="����" />
										</c:if>
										<c:if test="${findUser.gender eq 2}">
											<c:out value="��Ÿ" />
										</c:if>
										</p>
									</div>
								</div>
							</div>
					</div>
					<!-- ���� �帣 üũ�ڽ�  -->
							<div id="genre-row" class="row">
								<div id="genre-label" class="col">���� �о�</div>
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

					<br>
					<div align="left">
						<p>
							<b>���� ���� ���</b>
						</p>

					</div>
					<!-- ���� ���� -->
					<div id="carouselExampleDark" class="carousel carousel-dark slide"
						data-bs-ride="carousel" data-bs-interval="false">

						<div class="carousel-inner" style="margin: 10px; padding: 10px;">
							<div class="carousel-item active">
								<div class="row" align="center">
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
					<!-- �� -->

					<div id="makeItcenter" align="right">
						<div class="col-auto">
							<input type="button" class="btn btn-primary mb-3" value="���� ����" onClick="#">
						</div>
					</div>
					<br>

					</form>



				</div>
			</div>
		</div>
	</div>
	<div id="footer">�����</div>
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


