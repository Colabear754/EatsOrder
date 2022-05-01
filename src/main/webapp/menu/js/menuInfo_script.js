/*
 * 작성자 : 정건영
 * 작성일 : 2022/04/26
 * 내용 : 메뉴 상세정보 스크립트
 */

$(function() {
	// 페이지 로드 시 기본 옵션 가격이 포함된 총 가격 계산
	var defaultOptPrice = $('#price').text().replace(/,/g, '').replace('원', '');
	
	$('input:radio:checked').each(function() {
		var optPrice = Number($(this).parent().siblings('.price').text().replace(/,/g, '').replace('원', ''));
		defaultOptPrice = (Number(defaultOptPrice) + Number(optPrice)).toLocaleString('ko-KR');
	})
	
	$('#total_price').text(defaultOptPrice + '원');
	
	// +, - 버튼으로 수량 조절 시
	$('#plus').click(function() {	
		var quantity = Number($('#quantity').text());
		$('#quantity').text(quantity + 1);
		calcPrice();
	})
	
	$('#minus').click(function() {
		var quantity = Number($('#quantity').text());
		
		if (quantity > 1) {
			$('#quantity').text(quantity - 1);
			calcPrice();
		}
	})
	
	// 체크박스와 라디오버튼 체크 여부가 변동될 때마다 가격 재계산 
	$('input:checkbox').change(function() {
		calcPrice();
	})
	
	$('input:radio').change(function() {
		calcPrice();
	})
	
	// 장바구니 추가
	$('#add-to-cart').click(function() {
		if ($('#email').val() === '') {	// 로그인 되어있지 않으면 로그인 화면으로 이동
			location.href = "/EatsOrder/member/loginForm.do";
		} else {	// 로그인 되어있으면 장바구니 추가
			var menu_id = $('#menu_id').val();
			var quantity = $('#quantity').text();
			var options = [];
			
			$('input:checkbox:checked').each(function() {
				console.log($(this).val())
				options.push($(this).val());
			})
			
			$('input:radio:checked').each(function() {
				options.push($(this).val());
			})
			
			$.ajax({
				type: "POST",
				url: "/EatsOrder/order/insertCartItem.do",
				traditional: true,
				data: {
					"menu_id": menu_id,
					"options": options,
					"quantity": quantity
				},
				success: function(data) {
					if (data > 0) {
						swal("주문표에 추가되었습니다.", "", "success").then(() => {
							$.ajax({
    							type: "POST",
    							url: "/EatsOrder/order/cart.do",
    							success: function(cart) {
    								$('#cart-area').empty();
    								$('#cart-area').html(cart);
    							},
    							error: function(request) {
    								alert('오류 발생1 : ' + request.statusText);
    							}
    						})
    						
    						$('#menu-modal').modal('hide');
	    				});
					} else if (data < -1) {
						swal({
							title: "주문표에 다른 매장에서 선택한 메뉴가 있습니다.",
							text: "주문표를 비우고 메뉴를 추가하시겠습니까?",
							icon: "warning",
							buttons: [
								'아니오', '예'
							]
						}).then((result) => {
							if (result) {
								$.ajax({
									type: "POST",
									url: "/EatsOrder/order/cleanCart.do",
									success: function(cleanResult) {
										if (cleanResult > 0) {
											$.ajax({
												type: "POST",
												url: "/EatsOrder/order/insertCartItem.do",
												traditional: true,
												data: {
													"menu_id": menu_id,
													"options": options,
													"quantity": quantity
												},
												success: function(data) {
													if (data > 0) {
														swal("주문표에 추가되었습니다.", "", "success").then((result2) => {
															if (result2) {
																$.ajax({
																	type: "POST",
																	url: "/EatsOrder/order/cart.do",
																	success: function(cart) {
																		$('#cart-area').empty();
																		$('#cart-area').html(cart);
																	},
																	error: function(request) {
																		alert('오류 발생2 : ' + request.statusText);
																	}
																})
	    						
																$('#menu-modal').modal('hide');
															}
														});
													}
												},
												error: function(request) {
													alert('오류 발생3 : ' + request.statusText);
												}
											})
										}
									}
								})
							}
						})
					}
				},
				error: function(request) {
					alert("오류 발생4 : " + request.statusText);
				}
			})
		}
	})
	
	// 주문하기
	$('#btn-order').click(function() {
		// 메뉴 상세 화면에서 주문하기 버튼을 누르면 주문표에 메뉴를 추가한 후 주문 화면으로 이동
		if ($('#email').val() === '') {	// 로그인 되어있지 않으면 로그인 화면으로 이동
			location.href = "/EatsOrder/member/loginForm.do";
		} else {	// 로그인 되어있으면 장바구니 추가
			var menu_id = $('#menu_id').val();
			var quantity = $('#quantity').text();
			var options = [];
			
			$('input:checkbox:checked').each(function() {
				console.log($(this).val())
				options.push($(this).val());
			})
			
			$('input:radio:checked').each(function() {
				options.push($(this).val());
			})
			
			$.ajax({
				type: "POST",
				url: "/EatsOrder/order/insertCartItem.do",
				traditional: true,
				data: {
					"menu_id": menu_id,
					"options": options,
					"quantity": quantity
				},
				success: function(data) {
					if (data > 0) {
						location.href = "/EatsOrder/order/orderForm.do";
					}
				},
				error: function(request) {
					alert("오류 발생 : " + request.statusText);
				}
			})
		}
	})
})

function calcPrice() {
	// 가격 계산 함수
	var defaultPrice = Number($('#price').text().replace(/,/g, '').replace('원', ''));
	var quantity = Number($('#quantity').text());
	
	$('input:radio:checked').each(function() {
		var optPrice = Number($(this).parent().siblings('.price').text().replace(/,/g, '').replace('원', ''));
		defaultPrice = Number(defaultPrice) + Number(optPrice);
	})
	
	$('input:checkbox:checked').each(function() {
		var optPrice = Number($(this).parent().siblings('.price').text().replace(/,/g, '').replace('원', ''));
		defaultPrice = Number(defaultPrice) + Number(optPrice);
	})
	
	$('#total_price').text((defaultPrice * quantity).toLocaleString('ko-KR') + '원');
}