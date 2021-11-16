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

<title>��� - ȸ������</title>
</head>
<body>
	<div id="header" align="center">
		<div class="container-fluid">
			<div class="row">
				<div class="col" align="center" style="padding: 50px;">
					<div id="registerForm-title">���� ���</div>
					<!-- ���� ��� form -->
					<form id="registerForm-form">
						<div id="form-container" class="container">
							<!-- ���� �̸� �Է� -->
							<div class="row">
								<div id="id-label" class="col-5">���̵�</div>
								<div id="id-input" class="col-6">
									<input type="text" class="form-control onlyAlphabetAndNumber"
										id="id" data-rule-required="true"
										placeholder="10���̳��� ���ĺ�, ���ڸ� �Է� �����մϴ�." maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="passward-label" class="col-5">��й�ȣ</div>
								<div id="passward-input" class="col-6">
									<input type="password" class="form-control" id="password"
										name="excludeHangul" data-rule-required="true"
										placeholder="10���̳��� ���ĺ�, ���ڸ� �Է� �����մϴ�." maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="passwardCheck-label" class="col-5">��й�ȣ Ȯ��</div>
								<div id="passwardCheck-input" class="col-6">
									<input type="password" class="form-control" id="passwordCheck"
										data-rule-required="true" placeholder="��й�ȣ�� �ٽ� �Է����ּ���."
										maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="companyName-label" class="col-5">ȸ���</div>
								<div id="companyName-input" class="col-6">
									<input type="text" class="form-control" id="companyName"
										data-rule-required="true" placeholder="10�� �̳��� �Է��� �ּ���."
										maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="email-label" class="col-5">����� �̸���</div>
								<div id="email-input" class="col-6">
									<input type="email" class="form-control" id="email"
										data-rule-required="true" placeholder="�̸����� �Է��ϼ���." maxlength="40">
								</div>
							</div>
							<div class="row">
								<div id="phoneNumber-label" class="col-5">����� ��ȭ��ȣ</div>
								<div id="phoneNumber-input" class="col-6">
									<input type="tel" class="form-control onlyNumber"
										id="phoneNumber" data-rule-required="true"
										placeholder="-�� �����ϰ� ���ڸ� �Է��ϼ���." maxlength="11">
								</div>
							</div>
							<hr id="terms-divide">
							<button id="terms-btn" type="button" class="btn btn-warning">���
								���� ����</button> ����� �а� ���뿡 �����Ͽ����ϴ�.
							<input type="radio" id="inputTermsYes" name="inputTermsYes"
								value="Y" checked> �����մϴ�.
							<button id="submit-btn" type="button" class="btn btn-warning">ȸ������</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		<hr class="haveMargin">
		<p class="text-center" align="center">
			<small><strong>��ü�� : UNI-CON</strong></small><br> <small>��ǥ : ���ظ�  ������ �ְ��� ���� �� �ּ� : �������� ���б� �� ����ڵ�Ϲ�ȣ:123-12-12345 �� ��ȭ : 02-123-1234</small><br> <small>Copyright��
				��� - ���ϰ� ��¥ ���� ����! .</small>
		</p>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<!-- ���� �̹��� ���ε� ��ũ��Ʈ -->
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