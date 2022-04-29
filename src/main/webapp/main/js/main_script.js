/* 
작성자: 김나연
작성완료일: 22/04/04
페이지명: 메인페이지

모달 관련 수정 : 정건영
수정일 : 2022/04/08
수정내용 : 주소 검색창을 모달 형태로 띄우도록 수정

alert 관련 수정 : 김나연
수정일 : 2022/04/28
수정내용 : alert을 sweetalert으로 수정
*/

//주소, 음식점 공백 확인
$(document).ready(function(){
    $("form").submit(function(){
        //주소 공백 확인
        if($("#address").val() == ""){
          swal({ icon: "info", title: "주소를 입력해주세요" }).then(function () {
        $("#addr_search").focus();
      });
      return false;
        }
    });
});

$(function() {
	//카카오 지도 api
	$('#address').click(function() {	// 주소 검색창을 클릭하면 모달 형태로 띄움
		new daum.Postcode({
			oncomplete : function(data) {	// 선택한 주소값을 세팅
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
	
	// 카테고리 아이템 선택 시 폼을 생성하여 매장 목록으로 이동
	$('.food_item').click(function() {
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
			newForm.append($('<input>', {type: 'hidden', name: 'searchText', value: ''}));
			// 폼을 body에 추가
			newForm.appendTo('body');
			
			newForm.submit();
			return false;
		}
	})
})

//음식점,메뉴 미선택 시 카테고리로 스크롤 이동
$(document).ready(function(){
	var offset = $("#main_food_text").offset(); //이동할 위치
	$("form").submit(function(){
		//음식점,메뉴는 공백, 주소는 공백이 아닐 때 스크롤
		if($("#food_search").val() == "" && $("#address").val()!=""){
			$("html, body").animate({scrollTop: offset.top},100);
			return false;
		}
	});
});

//주소 선택 없이 카테고리 선택 금지, 주소입력창으로 스크롤이동/포커스
$(document).ready(function(){
	var offset = $("header").offset(); //이동할 위치
	$("main a").click(function(){
		if($("#address").val() == ""){
			swal({ icon: "info", title: "주소를 먼저 입력해주세요" }).then(
        function () {
          $("#addr_search").focus();
          $("html, body").animate({ scrollTop: offset.top }, 100);
        }
      );
      return false;
		}
	});
});