/*
작성자: 김나연
작성완료일: 22/04/04
페이지명: 이메일 찾기
*/

$(document).ready(function () {
  //제이쿼리 정규식 표현
  //휴대폰
  var phoneCheck = RegExp(/^01[0179][0-9]{7,8}$/);

  $("form").submit(function () {
    //휴대폰 공백 확인
    if ($("#p_num").val() == "") {
      swal({ icon: "info", title: "휴대폰 번호를 입력해주세요" }).then(
        function () {
          $("#p_num").focus();
        }
      );
      return false;
    }

    //휴대폰 유효성 검사
    if (!phoneCheck.test($("#p_num").val())) {
      swal({
        icon: "info",
        title: "휴대폰 번호 형식에 맞게 입력해주세요",
      }).then(function () {
        $("#p_num").val("");
        $("#p_num").focus();
      });
      return false;
    }
  });
});
