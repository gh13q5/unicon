<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/main.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<style>
#singUpP {
	font-size: 25px;
	width: 300px;
	hight: 80px;
	padding: 15px;
	margin: 50px;
	border-style: solid;
	border-width: 2px;
}

.input-form {
	max-width: 680px;
	margin-top: 80px;
	padding: 32px;
	background: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	-webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	-moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
	box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
}

#makeItcenter, #userCategorys {
	width: 500px;
}

.form-group {
	white-space: nowrap;
}

#categoryBox {
	hight: 400px;
	padding: 15px;
	border-style: solid;
	border-width: 2px;
}

.haveMargin {
	margin-top: 50px;
}

#finishButton {
	margin-top: 100px;
}
</style>

<title>updateUserInfo</title>
</head>
<body>
	<div id="header" align="center">
		<a href="<c:url value='/main'></c:url>"> <img
			src="images/title_logo.png" id="title-logo">
		</a>
		<hr id="title-bar">
		<div class="container-fluid">
			<div class="row">
				<div class="col-2">
					<div class="accordion" id="accordionExample">
						<div class="accordion-item">
							<h2 class="accordion-header" id="headingOne">
								<button class="accordion-button" type="button"
									data-bs-toggle="collapse" data-bs-target="#collapseOne"
									aria-expanded="true" aria-controls="collapseOne">ī�װ�</button>
							</h2>
							<div id="collapseOne" class="accordion-collapse collapse show"
								aria-labelledby="headingOne" data-bs-parent="#accordionExample">

								<div class="list-group">

									<a
										href="<c:url value='/category'><c:param name='category' value='0' /></c:url>"
										class="list-group-item list-group-item-action"
										aria-current="true"> ������ </a> <a
										href="<c:url value='/category'><c:param name='category' value='1' /></c:url>"
										class="list-group-item list-group-item-action">����</a> <a
										href="<c:url value='/category'><c:param name='category' value='2' /></c:url>"
										class="list-group-item list-group-item-action">���÷���</a> <a
										href="<c:url value='/category'><c:param name='category' value='3' /></c:url>"
										class="list-group-item list-group-item-action">�ùķ��̼�</a> <a
										href="<c:url value='/category'><c:param name='category' value='4' /></c:url>"
										class="list-group-item list-group-item-action">�׼�</a> <a
										href="<c:url value='/category'><c:param name='category' value='5' /></c:url>"
										class="list-group-item list-group-item-action">����</a> <a
										href="<c:url value='/category'><c:param name='category' value='6' /></c:url>"
										class="list-group-item list-group-item-action">����</a> <a
										href="<c:url value='/category'><c:param name='category' value='7' /></c:url>"
										class="list-group-item list-group-item-action">FPS</a>

								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="col-8 border">


					<div id=contents align="center">
						<p id=singUpP>���� ����</p>
						<form class="form-horizontal" role="form" method="post">
							<div class="form-group" id="email"></div>
							<div class="row justify-content-center">
								<div class="col-4">
									<p>�г���</p>
								</div>
								<div class="col-4">
									<input type="text" class="form-control" id="nickname" data-rule-required="true" placeholder="����" maxlength="15">
								</div>
							</div>
							<div class="form-group" id="email"></div>
							<div class="row justify-content-center">
								<div class="col-4">
									<p>���̵�</p>
								</div>
								<div class="col-4">
									<input type="text" class="form-control onlyAlphabetAndNumber" id="id"
									data-rule-required="true" placeholder="10���̳��� ���ĺ�, ���ڸ� �Է� �����մϴ�." maxlength="10">
								</div>
							</div>
							<div class="form-group" id="email"></div>
							<div class="row justify-content-center">
								<div class="col-4">
									<p>��й�ȣ</p>
								</div>
								<div class="col-4">
									<input type="password" class="form-control" id="password" name="excludeHangul" data-rule-required="true"
									placeholder="10���̳��� ���ĺ�, ���ڸ� �Է� �����մϴ�." maxlength="30">
								</div>
							</div>
							<div class="form-group" id="email"></div>
							<div class="row justify-content-center">
								<div class="col-4">
									<p>Email</p>
								</div>
								<div class="col-4">
									<input type="email" class="form-control" id="email" data-rule-required="true" placeholder="�̸���" maxlength="40">
								</div>
							</div>
							<div class="form-group" id="phoneNumber">
								<div class="row justify-content-center">
									<div class="col-4">
										<p>��ȭ��ȣ</p>
									</div>
									<div class="col-4">
										<input type="tel" class="form-control onlyNumber" id="phoneNumber" data-rule-required="true" placeholder="-�� �����ϰ� ���ڸ� �Է��ϼ���." maxlength="11">
									</div>
								</div>
							</div>
							<div class="form-group" id="birthday">
								<div class="row justify-content-center">
									<div class="col-4">
										<p>�������</p>
									</div>
									<div class="col-4">
										<input type="date" class="form-control onlyNumber" id="birthday"
										data-rule-required="true" placeholder="������� 6�ڸ� �Է����ּ���. ��)961204"
										maxlength="6">
									</div>
								</div>
							</div>
							<div class="form-group" id="gender">
								<div class="row justify-content-center">
									<div class="col-4">
										<p>����</p>
									</div>
									<div class="col-4">
										<select class="form-control" id="gender">
											<option value="M">��</option>
											<option value="F">��</option>
											<option value="B">�� ��</option>
										</select>
									</div>
								</div>
							</div>
					</div>
					<div class="form-group" id="userCategorys" align="left">
						<label for="chooseCategorys" class="col-lg-2 control-label">���ɺо�
							(�ּ� 1�� ��)</label>
						<div id="categoryBox" align="left">
							<label><input type="checkbox" name="ganre1" value="0">�����帣
								1rrrrrrrrrrrrr</label><br> <label><input type="checkbox"
								name="ganre2" value="1">�����帣 2rrrr</label><br> <label><input
								type="checkbox" name="ganre3" value="2">�����帣 3rr</lalel><br>
								<label><input type="checkbox" name="ganre4" value="3">�����帣
									4rrrrrrrrr</label><br> <label><input type="checkbox"
									name="ganre5" value="4">�����帣 5rr</label><br> <label><input
									type="checkbox" name="ganre6" value="5">�����帣 6</label><br>
						</div>
						<br>
						<div id="makeItcenter" align="right">
							<div class="col-auto">
    							<button type="button" class="btn btn-primary mb-3">�Ϸ�</button>
  							</div>
						</div>
						<br>
					</div>

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


