<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<!-- 작성자: 허우림. 업로드일자:2022.04.04. 페이지 이름:매장목록페이지 -->
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>매장목록,매장상세 헤더</title>
    <script src="https://kit.fontawesome.com/6cc0f3db28.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/res_list_header.css">
    <script type="text/javascript" src="./js/res_list_header.js"></script>
</head>
<body>
<!-- <div class="container"> -->
<header>
    <div class="box">
    </div>
    <div id="address-modal">
				<div id="wrapper"></div>
	</div>
    <div class="search_addr">
        <div class="white_box"></div>
        <div class="search_input">
            <input class="search1" type="text" id="address-search" value="${address}" placeholder="주소를 입력하세요." readonly="readonly">
            <div class="search_btn"><i class="fa-solid fa-magnifying-glass fa-lg"></i><b class="find_addr">검색하기</b></div>
        </div>
    </div>
    <div class="grid">
        <div class="category" id="0"><a href="#" class="food_category">전체보기</a></div>
        <div class="category" id="1"><a href="#" class="food_category">1인분 주문</a></div>
        <div class="category" id="2"><a href="#" class="food_category">프랜차이즈</a></div>
        <div class="category" id="3"><a href="#" class="food_category">치킨</a></div>
        <div class="category" id="4"><a href="#" class="food_category">피자/양식</a></div>
        <div class="category" id="5"><a href="#" class="food_category">중국집</a></div>
        <div class="category" id="6"><a href="#" class="food_category">한식</a></div>
        <div class="category" id="7"><a href="#" class="food_category">일식/돈까스</a></div>
        <div class="category" id="8"><a href="#" class="food_category">족발/보쌈</a></div>
        <div class="category" id="9"><a href="#" class="food_category">야식</a></div>
        <div class="category" id="10"><a href="#" class="food_category">분식</a></div>
        <div class="category" id="11"><a href="#" class="food_category">카페/디저트</a></div>
        <div class="category" id="12"><a href="#" class="food_category">편의점/마트</a></div>
    </div>
    <hr>
</header>
<!-- </div> -->
</body>
</html>