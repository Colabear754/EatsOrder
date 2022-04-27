//퀵메뉴 따라오기
$(document).ready(function () {
  var currentPosition = parseInt($(".mypage_wrap").css("top"));
  $(window).scroll(function () {
    var position = $(window).scrollTop();
    $(".mypage_wrap")
      .stop()
      .animate({ top: position + currentPosition + "px" }, 1000);
  });
});
