/*
작성자: 김나연
작성완료일: 22/04/12
페이지명: 이메일 찾기
*/

$(document).ready(function() {
	//제이쿼리 정규식 표현
	//비밀번호
	var passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);

	// 비밀번호
    $('#password').keyup(function() {
    	if (!passwdCheck.test($("#password").val())) {
    		$('#password_error').css('display', 'flex');
    		$('#password_error').text("비밀번호를 조건에 맞게 입력하세요.");
    		$('#available_password').css('display', 'none');
    	} else {
    		$('#password_error').css('display', 'none');
    		$('#available_password').css('display', 'flex');
    	}
    	
    	if ($('#password').val() != "") {
	    	if ($('#re_pwd').val() != $('#password').val()) {
	    		$('#re_pwd_error').css('display', 'flex');
	    		$('#re_pwd_error').text("비밀번호가 일치하지 않습니다.");
	    		$('#re_pwd_equal').css('display', 'none');
	    	} else {
	    		$('#re_pwd_error').css('display', 'none');
	    		$('#re_pwd_equal').css('display', 'flex');
	    	}
    	} else {
  		  $('#re_pwd_error').css('display', 'none');
		  $('#re_pwd_equal').css('display', 'none');
	  }
    })
      
    // 비밀번호 확인
    $('#re_pwd').keyup(function() {
    	if ($('#password').val() != "") {
	    	if ($('#re_pwd').val() != $('#password').val()) {
	    		$('#re_pwd_error').css('display', 'flex');
	    		$('#re_pwd_error').text("비밀번호가 일치하지 않습니다.");
	    		$('#re_pwd_equal').css('display', 'none');
	    	} else {
	    		$('#re_pwd_error').css('display', 'none');
	    		$('#re_pwd_equal').css('display', 'flex');
	    	}
    	} else {
    		$('#re_pwd_error').css('display', 'none');
    		$('#re_pwd_equal').css('display', 'none');
    	}
    })
    
	$("#submit").click(function() {
		var email = $('#email').val();
		var password = $('#password').val();
		
	    //비밀번호 공백 확인
	    if(password == ""){
		  	$("#password").focus();
		  	// alert 대신 페이지에 출력하도록 수정
		  	$('#password_error').css('display', 'flex');
  		  	$('#password_error').text("비밀번호를 입력하세요.");
  		  	$('#password').css('outline', '2px solid red');
  		  	return false;
	  	}

	    //비밀번호 유효성검사
	    if(!passwdCheck.test(password)) {
		  	$("#password").val("");
		  	$("#password").focus();
		  	// alert 대신 페이지에 출력하도록 수정
		  	$('#password_error').css('display', 'flex');
  		  	$('#password_error').text("비밀번호를 조건에 맞게 입력하세요.");
  		  	$('#password').css('outline', '2px solid red');
  		  	return false;
	  	}

	  	//비밀번호 확인 공백 확인
	  	if($("#re_pwd").val() == ""){
		  	$("#re_pwd").focus();
		  	// alert 대신 페이지에 출력하도록 수정
		  	$('#re_pwd_error').css('display', 'flex');
  		  	$('#re_pwd_error').text("비밀번호를 조건에 맞게 입력하세요.");
  		  	$('#re_pwd').css('outline', '2px solid red');
  		  	return false;
	  	}
 
	  	//비밀번호 똑같은지
	  	if(password != ($("#re_pwd").val())){ 
		  	$("#password").focus();
		  	// alert 대신 페이지에 출력하도록 수정
		  	$('#re_pwd_error').css('display', 'flex');
  		  	$('#re_pwd_error').text("비밀번호가 일치하지 않습니다.");
  		  	$('#password').css('outline', '2px solid red');
  		  	$('#re_pwd').css('outline', '2px solid red');
  		  	return false;
	  	}
	  	
	  	$.ajax({
	  		type: "POST",
	  		url: "/EatsOrder/member/resetPasswordProc.do",
	  		data: "email=" + email + "&password=" + password,
	  		dataType: "text",
	  		success: function(data) {
				if (data > 0) {
  				  	alert("비밀번호를 성공적으로 재설정하였습니다. 로그인 화면으로 돌아갑니다.");
  				  	window.location.href = "/EatsOrder/member/loginForm.do";
				} else {
					alert("알 수 없는 오류로 비밀번호 재설정에 실패하였습니다.");
				}
			},
			error: function(request) {
				alert("오류 발생 : " + request.statusText);
			}
	  	})
	});
});
	
//도움말 펼치기
$(document).ready(function() {
	$("#form_helper").hide();
	
	$("#password").focusin(function() {
	    $("#form_helper").show();
	});
});
