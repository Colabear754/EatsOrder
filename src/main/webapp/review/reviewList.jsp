<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<c:forEach var="reviewData" items="${reviewDetailList}">
		<ul id="review" class="list-group review-list">
			<li class="list-group-item star-point" style="display: block;">
				<div>
					<span class="riview-id">${reviewData.writer}님</span> <span class="review-time">${reviewData.review.regist_date}</span> <a href="#" class="btn-report" style="position: absolute; right: 16px; color: #999">신고</a>
				</div>
				<div>
					<div class="star-point" style="display: block;">
						<span class="total"> <span class="full ">★</span>
						</span> <span class="total"> <span class="full ">★</span>
						</span> <span class="total"> <span class="full ">★</span>
						</span> <span class="total"> <span class="full ">★</span>
						</span> <span class="total"> <span class="full ">★</span>
						</span> <span class="rating" style="display: none;">${reviewData.review.rating}</span>
					</div>
				</div>
				<table class="info-images" style="width: 100%">
					<tbody>
						<tr>
							<c:if test="${reviewData.review.photo1 != null}">
								<td>
				                    <div class="reimg">
				                        <img src="./img/${reviewData.review.photo1}" style="max-width: 100%; width: 100%; height: 300px;">
				                    </div>
				                </td>
			                </c:if>
							<c:if test="${reviewData.review.photo2 != null}">
								<td>
				                    <div class="reimg">
				                        <img src="./img/${reviewData.review.photo2}" style="max-width: 100%; width: 100%; height: 300px;">
				                    </div>
				                </td>
			                </c:if>
							<c:if test="${reviewData.review.photo3 != null}">
								<td>
				                    <div class="reimg">
				                        <img src="./img/${reviewData.review.photo3}" style="max-width: 100%; width: 100%; height: 300px;">
				                    </div>
				                </td>
			                </c:if>
							<c:if test="${reviewData.review.photo4 != null}">
								<td>
				                    <div class="reimg">
				                        <img src="./img/${reviewData.review.photo4}" style="max-width: 100%; width: 100%; height: 300px;">
				                    </div>
				                </td>
			                </c:if>
							<c:if test="${reviewData.review.photo5 != null}">
								<td>
				                    <div class="reimg">
				                        <img src="./img/${reviewData.review.photo5}" style="max-width: 100%; width: 100%; height: 300px;">
				                    </div>
				                </td>
			                </c:if>
						</tr>
					</tbody>
				</table>
				<div class="order-items" style="width: 100%;">${reviewData.orderedItems}</div>
				<p class="ng-binding">${reviewData.review.content}</p>
			</li>
		</ul>
	</c:forEach>
</body>
</html>