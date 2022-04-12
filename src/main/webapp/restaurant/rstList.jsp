<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- 작성자: 허우림. 업로드일자:2022.04.09. 페이지 이름:매장목록페이지 -->
<html>
<head>
    <meta charset="UTF-8">
    <title>매장목록페이지</title>
    <link rel="stylesheet" type="text/css" href="res_list.css">
    <script src="https://kit.fontawesome.com/6cc0f3db28.js" crossorigin="anonymous"></script>
</head>
<body>
<script>
</script>
<div class="container">
<header>
    <div class="box">
    </div>
    <div class="search_addr">
        <div class="white_box"></div>
        <div class="search_input">
            <input class="search1" type="text" placeholder="주소를 입력하세요."><div class="search_btn1"><i class="fa-solid fa-magnifying-glass fa-lg"></i><b class="find_addr">주소 찾기</b></div>
        </div>
    </div>
    <div class="grid">
        <div class="category"><a href="/EatsOrder/rstList.do">전체보기</a></div>
        <div class="category"><a href="#">1인분 주문</a></div>
        <div class="category"><a href="#">프랜차이즈</a></div>
        <div class="category"><a href="#">치킨</a></div>
        <div class="category"><a href="#">피자/양식</a></div>
        <div class="category"><a href="#">중국집</a></div>
        <div class="category"><a href="#">한식</a></div>
        <div class="category"><a href="#">일식/돈까스</a></div>
        <div class="category"><a href="#">족발/보쌈</a></div>
        <div class="category"><a href="#">야식</a></div>
        <div class="category"><a href="#">분식</a></div>
        <div class="category"><a href="#">카페/디저트</a></div>
        <div class="category"><a href="#">편의점/마트</a></div>
    </div>
    <hr style="border:1px color= silver;" width="100%">
</header>
<br><br>
    <div class="search">
        <form name="rstForm" action="/EatsOrder/restaurant/rstInfo.do" id="resForm">
            <select name="orderBy" >
                <option value="1">기본 정렬순</option>
                <option value="2">별점순</option>
                <option value="3">리뷰 많은 순</option>
                <option value="4">최소 주문 금액 순</option>
            </select>
                <input class="search2" type="text" name="searchText" placeholder="음식점이나 메뉴를 검색해보세요">
            <div class="search_btn2" onClick="document.getElementById('rstForm').submit();"><i class="fa-solid fa-magnifying-glass fa-lg"></i></div>
        </form>
    </div>
    <br><br><br><br>
    <!-- 매장 검색 결과가 없으면 -->
    <c:if test="${rstData.isEmpty()}">
    	매장 등록 준비 중입니다.
    </c:if>
    <!-- 매장 검색 결과가 있으면 -->
    <c:if test="${rstData.isEmpty()==false}">
    <div class="title">잇츠오더 플러스</div><br>
    
    <div class="outer-grid">
    	<c:forEach var="rstData" items="${rstData}"> <!-- begin="0" end="4" 배열중에 일부만 출력 가능. 나중에 잇츠오더 플러스, 잇츠오더 등록가게 구분해서 출력할때 사용하기  -->
    	<a href="/EatsOrder/restaurant/rstInfo.do?rst_id=${rstData.getRst_id}">
	        <div class="rst_photo">${rstData.getRst_photo}</div>
	        <div class="inner-grid">
	            <div class="rst_logo">${rstData.getRst_logo}</div>
	            <div class="rst_text">
	                <div class="rst_name">${rstData.getRst_name}</div>
	                <div class="rst_info">
	                    <img src="star.png"/> 별점 ${rstData.getRating}점 | 리뷰 ${rstData.getReviewCount}개<br>
								                    		사장님 댓글 ${rstData.getReplyCount}개<br>
								                    		${rstData.getDelivery_tip}원 이상 배달
	                </div>
	            </div>
	           </div>
           </a>
         </c:forEach>
     </div>
     </c:if>
    <br>
    <div class="title">잇츠오더 등록가게</div><br>
    
</div>
</body>
</html>