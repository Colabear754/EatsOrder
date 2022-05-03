<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<link rel="stylesheet" href="../order/css/cart.css">
	<script type="text/javascript" src="../order/js/cart.js"></script>
</head>
<body>
	<div class="sticky">
		<input type="hidden" id="cart-rst_id" value="${rst_id}">
		<input type="hidden" id="order_price" value="${total_price}">
		<ul class="orderlist ">
			<li class="aorder">주문표</li>
			<c:if test="${rst_id != 0}">
				<li><h5><a class="order1"  href="/EatsOrder/restaurant/rst_form?rst_id=${rst_id}">${rst_name}</a></h5></li>
			</c:if>
			<c:forEach var="cartItem" items="${cartItems}">
				<li class="border">${cartItem.menu_name}
					<ul>
						<c:forEach var="option" items="${cartItem.selectedOptions}">
						<li>${option.option_name}</li>
						</c:forEach>
						</ul>
					<div class="price">× ${cartItem.quantity} : <fmt:formatNumber value="${cartItem.price}" pattern="#,###" />원</div>
					<button type="button" class="delete" onclick="deleteRow(this);">X</button>
					<input type="hidden" class="menu_id" value="${cartItem.menu_id}">
				</li>
				
			</c:forEach>
			<li class="corder"><span class="cart-text">합계</span></li>
			<li class="dorder"><span class="cart-text"><fmt:formatNumber value="${total_price}" pattern="#,###" />원</span></li>
			<c:if test="${using_point > 0}">
				<li class="corder"><span class="cart-text">포인트 할인</span></li>
				<li class="dorder"><span class="cart-text" id="using_point"><fmt:formatNumber value="${using_point}" pattern="#,###" />P</span></li>
			</c:if>
			<c:if test="${delivery_tip > 0}">
				<li class="corder"><span class="cart-text">배달비</span></li>
				<li class="dorder"><span class="cart-text"><fmt:formatNumber value="${delivery_tip}" pattern="#,###" />원</span></li>
			</c:if>
			<li class="corder"><span class="cart-text">전체금액</span></li>
			<li class="dorder" id="total_amt"><span class="cart-text"><fmt:formatNumber value="${total_price + delivery_tip - using_point}" pattern="#,###" />원</span></li>
		</ul>
		<c:if test="${not isOrderForm}">
			<button type="button" class="btn btn-warning" id="order-form-btn">주문하기</button>
		</c:if>
		<c:if test="${isOrderForm}">
			<button type="button" class="btn btn-warning" id="order-btn">주문하기</button>
		</c:if>
	</div>
</body>
</html>