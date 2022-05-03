/*
 * 매장 목록 스크립트
 * 작성자 : 정건영
 * 작성일 : 2022/04/14
 */

$(function() {
//	var total_list;
//	
//	if (location.hash) {
//		var data = history.state;
//		if (data) {
//			$('.outer-grid').append(data.list);
//			$('#address-search').val(data.address);
//			$('#address').val(data.address);
//			$('#sido').val(data.sido);
//			$('#sigungu').val(data.sigungu);
//			$('#bname').val(data.bname);
//			$('#pageNum').val(data.page);
//		}
//	}

	toggleMoreBtn();
	
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
	
	//카카오 지도 api
	$('#address-search').click(function() {	// 주소 검색창을 클릭하면 모달 형태로 띄움
		new daum.Postcode({
			oncomplete : function(data) {	// 선택한 주소값을 세팅
				$('#address-search').val(data.address)
				$('#address').val(data.address)
				$('#sido').val(data.sido)
				$('#sigungu').val(data.sigungu)
				$('#bname').val(data.bname)
				$('#address-modal').css('display', 'none')
				$('#wrapper').empty()
			},
			useBannerLink: false
		}).embed(document.getElementById('wrapper'))

		$('#address-modal').css('display', 'flex')
	})

	$(document).mouseup(function(e) {
		if ($('#wrapper').has(e.target).length === 0) {
			$('#address-modal').css('display', 'none')
		}
	})
	
	// 카테고리 변경
	$('.category').click(function() {
		if ($('#address').val() != '') {
			$('#pageNum').val('1');
			// 폼 생성 및 속성 설정
			var newForm = $('<form></form>');
			newForm.attr('method', 'post');
			newForm.attr('action', '/EatsOrder/restaurant/rstList.do');
			// 폼에 항목 추가
			newForm.append($('<input>', {type: 'hidden', name: 'category_id', value: $(this).attr('id')}));
			newForm.append($('<input>', {type: 'hidden', name: 'address', value: $('#address').val()}));
			newForm.append($('<input>', {type: 'hidden', name: 'sido', value: $('#sido').val()}));
			newForm.append($('<input>', {type: 'hidden', name: 'sigungu', value: $('#sigungu').val()}));
			newForm.append($('<input>', {type: 'hidden', name: 'bname', value: $('#bname').val()}));
			newForm.append($('<input>', {type: 'hidden', name: 'searchText', value: $('#searchText').val()}));
			// 폼을 body에 추가
			newForm.appendTo('body');
			
			newForm.submit();
			return false;
		}
	})
	
	// 정렬 순서 변경
	$('#orderBy').change(function() {
		$('#rstForm').submit();
	})
	
	// 매장 더보기
	$('#more-rst-btn').click(function() {
		$('#pageNum').val(Number($('#pageNum').val()) + 1);
		
		$.ajax({
			type: "POST",
			url: "/EatsOrder/restaurant/more_rst_list.do",
			data: {
				"sido": $('#sido').val(),
				"sigungu": $('#sigungu').val(),
				"bname": $('#bname').val(),
				"pageNum": $('#pageNum').val()
			},
			success: function(data) {
				$('.outer-grid').append(data);
//				total_list += data;
//				history.replaceState(
//						{
//							list: total_list, 
//							page: $('#pageNum').val(),
//							address: $('#address').val(),
//							sido: $('#sido').val(),
//							sigungu: $('#sigungu').val(),
//							bname: $('#bname').val()
//						}, 
//						'page ' + $('#pageNum').val(), 
//						'/EatsOrder/restaurant/rstList.do##');
				toggleMoreBtn();
			},
			error: function(request) {
				alert('오류 발생 : ' + request.statusText);
			}
		})
	})
})

function toggleMoreBtn() {
	if ($('#rst_count').val() <= $('#pageNum').val() * 12) {
		$('.row').hide();
	} else {
		$('.row').show();
	}
}