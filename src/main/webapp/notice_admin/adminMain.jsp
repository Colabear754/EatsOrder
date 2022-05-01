<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>관리자 메인</title>
    <!-- css연결 : google font -->
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
    </style>
    <!-- css연결 : admin_main_style -->
    <link rel="stylesheet" href="./css/admin_main.css">
</head>
<body>
    <header>
        <div class="header_top">
            <div class="header_box">
                <a href="/notice_admin/adminMain.do" class="logo"><img src="./img/logo_white.png" alt="로고-아이콘"></a>
            </div>
        </div>
    </header>
    <main>
        <div class="admin_wrapper">
            <div class="admin_box">
                <h1>관리자 페이지</h1>
            </div>
            <ul>
                <li><a href="#">이벤트/공지사항/FAQ 게시판관리</a></li>
<!--                 <li><a href="#">공지사항 관리</a></li>
                <li><a href="#">FAQ게시판 관리</a></li> -->
            </ul>
        </div>
    </main>
</body>
</html>