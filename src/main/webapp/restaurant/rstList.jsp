<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<%-- 작성자: 허우림. 업로드일자:2022.04.09. 페이지 이름:매장목록페이지 
	jsp변환 : 정건영, 변환일 : 2022/04/15
--%>
<html>
<head>
    <meta charset="UTF-8">
    <title>매장목록</title>
    <link rel="stylesheet" type="text/css" href="./css/rst_list.css?1">
    <script src="https://kit.fontawesome.com/6cc0f3db28.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript" src="./js/rstList_script.js"></script>
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
		    <hr>
		</header>
		<br><br>
	    <div class="search">
	        <form name="rstForm" action="/EatsOrder/restaurant/rstList.do" method="post" id="rstForm">
	            <select name="orderBy" id="orderBy">
	                <option value="1" ${orderBy == 1 ? 'selected="selected"' : ''}>기본 정렬순</option>
	                <option value="2" ${orderBy == 2 ? 'selected="selected"' : ''}>별점순</option>
	                <option value="3" ${orderBy == 3 ? 'selected="selected"' : ''}>리뷰 많은 순</option>
	                <option value="4" ${orderBy == 4 ? 'selected="selected"' : ''}>최소 주문 금액 순</option>
	            </select>
	            <input class="search2" type="text" name="searchText" id="searchText" value="${searchText}" placeholder="음식점이나 메뉴를 검색해보세요">
	            <input type="hidden" name="category_id" value="${category_id}">
	            <input type="hidden" name="address" id="address" value="${address}">
	            <input type="hidden" name="sido" id="sido" value="${sido}">
	            <input type="hidden" name="sigungu" id="sigungu" value="${sigungu}">
	            <input type="hidden" name="bname" id="bname" value="${bname}">
	            <div class="search_btn2" onClick="document.getElementById('rstForm').submit();"><i class="fa-solid fa-magnifying-glass fa-lg"></i></div>
	        </form>
	    </div>
	    <br><br><br><br>
	    <c:set var="rstDataLength" value="${fn:length(rstData)}"></c:set>
	    <c:if test="${rstDataLength == 0}">
	    	<div id="no-rst">찾으시는 가게가 없습니다.</div>
	    </c:if>
	    <div class="outer-grid">
	    <c:if test="${rstDataLength > 0}">
	    	<c:forEach var="rstData" items="${rstData}"> 
	    	<a href="/EatsOrder/restaurant/rst_form.do" class="rst-link" id="${rstData.restaurant.rst_id}">
		        <div class="rst_photo"><img src="./img/${rstData.restaurant.rst_photo}" width="300px" height="200px"></div>
		        <div class="inner-grid">
		            <div class="rst_logo"><img src="./img/${rstData.restaurant.rst_logo}"></div>
		            <div class="rst_text">
		                <div class="rst_name">${rstData.restaurant.rst_name}</div>
		                <div class="rst_info">
		                    <img src="https://img.icons8.com/fluency/48/000000/star.png"/> 별점 ${rstData.rating}점 | 리뷰 <fmt:formatNumber value="${rstData.reviewCount}" pattern="#,###"/>개<br>
									                    		사장님 댓글 <fmt:formatNumber value="${rstData.replyCount}" pattern="#,###"/>개<br>
									                    		<fmt:formatNumber value="${rstData.restaurant.min_order}" pattern="#,###"/>원 이상 배달
		                </div>
		            </div>
		           </div>
	           </a>
	         </c:forEach>
	     </c:if>
	     </div>
	    <br>
	</div>
	<jsp:include page="../component/footer.html" />
</body>
</html>