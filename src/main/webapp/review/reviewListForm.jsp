<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../review/js/review.js"></script>
	<link href="../review/css/review.css" rel="stylesheet" type="text/css">
</head>
<body>
	<input type="hidden" id="review_rst" value="${rst_id}">
	<div class="container">
		<div class="row" style="padding: 0; margin: 0;">
			<div class="co col-sm-12">
				<div class="row" style="border: 1px solid gray; padding: 15px;">
					<div class="restaurant-star-point col-sm-6" style="border-right: 1px solid gray; text-align: center;">
						<h3>${rating}</h3>
						<div class="rating rating-large" data-rate="${rating}">
							<span class="star voted" rel="1">★</span>
							<span class="star voted" rel="2">★</span>
							<span class="star voted" rel="3">★</span>
							<span class="star voted" rel="4">★</span> 
							<span class="star" rel="5"></span>
						</div>
					</div>
				</div>
				<!-- row-->
				<div class="row" style="border: 1px solid grey; height: 50px;">
					<div class="review-count" style="top: 10px; width: 100%; margin-top: 10px;">
						<div class="review-c">
							리뷰 <strong class="ng-binding"><span id="review_count"><fmt:formatNumber value="${review_count}" pattern="#,###"/></span></strong> 개. 사장님댓글 <strong class="ng-binding"><fmt:formatNumber value="${reply_count}" pattern="#,###"/></strong> 개
							<div class="opt-btn" style="float: right; position: relative; right: 65px;">
								<span class="reviewbar" style="float: left;"> 사진리뷰만</span> <input type="checkbox" id="onlyPhotoReview"> <label for="onlyPhotoReview" class="switch_label"> <span class="onf_btn"></span>
								</label>

							</div>
						</div>
					</div>
					<!-- review-count-->
				</div>
				<!-- row-->
				<div id="form-review-list" class="row" style="display: block; border: 1px solid gray">
					<!-- 여기에 리뷰가 들어감 -->
				</div>
				<div class="row" style="display: block; border: 1px solid gray">
					<input type="hidden" id="pageNum" value="1">
					<div class="list-group-item btn-more" style="display: block; padding: 0; text-align: center; color: #FC6E4D; border-top: 1px solid gray;">
						<a ng-cilck="get_next_reviews()"> 
							<span> <input type="button" class="btn btn-warning" id="more-review-btn" value="더보기"> <i class="arr-down"></i> </span>
						</a>
					</div>
				</div>
				<!-- row-->
			</div>
			<!-- col-sm-8-->
		</div>
		<!-- row-->
	</div>
	<!-- contaniner-->
</body>
</html>
