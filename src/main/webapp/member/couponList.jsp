<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 쿠폰함
    
    수정자 : 정건영
    수정일 : 2022/04/06 
    -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쿠폰함</title>
    <link rel="stylesheet" href="./css/myPage_coupon_style.css">
</head>
<body>
    <main>
    	<c:set var="couponCount" value="${fn:length(couponData)}" />
        <div class="content_box">
            <div class="coupon_wrapper">
                <h1>쿠폰함</h1>
                <div class="coupon_all_box">
	                <c:if test="${couponCount == 0}">
	                	<h3>사용 가능한 쿠폰이 없습니다.</h3>
	                </c:if>
	                <c:if test="${couponCount > 0}">
	                	<c:forEach var="couponInfo" items="${couponData}">
		                    <div class="coupon_box">
		                        <div><img src="./img/coupon.png" alt="쿠폰-배경-이미지">
		                            <p><fmt:formatNumber value="${couponInfo.coupon.discount_amount}" pattern="#,###" />원</p>
		                        </div>
		                        <h3><fmt:formatNumber value="${couponInfo.coupon.available_price}" pattern="#,###" />원 이상 주문 시</h3>
		                        <p>${couponInfo.coupon.expiration_date}까지 사용</p>
		                        <p>${couponInfo.coupon.coupon_info}</p>
		                        <p>남은 사용 횟수 : ${couponInfo.available_count}</p>
		                    </div>
	                    </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </main>
</body>

</html>