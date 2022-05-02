/*
 * 매장 상세 스크립트
 * 작성자 : 정건영
 * 작성일 : 2022/04/22
 */

$(function() {
	// 페이지가 처음 로드 되었을 땐 메뉴 리스트 출력
	$.ajax({
		type: "POST",
		url: "/EatsOrder/menu/menuList.do",
		data: "rst_id=" + $('#rst_id').val(),
		dataType: "text",
		success: function(data) {
			$('#menu-list').html(data);
		},
		error: function(request) {
			alert('오류 발생 : ' + request.statusText);
		}
	})
	
	// 페이지가 로드되면 주문표 출력
	$.ajax({
		type: "POST",
		url: "/EatsOrder/order/cart.do",
		success: function(data) {
			$('#cart-area').html(data);
		},
		error: function(request) {
			alert('오류 발생 : ' + request.statusText);
		}
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
				$('.modal-backdrop').remove();
				$('#address-modal').css('display', 'none');
				$('#wrapper').empty();
			},
			useBannerLink: false
		}).embed(document.getElementById('wrapper'))
	})
	
	// 매장 카테고리를 클릭하면 해당 카테고리 매장 목록으로 이동
	$('.category').click(function(e) {
		e.preventDefault();
		
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
		} else {
			swal("배달받을 주소를 입력해주세요.", "", "warning");
		}
	})
	
	// 매장 찜하기 기능
	$('#favorite').click(function() {
		if ($('#email').val() === '') {	// 로그인 되어있지 않으면 로그인 화면으로 이동
			location.href = "/EatsOrder/member/loginForm.do";
		} else {	// 로그인 되어있으면 찜하기 및 찜하기 취소
			var email = $('#email').val();
			var rst_id = $('#rst_id').val();
			
			$.ajax({
				type: "POST",
				url: "/EatsOrder/restaurant/favoriteRst.do",
				data: {
					"email": email,
					"rst_id": rst_id
				},
				success: function(result) {
					if (result == 1) {
						$('#favorite').addClass('done');
					} else if (result == 2) {
						$('#favorite').removeClass('done');
					} else {
						swal("알 수 없는 오류가 발생하였습니다.", "", "warning");
					}
				},
				error: function(request) {
					alert('오류 발생 : ' + request.statusText);
				}
			})
		}
		
		return false;
	})
	
	// 메뉴 탭 클릭 시
	$('#menu-tab').click(function() {
		var rst_id = $('#rst_id').val();
		
		if (!$('#menu-tab').hasClass('active')) {
			// 기존 탭 내용 모두 제거
			$('#menu-list').empty();
			$('#review-list').empty();
			$('#rst-info').empty();
			// 메뉴리스트를 받아와서 태그에 추가
			$.ajax({
				type: "POST",
				url: "/EatsOrder/menu/menuList.do",
				data: "rst_id=" + rst_id,
				dataType: "text",
				success: function(data) {
					$('#menu-list').html(data);
				},
				error: function(request) {
					alert('오류 발생 : ' + request.statusText);
				}
			})
		}
	})
	
	// 리뷰 탭 클릭 시
	$('#review-tab').click(function() {
		var rst_id = $('#rst_id').val();
		var pageNum = 1;
		
		if (!$('#review-tab').hasClass('active')) {
			// 기존 탭 내용 모두 제거
			$('#menu-list').empty();
			$('#review-list').empty();
			$('#rst-info').empty();
			// 리뷰리스트를 받아와서 태그에 추가
			$.ajax({
				type: "POST",
				url: "/EatsOrder/review/reviewListForm.do",
				data: "rst_id=" + rst_id + "&pageNum=" + pageNum,
				dataType: "text",
				success: function(data) {
					$('#review-list').html(data);
				},
				error: function(request) {
					alert('오류 발생 : ' + request.statusText);
				}
			})
		}
	})
	
	// 매장정보 탭 클릭 시
	$('#info-tab').click(function() {
		var rst_id = $('#rst_id').val();
		
		if (!$('#info-tab').hasClass('active')) {
			// 기존 탭 내용 모두 제거
			$('#menu-list').empty();
			$('#review-list').empty();
			$('#rst-info').empty();
			// 리뷰리스트를 받아와서 태그에 추가
			$.ajax({
				type: "POST",
				url: "/EatsOrder/restaurant/rst_info.do",
				data: "rst_id=" + rst_id,
				dataType: "text",
				success: function(data) {
					$('#rst-info').html(data);
				},
				error: function(request) {
					alert('오류 발생 : ' + request.statusText);
				}
			})
		}
	})
})