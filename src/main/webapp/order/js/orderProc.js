$(function() {
	if ($('#order_number').val() === '') {
		alert('주문 과정에서 오류가 발생했습니다.');
		location.history.back();
	} else {
		var newForm = $('<form></form>');
		newForm.attr('method', 'post');
		newForm.attr('action', '/EatsOrder/order/orderResult.do');
		// 폼에 항목 추가
		newForm.append($('<input>', {type: 'hidden', name: 'order_number', value: $('#order_number').val()}));
		newForm.append($('<input>', {type: 'hidden', name: 'rst_id', value: $('#rst_id').val()}));
		// 폼을 body에 추가
		newForm.appendTo('body');
		
		newForm.submit();
	}
})