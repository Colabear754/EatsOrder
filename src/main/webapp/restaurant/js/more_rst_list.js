$(function() {
	$('.rst-link').click(function(e) {
		e.preventDefault();
		// 폼 생성 및 속성 설정
		var newForm = $('<form></form>');
		newForm.attr('method', 'post');
		newForm.attr('action', '/EatsOrder/restaurant/rst_form.do');
		// 폼에 항목 추가
		newForm.append($('<input>', {type: 'hidden', name: 'rst_id', value: $(this).attr('id')}));
		newForm.append($('<input>', {type: 'hidden', name: 'address', value: $('#address').val()}));
		newForm.append($('<input>', {type: 'hidden', name: 'sido', value: $('#sido').val()}));
		newForm.append($('<input>', {type: 'hidden', name: 'sigungu', value: $('#sigungu').val()}));
		newForm.append($('<input>', {type: 'hidden', name: 'bname', value: $('#bname').val()}));
		// 폼을 body에 추가
		newForm.appendTo('body');
		
		newForm.submit();
	})
})