<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 메인페이지
    
    jsp변환 : 정건영
    변환일 : 2022/04/08
    추가 수정내용 : 주소 검색을 모달 띄우도록 수정
    --%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/main_style.css">
    <link rel="stylesheet" href="./css/main_header_logBefore_style.css">
    <link rel="stylesheet" href="./css/main_header_logAfter_style.css">
    <title>잇츠오더</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
    </style>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
     <script type="text/javascript" src="./js/main_script.js"></script>
     <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://kit.fontawesome.com/6cc0f3db28.js" crossorigin="anonymous"></script>
</head>
<body>
    <header>
	    <div class="header_top">
	    	<div class="logo"><a href="/EatsOrder/main/main.do"><img src="./img/Logo.png" alt="로고-아이콘"></a></div>
    		<c:if test="${account != null}">
	            <ul class="icon_menu">
	                <li><a href="/EatsOrder/member/myPage.do"><i class="fa-solid fa-heart"> MyPage</i></a>
	                    <ul>
	                        <li><a href="/EatsOrder/member/favoriteRstList.do">찜 목록</a></li>
	                        <li><a href="/EatsOrder/member/couponList.do">쿠폰함</a></li>
	                        <li><a href="/EatsOrder/member/myReviewList.do">리뷰관리</a></li>
	                        <li><a href="/EatsOrder/member/orderList.do">회원주문내역</a></li>
	                        <li><a href="/EatsOrder/member/updateMemberForm.do">내 정보 수정</a></li>
	                    </ul>
	                </li>
	                <li><a href="#"><i class="fa-solid fa-cart-shopping"> Cart</i></a></li>
	                <li><a href="/EatsOrder/member/logout.do"><i class="fa-solid fa-power-off"> Logout</i></a></li>
	            </ul>
        	</c:if>
        	<c:if test="${account == null}">
	            <ul class="icon_menu">
	                <li><a href="#"><i class="fa-solid fa-cart-shopping"> Cart</i></a></li>
	                <li><a href="/EatsOrder/member/loginForm.do"><i class="fa-solid fa-user"> Login</i></a></li>
	            </ul>
        	</c:if>
	    </div>
        <div class="header_back">
            <div class="all_header">
                <div class="header_first">
                    <div class="header_text">
                        <h1>빠른 음식배달 잇츠오더</h1>
                        <h3>원하시는 음식을 검색해보세요.</h3>
                    </div>
                    <div id="address-modal">
						<div id="wrapper"></div>
					</div>
                    <form class="search_box">
                        <div>
                            <i class="fa-solid fa-location-dot"></i>
                            <input type="text" name="addr_search" id="addr_search" placeholder="클릭해서 주소를 선택해주세요 (필수입력)" readonly="readonly">
                            <input type="text" name="sido" id="sido" hidden="true">
                            <input type="text" name="sigungu" id="sigungu" hidden="true">
                            <input type="text" name="bname" id="bname" hidden="true">
                        </div>
                        <div class="food_search_bar">
                            <i class="fa-solid fa-magnifying-glass"></i>
                            <input type="text" name="food_search" id="food_search" placeholder="메뉴와 음식점을 검색하세요 (선택사항)">
                            <input type="submit" name="search_button" value="검색">
                        </div>
                    </form>
                </div>
                <div class="main_img_container">
                    <img src="./img/main_food.png" alt="머릿글-장식-사진">
                </div>
            </div>
        </div>
    </header>
    <main>
        <div class="main_food_text" id="main_food_text"><h1>원하시는 음식 카테고리를 선택하세요</h1></div>
        <div class="food_select_container">
            <a href="#" class="food_item">
                <img src="./img/event.png" class="first_img" alt="이벤트-사진"><div></div>
                <span class="caption">이벤트/공지</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/all.png" alt="전체보기-사진"><div>전체보기</div>
                <span class="caption">전체보기</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/oneMeal_crop.png" alt="1인분-사진"><div>1인분 주문</div>
                <span class="caption">1인분 주문</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/fran_crop.png" alt="프랜차이즈-사진"><div>프랜차이즈</div>
                <span class="caption">프랜차이즈</span>
            </a>
            <a href="#" class="food_item">
               <img src="./img/chicken_crop.png" alt="치킨-사진"><div>치킨</div>
                <span class="caption">치킨</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/pizza_crop.png" alt="피자-양식-사진"><div>피자/양식</div>
                <span class="caption">피자/양식</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/china_crop.png" alt="중국집-사진"><div>중국집</div>
                <span class="caption">중국집</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/korea_crop.png" alt="한식-사진"><div>한식</div>
                <span class="caption">한식</span>
            </a>
            <a href="#" class="food_item">
               <img src="./img/japan_crop.png" alt="일식-돈까스-사진"><div>일식/돈까스</div>
                <span class="caption">일식/돈까스</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/bossam_crop.png" alt="족발-보쌈-사진"><div>족발/보쌈</div>
                <span class="caption">족발/보쌈</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/nighrMeal_crop.png" alt="야식-사진"><div>야식</div>
                <span class="caption">야식</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/bun_crop.png" alt="분식-사진"><div>분식</div>
                <span class="caption">분식</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/coffee_crop.png" alt="카페-디저트-사진"><div>카페/디저트</div>
                <span class="caption">카페/디저트</span>
            </a>
            <a href="#" class="food_item">
                <img src="./img/ramen_crop.png" alt="편의점-마트-사진"><div>편의점/마트</div>
                <span class="caption">편의점/마트</span>
            </a>
        </div>
    </main>
</body>
</html>