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
<link rel="stylesheet" href="css/registerForm.css">

<title>찜꽁 - 회원가입</title>
</head>
<body>
	<div id="header" align="center">
		<div class="container-fluid">
			<div class="row">
				<div class="col" align="center" style="padding: 50px;">
					<div id="registerForm-title">게임 등록</div>
					<!-- 게임 등록 form -->
					<form id="registerForm-form">
						<div id="form-container" class="container">
							<!-- 게임 이름 입력 -->
							<div class="row">
								<div id="id-label" class="col-5">아이디</div>
								<div id="id-input" class="col-6">
									<input type="text" class="form-control onlyAlphabetAndNumber"
										id="id" data-rule-required="true"
										placeholder="10자이내의 알파벳, 숫자만 입력 가능합니다." maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="passward-label" class="col-5">비밀번호</div>
								<div id="passward-input" class="col-6">
									<input type="password" class="form-control" id="password"
										name="excludeHangul" data-rule-required="true"
										placeholder="10자이내의 알파벳, 숫자만 입력 가능합니다." maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="passwardCheck-label" class="col-5">비밀번호 확인</div>
								<div id="passwardCheck-input" class="col-6">
									<input type="password" class="form-control" id="passwordCheck"
										data-rule-required="true" placeholder="비밀번호를 다시 입력해주세요."
										maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="companyName-label" class="col-5">회사명</div>
								<div id="companyName-input" class="col-6">
									<input type="text" class="form-control" id="companyName"
										data-rule-required="true" placeholder="10자 이내로 입력해 주세요."
										maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="email-label" class="col-5">담당자 이메일</div>
								<div id="email-input" class="col-6">
									<input type="email" class="form-control" id="email"
										data-rule-required="true" placeholder="이메일을 입력하세요." maxlength="40">
								</div>
							</div>
							<div class="row">
								<div id="phoneNumber-label" class="col-5">담당자 전화번호</div>
								<div id="phoneNumber-input" class="col-6">
									<input type="tel" class="form-control onlyNumber"
										id="phoneNumber" data-rule-required="true"
										placeholder="-를 제외하고 숫자만 입력하세요." maxlength="11">
								</div>
							</div>
							<hr id="terms-divide">
							<button id="terms-btn" type="button" class="btn btn-warning">약관
								전문 보기</button> 약관을 읽고 내용에 동의하였습니다.
							<input type="radio" id="inputTermsYes" name="inputTermsYes"
								value="Y" checked> 동의합니다.
							<button id="submit-btn" type="button" class="btn btn-warning">회원가입</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<hr class="haveMargin">
		<p class="text-center" align="center">
			<small><strong>업체명 : UNI-CON</strong></small><br> <small>대표 : 심해림  박주희 최가희 조수 ㆍ 주소 : 동덕여자 대학교 ㆍ 사업자등록번호:123-12-12345 ㆍ 전화 : 02-123-1234</small><br> <small>Copyrightⓒ
				찜꽁 - 찜하고 꽁짜 보상 받자! .</small>
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