<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
			<!DOCTYPE html>
			<html lang="ko">

			<head>
				<%-- 작성자: 김나연 작성완료일: 22/04/04 페이지명: 주문내역 jsp변환 : 정건영 변환일 : 2022/04/08 --%>
					<meta charset="UTF-8">
					<meta http-equiv="X-UA-Compatible" content="IE=edge">
					<meta name="viewport" content="width=device-width, initial-scale=1.0">
					<title>주문내역</title>
					<link rel="stylesheet" href="./css/myPage_order_style.css">
			</head>

			<body>
				<jsp:include page="../component/main_header_logAfter.html" />
				<main>
					<jsp:include page="./myPage.jsp" />
					<c:set var="orderCount" value="${fn:length(result)}" />
					<div class="content_box">
						<div class="order_wrapper" id="order_wrapper2">
							<h1>주문내역</h1>
							<div class="grid_box2">
								<!-- 주문내역 박스1 -->
								<div class="order_box2">
									<c:if test="${orderCount == 0}">
										<h3>주문내역이 없습니다.</h3>
									</c:if>
									<c:if test="${orderCount > 0}">
										<c:forEach var="order" items="${result}">
											<div class="order_box">
												<h3><a href="#">${order.rst_name}<span>></span></a></h3>
												<div class="del_btn">
													<a href="#">삭제</a>
												</div>
												<div class="order_img">
													<img src="${order.rst_logo}">
												</div>
												<div class="order_text2">
													<c:if test="${order.count == 0}">
														<p>${order.menu_name}</p>
													</c:if>
													<c:if test="${order.count > 0}">
														<p>${order.menu_name} 외 ${order.count}개</p>
													</c:if>
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