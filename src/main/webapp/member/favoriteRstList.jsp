<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 찜목록
    
    jsp변환 : 정건영
    변환일 : 2022/04/06

    찜목록 제목 위 div class,id 명 변경, 추가 : 김나연
    수정일 : 2022/05/01
    --%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>찜 목록</title>
    <link rel="stylesheet" href="./css/mypage_like_style.css">
</head>

<body>
	<jsp:include page="../component/main_header_logAfter.html" />
    <main>
		<jsp:include page="./myPage.jsp" />
    	<c:set var="favoriteRstCount" value="${fn:length(favoriteRstData)}" />
        <div class="content_box">
            <div class="like_wrapper" id="like_wrapper2">
                <h1>찜 목록</h1>
                <c:if test="${favoriteRstCount == 0}">
                	<h3>찜 매장이 없습니다.</h3>
                </c:if>
                <c:if test="${favoriteRstCount > 0}">
                	<c:forEach var="favoriteRst" items="${favoriteRstData}">
		                <div class="review_all_box">
		                    <div class="review_box">
		                        <h3><a href="/EatsOrder/restaurant/rst_form.do?rst_id=${favoriteRst.restaurant.rst_id}">${favoriteRst.restaurant.rst_name}<span>></span></a></h3>
		                        <div class="del_btn">
		                            <a href="#">삭제</a>
		                        </div>
		                        <div class="review_img">
		                            <img src="../restaurant/img/${favoriteRst.restaurant.rst_logo }" width="100px" height="100px">
		                        </div>
		                        <div class="review_text">
		                            <span>★</span><span>${favoriteRst.rating}</span>
		                            <p>최소주문 <fmt:formatNumber value="${favoriteRst.restaurant.min_order}" pattern="#,###" />원</p>
		                        </div>
		                    </div>
		                </div>
	                </c:forEach>
                </c:if>
            </div>
        </div>
    </main>
    <jsp:include page="../component/footer.html" />
</body>

</html>