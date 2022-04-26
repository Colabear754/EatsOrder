<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
</head>
<body>
	<div class="sticky">
		<ul class="orderlist ">
			<li class="aorder">주문표</li>
			<c:forEach var="cartItem" items="${cartItems}">
				<li class="border">${cartItem.menu_name}× ${cartItem.quantity}<span class="price"><fmt:formatNumber value="${cartItem.total_price}" pattern="#,###" />원</span>
					<ul>
						<li>${cartItem.option_name}</li>
					</ul>
				</li>
			</c:forEach>
			<c:if test="${delivery_tip > 0}">
				<li class="corder"><span class="cart-text">배달비</span></li>
				<li class="dorder"><span class="cart-text"><fmt:formatNumber value="${delivery_tip}" pattern="#,###" />원</span></li>
			</c:if>
			<li class="corder"><span class="cart-text">전체금액</span></li>
			<li class="dorder" id="total_amt"><span class="cart-text"><fmt:formatNumber value="${total_price}" pattern="#,###" />원</span></li>
		</ul>
		<button type="button" class="btn btn-warning" onclick="window.location.href='/EatsOrder/orderForm.do'">주문하기</button>
	</div>
</body>
</html>