//슬라이드 js
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

	$('#myModal').on('show.bs.modal', function (e) {
		$("#totAmt").html(11000);
		$("#result").html(1);
		$('input[name="check_1"]').each(function () {
			$(this).prop('checked', false);
		});
	});

	// 추가버튼 이벤트
	$("#plus").click(function () {
		fn_count(1);
	});

	// 삭제버튼 이벤트
	$("#minus").click(function () {
		fn_count(-1);
	});

	// 체크박스 클릭 이벤트 
	$('input[name="check_1"]').click(function () {
		var val = $(this).val();
		val = ($(this).is(':checked')) ? val : (val * -1);
		var totAmt = 0;
		totAmt = gfn_replace($("#totAmt").html());
		totAmt = parseInt(totAmt) + parseInt(val);
		$("#totAmt").html(totAmt.toLocaleString());
	});


});

function gfn_replace(val) {

	var rtnVal = "";

	rtnVal = val.replace("합계", "").replace("원", "").replace(",", "").replace(" ", "");

	return rtnVal;
}

function fn_count(value) {
	value = value || 0;

	var cnt = 0;
	var amt = 0;
	var price = 0;

	amt = parseInt(gfn_replace($("#amt").html()));
	cnt = parseInt(gfn_replace($("#result").html()));

	cnt = cnt + value;
	if (cnt < 1) cnt = 1;

	var optval = 0;
	$('input[name="check_1"]').each(function () {
		if ($(this).is(':checked')) {
			var val = parseInt($(this).val() || 0);
			optval = optval + val;
		}
	});

	price = (cnt * amt) + optval;

	$("#result").html(cnt);
	$("#totAmt").html("");
	$("#totAmt").html(price.toLocaleString());

}

//버튼클릭시
function buttonClick(bt) {
	alert(bt.value + "주문되었습니다");
}

//주문표 클릭시
function orderList(bt) {

	// 기존 리스트
	var orderList = $(".border").html();

	//기존 주문금액
	var orderListAmt = 0;
	orderListAmt = gfn_replace($("#total_amt").html());

	// 현재 주문 금액
	var totAmt = 0;
	totAmt = gfn_replace($("#totAmt").html());

	var amt = 0;
	amt = parseInt(orderListAmt) + parseInt(totAmt);

	$(".border").html(orderList + $("#menu").html() + "<br>");
	$("#total_amt").html("합계 " + amt.toLocaleString() + "원");









}
