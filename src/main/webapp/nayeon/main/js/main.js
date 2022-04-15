/* 
작성자: 김나연
작성완료일: 22/04/04
페이지명: 메인페이지
*/

//주소, 음식점 공백 확인
$(document).ready(function () {
  $("form").submit(function () {
    //주소 공백 확인
    if ($("#addr_search").val() == "") {
      swal({ icon: "info", title: "주소를 입력해주세요" }).then(function () {
        $("#addr_search").focus();
      });
      return false;
    }
  });
});

//카카오 지도 api
window.onload = function () {
  document.getElementById("addr_search").addEventListener("click", function () {
    //주소입력칸을 클릭하면
    //카카오 지도 발생
    new daum.Postcode({
      oncomplete: function (data) {
        //선택시 입력값 세팅
        document.getElementById("addr_search").value = data.address; // 주소 넣기
        document.querySelector("input[name=food_search]").focus(); //상세입력 포커싱
      },
    }).open();
  });
};

//음식점,메뉴 미선택 시 카테고리로 스크롤 이동
$(document).ready(function () {
  var offset = $("#main_food_text").offset(); //이동할 위치
  $("form").submit(function () {
    //음식점,메뉴는 공백, 주소는 공백이 아닐 때 스크롤
    if ($("#food_search").val() == "" && $("#addr_search").val() != "") {
      $("html, body").animate({ scrollTop: offset.top }, 100);
      return false;
    }
  });
});

//주소 선택 없이 카테고리 선택 금지, 주소입력창으로 스크롤이동/포커스
$(document).ready(function () {
  var offset = $("header").offset(); //이동할 위치
  $("main a").click(function () {
    if ($("#addr_search").val() == "") {
      swal({ icon: "info", title: "주소를 먼저 입력해주세요" }).then(
        function () {
          $("#addr_search").focus();
          $("html, body").animate({ scrollTop: offset.top }, 100);
        }
      );
      return false;
    }
  });
});
