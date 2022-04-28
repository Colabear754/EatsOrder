/*
 * 작성자 : 정건영
 * 작성일 : 2022/04/24
 * 내용 : 메뉴 목록 스크립트
 */

$(function() {
	// 드롭다운 메뉴 스크립트
	$('button').click(function() {
		var dropdown = $(this).parent();
		
		if(!dropdown.hasClass('show')) {
			$(this).parent().addClass('show');
		} else {
			$(this).parent().removeClass('show');
		}
	})
	
	// 메뉴 클릭 시 해당 메뉴를 모달창으로 띄움
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