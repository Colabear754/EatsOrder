<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="./js/more_rst_list.js"></script>
</head>
<body>
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
</body>
</html>