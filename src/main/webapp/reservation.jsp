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
<!-- CSS -->
<link rel="stylesheet" href="css/reservation.css">

<title>찜꽁 - 게임 예약</title>
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
									aria-expanded="true" aria-controls="collapseOne">메뉴1</button>
							</h2>
							<div id="collapseOne" class="accordion-collapse collapse show"
								aria-labelledby="headingOne" data-bs-parent="#accordionExample">

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
				<div class="col-8" align="left" style="padding: 50px;">
					<!-- 작성 다 하면 메뉴랑 아이디창은 없애두기 -->
					<h1 id="title">게임 이름</h1>
					<h5 id="company-title">개발사 이름</h5>
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
							<div class="carousel-item active">
								<img src="images/sample01.jpg" class="d-block w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img src="images/sample02.jpg" class="d-block w-100" alt="...">
							</div>
							<div class="carousel-item">
								<img src="images/sample03.jpg" class="d-block w-100" alt="...">
							</div>
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
						<button type="button" class="btn btn-warning">#게임태그01</button>
						<button type="button" class="btn btn-warning">#게임태그02</button>
						<button type="button" class="btn btn-warning">#게임태그03</button>
					</div>
					<!-- 예약자 통계 및 예약 버튼 -->
					<div id="reservation-status">
						<div id="total-reservate" class="container">
							<div class="row">
								<div id="total-reservate-text" class="col-9">예약 현황</div>
								<div id="total-reservate-num" class="col-3">n명</div>
							</div>
						</div>
						<button id="reservate" type="button" class="btn btn-warning" onclick="alert('예약되었습니다!');">사전예약</button>
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
								<h5 class="card-title">(게임 소개글 예시 : 플레이 스토어 '드래곤 플라이트' 발췌)</h5>
								<br>
								<p class="card-text">
									드래곤 플라이트는 앞에 놓인 적들을 마법으로 제압해 더 멀리 날아가는 게임입니다.<br>18종의 캐릭터와
									300여 종의 새끼용과 함께, 한손으로 보스를 물리쳐보세요!
								</p>
							</div>
							<div class="tab-pane fade" id="reward" role="tabpannel"
								aria-labelledby="reward-tab">
								<img src="images/sample03.jpg" class="d-block w-100" alt="...">
							</div>
							<div class="tab-pane fade" id="statistics" role="tabpannel"
								aria-labelledby="statistics-tab">
								<img src="images/sample04.jpg" class="d-block w-100" alt="...">
							</div>
						</div>
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<!-- navbar 탭 스크립트 -->
	<script>
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
</body>
</html>


