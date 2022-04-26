/*
작성자: 김나연
작성완료일: 22/04/04
페이지명: 회원정보수정
*/

$(document).ready(function () {
  //제이쿼리 정규식 표현
  //비밀번호
  var passwdCheck = RegExp(
    /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/
  );
  //닉네임
  var nickNameCheck = RegExp(/^[가-힣a-zA-Z0-9]{2,10}$/);
  //휴대폰
  var phoneCheck = RegExp(/^01[0179][0-9]{7,8}$/);

  $("form").submit(function () {
    //비밀번호 공백 확인
    if ($("#new_pwd").val() == "") {
      swal({ icon: "info", title: "비밀번호를 입력해주세요" }).then(
        function () {
          $("#new_pwd").focus();
        }
      );
      return false;
    }

    //비밀번호 유효성검사
    if (!passwdCheck.test($("#new_pwd").val())) {
      swal({ icon: "info", title: "비밀번호 형식에 맞게 입력해주세요" }).then(
        function () {
          $("#new_pwd").val("");
          $("#new_pwd").focus();
        }
      );
      return false;
    }

    //새 비밀번호 확인 - 공백 확인
    if ($("#renew_pwd").val() == "") {
      swal({ icon: "info", title: "비밀번호 확인을 입력해주세요" }).then(
        function () {
          $("#renew_pwd").focus();
        }
      );
      return false;
    }

    //비밀번호 똑같은지
    if ($("#new_pwd").val() != $("#renew_pwd").val()) {
      swal({ icon: "info", title: "비밀번호가 틀립니다" }).then(function () {
        $("#new_pwd").val("");
        $("#renew_pwd").val("");
        $("#new_pwd").focus();
      });
      return false;
    }

    //닉네임 공백 확인
    if ($("#name").val() == "") {
      swal({ icon: "info", title: "닉네임을 입력해주세요" }).then(function () {
        $("#name").focus();
      });
      return false;
    }

    //닉네임 유효성 검사
    if (!nickNameCheck.test($("#name").val())) {
      swal({ icon: "info", title: "닉네임 형식에 맞게 입력해주세요" }).then(
        function () {
          $("#name").val("");
          $("#name").focus();
        }
      );
      return false;
    }

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

//도움말 펼치기
$(document).ready(function () {
  $("#form_helper1").hide();
  $("#form_helper2").hide();
  $("#form_helper3").hide();
  $("#form_helper4").hide();

  $("#new_pwd").click(function () {
    $("#form_helper1").show();
  });

  $("#renew_pwd").click(function () {
    $("#form_helper2").show();
  });

  $("#name").click(function () {
    $("#form_helper3").show();
  });

  $("#p_num").click(function () {
    $("#form_helper4").show();
  });
});
