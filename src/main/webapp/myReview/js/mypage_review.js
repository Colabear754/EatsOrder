// 작성 전 리뷰 기능 ##########################################
// textarea 글자수세기 : form 1
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
//---------------------------------------------------------------
//리뷰작성 : 우림. 별점값 전달하기
//name=rating1을 클릭하면. rating1의 value값을 알림창으로띄우고. rating1의 value값을 input hidden의 value에 넣어서 폼으로 전송

$(document).ready(function(){
	$("[name='rating1']").click(function(){
		var rating=$(this).attr('value');
		alert(rating);
	 	$('input[id=ratings]').val(rating);
	});
	$("[name='rating2']").click(function(){
		var rating=$(this).attr('value');
		alert(rating);
	 	$('input[id=ratings]').val(rating);
	});
	$("[name='rating3']").click(function(){
		var rating=$(this).attr('value');
		alert(rating);
	 	$('input[id=ratings]').val(rating);
	});
	$("[name='rating4']").click(function(){
		var rating=$(this).attr('value');
		alert(rating);
	 	$('input[id=ratings]').val(rating);
	});
	$("[name='rating5']").click(function(){
		var rating=$(this).attr('value');
		alert(rating);
	 	$('input[id=ratings]').val(rating);
	});
});

//작성버튼 누르면 해당 폼 hide되도록
$(document).ready(function(){
	$("[name='submit_btn']").click(function(){
		$("[name='review_form']").hide();
	})
})

// 리뷰 삭제 : 버튼 경고창

//작성완료 리뷰박스
$(document).ready(function () {
  //삭제 클릭 시 작성완료 위치에 스크롤 고정
  var offset = $(".review_wrapper").offset(); //이동할 위치

  $(" .swal-button swal-button--confirm swal-button--danger").click(function () {
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
        })

        // 리뷰 삭제 승인 시 리뷰 hide
        $(".review_box").hide();
        
        /*
        //이메일,리뷰번호 넘기기
		var newForm=$('<form></form');
		newForm.attr('method','post');
		newForm.attr('action','deleteMyReview.do');
		
		newForm.append($('<input>',{type:'hidden',name:'email',value:$("[class='email']").val()}));
		newForm.append($('<input>',{type:'hidden',name:'review_number',value:$("[class='review_number']").val()}));
		
		newForm.appendTo('body');
		
		newForm.submit();
		return false;
		*/
      } else {
        swal("리뷰가 삭제되지 않았습니다.");
      } 
    });  
  });
});
//---------------------------------------------------------------
// 리뷰 작성 완료
//---------------------------------------------------------------
//작성 전 리뷰 : 아이디 뒤에 숫자 증가
// 작성 완료 리뷰 기능 ##########################################
// 작성완료 리뷰 : 별점출력

// 첫번째 작성완료 리뷰박스
$(document).ready(function () {
  var starOn = $(".star_value").text();

  if (starOn < 2) {
    $(".star_off1 i:nth-child(1)").css("color", "#FECB10");
  } else if (starOn < 3) {
    $(".star_off1 i:nth-child(1)").css("color", "#FECB10");
    $(".star_off1 i:nth-child(2)").css("color", "#FECB10");
  } else if (starOn < 4) {
    $(".star_off1 i:nth-child(1)").css("color", "#FECB10");
    $(".star_off1 i:nth-child(2)").css("color", "#FECB10");
    $(".star_off1 i:nth-child(3)").css("color", "#FECB10");
  } else if (starOn < 5) {
    $(".star_off1 i:nth-child(1)").css("color", "#FECB10");
    $(".star_off1 i:nth-child(2)").css("color", "#FECB10");
    $(".star_off1 i:nth-child(3)").css("color", "#FECB10");
    $(".star_off1 i:nth-child(4)").css("color", "#FECB10");
  } else if (starOn < 6) {
    $(".star_off1 i").css("color", "#FECB10");
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
  var offset = $(".review_wrapper2").offset(); //이동할 위치

  $(".review_del").click(function () {
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
        $(".review_done").hide();
      } else {
        swal("리뷰가 삭제되지 않았습니다.");
      }
    });
  });
});

