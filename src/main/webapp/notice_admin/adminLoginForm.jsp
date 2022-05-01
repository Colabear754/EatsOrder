<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${account != null}">
	<meta http-equiv="Refresh" content="0;url=/EatsOrder/main/main.do">
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 
    작성자: 정건영
    작성완료일: 22/05/01
    페이지명: 관리자 로그인 폼
    --%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 로그인</title>
    <link rel="stylesheet" href="./css/login_style.css">
    <link rel="stylesheet" href="./css/admin_main.css">
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
                <a href="/EatsOrder/notice_admin/adminLoginForm.do" class="logo"><img src="./img/logo_white.png" alt="로고-아이콘"></a>
            </div>
        </div>
    </header>
    <main>
        <div class="login_wrapper">
            <div class="login_box">
                <h1>관리자 로그인</h1>
                <input type="text" name="admin_id" id="admin_id" placeholder="관리자 계정을 입력해주세요">
                <input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요">
                <div id="login_error">계정 또는 비밀번호를 확인해주세요.</div>
                <div class="login_btn_box">
                    <input type="button" name="login" id="login" value="로그인">
                </div>
            </div>
        </div>
    </main>
</body>

</html>