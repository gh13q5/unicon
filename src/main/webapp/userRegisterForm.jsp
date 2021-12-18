<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="css/main.css">

<title>찜꽁 - 일반 사용자 회원가입</title>
</head>
<body>
	<div id="header" align="center">
		<a href="<c:url value='/main'></c:url>"> <img
			src="images/title_logo.png" id="title-logo">
		</a>
		<hr id="title-bar">
		</div>
		<div class="container-fluid">
		<c:if test="${registerFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>
			<div class="row">
				<div class="col" align="center" style="padding-left: 18%; padding-right:18%; padding-top:3%;">
					<div id="registerForm-title">회원가입</div>
					<!-- 회원가입 form -->
					<form id="registerForm-form"  method="post" action="<c:url value='/register/user'/>">
						<div id="form-container" class="container">
							<!-- 회원 정보 입력 -->
							<div class="row">
								<div id="id-label" class="col-5">아이디</div>
								<div id="id-input" class="col-6" style="padding:0px;">
									<input name="id" type="text" class="form-control onlyAlphabetAndNumber"
										id="id" data-rule-required="true"
										placeholder="10자이내의 알파벳, 숫자만 입력 가능합니다." maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="password-label" class="col-5">비밀번호</div>
								<div id="password-input" class="col-6" style="padding:0px;">
									<input type="password" class="form-control" id="password"
										name="password" data-rule-required="true"
										placeholder="10자이내의 알파벳, 숫자만 입력 가능합니다." maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="passwordCheck-label" class="col-5">비밀번호 확인</div>
								<div id="passwordCheck-input" class="col-6">
									<input name="passwordCheck" type="password" class="form-control" id="passwordCheck"
										data-rule-required="true" placeholder="비밀번호를 다시 입력해주세요."
										maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="nickname-label" class="col-5">닉네임</div>
								<div id="nickname-input" class="col-6">
									<input name="name" type="text" class="form-control" id="nickname"
										data-rule-required="true" placeholder="10자 이내로 입력해 주세요."
										maxlength="10">
								</div>
							</div>
							<div class="row">
								<div id="email-label" class="col-5">이메일</div>
								<div id="email-input" class="col-6">
									<input name="email" type="email" class="form-control" id="email"
										data-rule-required="true" placeholder="이메일을 입력하세요."
										maxlength="40">
								</div>
							</div>
							<div class="row">
								<div id="phoneNumber-label" class="col-5">전화번호</div>
								<div id="phoneNumber-input" class="col-6">
									<input name="phone_number" type="tel" class="form-control onlyNumber"
										id="phoneNumber" data-rule-required="true"
										placeholder="-를 제외하고 숫자만 입력하세요." maxlength="11">
								</div>
							</div>
							<div class="row">
								<div id="birthday-label" class="col-5">생년월일</div>
								<div id="birthday-input" class="col-6">
									<input name="birthDay" type="date" class="form-control onlyNumber"
										id="birthday" data-rule-required="true"
										placeholder="생년월일 6자를 입력해주세요. 예)961204" maxlength="6">
								</div>
							</div>
							<div class="row">
								<div id="gender-label" class="col-5">성별</div>
								<div id="gender-input" class="col-6">
									<select name="gender" class="form-control" id="gender">
										<option value="0">남</option>
										<option value="1">여</option>
										<option value="2">그 외</option>
									</select>
								</div>
							</div>
							<!-- 게임 장르 체크박스  -->
							<!--<div id="genre-label" class="col-7">관심 분야 (최소 1개 이상)</div>
							<div id="genre-row" class="row">
								<div id="genre-input">
									<div class="checkbox-group">
										<input name="genre01" class="form-check-input" type="checkbox" value="Y"
											id="genre01"> <label class="form-check-label"
											for="genre01">게임장르01</label> <input  name="genre02" class="form-check-input"
											type="checkbox" value="Y" id="genre02"> <label
											class="form-check-label" for="genre02">게임장르02</label> <input  name="genre03"
											class="form-check-input" type="checkbox" value="Y"
											id="genre03"> <label class="form-check-label"
											for="genre03">게임장르03</label> <input  name="genre04"class="form-check-input"
											type="checkbox" value="Y" id="genre04"> <label
											class="form-check-label" for="genre04">게임장르04</label>
									</div>
									<div class="checkbox-group">
										<input name="genre05" class="form-check-input" type="checkbox" value="Y"
											id="genre05"> <label class="form-check-label"
											for="genre05">게임장르05</label> <input  name="genre06" class="form-check-input"
											type="checkbox" value="Y" id="genre06"> <label
											class="form-check-label" for="genre06">게임장르06</label> <input name="genre07"
											class="form-check-input" type="checkbox" value="Y"
											id="genre07"> <label class="form-check-label"
											for="genre07">게임장르07</label> <input  name="genre08"class="form-check-input"
											type="checkbox" value="Y" id="genre08"> <label
											class="form-check-label" for="genre08">게임장르08</label>
									</div>
								</div>
							</div>-->
							<hr id="terms-divide">
							<button onclick="window.open('readme.jsp')" id="terms-btn" type="button" class="btn btn-warning">약관
								전문 보기</button>
							약관을 읽고 내용에 동의하였습니다. <input type="radio" id="inputTermsYes"
								name="inputTermsYes" value="Y"> 동의합니다.
							<button id="submit-btn"  type="submit" class="btn btn-warning">가입하기</button>
						</div>
					</form>
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


</body>
</html>