<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 회원가입 완료
    
    jsp변환 : 정건영
    변환일 : 2022/04/15
    --%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 완료</title>
    <link rel="stylesheet" href="./css/join_check_style.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/6cc0f3db28.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../component/login_header.html" />
    <main>
        <form class="join_wrapper">
            <div class="join_box">
                <h1>${email}님, 가입을 환영합니다.</h1>
            </div>
            <div class="join_btn_box">
                <button type="button" name="join_btn" id="home">홈으로 돌아가기</button>
                <button type="button" name="join_btn" id="login">로그인 하러 가기</button>
            </div>
        </form>
    </main>
    <jsp:include page="../component/footer.html" />
</body>
<script type="text/javascript">
	$(function() {
		$('#home').click(function() {
			location.window.href = "/EatsOrder/main/main.do";
		})
		
		$('#login').click(function() {
			location.window.href = "/EatsOrder/member/loginForm.do";
		})
	})
</script>
</html>