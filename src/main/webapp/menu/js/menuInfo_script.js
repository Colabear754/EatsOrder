$(function() {
	// 페이지 로드 시 기본 옵션 가격이 포함된 총 가격 계산
	var defaultOptPrice = $('#price').text().replace(/,/g, '').replace('원', '');
	
	$('input:radio:checked').each(function() {
		var optPrice = Number($(this).parent().parent().children('.price').text().replace(/,/g, '').replace('원', ''));
		defaultOptPrice = Number(defaultOptPrice) + Number(optPrice);
	})
	
	$('#total_price').text(defaultOptPrice.toLocaleString('ko-KR') + '원');
	
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
	
	$('#add-to-cart').click(function() {
		var menu_id = $('#menu_id').val();
		var quantity = $('#quantity').text();
		var options = [];
		
		$('input:checkbox:checked').each(function() {
			options.push($(this).val());
		})
		
		$('input:radio:checked').each(function() {
			options.push($(this).val());
		})
		
		$.ajax({
			type: "POST",
			url: "/EatsOrder/order/insertCartItem.do",
			data: {
				"menu_id": menu_id,
				"options": options,
				"quantity": quantity
			},
			success: function(data) {
				if (data > 0) {
					swal("주문표에 추가되었습니다.", "", "success").then((result) => {
    					if (result) {
    						$.ajax({
    							type: "POST",
    							url: "/EatsOrder/order/cart.do",
    							success: function(cart) {
    								$('#cart-area').html(cart);
    							},
    							error: function(request) {
    								alert('오류 발생1 : ' + request.statusText);
    							}
    						})
    					}
    				});
				}
			},
			error: function(request) {
				alert("오류 발생2 : " + request.statusText);
			}
		})
	})
})

function calcPrice() {
	// 가격 계산 함수
	var defaultPrice = Number($('#price').text().replace(/,/g, '').replace('원', ''));
	var quantity = Number($('#quantity').text());
	
	$('input:radio:checked').each(function() {
		var optPrice = Number($(this).parent().parent().children('.price').text().replace(/,/g, '').replace('원', ''));
		defaultPrice = Number(defaultPrice) + Number(optPrice);
	})
	
	$('input:checkbox:checked').each(function() {
		var optPrice = Number($(this).parent().parent().children('.price').text().replace(/,/g, '').replace('원', ''));
		defaultPrice = Number(defaultPrice) + Number(optPrice);
	})
	
	$('#total_price').text((defaultPrice * quantity).toLocaleString('ko-KR') + '원');
}