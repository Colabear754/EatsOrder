/*
작성자: 김나연
작성완료일: 22/04/04
페이지명: 비밀번호 찾기

수정자 : 정건영
수정일 : 2022/04/12
수정내용 : 휴대폰번호 자동 하이픈 추가함수 및 동적 웹 페이지를 위한 함수 추가
*/

$(document).ready(function(){ //제이쿼리 정규식 표현
    //이메일
    var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    //휴대폰
    var phoneCheck = RegExp(/^01[0179]-[0-9]{3,4}-[0-9]{3,4}$/);
    
    
    // 전화번호를 입력하면 자동으로 하이픈 추가
    $('#phone').keyup(function() {
    	$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})/, "$1-$2-$3").replace("--", "-") );
    })

    $("#search_btn").click(function(){
        var email = $('#email').val();
        var phone = $('#phone').val();
        
 	  	//이메일 공백 확인
    	if(email == ""){
    		$("#email").focus();
    		// alert 대신 페이지에 출력하도록 수정
    		$('#email_error').css('display', 'flex');
    		$('#email_error').text("이메일을 입력해주세요.");
 		   	$('#email').css('outline', '2px solid red');
 		   	return false;
    	}
        
    	//이메일 유효성 검사
    	if(!emailCheck.test(email)){
    		$("#email").val("");
    		$("#email").focus();
    		// alert 대신 페이지에 출력하도록 수정
    		$('#email_error').css('display', 'flex');
    		$('#email_error').text("이메일 형식에 맞게 입력해주세요.");
    		$('#email').css('outline', '2px solid red');
    		return false;
    	}

    	//휴대폰 공백 확인
    	if(phone == ""){
    		$("#phone").focus();
	      	// alert 대신 페이지에 출력하도록 수정
    		$('#phone_error').css('display', 'flex');
    		$('#phone_error').text("휴대폰 번호를 입력해주세요.");
    		$('#phone').css('outline', '2px solid red');
    		return false;
    	}

    	//휴대폰 유효성 검사
    	if(!phoneCheck.test(phone)){
    		$("#phone").val("");
    		$("#phone").focus();
    		// alert 대신 페이지에 출력하도록 수정
    		$('#phone_error').css('display', 'flex');
    		$('#phone_error').text("휴대폰 번호를 형식에 맞게 입력해주세요.");
    		$('#phone').css('outline', '2px solid red');
    		return false;
    	}
    	
    	$.ajax({
    		type: "POST",
    		url: "/EatsOrder/member/checkValidMemeber.do",
    		data: "email=" + email + "&phone=" + phone,
    		dataType: "text",
    		success: function(data) {
				if (data.indexOf("true") < 0) {
					$('#result').text('해당하는 정보로 가입된 회원이 존재하지 않습니다.');
					$('#result_wrapper').css('display', 'block');
					$('#regist_button').css('display', 'block');
				} else {
					window.location.href = "/EatsOrder/member/resetPasswordForm.do?email=" + email;
				}
			},
			error: function(request) {
				alert('오류 발생 : ' + request.statusText)
			}
    	})
   	});
});