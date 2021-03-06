<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 주문내역
    
    jsp변환: 정건영
    변환일: 2022/04/08
    --%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문내역</title>
    <link rel="stylesheet" href="./css/myPage_review_style.css">
</head>
<body>
	<jsp:include page="../component/main_header_logAfter.html" />
    <main>
		<jsp:include page="./myPage.jsp" />
        <c:set var="reviewCount" value="${fn:length(reviewData)}" />
        <div class="content_box">
            <div class="review_wrapper">
                <h1>리뷰관리</h1>
                <div class="review_all_box">
                	<c:if test="${reviewCount == 0}">
                		<h3>작성된 리뷰가 없습니다.</h3>
                	</c:if>
                	<c:if test="${reviewCount > 0}">
	                	<c:forEach var="reviewData" items="${reviewData}">
		                    <div class="review_box">
		                        <h3><a href="/EatsOrder/restaurant/rst_form.do?rst_id=${reviewData.restaurant.rst_id}">${reviewData.restaurant.rst_name}<span>></span></a></h3>
		                        <div class="star">
		                            <span>★</span>
		                            <span>★</span>
		                            <span>★</span>
		                            <span>★</span>
		                            <span>★</span>
		                            ${reviewData.review.rating}
		                        </div>
		                        <div class="del_btn">
		                        	<c:if test="${reviewData.overDate <= 7}">
		                            <a href="/EatsOrder/review/updateReviewForm.do">수정</a>
		                            <span>|</span>
		                            </c:if>
		                            <a href="/EatsOrder/review/deleteReviewForm.do">삭제</a>
		                        </div>
		                        <c:if test="${reviewData.review.photo1 != null}">
				                    <div class="review_img">
				                        <img src="${reviewData.review.photo1}">
				                    </div>
			                    </c:if>
			                    <c:if test="${reviewData.review.photo2 != null}">
				                    <div class="review_img">
				                        <img src="${reviewData.review.photo2}">
				                    </div>
			                    </c:if>
			                    <c:if test="${reviewData.review.photo3 != null}">
				                    <div class="review_img">
				                        <img src="${reviewData.review.photo3}">
				                    </div>
			                    </c:if>
			                    <c:if test="${reviewData.review.photo4 != null}">
				                    <div class="review_img">
				                        <img src="${reviewData.review.photo4}">
				                    </div>
			                    </c:if>
			                    <c:if test="${reviewData.review.photo5 != null}">
				                    <div class="review_img">
				                        <img src="${reviewData.review.photo5}">
				                    </div>
			                    </c:if>
		                        <div class="review_text">
		                            <div>선택메뉴</div>
		                            <p>${reviewData.review.content}</p>
		                        </div>
		                    </div>
	                    </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </main>
    <jsp:include page="../component/footer.html" />
</body>

</html>