$(function() {
	$('button').click(function() {
		var dropdown = $(this).parent();
		
		if(!dropdown.hasClass('show')) {
			$(this).parent().addClass('show');
		} else {
			$(this).parent().removeClass('show');
		}
	})
})