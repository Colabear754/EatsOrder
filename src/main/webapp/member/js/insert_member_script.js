/*
 * 작성자: 김나연
 * 작성완료일: 22/04/04
 * 페이지명: 회원가입
 * 
 * 수정자 : 정건영
 * 수정일 : 2022/04/10
 * 수정내용 : 동적 웹 페이지를 코드로 수정
*/

$(document).ready(function() { //제이쿼리 정규식 표현
    //이메일
    var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    //비밀번호
    var passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);
    //닉네임
    var nicknameCheck = RegExp(/^[가-힣a-zA-Z0-9]{2,10}$/);
    //휴대폰
    var phoneCheck = RegExp(/^01[0179]-[0-9]{3,4}-[0-9]{3,4}$/);

    // 중복 확인 여부
    var availableEmail = false;
    var availableNickname= false;
    var availablePhone = false;

    // 전화번호를 입력하면 자동으로 하이픈 추가
    $('#phone').keyup(function() {
    	$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})/, "$1-$2-$3").replace("--", "-") );
    })
      
    // 동적 웹 페이지를 위한 이벤트 함수 추가부분
    // 이메일 중복 확인
    $('#email_check').click(function() {
  	  	var email = $('#email').val();
  	  	var type = 'email';
    	  
    	if (!emailCheck.test(email)) {
    		$('#email_error').css('display', 'flex');
    		$('#email_error').text("이메일 형식에 맞게 입력해주세요.");
    	} else {
    		$.ajax({
    			type: "POST",
        		url: "/EatsOrder/member/checkDuplicateMember.do",
        		data: "type=" + type + "&email=" + email,
        		dataType: "text",
        		success: function(data) {
        			if (data.indexOf("true") > 0) {
        				$('#email_error').css('display', 'flex');
        	    		$('#email_error').text("이미 사용중인 이메일입니다.");
        				$('#available_email').css('display', 'none');
        			} else {
        				$('#email_error').css('display', 'none');
        				$('#available_email').css('display', 'flex');
        				$('#email').css('outline', '');
        				availableEmail = true;
        			}
        		},
        		error: function(request) {
        			alert("오류 발생 : " + request.status)
      			}
        	})
    	}
    })

    // 이메일 값을 변경하면 중복체크 결과 초기화
    $('#email').keyup(function() {
    	availableEmail = false;
    })

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

    // 닉네임 중복 확인
    $('#nickname_check').click(function() {
    	var nickname = $('#nickname').val();
    	var type = 'nickname';

    	if (!nicknameCheck.test(nickname)) {
    		$('#nickname_error').css('display', 'flex');
    		$('#nickname_error').text("닉네임 형식에 맞게 입력해주세요.");
    	} else {
    		$.ajax({
    			type: "POST",
    			url: "/EatsOrder/member/checkDuplicateMember.do",
    			data: "type=" + type + "&nickname=" + nickname,
    			dataType: "text",
    			success: function(data) {
    				if (data.indexOf("true") > 0) {
    					$('#nickname_error').css('display', 'flex');
    					$('#nickname_error').text("이미 사용중인 닉네임입니다.");
    					$('#available_nickname').css('display', 'none');
    				} else {
    					$('#nickname_error').css('display', 'none');
    					$('#available_nickname').css('display', 'flex');
    					$('#nickname').css('outline', '');
    					availableNickname = true;
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
    				if (data.indexOf("true") > 0) {
    					$('#phone_error').css('display', 'flex');
    					$('#phone_error').text("이미 사용중인 휴대폰 번호입니다.");
    					$('#available_phone').css('display', 'none');
    				} else {
    					$('#phone_error').css('display', 'none');
    					$('#available_phone').css('display', 'flex');
    					$('#phone').css('outline', '');
    					availablePhone = true;
    				}
    			},
    			error: function(request) {
    				alert("오류 발생 : " + request.status)
    			}	
    		})
    	}
    })

    // 휴대폰 번호 값을 변경하면 중복 확인 결과 초기화
    $('#phone').keyup(function() {
    	availablePhone = false;
    })
    
    // 이용약관 보기
    $('#terms').click(function() {
    	$.ajax({
    		type: "get",
    		url: "/EatsOrder/component/Utilization.html",
    		success: function(data) {
				$('#wrapper').html(data);
		    	$('#terms-modal').css('display', 'flex');
			}
    	})
    	
    	$(document).mouseup(function(e) {
    		if ($('#wrapper').has(e.target).length === 0) {
    			$('#terms-modal').css('display', 'none');
    			$('#wrapper').empty();
    		}
    	})
    })

    // 회원 가입
    $('#submit').click(function() {
    	var email = $('#email').val();
    	var password = $('#password').val();
    	var nickname = $('#nickname').val();
    	var phone = $('#phone').val();
    	var receive_marketing = $('receive_marketing').is(':checked');

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
    	if(!nicknameCheck.test(nickname)){
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
    	if (!availableEmail) {
    		$('#email_error').css('display', 'flex');
    		$('#email_error').text("이메일 중복 확인을 해주세요.");
    		$('#email').css('outline', '2px solid red');
    		return false;
    	}

    	if (!availableNickname) {
    		$('#nickname_error').css('display', 'flex');
    		$('#nickname_error').text("닉네임 중복 확인을 해주세요.");
    		$('#nickname').css('outline', '2px solid red');
    		return false;
    	}
    	
    	if (!availablePhone) {
    		$('#phone_error').css('display', 'flex');
    		$('#phone_error').text("휴대폰 번호 중복 확인을 해주세요.");
    		$('#phone').css('outline', '2px solid red');
    		return false;
    	}
    	
    	// 이용약관 동의 체크 여부
    	if (!$('#agree').is(':checked')) {
    		$('#terms_error').css('display', 'flex');
    		$('#terms_label').css('outline', '2px solid red');
    		return false;
    	}
    	
    	$.ajax({
    		type: "POST",
    		url: "/EatsOrder/member/insertMemberProc.do",
    		data: "email=" + email + "&password=" + password + "&nickname=" + nickname + "&phone=" + phone + "&receive_marketing=" + receive_marketing,
    		dataType: "text",
    		success: function(data) {
    			if (data > 0) {
    				swal("회원가입에 성공하였습니다.", "로그인 화면으로 돌아갑니다.", "success").then(() => {
    					window.location.href = "/EatsOrder/member/loginForm.do"
    				});
    			} else {
    				swal("회원가입에 실패하였습니다.", "입력 정보를 확인해주세요.", "warning");
    			}
    		}, 
    		error: function(request) {
    			swal("오류 발생", request.statusText, "error");
    		}
    	});
    })
});

//도움말 펼치기
$(document).ready(function() { 
	$("#form_helper1").hide();
	$("#form_helper2").hide();
	$("#form_helper3").hide();
	$("#form_helper4").hide();
	$("#form_helper5").hide();
	
	$("#email").focusin(function(){
		$("#form_helper1").show();
	});
	
	$("#password").focusin(function(){
		$("#form_helper2").show();
	});
	
	$("#re_pwd").focusin(function(){
		$("#form_helper3").show();
	});
	
	$("#nickname").focusin(function(){
		$("#form_helper4").show();
	});
	
	$("#phone").focusin(function(){
		$("#form_helper5").show();
	});
});