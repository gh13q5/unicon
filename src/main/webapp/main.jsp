<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
									<a href="/category.jsp"
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
					<!-- search -->
					<div class="input-group mb-3">
						<div class="col-5">
							<input type="text" class="form-control" placeholder="search"
								aria-label="search" aria-describedby="button-addon2">
						</div>
						<div class="col-auto">
							<button class="btn btn-outline-secondary" type="button"
								id="button-addon2">search</button>
						</div>
					</div>

					<!-- recommend -->
					<p class="h2">Recommend Games</p>
					<div id="carouselExampleDark" class="carousel carousel-dark slide"
						data-bs-ride="carousel">

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

					<!-- �Ϲ� ���� -->
					<p class="h2">Entire Games</p>
					<div class="row" align="center">
						<div class="col">
							<div class="card" style="width: 15rem;">
								<img src="images/wallR.jpg" class="card-img-top" alt="...">
								<div class="card-body">
									<h5 class="card-title">game title</h5>
									<p class="card-text">This is a wider card with supporting
										text below as a natural lead-in to additional content. This
										content is a little bit longer.</p>
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
									<p class="card-text">This is a wider card with supporting
										text below as a natural lead-in to additional content. This
										content is a little bit longer.</p>
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
									<p class="card-text">This is a wider card with supporting
										text below as a natural lead-in to additional content. This
										content is a little bit longer.</p>
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
									<p class="card-text">This is a wider card with supporting
										text below as a natural lead-in to additional content. This
										content is a little bit longer.</p>
									<p class="card-text">
										<small class="text-muted">Last updated 3 mins ago</small>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- �α��� �� -->
				<!-- <div class="col-2">
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
				</div> -->
				<!-- �α��� �� -->
				<div class="col-2">
					<div class="card border-warning mb-3" style="max-width: 18rem;">
						<div class="card-body">

							<div class="row mb-3">
								<p class="h5" align="left">
									<u>username</u> ��
								</p>
							</div>
							<div class="row mb-3">
								<p class="h5" align="left">
									����: <u>9999</u>point
								</p>
							</div>
							<div class="row mb-3">
								<button type="submit" class="btn btn-primary">MY PAGE</button>
							</div>
							<div class="row mb-3">
								<div class="col-sm-7" align="left">
									<button type="submit" class="btn btn-primary">����Ʈ ����</button>
								</div>
								<div class="col-sm-4" align="right">
									<a href="#">logout</a>
								</div>

							</div>
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


