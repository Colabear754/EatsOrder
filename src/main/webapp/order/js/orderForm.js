$(function() {
	// 페이지가 로드되면 주문표 출력
	$.ajax({
		type: "POST",
		url: "/EatsOrder/order/cart.do",
		data: "isOrderForm=true",
		success: function(data) {
			$('#cart-area').html(data);
		},
		error: function(request) {
			alert('오류 발생 : ' + request.statusText);
		}
	})
	
	// 전화번호를 입력하면 자동으로 하이픈 추가
	$('#phone').keyup(function() {
    	$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})/, "$1-$2-$3").replace("--", "-") );
    })
    
    // 요청사항 입력 시 이벤트
    $('#order-request').keyup(function() {
    	// 요청사항 길이를 출력
    	$('#text-length').text($(this).val().length);
    	
    	// 요청사항 길이가 100자를 초과하면 경고메시지 출력
    	if ($(this).val().length > 100) {
    		swal("요청 사항은 100자 이하로 입력해주세요.", "", "warning");
    		$(this).val($(this).val().substr(0, 100));
    	}
    })
})