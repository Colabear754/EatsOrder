<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 매장상세페이지 2022-04-04 김시웅 -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>매장상세페이지</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="X-UA-Compatible" content="Chrome" />
    <meta name="viewport"
        content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=0" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link href="./css/rst_form.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript" src="./js/rst_form.js"></script>
</head>
<body>
    <c:if test="${account == null}">
		<jsp:include page="../component/main_header_logBefore.html" />
	</c:if>
	<c:if test="${account != null}">
		<jsp:include page="../component/main_header_logAfter.html" />
	</c:if>
	<div class="container">
		<header>
		    <div>
				<input type="hidden" id="rst_id" value="${restaurant.rst_id}">
	            <input type="hidden" name="address" id="address" value="${address}">
	            <input type="hidden" name="sido" id="sido" value="${sido}">
	            <input type="hidden" name="sigungu" id="sigungu" value="${sigungu}">
	            <input type="hidden" name="bname" id="bname" value="${bname}">
		    </div>
		    <div class="box">
		    </div>
		    <div id="address-modal">
				<div id="wrapper"></div>
			</div>
		    <div class="search_addr">
		        <div class="white_box"></div>
		        <div class="search_input">
		            <input class="search1" type="text" id="address-search" value="${address}" placeholder="주소를 입력하세요." readonly="readonly"><div class="search_btn1"><i class="fa-solid fa-magnifying-glass fa-lg"></i><b class="find_addr">검색 하기</b></div>
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
		    <hr style="border:1px color= silver;" width="100%">
		</header>
	</div>
    <div class="main">
        <div class="row" style="border:none">
            <div class="col-sm-8">
                <div class="row" style=" border-radius: 6px; border: 1px solid gray;">
                    <img class="img-responsive col-sm-3" src="../restaurant-detail/img/sand.jpg"
                        style="border-right: 1px dotted gray; height: 110px;">
                    <ul class="list-unstyled col-sm-9">
                        <li>${restaurant.rst_name}</li>
                        <li>별점 : ${rating}</li>
                        <li>최소주문금액 : <fmt:formatNumber value="${restaurant.min_order}" pattern="#,###"/>원</li>
                        <li>배달시간 : ${restaurant.estimated_time}</li>
                    </ul>
                </div>
                <div class="restaurant-title row">
                    <ul class="nav nav-pills nav-justified">
                        <li class="nav-item"><a href="#menu-list" class="nav-link active" data-toggle="tab" id="menu-tab">메뉴</a></li>
                        <li class="nav-item"><a href="#review-list" class="nav-link" data-toggle="tab" id="review-tab">클린리뷰</a></li>
                        <li class="nav-item"><a href="#rst-info" class="nav-link" data-toggle="tab" id="info-tab">정보</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade show active" id="menu-list"></div>
                        <div class="tab-pane fade" id="review-list"></div>
                        <div class="tab-pane fade" id="rst-info"></div>
                   </div><!-- tab-content -->
               </div><!-- restaurant-title row -->
            </div> <!-- col-sm-8 -->
        </div> <!-- row -->
    </div><!-- main -->
    <jsp:include page="../component/footer.html" />
</body>
</html>
