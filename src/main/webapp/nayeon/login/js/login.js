/* 
작성자: 김나연
작성완료일: 22/04/04
페이지명: 로그인
*/

$(document).ready(function () {
  //아이디
  var emailCheck = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
  //패스워드
  var passwdCheck = RegExp(
    /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/
  );

  $("form").submit(function () {
    //이메일 공백 확인
    if ($("#e_mail").val() == "") {
      swal({ icon: "info", title: "이메일을 입력해주세요" }).then(function () {
        $("#e_mail").focus();
      });
      return false;
    }

    //이메일 유효성 검사
    if (!emailCheck.test($("#e_mail").val())) {
      swal({ icon: "info", title: "이메일형식에 맞게 입력해주세요" }).then(
        function () {
          $("#e_mail").val("");
          $("#e_mail").focus();
        }
      );
      return false;
    }

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
  });
});
