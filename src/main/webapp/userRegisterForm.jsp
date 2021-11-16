<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>userRegisterForm</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
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
.haveMargin{
	margin-top: 50px;
}
#finishButton{
	margin-top: 100px;
}
</style>
</head>
<body>
	<div id="header" align="center">
		<h1>
			<br>���
		</h1>
		<hr>
	</div>
	<div id=contents align="center">
		<p id=singUpP>ȸ������</p>
		<form class="form-horizontal" role="form" method="post">
			<div id="makeItcenter" align="left">
				<div class="form-group" id="userId">
					<label for="inputId" class="col-lg-2 control-label">���̵�</label> <input
						type="text" class="form-control onlyAlphabetAndNumber" id="id"
						data-rule-required="true" placeholder="10���̳��� ���ĺ�, ���ڸ� �Է� �����մϴ�."
						maxlength="10">
				</div>
				<div class="form-group" id="userPassword">
					<label for="inputPassword" class="col-lg-2 control-label">��й�ȣ</label>
					<input type="password" class="form-control" id="password"
						name="excludeHangul" data-rule-required="true"
						placeholder="10���̳��� ���ĺ�, ���ڸ� �Է� �����մϴ�." maxlength="30">
				</div>
				<div class="form-group" id="userPasswordCheck">
					<label for="inputPasswordCheck" class="col-lg-2 control-label">��й�ȣ
						Ȯ��</label> <input type="password" class="form-control" id="passwordCheck"
						data-rule-required="true" placeholder="��й�ȣ ���Է�" maxlength="30">
				</div>
				<div class="form-group" id="userNickname">
					<label for="inputNickname" class="col-lg-2 control-label">����</label>
					<input type="text" class="form-control" id="nickname"
						data-rule-required="true" placeholder="����" maxlength="15">
				</div>
				<div class="form-group" id="userEmail">
					<label for="inputEmail" class="col-lg-2 control-label">�̸���</label>
					<input type="email" class="form-control" id="email"
						data-rule-required="true" placeholder="�̸���" maxlength="40">
				</div>
				<div class="form-group" id="userPhoneNumber">
					<label for="inputPhoneNumber" class="col-lg-2 control-label">�޴���
						��ȣ</label> <input type="tel" class="form-control onlyNumber"
						id="phoneNumber" data-rule-required="true"
						placeholder="-�� �����ϰ� ���ڸ� �Է��ϼ���." maxlength="11">
				</div>
				<div class="form-group" id="userBirthday">
					<label for="inputBirthday" class="col-lg-2 control-label">�������</label>
					<input type="date" class="form-control onlyNumber" id="birthday"
						data-rule-required="true" placeholder="������� 6�ڸ� �Է����ּ���. ��)961204"
						maxlength="6">
				</div>
				<div class="form-group" id="userSex">
					<label for="chooseSex" class="col-lg-2 control-label">����</label> 
					<select
						class="form-control" id="gender">
						<option value="M">��</option>
						<option value="F">��</option>
						<option value="B">�� ��</option>
					</select>
				</div>
				</div>
				<hr class="haveMargin">
				<div class="form-group" id="userCategorys" align= "left" >
					<label for="chooseCategorys" class="col-lg-2 control-label">���ɺо�
						(�ּ� 1�� ��)</label>
					<div id="categoryBox" align= "left">
						<label><input type="checkbox" name="ganre1" value="0">�����帣 1rrrrrrrrrrrrr</label><br>
						<label><input type="checkbox" name="ganre2" value="1">�����帣 2rrrr</label><br>
						<label><input type="checkbox" name="ganre3" value="2">�����帣 3rr</lalel><br>
						<label><input type="checkbox" name="ganre4" value="3">�����帣 4rrrrrrrrr</label><br>
						<label><input type="checkbox" name="ganre5" value="4">�����帣 5rr</label><br>
						<label><input type="checkbox" name="ganre6" value="5">�����帣 6</label><br>
					</div>
				</div>
			
			<hr class="haveMargin">
			<div class="form-group" id="termsCheck">
				<button class="btn btn-primary">��� ���� ����</button>
				����� �а� ���뿡 �����Ͽ����ϴ�.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" id="inputTermsYes"
					name="inputTermsYes" value="Y" checked> �����մϴ�.
			</div>
			<div class="form-group" id="finishButton">
				<button type="submit" class="btn btn-primary">�����ϱ�</button>
			</div>
		</form>
	</div>
	<div id="footer">
		<hr class="haveMargin">
		<p class="text-center" align="center">
			<small><strong>��ü��</strong></small><br> <small>��ǥ : ȫ�浿
				�� �ּ� : ��Ÿ� �� ����ڵ�Ϲ�ȣ:123-12-12345 �� ��ȭ : 02-123-1234</small><br> <small>Copyright��
				test.com All rights reserved.</small>
		</p>
	</div>
</body>
</html>


