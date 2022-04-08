<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 주문내역
    -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문내역</title>
    <link rel="stylesheet" href="./css/myPage_order_style.css">
</head>
<body>
    <main>
        <div class="content_box">
            <div class="review_wrapper">
                <h1>주문내역</h1>
                <div class="review_all_box">
                	<c:forEach var="order" items="${result}">
	                    <div class="review_box">
	                        <h3><a href="#">${order.rst_name}<span>></span></a></h3>
	                        <div class="del_btn">
	                            <a href="#">상세보기</a>
	                        </div>
	                        <div class="review_img">
	                            <img src="${order.rst_logo}">
	                        </div>
	                        <div class="review_text">
	                        	<c:if test="${order.count == 0}">
	                            	<p>${order.menu_name}</p>
	                            </c:if>
	                            <c:if test="${order.count > 0}">
	                            	<p>${order.menu_name} 외 ${order.count}개</p>
	                            </c:if>
	                        </div>
                    	</div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>
</body>
</html>