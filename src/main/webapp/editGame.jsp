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
<link rel="stylesheet" href="css/uploadGame.css">

<title>찜꽁 - 게임 정보 수정</title>
</head>
<body>
	<!-- ****************************** -->
	<!-- uploadGame에서 placeHolder 등을 추가 -->
	<!-- ****************************** -->
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
				<div class="col-8" align="center" style="padding: 50px;">
					<div id="upload-title">게임 정보 수정</div>
					<!-- 게임 정보 수정 form -->
					<form id="upload-form">
						<div id="form-container" class="container">
							<!-- 게임 이름 입력 -->
							<div class="row">
								<div id="name-label" class="col-4">게임 이름</div>
								<div id="name-input" class="col-6">
									<input type="text" class="form-control" id="name"
										placeholder="수정 전게임 이름">
								</div>
							</div>
							<!-- 예약 기간 입력 -->
							<div class="row">
								<div id="period-label" class="col-4">예약 기간</div>
								<div id="period-input" class="col-6">
									<input type="date" class="form-control" id="start-period">
									~ <input type="date" class="form-control" id="end-period">
								</div>
							</div>
							<!-- 홍보 이미지 업로드 -->
							<div class="row">
								<div id="image-label" class="col">홍보 이미지 (최대 4개)</div>
								<div id="image-input">
									<label for="image01"> <img
										src="images/image_upload.jpg" class="image-upload-icon"
										alt="...">
									</label> <input type="file" class="form-control" id="image01">
									<label for="image02"> <img
										src="images/image_upload.jpg" class="image-upload-icon"
										alt="...">
									</label><input type="file" class="form-control" id="image02"> <label
										for="image03"> <img src="images/image_upload.jpg"
										class="image-upload-icon" alt="...">
									</label><input type="file" class="form-control" id="image03"> <label
										for="image04"> <img src="images/image_upload.jpg"
										class="image-upload-icon" alt="...">
									</label> <input type="file" class="form-control" id="image04">
								</div>
							</div>
							<!-- 게임 소개글 입력 -->
							<div id="description-row" class="row">
								<div id="description-label" class="col">게임 소개글</div>
								<div id="description-input">
									<textarea class="form-control" id="description" rows="8"
										placeholder="수정 전게임 소개글"></textarea>
								</div>
							</div>
							<!-- 게임 장르 체크박스  -->
							<div id="genre-row" class="row">
								<div id="genre-label" class="col">게임 장르 (최소 1개 이상)</div>
								<div id="genre-input">
									<div class="checkbox-group">
										<input class="form-check-input" type="checkbox" value=""
											id="genre01"> <label class="form-check-label"
											for="genre01">게임장르01</label> <input class="form-check-input"
											type="checkbox" value="" id="genre02"> <label
											class="form-check-label" for="genre02">게임장르02</label> <input
											class="form-check-input" type="checkbox" value=""
											id="genre03"> <label class="form-check-label"
											for="genre03">게임장르03</label> <input class="form-check-input"
											type="checkbox" value="" id="genre04"> <label
											class="form-check-label" for="genre04">게임장르04</label>
									</div>
									<div class="checkbox-group">
										<input class="form-check-input" type="checkbox" value=""
											id="genre05"> <label class="form-check-label"
											for="genre05">게임장르05</label> <input class="form-check-input"
											type="checkbox" value="" id="genre06"> <label
											class="form-check-label" for="genre06">게임장르06</label> <input
											class="form-check-input" type="checkbox" value=""
											id="genre07"> <label class="form-check-label"
											for="genre07">게임장르07</label> <input class="form-check-input"
											type="checkbox" value="" id="genre08"> <label
											class="form-check-label" for="genre08">게임장르08</label>
									</div>
								</div>
							</div>
							<!-- 게임 보상 입력 -->
							<hr id="reward-divide">
							<div id="reward-description-row" class="row">
								<div id="reward-description-label" class="col">사전예약 보상 목록</div>
								<div id="reward-description-input">
									<textarea class="form-control" id="reward-description" rows="8"
										placeholder="수정 전 보상 목록"></textarea>
									<!-- 게임 보상 이미지 업로드 -->
									<div id="reward-image-input" style="margin-top: 10px;">
										<label for="reward-image01"> <img
											src="images/image_upload.jpg" class="image-upload-icon"
											alt="...">
										</label> <input type="file" class="form-control" id="reward-image01">
										<label for="reward-image02"> <img
											src="images/image_upload.jpg" class="image-upload-icon"
											alt="...">
										</label><input type="file" class="form-control" id="reward-image02">
										<label for="reward-image03"> <img
											src="images/image_upload.jpg" class="image-upload-icon"
											alt="...">
										</label><input type="file" class="form-control" id="reward-image03">
										<label for="reward-image04"> <img
											src="images/image_upload.jpg" class="image-upload-icon"
											alt="...">
										</label> <input type="file" class="form-control" id="reward-image04">
									</div>
								</div>
							</div>
							<button id="upload-btn" type="button" class="btn btn-warning">수정
								및 게임 재등록</button>
						</div>
					</form>
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


