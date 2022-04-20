/**
 * 작성자 : 정건영
 * 작성일 : 2022/04/13
 * 내용 : 회원 탈퇴 스크립트
 */

$(function() {
	// 라디오 버튼 클릭 이벤트
	$('input:radio[name=check1]').click(function() {
		if ($('input:radio[name=check1]:checked').val() == '5') {	// 기타 선택 시 텍스트필드 활성화
			$('#etc').attr('disabled', false);
		} else {	// 기타 외 선택 시 텍스트필드 내용 초기화 및 비활성화
			$('#etc').val('');
			$('#etc').attr('disabled', true);
		}

		$('#reason_error').css('display', 'none');
		$('.t1').css('outline', '');
	})
	
	// 체크박스 클릭 시 오류 메시지 제거
	$('#check_label').click(function() {
		$('#agree_error').css('display', 'none');
	})
	
	$('#password').keyup(function() {
		$('#password').css('outline', '');
		$('#password_error').css('display', 'none');
	})
	
	// 회원 탈퇴
	$('#withdraw').click(function() {
		var checkedRadio = $('input:radio[name=check1]:checked').attr('id');
		var email = $('#email').val();
		var password = $('#password').val();
		var reason_withdraw = $('label[for="' + checkedRadio + '"]').text();
		
		// 탈퇴 사유를 선택하지 않거나 기타를 선택하고 15자 이상 입력하지 않으면 탈퇴 불가
		if (checkedRadio == null) {
			$('#reason_error').css('display', 'inline-block');
			$('#reason_error').text('탈퇴사유를 선택하여 주세요.')
			$('.t1').css('outline', '2px solid red');
			return false;
		} else if (checkedRadio == 'reason5' && $('#etc').val().length < 15) {
			$('#reason_error').css('display', 'inline-block');
			$('#reason_error').text('탈퇴사유를 15자 이상 입력하여 주세요.')
			$('.t1').css('outline', '2px solid red');
			return false;
		} else {	// 그 외의 경우는 오류 메시지 및 외곽선 제거
			$('#reason_error').css('display', 'none');
			$('.t1').css('outline', '');
		}
		
		// 기타 선택 시 인풋 필드 내용을 탈퇴 사유로 사용
		if (checkedRadio == 'reason5') {
			reason_withdraw = $('#etc').val();
		}
		
		// 회원 탈퇴에 동의하지 않으면 탈퇴 불가
		if (!$('#check2').is(':checked')) {
			$('#agree_error').css('display', 'flex');
			return false;
		}
		
		$.ajax({
			type: "POST",
			url: "/EatsOrder/member/deleteMemberProc.do",
			data: "email=" + email + "&password=" + password + "&reason_withdraw=" + reason_withdraw,
			dataType: "text",
			success: function(data) {
				if (data > 0) {
					swal("성공적으로 탈퇴하였습니다.", "메인 페이지로 돌아갑니다.", "success").then((result) => {
    					if (result) {
    						window.location.href = "/EatsOrder/main/main.do";
    					}
    				});
				} else {
					$('#password_error').css('display', 'flex');
					$('#password').css('outline', '2px solid red');
				}
			},
			error: function(request) {
    			swal("오류 발생", request.statusText, "error");
			}
		})
	})
})