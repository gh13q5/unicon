<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
									aria-expanded="true" aria-controls="collapseOne">ī�װ����� ����</button>
							</h2>
							<div id="collapseOne" class="accordion-collapse collapse show"
								aria-labelledby="headingOne" data-bs-parent="#accordionExample">
								
								<div class="list-group">
								
									<a href="<c:url value='/category'><c:param name='category' value='0' /></c:url>"
										class="list-group-item list-group-item-action"
										aria-current="true" > ������ </a> <a href="<c:url value='/category'><c:param name='category' value='1' /></c:url>"
										class="list-group-item list-group-item-action">����</a> <a
										href="<c:url value='/category'><c:param name='category' value='2' /></c:url>" class="list-group-item list-group-item-action">���÷���</a>
									<a href="<c:url value='/category'><c:param name='category' value='3' /></c:url>" class="list-group-item list-group-item-action">�ùķ��̼�</a>
									<a href="<c:url value='/category'><c:param name='category' value='4' /></c:url>" class="list-group-item list-group-item-action">�׼�</a>
									<a href="<c:url value='/category'><c:param name='category' value='5' /></c:url>" class="list-group-item list-group-item-action">����</a>
									<a href="<c:url value='/category'><c:param name='category' value='6' /></c:url>" class="list-group-item list-group-item-action">����</a>
									<a href="<c:url value='/category'><c:param name='category' value='7' /></c:url>" class="list-group-item list-group-item-action">FPS</a>
								
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
					<!-- �Ϲ� ���� -->
					<div class="row">
						<div class="col" align="left" style="margin-top: 10px;">
							<h1 class="display-5">Category Name</h1>
						</div>
						<div class="col" align="right" style="margin: 20px;">
							<button type="button" class="btn btn-warning">���� ���</button>
						</div>
					</div>
					<hr />
					<div class="row">
						<div class="col" align="left">
							<h4>
								<u>�������� ���� ��</u>
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
					<c:forEach var="Sgame" items="${searchGameList}"
						varStatus="status">
						<<c:choose>
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
								<img src="images/wallR.jpg" class="card-img-top" alt="...">
								<div class="card-body">
									<h5 class="card-title">
										<c:out value="${Sgame.title}"></c:out>
									</h5>
									<p class="card-text">
										<c:out value="${Sgame.description}"></c:out>
									</p>
									<p class="card-text">
										<small class="text-muted">~<c:out
												value="${Sgame.end_date}"></c:out></small>
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
					<div>
						<br>
					</div>
					<div class="row">
						<div class="col" align="left" style="margin-left: 10px">
							<h4>
								<u>�������� ����</u>
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
						
						<c:forEach var="ESgame" items="${endsearchGameList}"
						varStatus="status">
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
								<img src="images/wallR.jpg" class="card-img-top" alt="...">
								<div class="card-body">
									<h5 class="card-title">
										<c:out value="${ESgame.title}"></c:out>
									</h5>
									<p class="card-text">
										<c:out value="${ESgame.description}"></c:out>
									</p>
									<p class="card-text">
										<small class="text-muted">~<c:out
												value="${ESgame.end_date}"></c:out></small>
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
			<small><strong>��ü��</strong></small><br> <small>��ǥ : ȫ�浿
				�� �ּ� : ��Ÿ� �� ����ڵ�Ϲ�ȣ:123-12-12345 �� ��ȭ : 02-123-1234</small><br> <small>Copyright��
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
</body>
</html>

