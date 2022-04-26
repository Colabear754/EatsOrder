//찜 박스 : 아이디 뒤에 숫자 증가
$(document).ready(function () {
  $(".like_box2").each(function (i) {
    $(this).attr("id", "like_done" + (i + 1));
  });
});

//삭제 버튼 : 아이디 뒤에 숫자 증가
$(document).ready(function () {
  $(".del_btn a").each(function (i) {
    $(this).attr("id", "like_del" + (i + 1));
  });
});

// 주문내역 삭제 : 버튼 경고창-----------------------------------------
// 첫번째 주문내역 박스
$(document).ready(function () {
  //삭제 클릭 시 작성완료 위치에 스크롤 고정
  var offset = $("#like_wrapper2").offset(); //이동할 위치

  $("#like_del1").click(function () {
    //삭제 클릭 시 작성완료 위치에 스크롤 고정
    $("html, body").animate({ scrollTop: offset.top }, 100);
    // sweetalert
    swal({
      title: "정말 삭제하시겠습니까?",
      text: "삭제하시면 해당 주문내역은 복구가 불가능합니다.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        swal("주문내역이 삭제되었습니다.", {
          icon: "success",
        });
        // 리뷰 삭제 승인 시 리뷰 hide
        $("#like_done1").hide();
      } else {
        swal("주문내역이 삭제되지 않았습니다.");
      }
    });
  });
});

// 두번째 주문내역 박스
$(document).ready(function () {
  //삭제 클릭 시 작성완료 위치에 스크롤 고정
  var offset = $("#like_wrapper2").offset(); //이동할 위치

  $("#like_del2").click(function () {
    //삭제 클릭 시 작성완료 위치에 스크롤 고정
    $("html, body").animate({ scrollTop: offset.top }, 100);
    // sweetalert
    swal({
      title: "정말 삭제하시겠습니까?",
      text: "삭제하시면 해당 주문내역은 복구가 불가능합니다.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        swal("주문내역이 삭제되었습니다.", {
          icon: "success",
        });
        // 리뷰 삭제 승인 시 리뷰 hide
        $("#like_done2").hide();
      } else {
        swal("주문내역이 삭제되지 않았습니다.");
      }
    });
  });
});

// 세번째 주문내역 박스
$(document).ready(function () {
  //삭제 클릭 시 작성완료 위치에 스크롤 고정
  var offset = $("#like_wrapper2").offset(); //이동할 위치

  $("#like_del3").click(function () {
    //삭제 클릭 시 작성완료 위치에 스크롤 고정
    $("html, body").animate({ scrollTop: offset.top }, 100);
    // sweetalert
    swal({
      title: "정말 삭제하시겠습니까?",
      text: "삭제하시면 해당 주문내역은 복구가 불가능합니다.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        swal("주문내역이 삭제되었습니다.", {
          icon: "success",
        });
        // 리뷰 삭제 승인 시 리뷰 hide
        $("#like_done3").hide();
      } else {
        swal("주문내역이 삭제되지 않았습니다.");
      }
    });
  });
});

// 네번째 주문내역 박스
$(document).ready(function () {
  //삭제 클릭 시 작성완료 위치에 스크롤 고정
  var offset = $("#like_wrapper2").offset(); //이동할 위치

  $("#like_del4").click(function () {
    //삭제 클릭 시 작성완료 위치에 스크롤 고정
    $("html, body").animate({ scrollTop: offset.top }, 100);
    // sweetalert
    swal({
      title: "정말 삭제하시겠습니까?",
      text: "삭제하시면 해당 주문내역은 복구가 불가능합니다.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then((willDelete) => {
      if (willDelete) {
        swal("주문내역이 삭제되었습니다.", {
          icon: "success",
        });
        // 리뷰 삭제 승인 시 리뷰 hide
        $("#like_done4").hide();
      } else {
        swal("주문내역이 삭제되지 않았습니다.");
      }
    });
  });
});
