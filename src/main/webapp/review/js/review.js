/*
 * 작성자 : 정건영
 * 작성일 : 2022/04/24
 * 내용 : 리뷰리스트 스크립트
 */
$(function() {
	var pageNumElement = $('#pageNum');
	// 페이지가 로드 되었을 때 리뷰 리스트 출력
	$.ajax({
		type: "POST",
		url: "/EatsOrder/review/reviewList.do",
		data: "rst_id=" + $('#review_rst').val() + "&pageNum=1&onlyPhotoReview=false",
		dataType: "text",
		success: function(data) {
			$('#form-review-list').html(data);
		},
		error: function(request) {
			alert('오류 발생 : ' + request.statusText);
		}
	})
	
	// 사진리뷰만 보기 변경 시
	$('#onlyPhotoReview').change(function() {
		var rst_id = $('#review_rst').val();
		var onlyPhotoReview = $('#onlyPhotoReview').is(':checked');
		pageNumElement.val('1');
		// 리뷰 리스트를 비우고 사진 리뷰만 다시 조회하여 출력
		$.ajax({
			type: "POST",
			url: "/EatsOrder/review/reviewList.do",
			data: "rst_id=" + rst_id + "&pageNum=1&onlyPhotoReview=" + onlyPhotoReview,
			dataType: "text",
			success: function(data) {
				$('#form-review-list').empty();
				$('#form-review-list').html(data);
			},
			error: function(request) {
				alert('오류 발생 : ' + request.statusText);
			}
		})
	})
	
	// 더보기 버튼 클릭 시
	$('#more-review-btn').click(function() {
		pageNumElement.val(Number(pageNumElement.val()) + 1);
		var rst_id = $('#review_rst').val();
		var pageNum = pageNumElement.val();
		var onlyPhotoReview = $('#onlyPhotoReview').is(':checked');
		// 현재 리뷰 리스트 아레에 새로 조회한 리뷰 추가
		$.ajax({
			type: "POST",
			url: "/EatsOrder/review/reviewList.do",
			data: "rst_id=" + rst_id + "&pageNum=" + pageNum + "&onlyPhotoReview=" + onlyPhotoReview,
			dataType: "text",
			success: function(data) {
				$('#form-review-list').html(data);
			},
			error: function(request) {
				alert('오류 발생 : ' + request.statusText);
			}
		})
	})
})