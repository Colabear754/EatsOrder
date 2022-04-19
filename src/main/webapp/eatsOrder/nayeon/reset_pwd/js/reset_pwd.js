/*
작성자: 김나연
작성완료일: 22/04/12
페이지명: 이메일 찾기
*/

$(document).ready(function () {
  //제이쿼리 정규식 표현
  //비밀번호
  var passwdCheck = RegExp(
    /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/
  );

  $("form").submit(function () {
    //비밀번호 공백 확인
    if ($("#pwd").val() == "") {
      swal({ icon: "info", title: "비밀번호를 입력해주세요" }).then(
        function () {
          $("#pwd").focus();
        }
      );
      return false;
    }

    //비밀번호 유효성검사
    if (!passwdCheck.test($("#pwd").val())) {
      swal({ icon: "info", title: "비밀번호 형식에 맞게 입력해주세요" }).then(
        function () {
          $("#pwd").val("");
          $("#pwd").focus();
        }
      );
      return false;
    }

    //비밀번호 확인 - 공백 확인
    if ($("#re_pwd").val() == "") {
      swal({ icon: "info", title: "비밀번호 확인을 입력해주세요" }).then(
        function () {
          $("#re_pwd").focus();
        }
      );
      return false;
    }

    //비밀번호 똑같은지
    if ($("#pwd").val() != $("#re_pwd").val()) {
      swal({ icon: "info", title: "비밀번호가 틀립니다" }).then(function () {
        $("#pwd").val("");
        $("#re_pwd").val("");
        $("#pwd").focus();
      });
      return false;
    }
  });
});

//도움말 펼치기
$(document).ready(function () {
  $("#form_helper").hide();

  $("#pwd").click(function () {
    $("#form_helper").show();
  });
});
