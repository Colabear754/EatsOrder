/*
 * 작성자 : 정건영
 * 작성일 : 2022/04/28
 * 내용 : 주문하기 화면 스크립트
 */
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
	
	// 포인트 입력 시 자동 콤마
	$('#point').keyup(function() {
		$(this).val($(this).val().replace(',', '').toLocaleString('ko-KR'));
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
    
    // 포인트 사용
    $('#use-point').click(function(e) {
    	e.preventDefault();
    	
    	var member_point = $('#member_point').text().replace(',', '');
    	var using_point = $('#point').val();
    	
    	if (member_point < using_point) {
    		swal("사용 가능한 포인트가 부족합니다.", "", "error");
    	} else {
    		$.ajax({
    			type: "POST",
    			url: "/EatsOrder/order/cart.do",
    			data: {
    				"isOrderForm": true,
    				"using_point": using_point
    			},
    			success: function(result) {
    				$('#cart-area').empty();
					$('#cart-area').html(result);
				},
				error: function(request) {
					alert('오류 발생 : ' + request.statusText)
				}
    		})
    		
    		$('#using_point').val(using_point);
    	}
    })
    
    //카카오 지도 api
	$('#address1').click(function() {	// 주소 검색창을 클릭하면 모달 형태로 띄움
		new daum.Postcode({
			oncomplete : function(data) {	// 선택한 주소값을 세팅
				$('#address1').val(data.address)
				$('#address-modal').css('display', 'none')
			},
			useBannerLink: false
		}).embed(document.getElementById('wrapper'))

		$('#address-modal').css('display', 'flex')
	})

	$(document).mouseup(function(e) {
		if ($('#wrapper').has(e.target).length === 0) {
			$('#address-modal').css('display', 'none')
		}
	})
    
    $('.address').keyup(function() {
    	$(this).css('outline', '');
    })
})