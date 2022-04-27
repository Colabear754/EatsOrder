$(function() {
	$('button').click(function() {
		var dropdown = $(this).parent();
		
		if(!dropdown.hasClass('show')) {
			$(this).parent().addClass('show');
		} else {
			$(this).parent().removeClass('show');
		}
	})
	
	$('.menu').children('a').click(function() {
		var menu_id = $(this).children('.menu_id').val();
		
		$.ajax({
			type: "POST",
			url: "/EatsOrder/menu/menuInfo.do",
			data: "menu_id=" + menu_id,
			success: function(data) {
				$('#menu-info').html(data);
			},
			error: function(request) {
				alert('오류 발생 : ' + request.statusText);
			}
		})
	})
})