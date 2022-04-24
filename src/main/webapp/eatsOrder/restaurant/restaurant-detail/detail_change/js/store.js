/*2022.04.24. 김시웅 수정 좋아요기능추가 

*/
$(function () {

	$('#carouselExample').on('slide.bs.carousel', function (e) {
		var $e = $(e.relatedTarget);
		var idx = $e.index();
		var itemsPerSlide = 4;
		var totalItems = $('.carousel-item').length;
		if (idx >= totalItems - (itemsPerSlide - 1)) {
			var it = itemsPerSlide - (totalItems - idx);
			for (var i = 0; i < it; i++) $('.carousel-item').eq((e.direction == "left") ? i : 0).appendTo('.carousel-inner');
		}
	});
	
	$(".btn-like").click(function() {
		$(this).toggleClass("done");
	})
	
});