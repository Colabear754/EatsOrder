/**
 * 매장목록, 매장상세 헤더 스크립트
 	작성자: 정건영
 	편집자: 허우림
 	작성일:22-04-30 
 */

 $(function(){
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
})
