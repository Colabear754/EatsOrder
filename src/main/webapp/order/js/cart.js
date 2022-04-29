/*
 * 작성자 : 정건영
 * 작성일 : 2022/04/28
 * 내용 : 주문표 스크립트
 */

$(function() {
	// 매장 상세 페이지에서 주문하기 버튼 클릭 시
	$('#order-form-btn').click(function() {
		if ($('#email').val() === '') {	// 로그인 되어있지 않으면 로그인 화면으로 이동
			location.href = "/EatsOrder/member/loginForm.do";
		} else {	// 로그인 되어있으면 주문하기 화면으로 이동
			var newForm = $('<form></form>');
			newForm.attr('method', 'post');
			newForm.attr('action', '/EatsOrder/order/orderForm.do');
			// 폼에 항목 추가
			newForm.append($('<input>', {type: 'hidden', name: 'address', value: $('#address').val()}));
			newForm.append($('<input>', {type: 'hidden', name: 'rst_id', value: $('#rst_id').val()}));
			// 폼을 body에 추가
			newForm.appendTo('body');
			
			newForm.submit();
		}
	})
	
	// 주문하기 페이지에서 버튼 클릭 시
	$('#order-btn').click(function() {
		var orderer = $('#orderer').val();
		var rst_id = $('#rst_id').val();
		var destination = $('#address1').val() + ' ' + $('#address2').val();
		var coupon_id = null;
		var used_point = 0;
		var payment_method = $('input:radio[name="c1"]:checked').siblings('span').text();
		var order_request = $('#order-request').val();
		
		if ($('#coupon_id').val() != '') {
			var coupon_id = $('#coupon_id').val();
		}
		
		if ($('#point').val() != '') {
			var used_point = $('#point').val();
		}
		
		if (payment_method == '') {
			swal("결제 수단을 선택하여 주세요.", "", "warning");
		} else if (Number($('#member_point').val()) < Number(used_point)) {
			swal("사용하려는 포인트가 사용 가능한 포인트보다 많습니다.", "", "warning");
		} else {
			// 폼 생성 및 속성 설정
			var newForm = $('<form></form>');
			newForm.attr('method', 'post');
			newForm.attr('action', '/EatsOrder/order/orderProc.do');
			// 폼에 항목 추가
			newForm.append($('<input>', {type: 'hidden', name: 'orderer', value: orderer}));
			newForm.append($('<input>', {type: 'hidden', name: 'rst_id', value: rst_id}));
			newForm.append($('<input>', {type: 'hidden', name: 'destination', value: destination}));
			newForm.append($('<input>', {type: 'hidden', name: 'coupon_id', value: coupon_id}));
			newForm.append($('<input>', {type: 'hidden', name: 'used_point', value: used_point}));
			newForm.append($('<input>', {type: 'hidden', name: 'payment_method', value: payment_method}));
			newForm.append($('<input>', {type: 'hidden', name: 'order_request', value: order_request}));
			// 폼을 body에 추가
			newForm.appendTo('body');
			
			newForm.submit();
		}
	})
})