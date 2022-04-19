<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
jsp변환 : 정건영
변환일 : 2022/04/13
 --%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link href="./css/delete_member_style.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="./js/delete_member_script.js"></script>
</head>
<body>
	<jsp:include page="../component/main_header_logAfter.html" />
	<main>
	<input type="hidden" id="email" value="${email}">
	<div class="member">
		<div class="m1">
			<h3>회원탈퇴</h3>
		</div>
		<div class="m2">
			<strong>탈퇴 시 유의사항 안내</strong>
		</div>
		<div class="m3">회원 탈퇴를 신청하기 전에 아래의 유의사항을 확인해 주세요</div>
		<div>
			<ul>
				<li class="m4">탈퇴 처리 이메일 아이디 안내</li>
				<li class="m8">아래 이메일 아이디가 탈퇴 처리됩니다.</li>
				<li class="m7">${email}</li>
				<li class="m10">탈퇴 후 위 이메일 아이디 정보로 로그인할 수 없으며 보유하신 포인트 및 쿠폰은 모두 소멸되어 복구가 불가능합니다.</li>
				<li class="m10">잇츠오더 회원 탈퇴와 동시에 모든 서비스 이용이 종료됩니다.</li>
			</ul>
		</div>
		<div>
			<ul>
				<li class="m5">개인 정보 삭제 안내</li>
				<li class="m10">탈퇴 처리된 이메일 아이디는 재 가입 방지를 위해 30일간 보존된 후 삭제 처리 되며 이 내용은 탈퇴 처리 완료시 이메일로 안내해 드립니다.</li>
				<li class="m10">작성하신 리뷰 정보는 탈퇴 후에도 삭제되지 않습니다.</li>
			</ul>
		</div>
		<div class="m6">비밀번호 확인</div>
		<div>
			<ul>
				<li class="m4 password">현재 사용중인 비밀번호를 입력해주세요.</li>
				<li id="password_field"><input type="password" id="password"></li>
				<li class="m4 error password" id="password_error">비밀번호가 다릅니다. 다시 확인해주세요.</li>
			</ul>
		</div>
		<div class="m6">탈퇴사유<span class="error" id="reason_error">탈퇴사유를 선택하여 주세요.</span></div>
		<div class="t1">
			<div class="checkbox">
				<input type="radio" name="check1" id="reason1" value="1" class="checkbox1"> <label for="reason1">사용빈도 낮음</label> 
				<input type="radio" name="check1" id="reason2" value="2" class="checkbox1"> <label for="reason2">서비스 불만</label> 
				<input type="radio" name="check1" id="reason3" value="3" class="checkbox1"> <label for="reason3">고객응대 불만</label>
				<input type="radio" name="check1" id="reason4" value="4" class="checkbox1"> <label for="reason4">개인정보 유출 우려</label>
				<input type="radio" name="check1" id="reason5" value="5" class="checkbox1"> <label for="reason5">기타</label>
				<input type="text" id="etc" disabled="disabled">
			</div>
		</div>
	</div>
	<div>
		<div class="checkbox">
			<div class="error" id="agree_error">회원 탈퇴를 위해서는 포인트 및 쿠폰 소멸에 동의해야 합니다.</div>
			<input type="checkbox" name="check2" id="check2" class="checkbox2"> <label for="check2">&nbsp;&nbsp;&nbsp;유의 사항을 모두 확인하였으며, 회원 탈퇴시 쿠폰, 포인트 소멸에 동의합니다.</label>
		</div>
		<div class="b1">
			<button type="button" id="withdraw" style="width: 20%; height: 40px; background-color: #ff9f00;">
				<strong>회원탈퇴하기</strong>
			</button>
			<button type="button" id="cancel" style="width: 20%; height: 40px; background-color: #fdfdfd;" onclick="history.back()">
				<strong>취소</strong>
			</button>
		</div>
	</div>
	</main>
	<jsp:include page="../component/footer.html" />
</body>
</html>
