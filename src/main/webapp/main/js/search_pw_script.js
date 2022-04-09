/*
작성자: 김나연
작성완료일: 22/04/04
페이지명: 비밀번호 찾기
*/

$(document).ready(function(){ //제이쿼리 정규식 표현
    //이메일
    var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
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