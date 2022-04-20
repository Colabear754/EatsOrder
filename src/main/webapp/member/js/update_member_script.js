/*
작성자: 김나연
작성완료일: 22/04/04
페이지명: 회원정보수정

수정자 : 정건영
수정일 : 2022/04/12
수정내용 : 동적 웹 페이지 작동을 위한 코드 추가
*/

$(document).ready(function() { //제이쿼리 정규식 표현
	//비밀번호
    var passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);
    //닉네임
    var nickNameCheck = RegExp(/^[가-힣a-zA-Z0-9]{2,10}$/);
    //휴대폰
    var phoneCheck = RegExp(/^01[0179]-[0-9]{3,4}-[0-9]{3,4}$/);

    // 중복 확인 여부
    var availableNickname= false;
    var availablePhone = false;

    // 전화번호를 입력하면 자동으로 하이픈 추가
    $('#phone').keyup(function() {
    	$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})/, "$1-$2-$3").replace("--", "-") );
    })
    
    //도움말 펼치기
    $("#form_helper1").hide();
    $("#form_helper2").hide();
    $("#form_helper3").hide();
    $("#form_helper4").hide();

    $("#newPassword").focusin(function(){
    	$("#form_helper1").show();
    });

    $("#renew_pwd").focusin(function(){
    	$("#form_helper2").show();
    });

    $("#nickname").focusin(function(){
    	$("#form_helper3").show();
    });

    $("#phone").focusin(function(){
    	$("#form_helper4").show();
    });
    
    // 중복 확인
    // 닉네임 중복 확인
    $('#nickname_check').click(function() {
    	var nickname = $('#nickname').val();
    	var type = 'nickname';

    	if (!nickNameCheck.test(nickname)) {
    		$('#nickname_error').css('display', 'flex');
    		$('#nickname_error').text("닉네임 형식에 맞게 입력해주세요.");
    	} else {
    		$.ajax({
    			type: "POST",
    			url: "/EatsOrder/member/checkDuplicateMember.do",
    			data: "type=" + type + "&nickname=" + nickname,
    			dataType: "text",
    			success: function(data) {
    				if (data.indexOf("true") < 0 && nickname == $('#nickname').val()) {
    					$('#nickname_error').css('display', 'none');
    					$('#available_nickname').css('display', 'flex');
    					$('#nickname').css('outline', '');
    					availableNickname = true;
    				} else {
    					$('#nickname_error').css('display', 'flex');
    					$('#nickname_error').text("이미 사용중인 닉네임입니다.");
    					$('#available_nickname').css('display', 'none');
    				}
    			},
    			error: function(request) {
    				alert("오류 발생 : " + request.status)
    			}
    		})
    	}
    })
    
    // 닉네임 값이 변경되면 중복체크 결과 초기화
    $('#nickname').keyup(function() {
    	availableNickname = false;
    })
    
    // 휴대폰 번호 중복 확인
    $('#phone_check').click(function() {
    	var phone = $('#phone').val();
    	var type = 'phone';

    	if (!phoneCheck.test(phone)) {
    		$('#phone_error').css('display', 'flex');
    		$('#phone_error').text("휴대폰 번호를 입력해주세요.");
    	} else {
    		$.ajax({
    			type: "POST",
    			url: "/EatsOrder/member/checkDuplicateMember.do",
    			data: "type=" + type + "&phone=" + phone,
    			dataType: "text",
    			success: function(data) {
    				if (data.indexOf("true") < 0 && phone == $('#phone2').val()) {
    					$('#phone_error').css('display', 'none');
    					$('#available_phone').css('display', 'flex');
    					$('#phone').css('outline', '');
    					availablePhone = true;
    				} else {
    					$('#phone_error').css('display', 'flex');
    					$('#phone_error').text("이미 사용중인 휴대폰 번호입니다.");
    					$('#available_phone').css('display', 'none');
    				}
    			},
    			error: function(request) {
    				alert("오류 발생 : " + request.status);
    			}	
    		})
    	}
    })

    // 휴대폰 번호 값을 변경하면 중복 확인 결과 초기화
    $('#phone').keyup(function() {
    	availablePhone = false;
    })
    
    // 비밀번호 체크
    $('#newPassword').keyup(function() {
    	if (!passwdCheck.test($("#newPassword").val())) {
    		$('#password_error').css('display', 'flex');
    		$('#password_error').text("비밀번호를 조건에 맞게 입력하세요.");
    		$('#available_password').css('display', 'none');
    	} else {
    		$('#password_error').css('display', 'none');
    		$('#available_password').css('display', 'flex');
    	}
    	
    	if ($('#newPassword').val() != "") {
	    	if ($('#renew_pwd').val() != $('#newPassword').val()) {
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
      
    // 비밀번호 확인 체크
    $('#renew_pwd').keyup(function() {
    	if ($('#newPassword').val() != "") {
	    	if ($('#renew_pwd').val() != $('#newPassword').val()) {
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
    
    // 회원 정보 수정
    $("#submit").click(function(){
    	var email = $('#email').val();
    	var password = $('#password').val();
    	var newPassword = $('#newPassword').val();
    	var nickname = $('#nickname').val();
    	var phone = $('#phone').val();
    	var receive_marketing = $('#receive_marketing').is(':checked');
    	
	    //새 비밀번호 공백 확인
	    if(newPassword == ""){
	    	$("#newPassword").focus();
		  	// alert 대신 페이지에 출력하도록 수정
		  	$('#password_error').css('display', 'flex');
  		  	$('#password_error').text("새로운 비밀번호를 입력하세요.");
  		  	$('#newPassword').css('outline', '2px solid red');
  		  	return false;
	    }
	    
	    //새 비밀번호 유효성검사
	    if(!passwdCheck.test(newPassword)) {
	        $("#newPassword").val("");
		  	$("#newPassword").focus();
		  	// alert 대신 페이지에 출력하도록 수정
		  	$('#password_error').css('display', 'flex');
  		  	$('#password_error').text("비밀번호를 조건에 맞게 입력하세요.");
  		  	$('#newPassword').css('outline', '2px solid red');
  		  	return false;
	    }
	 
	    //새 비밀번호, 새 비밀번호 확인 똑같은지
	    if(newPassword != ($("#renew_pwd").val())){ 
	    	 $("#newPassword").focus();
			 // alert 대신 페이지에 출력하도록 수정
			 $('#re_pwd_error').css('display', 'flex');
	  		 $('#re_pwd_error').text("비밀번호가 일치하지 않습니다.");
	  		 $('#newPassword').css('outline', '2px solid red');
	  		 $('#renew_pwd').css('outline', '2px solid red');
	  		 return false;
	    }
	
	    //닉네임 공백 확인
	    if(nickname == ""){
	    	$("#nickname").focus();
	    	// alert 대신 페이지에 출력하도록 수정
			$('#nickname_error').css('display', 'flex');
    		$('#nickname_error').text("닉네임을 입력해주세요.");
    		$('#nickname').css('outline', '2px solid red');
		    return false;
	    }
	
	    //닉네임 유효성 검사
	    if(!nickNameCheck.test($("#name").val())){
		    $("#nickname").val("");
		    $("#nickname").focus();
	    	// alert 대신 페이지에 출력하도록 수정
			$('#nickname_error').css('display', 'flex');
    		$('#nickname_error').text("닉네임 형식에 맞게 입력해주세요.");
    		$('#nickname').css('outline', '2px solid red');
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
	    
    	// 중복 확인을 했는지 체크
	    // 닉네임이나 전화번호를 변경하지 않을 경우엔 체크하지 않음
	    if (!availableNickname && nickname != $('#nickname2').val()) {
    		$('#nickname_error').css('display', 'flex');
    		$('#nickname_error').text("닉네임 중복 확인을 해주세요.");
    		$('#nickname').css('outline', '2px solid red');
    		return false;
    	}
    	
    	if (!availablePhone && phone != $('#phone2').val()) {
    		$('#phone_error').css('display', 'flex');
    		$('#phone_error').text("휴대폰 번호 중복 확인을 해주세요.");
    		$('#phone').css('outline', '2px solid red');
    		return false;
    	}
    	
    	// 기존 비밀번호와 새 비밀번호가 같은지 체크
    	if (password == newPassword) {
		  	$('#password_error').css('display', 'flex');
  		  	$('#password_error').text("기존 비밀번호와 같은 비밀번호를 사용할 수 없습니다.");
    	}
	    
	    $.ajax({
    		type: "POST",
    		url: "/EatsOrder/member/updateMemberProc.do",
    		data: "email=" + email + "&password=" + password + "&newPassword=" + newPassword + "&nickname=" + nickname + "&phone=" + phone + "&receive_marketing=" + receive_marketing,
    		dataType: "text",
    		success: function(data) {
    			if (data > 0) {
    				swal("회원정보가 성공적으로 수정되었습니다.", "", "success").then((result) => {
    					if (result) {
    						window.location.href = "/EatsOrder/main/main.do"
    					}
    				});
    			} else if (data < 0) {
    				$('#ex_password_error').css('display', 'flex');
    				$('#ex_password_error').text('현재 비밀번호가 일치하지 않습니다.');
    				$('#password').css('outline', '2px solid red');
    			} else {
    				swal("회원 정보 수정에 실패하였습니다.", "입력 정보를 확인해주세요.", "warning");
    			}
    		}, 
    		error: function(request) {
    			swal("오류 발생", request.statusText, "error");
    		}
    	});
    });
});