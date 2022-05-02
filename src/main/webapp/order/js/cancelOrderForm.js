$(function() {
	// 주문취소 버튼 클릭 시
	$('#cancel').click(function() {
		var order_number = $('#order_number').text();
		var reason_cancellation = $('#resaon_cancellation').val();
		// 주문취소 사유를 5글자 미만으로 입력하면 경고문 출력
		if($('#resaon_cancellation').val().replace(' ', '') < 5) {
			$('#resaon_cancellation').css('outline', '2px solid red');
			$('.error').css('display', 'block');
		} else {
			$.ajax({
				type: "POST",
				url: "/EatsOrder/order/cancelOrder.do",
				data: {
					"order_number": order_number,
					"reason_cancellation": reason_cancellation
				},
				success: function(result) {
					if (result > 0) {
						swal("주문이 취소되었습니다.", "", "success").then(() => {
							location.href = "/EatsOrder/member/orderList.do";
						})
					} else {
						swal("주문이 접수되어 취소가 불가능합니다. 매장에 직접 문의해주세요.", "", "error");
					}
				},
				error: function(request) {
					alert('오류 발생 : ' + request.statusText);
				}
			})
		}
	})
	
	$('#resaon_cancellation').keyup(function() {
    	// 취소사유 길이를 출력
    	$('#text-length').text($(this).val().length);
    	
    	$(this).css('outline', '');
    	
    	// 취소사유 길이가 100자를 초과하면 경고메시지 출력
    	if ($(this).val().length > 100) {
    		swal("주문취소 사유는 100자 이하로 입력해주세요.", "", "warning");
    		$(this).val($(this).val().substr(0, 100));
    	}
    })
})