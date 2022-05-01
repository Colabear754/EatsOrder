/*
 * 작성자 : 정건영
 * 작성일 : 2022/04/30
 * 내용 : 메인 헤더 주문표 버튼 스크립트
 */

$(function() {
	$('#cart').click(function(e) {
		e.preventDefault();
		
		$.ajax({
			type: "POST",
			url: "/EatsOrder/member/cart.do",
			success: function(result) {
				if (result > 0) {
					location.href = '/EatsOrder/restaurant/rst_form.do?rst_id=' + result;
				} else {
					swal("주문표에 등록된 메뉴가 없습니다.", "", "error");
				}
			}
		})
	})
})