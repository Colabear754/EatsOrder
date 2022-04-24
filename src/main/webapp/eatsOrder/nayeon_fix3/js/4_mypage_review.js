// 작성 전 리뷰 기능 ##########################################
// textarea 글자수세기
$(document).ready(function () {
  $(".review_write").keyup(function (e) {
    let content = $(this).val();
    // 글자수 세기
    if (content.length == 0 || content == "") {
      $(".text_count").text("0자");
    } else {
      $(".text_count").text(content.length + "자");
    }

    // 글자수 제한
    if (content.length > 180) {
      // 180자 부터는 타이핑 되지 않도록
      $(this).val($(this).val().substring(0, 180));
      // 180자 넘으면 알림창 뜨도록
      swal({ icon: "info", title: "글자수는 180자까지 입력 가능합니다." }).then(
        function () {
          $(".review_write").focus();
        }
      );
      return false;
    }
  });
});

//리뷰작성 : 별점 주기
$(document).ready(function () {
  $("#star button").click(function () {
    $(this).parent().children("button").removeClass("on");
    $(this).addClass("on").prevAll("button").addClass("on");
    console.log($(this).attr("value"));
  });
});

//---------------------------------------------------------------
//파일 수 입력 경고창
$(document).ready(function () {
  // var sel_alert = $("#img_count").val();
  var sel_alert = 3;

  if (sel_alert > 5) {
    swal({ icon: "info", title: "파일은 5개까지 추가 가능합니다" });
  }
  return false;
});

//---------------------------------------------------------------
// 선택메뉴

// $(document).ready(function () {
//   var select_menu = 1;

// }

//---------------------------------------------------------------
// 리뷰 작성 완료
$(document).ready(function () {
  $("#submit_btn").click(function () {
    $("#review_form1").hide();
    $("#review_result4").show();
  });
});

//---------------------------------------------------------------
// 리뷰 삭제 : 버튼 경고창
$(document).ready(function () {
  $("#review_del").click(function () {
    swal({
      title: "정말 삭제하시겠습니까?",
      text: "삭제하시면 해당 리뷰는 다시는 작성이 불가능합니다.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        swal("리뷰가 삭제되었습니다.", {
          icon: "success",
        });
      } else {
        swal("리뷰가 삭제되지 않았습니다.");
      }
    });
  });
});

// 작성 완료 리뷰 기능 ##########################################
// 리뷰보기 : 별점출력 =>z-index방식
$(document).ready(function () {
  var starOn = $(".star_value").text();

  if (starOn < 2) {
    $(".star_off i:nth-child(1)").css("color", "#FECB10");
  } else if (starOn < 3) {
    $(".star_off i:nth-child(1)").css("color", "#FECB10");
    $(".star_off i:nth-child(2)").css("color", "#FECB10");
  } else if (starOn < 4) {
    $(".star_off i:nth-child(1)").css("color", "#FECB10");
    $(".star_off i:nth-child(2)").css("color", "#FECB10");
    $(".star_off i:nth-child(3)").css("color", "#FECB10");
  } else if (starOn < 5) {
    $(".star_off i:nth-child(1)").css("color", "#FECB10");
    $(".star_off i:nth-child(2)").css("color", "#FECB10");
    $(".star_off i:nth-child(3)").css("color", "#FECB10");
    $(".star_off i:nth-child(4)").css("color", "#FECB10");
  } else if (starOn < 6) {
    $(".star_off i").css("color", "#FECB10");
  } else {
    return false;
  }
});
