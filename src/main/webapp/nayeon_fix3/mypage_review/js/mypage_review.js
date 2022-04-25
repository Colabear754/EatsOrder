// 작성 전 리뷰 기능 ##########################################
// textarea 글자수세기 : form 1
$(document).ready(function () {
  $("#review_write1").keyup(function (e) {
    let content = $(this).val();
    // 글자수 세기
    if (content.length == 0 || content == "") {
      $("#text_count1").text("0자");
    } else {
      $("#text_count1").text(content.length + "자");
    }

    // 글자수 제한
    if (content.length > 180) {
      // 180자 부터는 타이핑 되지 않도록
      $(this).val($(this).val().substring(0, 180));
      // 180자 넘으면 알림창 뜨도록
      swal({ icon: "info", title: "글자수는 180자까지 입력 가능합니다." }).then(
        function () {
          $("#review_write1").focus();
        }
      );
      return false;
    }
  });
});

// textarea 글자수세기 : form 2
$(document).ready(function () {
  $("#review_write2").keyup(function (e) {
    let content = $(this).val();
    // 글자수 세기
    if (content.length == 0 || content == "") {
      $("#text_count2").text("0자");
    } else {
      $("#text_count2").text(content.length + "자");
    }

    // 글자수 제한
    if (content.length > 180) {
      // 180자 부터는 타이핑 되지 않도록
      $(this).val($(this).val().substring(0, 180));
      // 180자 넘으면 알림창 뜨도록
      swal({ icon: "info", title: "글자수는 180자까지 입력 가능합니다." }).then(
        function () {
          $("#review_write2").focus();
        }
      );
      return false;
    }
  });
});

// textarea 글자수세기 : form 3
$(document).ready(function () {
  $("#review_write3").keyup(function (e) {
    let content = $(this).val();
    // 글자수 세기
    if (content.length == 0 || content == "") {
      $("#text_count3").text("0자");
    } else {
      $("#text_count3").text(content.length + "자");
    }

    // 글자수 제한
    if (content.length > 180) {
      // 180자 부터는 타이핑 되지 않도록
      $(this).val($(this).val().substring(0, 180));
      // 180자 넘으면 알림창 뜨도록
      swal({ icon: "info", title: "글자수는 180자까지 입력 가능합니다." }).then(
        function () {
          $("#review_write3").focus();
        }
      );
      return false;
    }
  });
});

// textarea 글자수세기 : form 4
$(document).ready(function () {
  $("#review_write4").keyup(function (e) {
    let content = $(this).val();
    // 글자수 세기
    if (content.length == 0 || content == "") {
      $("#text_count4").text("0자");
    } else {
      $("#text_count4").text(content.length + "자");
    }

    // 글자수 제한
    if (content.length > 180) {
      // 180자 부터는 타이핑 되지 않도록
      $(this).val($(this).val().substring(0, 180));
      // 180자 넘으면 알림창 뜨도록
      swal({ icon: "info", title: "글자수는 180자까지 입력 가능합니다." }).then(
        function () {
          $("#review_write4").focus();
        }
      );
      return false;
    }
  });
});

//---------------------------------------------------------------

//리뷰작성 : 별점 주기
$(document).ready(function () {
  $("#star button").click(function () {
    $(this).parent().children("button").removeClass("on");
    $(this).addClass("on").prevAll("button").addClass("on");
    console.log($(this).attr("value"));
  });
});

//---------------------------------------------------------------
// 리뷰 작성 완료
$(document).ready(function () {
  $("#submit_btn").click(function () {
    $("#review_form1").hide();
    $("#review_result4").show();
  });
});

//---------------------------------------------------------------
//작성 전 리뷰 : 아이디 뒤에 숫자 증가
$(document).ready(function () {
  $(".review_box1").each(function (i) {
    $(this).attr("id", "review_form" + (i + 1));
  });
});

// 작성 완료 리뷰 기능 ##########################################
// 작성완료 리뷰 : 별점출력

// 첫번째 작성완료 리뷰박스
$(document).ready(function () {
  var starOn = $("#star_value1").text();

  if (starOn < 2) {
    $("#star_off1 i:nth-child(1)").css("color", "#FECB10");
  } else if (starOn < 3) {
    $("#star_off1 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off1 i:nth-child(2)").css("color", "#FECB10");
  } else if (starOn < 4) {
    $("#star_off1 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off1 i:nth-child(2)").css("color", "#FECB10");
    $("#star_off1 i:nth-child(3)").css("color", "#FECB10");
  } else if (starOn < 5) {
    $("#star_off1 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off1 i:nth-child(2)").css("color", "#FECB10");
    $("#star_off1 i:nth-child(3)").css("color", "#FECB10");
    $("#star_off1 i:nth-child(4)").css("color", "#FECB10");
  } else if (starOn < 6) {
    $("#star_off1 i").css("color", "#FECB10");
  } else {
    return false;
  }
});

// 두번째 작성완료 리뷰박스
$(document).ready(function () {
  var starOn = $("#star_value2").text();

  if (starOn < 2) {
    $("#star_off2 i:nth-child(1)").css("color", "#FECB10");
  } else if (starOn < 3) {
    $("#star_off2 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off2 i:nth-child(2)").css("color", "#FECB10");
  } else if (starOn < 4) {
    $("#star_off2 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off2 i:nth-child(2)").css("color", "#FECB10");
    $("#star_off2 i:nth-child(3)").css("color", "#FECB10");
  } else if (starOn < 5) {
    $("#star_off2 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off2 i:nth-child(2)").css("color", "#FECB10");
    $("#star_off2 i:nth-child(3)").css("color", "#FECB10");
    $("#star_off2 i:nth-child(4)").css("color", "#FECB10");
  } else if (starOn < 6) {
    $("#star_off2 i").css("color", "#FECB10");
  } else {
    return false;
  }
});

// 세번째 작성완료 리뷰박스
$(document).ready(function () {
  var starOn = $("#star_value3").text();

  if (starOn < 2) {
    $("#star_off3 i:nth-child(1)").css("color", "#FECB10");
  } else if (starOn < 3) {
    $("#star_off3 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off3 i:nth-child(2)").css("color", "#FECB10");
  } else if (starOn < 4) {
    $("#star_off3 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off3 i:nth-child(2)").css("color", "#FECB10");
    $("#star_off3 i:nth-child(3)").css("color", "#FECB10");
  } else if (starOn < 5) {
    $("#star_off3 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off3 i:nth-child(2)").css("color", "#FECB10");
    $("#star_off3 i:nth-child(3)").css("color", "#FECB10");
    $("#star_off3 i:nth-child(4)").css("color", "#FECB10");
  } else if (starOn < 6) {
    $("#star_off3 i").css("color", "#FECB10");
  } else {
    return false;
  }
});

// 네번째 작성완료 리뷰박스
$(document).ready(function () {
  var starOn = $("#star_value4").text();

  if (starOn < 2) {
    $("#star_off4 i:nth-child(1)").css("color", "#FECB10");
  } else if (starOn < 3) {
    $("#star_off4 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off4 i:nth-child(2)").css("color", "#FECB10");
  } else if (starOn < 4) {
    $("#star_off4 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off4 i:nth-child(2)").css("color", "#FECB10");
    $("#star_off4 i:nth-child(3)").css("color", "#FECB10");
  } else if (starOn < 5) {
    $("#star_off4 i:nth-child(1)").css("color", "#FECB10");
    $("#star_off4 i:nth-child(2)").css("color", "#FECB10");
    $("#star_off4 i:nth-child(3)").css("color", "#FECB10");
    $("#star_off4 i:nth-child(4)").css("color", "#FECB10");
  } else if (starOn < 6) {
    $("#star_off4 i").css("color", "#FECB10");
  } else {
    return false;
  }
});

//---------------------------------------------------------------
//작성완료 리뷰 : 아이디 뒤에 숫자 증가
$(document).ready(function () {
  $(".review_box2").each(function (i) {
    $(this).attr("id", "review_done" + (i + 1));
  });
});

//---------------------------------------------------------------
// 리뷰 삭제 : 버튼 경고창

// 첫번째 작성완료 리뷰박스
$(document).ready(function () {
  //삭제 클릭 시 작성완료 위치에 스크롤 고정
  var offset = $("#review_wrapper2").offset(); //이동할 위치

  $("#review_del1").click(function () {
    //삭제 클릭 시 작성완료 위치에 스크롤 고정
    $("html, body").animate({ scrollTop: offset.top }, 100);
    // sweetalert
    swal({
      title: "정말 삭제하시겠습니까?",
      text: "삭제하시면 해당 리뷰는 복구가 불가능합니다.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        swal("리뷰가 삭제되었습니다.", {
          icon: "success",
        });
        // 리뷰 삭제 승인 시 리뷰 hide
        $("#review_done1").hide();
      } else {
        swal("리뷰가 삭제되지 않았습니다.");
      }
    });
  });
});

// 두번째 작성완료 리뷰박스
$(document).ready(function () {
  //삭제 클릭 시 작성완료 위치에 스크롤 고정
  var offset = $("#review_wrapper2").offset(); //이동할 위치

  $("#review_del2").click(function () {
    //삭제 클릭 시 작성완료 위치에 스크롤 고정
    $("html, body").animate({ scrollTop: offset.top }, 100);
    // sweetalert
    swal({
      title: "정말 삭제하시겠습니까?",
      text: "삭제하시면 해당 리뷰는 복구가 불가능합니다.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        swal("리뷰가 삭제되었습니다.", {
          icon: "success",
        });
        // 리뷰 삭제 승인 시 리뷰 hide
        $("#review_done2").hide();
      } else {
        swal("리뷰가 삭제되지 않았습니다.");
      }
    });
  });
});

// 세번째 작성완료 리뷰박스
$(document).ready(function () {
  //삭제 클릭 시 작성완료 위치에 스크롤 고정
  var offset = $("#review_wrapper2").offset(); //이동할 위치

  $("#review_del3").click(function () {
    //삭제 클릭 시 작성완료 위치에 스크롤 고정
    $("html, body").animate({ scrollTop: offset.top }, 100);
    // sweetalert
    swal({
      title: "정말 삭제하시겠습니까?",
      text: "삭제하시면 해당 리뷰는 복구가 불가능합니다.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        swal("리뷰가 삭제되었습니다.", {
          icon: "success",
        });
        // 리뷰 삭제 승인 시 리뷰 hide
        $("#review_done3").hide();
      } else {
        swal("리뷰가 삭제되지 않았습니다.");
      }
    });
  });
});

// 네번째 작성완료 리뷰박스
$(document).ready(function () {
  //삭제 클릭 시 작성완료 위치에 스크롤 고정
  var offset = $("#review_wrapper2").offset(); //이동할 위치

  $("#review_del4").click(function () {
    //삭제 클릭 시 작성완료 위치에 스크롤 고정
    $("html, body").animate({ scrollTop: offset.top }, 100);
    // sweetalert
    swal({
      title: "정말 삭제하시겠습니까?",
      text: "삭제하시면 해당 리뷰는 복구가 불가능합니다.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        swal("리뷰가 삭제되었습니다.", {
          icon: "success",
        });
        // 리뷰 삭제 승인 시 리뷰 hide
        $("#review_done4").hide();
      } else {
        swal("리뷰가 삭제되지 않았습니다.");
      }
    });
  });
});
