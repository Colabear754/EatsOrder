/* 2022년 04.24 김시웅 
*/
$(function () {
    var review_count = $('#review_count').text().replace(',', '');
    var pageNum = $('#pageNum').val()

    //별점 
    $('.rating').each(function(){
    	var rate = $(this).data("rate");
    	$(this).find('.star').each(function(n) {
    		if (n < rate) $(this).show();
    	});
    });

    // 더보기 버튼
    if (review_count <= pageNum * 10) {
    	$('.btn-more').hide();
    } else {
    	$('.btn-more').show();
    }
});
    