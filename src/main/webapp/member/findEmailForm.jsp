<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 이메일 찾기
    
    jsp변환 : 정건영
    변환일 : 2022/04/10
    --%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이메일 찾기</title>
    <link rel="stylesheet" href="./css/search_id_style.css">
    <link rel="stylesheet" href="./css/login_header_style.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./js/search_id_script.js"></script>
    <script src="https://kit.fontawesome.com/6cc0f3db28.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../component/login_header.html" />
    <main>
        <div id="search_wrapper">
            <div class="search_box">
                <h1>이메일 찾기</h1>
                <p>휴대폰 번호</p>
                <input type="text" name="phone" id="phone" oninput="this.value = this.value.replaceAll(/\D/g, '')" placeholder="휴대폰 번호를 입력해주세요." maxlength="13">
                <div id="error">휴대폰 번호를 입력해주세요.</div>
            </div>
            <div class="search_btn_box">
                <input type="button" id="submit" value="찾기">
            </div>
        </div>
        <div id="result_wrapper">
        	<p id="result"></p>
        	<a class="button" id="login_button" href="/EatsOrder/member/loginForm.do">로그인 하러 가기</a>
        	<a class="button" id="regist_button" href="/EatsOrder/member/insertMemberForm.do">회원 가입</a>
        </div>
    </main>
    <jsp:include page="../component/footer.html" />
</body>
</html>