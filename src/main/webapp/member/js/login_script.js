/* 
작성자: 김나연
작성완료일: 22/04/04
페이지명: 로그인

수정자 : 정건영
수정일 : 2022/04/10
수정내용 : ajax로그인 추가
*/

$(document).ready(function(){
    //아이디
    var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    //패스워드
    var passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);

    $("form").submit(function(){
        //이메일 공백 확인
        if($("#account").val() == ""){
          alert("이메일을 입력해주세요");
          $("#account").focus();
          return false;
        }
             
        //이메일 유효성 검사
//        if(!emailCheck.test($("#account").val())){
//          alert("이메일형식에 맞게 입력해주세요")
//          $("#account").val("");
//          $("#account").focus();
//          return false;
//        }
  
        //비밀번호 공백 확인
        if($("#password").val() == ""){
          alert("비밀번호를 입력해주세요");
          $("#password").focus();
          return false;
        }
  
        //비밀번호 유효성검사
//         if(!passwdCheck.test($("#password").val())) {
//          alert("형식에 맞게 비밀번호를 입력해주세요");
//          $("#password").val("");
//          $("#password").focus();
//          return false;
//          }
    });
    
    // 로그인버튼으로 로그인
    $("#login").click(function() {
    	var type = $("#type").val()
    	var account = $("#account").val()
    	var password = $("#password").val()
    	$.ajax({
    		type: "POST",
    		url: "/EatsOrder/member/login.do",
    		data: "type=" + type + "&account=" + account + "&password=" + password,
    		dataType: "text",
    		success: function(data) {
    			if (data.indexOf("true") > 0) {
    				window.location.href = "/EatsOrder/main/main.do"
    			} else {
    				$("#login_error").css("display", "block")
    				$("#account").css("outline", "2px solid red")
    				$("#password").css("outline", "2px solid red")
    			}
    		},
    		error: function(request) {
    			alert("오류 발생 : " + request.status)
    		}
    	})
    })
    
    // 엔터키를 눌렀을 때 로그인
    $('#account').keyup(function(e) {
		$(this).css('outline', '')
		if (e.keyCode == 13) {
			login()
		}
	})
	
	$('#password').keyup(function(e) {
		$(this).css('outline', '')
		if (e.keyCode == 13) {
			login()
		}
	})
});

//엔터키를 눌렀을 때 로그인 되도록 별도 함수로 작성
function login() {
	var type = $("#type").val()
	var account = $("#account").val()
	var password = $("#password").val()
	$.ajax({
		type: "POST",
		url: "/EatsOrder/member/login.do",
		data: "type=" + type + "&account=" + account + "&password=" + password,
		dataType: "text",
		success: function(data) {
			if (data.indexOf("true") > 0) {
				window.location.href = "/EatsOrder/main/main.do"
			} else {
				$("#login_error").css("display", "block")
				$("#account").css("outline", "2px solid red")
				$("#password").css("outline", "2px solid red")
			}
		},
		error: function(request) {
			alert("오류 발생 : " + request.status)
		}
	})
}