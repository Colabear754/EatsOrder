/*
작성자: 김나연
작성완료일: 22/04/04
페이지명: 이메일 찾기

수정자: 정건영
수정일: 2022/04/10
수정내용: 동적 웹 페이지를 위한 함수 추가
*/

$(document).ready(function(){ //제이쿼리 정규식 표현
     //휴대폰
	 $("#submit").click(function() {
		 var phone = $('#phone').val();
	     //휴대폰 공백 확인
		 if(phone == "") {
		     $("#phone").focus();
		     // alert 대신 화면에 경고문을 표시하도록 수정
			 $('#error').css('display', 'block')
			 $('#phone').css('outline', '2px solid red')
		     return false;
	     }
		 
		 // 아이디 찾기 함수
		 $.ajax({
			 type: "POST",
			 url: "/EatsOrder/member/findEmailProc.do",
			 data: "phone=" + phone,
			 dataType: "text",
			 success: function(data) {
				if (data.indexOf('@') > 0) {
					$('#search_wrapper').css('display', 'none');
					$('#result').html('회원님의 아이디는 <strong>' + data + '</strong>입니다.');
					$('#result_wrapper').css('display', 'block');
					$('#login_button').css('display', 'block');
				} else {
					$('#result').text('해당하는 정보의 아이디가 존재하지 않습니다.');
					$('#result_wrapper').css('display', 'block');
					$('#regist_button').css('display', 'block');
				}
			},
			error: function(request) {
				alert("오류 발생 : " + request.status);
			}
		 })
	 })
	 
	 // 휴대폰 번호 자동 하이픈 추가
	 $('#phone').keyup(function() {
    	$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})/, "$1-$2-$3").replace("--", "-") );
	 })
})