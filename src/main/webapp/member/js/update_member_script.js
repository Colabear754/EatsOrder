/*
작성자: 김나연
작성완료일: 22/04/04
페이지명: 회원정보수정
*/

$(document).ready(function() { //제이쿼리 정규식 표현
    //비밀번호
    var passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);
    //닉네임
    var nickNameCheck = RegExp(/^[가-힣a-zA-Z0-9]{2,10}$/);
    //휴대폰
    var phoneCheck = RegExp(/^01[0179]-[0-9]{3,4}-[0-9]{3,4}$/);

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
    
    $("form").submit(function(){
    //새 비밀번호 공백 확인
    if($("#newPassword").val() == ""){
	    alert("비밀번호를 입력해주세요");
	    $("#newPassword").focus();
	    return false;
    }
    
    //새 비밀번호 유효성검사
    if(!passwdCheck.test($("#newPassword").val())) {
	    alert("형식에 맞게 비밀번호를 입력해주세요");
	    $("#newPassword").val("");
	    $("#newPassword").focus();
	    return false;
    }
 
    //새 비밀번호, 새 비밀번호 확인 똑같은지
     if($("#newPassword").val() != ($("#renew_pwd").val())){ 
	     alert("비밀번호가 틀립니다.");
	     $("#newPassword").val("");
	     $("#renew_pwd").val("");
	     $("#newPassword").focus();
	     return false;
     }

    //닉네임 공백 확인
    if($("#name").val() == ""){
	    alert("닉네임을 입력해주세요");
	    $("#name").focus();
	    return false;
    }

    //닉네임 유효성 검사
    if(!nickNameCheck.test($("#name").val())){
    	alert("형식에 맞게 입력해주세요");
    	$("#name").val("");
    	$("#name").focus();
    	return false;
    }

    //휴대폰 공백 확인
    if($("#phone").val() == ""){
    	alert("휴대폰 번호를 입력해주세요");
    	$("#phone").focus();
    	return false;
    }

    //휴대폰 유효성 검사
    if(!phoneCheck.test($("#phone").val())){
	    alert("형식에 맞게 입력해주세요");
	    $("#phone").val("");
	    $("#phone").focus();
	    return false;
    }
    
    $(document).on("keyup", ".phoneNumber", function() { 
    	$(this).val($(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); 
    });
  });
});