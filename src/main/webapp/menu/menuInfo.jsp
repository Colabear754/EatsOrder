<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<!--  아래 부트스트랩은 슬라이드를 위한 부트스랩이라 삭제하지는 않고 주석처리로 해놓았습니다  2022.04.27 김시웅 부트스트랩충돌 수정 -->
	<!--  link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" -->
	<link href="../menu/css/menuInfo.css" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="../menu/js/menuInfo_script.js"></script>
</head>
<body>
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title">메뉴상세</h4>
			<button type="button" class="close" data-dismiss="modal">×</button>
		</div>
		<div class="modal-body">
			<input type="hidden" id="menu_id" value="${menu.menu_id}">
			<div class="detail-image">
				<img src="../menu/img/${menu.menu_photo}">
			</div>
			<!--detail-image-->
			<div class="detail-text" id="menu">${menu.menu_name}</div>
			<div class="detail-price">
				<strong>가격</strong>
				<div class="price" id="price">
					<fmt:formatNumber value="${menu.price}" pattern="#,###" />원
				</div>
			</div>
			<!--detail-price-->
			<div class="option-list-wrap">
				<div class="option-list-tit">
					<strong>추가메뉴</strong>
				</div>
				<!--option-list-tit-->
				<div class="option-list">
					<c:forEach var="optionData" items="${optionList}">
						<c:if test="${optionData.optionGroup.essential == 1}">
							<lable class="menu-container">
							<div>${optionList.optionGroup.group_name}(필수선택)</div>
							<c:forEach var="option" items="${optionData.optionList}" varStatus="status">
								<c:if test="${status.first}">
									<div>
										<label><input type="radio" id="${option.option_id}" name="opt${optionData.optionGroup.group_id}" value="${option.option_id}" checked="checked" />&nbsp;${option.option_name}</label> <span class="price"><fmt:formatNumber value="${option.price}" pattern="#,###" />원</span>
									</div>
								</c:if>
								<c:if test="${not status.first}">
									<div>
										<label><input type="radio" id="${option.option_id}" name="opt${optionData.optionGroup.group_id}" value="${option.option_id}" />&nbsp;${option.option_name}</label> <span class="price"><fmt:formatNumber value="${option.price}" pattern="#,###" />원</span>
									</div>
								</c:if>
							</c:forEach> 
							</lable>
						</c:if>
						<c:if test="${optionData.optionGroup.essential == 0}">
							<lable class="menu-container">
							<div>${optionData.optionGroup.group_name}(선택사항)</div>
							<c:forEach var="option" items="${optionData.optionList}">
								<div>
									<label><input type="checkbox" id="${option.option_id}" name="opt${optionData.optionGroup.group_id}" value="${option.option_id}" />&nbsp;${option.option_name}</label> <span class="price"><fmt:formatNumber value="${option.price}" pattern="#,###" />원</span>
								</div>
							</c:forEach>
							</lable>
						</c:if>
					</c:forEach>
				</div><!--option-list-->
			</div><!-- option-list-wrap-->
			<div class="quantity-control">
				<strong>수량</strong> <input type="button" id="plus" value='+' style="float: right; width: 25px;" />
				<div id="quantity" style="float: right; padding-left: 10px; padding-right: 10px;">1</div>
				<input type="button" id="minus" value='-' style="float: right; width: 25px;" />
			</div><!--quantity-control-->
			<div class="detail-price">
				<strong>총주문금액</strong>
				<div class="price" id="total_price"></div>
			</div><!--detail-price-->
		</div><!--modal-body-->
		<div class="modal-footer">
			<div class="detail btn-wrap">
				<button class="btn-add-cart" id="add-to-cart">주문표에추가</button>
				<button class="btn-order" id="order">주문하기</button>
			</div><!-- detail btn-wrap -->
		</div><!-- modal-footer -->
	</div><!-- content-->
</body>
</html>
