<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${account != null}">
	<meta http-equiv="Refresh" content="0;url=/EatsOrder/main/main.do">
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 로그인 폼
    
    jsp변환 : 정건영
    변환일 : 2022/04/08
    추가수정내용 : 동적 웹 페이지 작동을 위한 태그 추가
    --%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="./css/login_style.css">
    <link rel="stylesheet" href="./css/login_header_style.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./js/login_script.js"></script>
    <script src="https://kit.fontawesome.com/6cc0f3db28.js" crossorigin="anonymous"></script>
</head>

<body>
	<header>
        <div class="header_top">
            <div class="header_box">
                <a href="/EatsOrder/main/main.do" class="logo"><img src="./img/Logo_white.png" alt="로고-아이콘"></a>
                <ul class="icon_menu">
                    <li><a href="#"><i class="fa-solid fa-cart-shopping"> Cart</i></a></li>
                    <li><a href="/EatsOrder/member/loginForm.do"><i class="fa-solid fa-user"> Login</i></a></li>
                </ul>
            </div>
        </div>
    </header>
    <main>
        <div class="login_wrapper">
            <div class="login_box">
                <h1>로그인</h1>
                <input type="radio" name="type" id="type" value="1" checked="checked">회원 <input type="radio" name="type" id="type" value="2">비회원
                <input type="text" name="account" id="account" placeholder="이메일을 입력해주세요">
                <input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요">
                <div id="login_error">계정 또는 비밀번호를 확인해주세요.</div>
                <div class="id_pw_search">
                    <a href="/EatsOrder/member/findEmailForm.do">이메일 찾기</a>
                    <span> | </span>
                    <a href="/EatsOrder/member/checkValidMember.do">비밀번호 찾기</a>
                </div>
                <div class="login_btn_box">
                    <input type="button" name="login" id="login" value="로그인">
                    <a href="/EatsOrder/member/insertMemberForm.do">회원가입</a>
                </div>
            </div>
        </div>
    </main>
</body>

</html>