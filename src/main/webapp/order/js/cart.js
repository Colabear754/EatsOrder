/*
 * 작성자 : 정건영
 * 작성일 : 2022/04/28
 * 내용 : 주문표 스크립트
 */

$(function() {
	// 매장 링크
	$('#rst-link').click(function(e) {
		e.preventDefault();
		
		var newForm = $('<form></form>');
		newForm.attr('method', 'post');
		newForm.attr('action', '/EatsOrder/restaurant/rst_form.do');
		// 폼에 항목 추가
		newForm.append($('<input>', {type: 'hidden', name: 'address', value: $('#address').val()}));
		newForm.append($('<input>', {type: 'hidden', name: 'sido', value: $('#sido').val()}));
		newForm.append($('<input>', {type: 'hidden', name: 'sigungu', value: $('#sigungu').val()}));
		newForm.append($('<input>', {type: 'hidden', name: 'bname', value: $('#bname').val()}));
		newForm.append($('<input>', {type: 'hidden', name: 'searchText', value: $('#searchText').val()}));
		newForm.append($('<input>', {type: 'hidden', name: 'rst_id', value: $('#cart-rst_id').val()}));
		// 폼을 body에 추가
		newForm.appendTo('body');
		
		newForm.submit();
	})
	
	// 매장 상세 페이지에서 주문하기 버튼 클릭 시
	$('#order-form-btn').click(function() {
		var min_order = Number($('#min_order').text().replace(',', ''));
		var total_price = Number($('#order_price').val());
		
		if ($('#email').val() === '') {	// 로그인 되어있지 않으면 로그인 화면으로 이동
			location.href = "/EatsOrder/member/loginForm.do";
		} else if (min_order > total_price) {
			swal("주문 금액이 부족합니다.", (min_order - total_price).toLocaleString('ko-KR') + "원 이상 메뉴를 추가해야 주문할 수 있습니다.", "warning");
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
		var member_point = $('#member_point').text().replace(',', '').replace('P', '');
		
		if ($('#coupon_id').val() != '') {
			var coupon_id = $('#coupon_id').val();
		}
		
		if ($('#using_point').val() != '') {
			used_point = $('#using_point').val();
		}
		
		if ($('#address1').val() === '' || $('#address2').val() === '') {
			$('.address_error').css('display', 'block');
			$('.address').css("outline", "2px solid red");
		} else if (payment_method == '') {
			swal("결제 수단을 선택하여 주세요.", "", "warning");
		} else if (member_point < used_point) {
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
	
	// 메뉴 삭제 버튼 클릭 시
	$('.delete').click(function() {
		var menu_id = $(this).siblings('.menu_id').val();
		
		swal({
			title: "메뉴를 삭제하시겠습니까?",
			icon: "warning",
			buttons: [
				'아니오', '예'
			]
		}).then((result) => {
			if (result) {
				$.ajax({
					type: "POST",
					url: "/EatsOrder/order/deleteCartItem.do",
					data: "menu_id=" + menu_id,
					success: function(dresult) {
						if (dresult > 0) {
							$.ajax({
								url: "/EatsOrder/order/cart.do",
								success: function(cart) {
									$('#cart-area').empty();
    								$('#cart-area').html(cart);
								}
							})
						}
					},
					error: function(request) {
						alert('오류 발생 : ' + request.statusText);
					}
				})
			}
		});
	})
	
	// 주문표의 매장id와 매장상세 페이지의 매장id가 일치하지 않으면 주문하기 버튼 비활성화
	if ($('#rst_id').val() != $('#cart-rst_id').val()) {
		$('#order-form-btn').prop('disabled', true);
	}
	
	// 주문표의 매장id와 매장상세 페이지의 매장id가 일치하면 주문하기 버튼 활성화
	if ($('#rst_id').val() == $('#cart-rst_id').val()) {
		$('#order-form-btn').prop('disabled', false);
	}
})