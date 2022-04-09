/* 
작성자: 김나연
작성완료일: 22/04/04
페이지명: 회원가입
*/

$(document).ready(function() { //제이쿼리 정규식 표현
      //이메일
      var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
      //비밀번호
      var passwdCheck = RegExp(/^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/);
      //닉네임
      var nickNameCheck = RegExp(/^[가-힣a-zA-Z0-9]{2,10}$/);
      //휴대폰
      var phoneCheck = RegExp(/^01[0179][0-9]{7,8}$/);

      $("form").submit(function(){
      //이메일 공백 확인
      if($("#e_mail").val() == ""){
        alert("이메일을 입력해주세요");
        $("#e_mail").focus();
        return false;
      }
           
      //이메일 유효성 검사
      if(!emailCheck.test($("#e_mail").val())){
        alert("이메일형식에 맞게 입력해주세요")
        $("#e_mail").val("");
        $("#e_mail").focus();
        return false;
      }

      //비밀번호 공백 확인
      if($("#pwd").val() == ""){
        alert("비밀번호를 입력해주세요");
        $("#pwd").focus();
        return false;
      }

      //비밀번호 유효성검사
       if(!passwdCheck.test($("#pwd").val())) {
        alert("형식에 맞게 비밀번호를 입력해주세요");
        $("#pwd").val("");
        $("#pwd").focus();
        return false;
        }

      //비밀번호 확인 공백 확인
      if($("#re_pwd").val() == ""){
        alert("비밀번호 확인을 입력해주세요");
        $("#re_pwd").focus();
        return false;
      }
   
      //비밀번호 똑같은지
        if($("#pwd").val() != ($("#re_pwd").val())){ 
        alert("비밀번호가 틀립니다.");
        $("#pwd").val("");
        $("#re_pwd").val("");
        $("#pwd").focus();
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
      if($("#p_num").val() == ""){
        alert("휴대폰 번호를 입력해주세요");
        $("#p_num").focus();
        return false;
      }

      //휴대폰 유효성 검사
      if(!phoneCheck.test($("#p_num").val())){
      alert("형식에 맞게 입력해주세요");
      $("#p_num").val("");
      $("#p_num").focus();
      return false;
      }
	});
});

//도움말 펼치기
$(document).ready(function() { 
  $("#form_helper1").hide();
  $("#form_helper2").hide();
  $("#form_helper3").hide();
  $("#form_helper4").hide();
  $("#form_helper5").hide();

  $("#e_mail").click(function(){
    $("#form_helper1").show();
  });

  $("#pwd").click(function(){
    $("#form_helper2").show();
  });

  $("#re_pwd").click(function(){
    $("#form_helper3").show();
  });

  $("#name").click(function(){
    $("#form_helper4").show();
  });

  $("#p_num").click(function(){
    $("#form_helper5").show();
  });
});