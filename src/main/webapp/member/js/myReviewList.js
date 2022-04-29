/*작성 전 리뷰 기능*/
//1. textarea 글자수세기 : review_form
$(document).ready(function () {
  $(".review_write").keyup(function (e) {
    let content = $(this).val();     
    //글자 입력하면 글자수 세서 넣어줌
    $(this).siblings('.text_count').text(content.length + "자");
    
    // 글자수 제한
    if (content.length==180) {
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

//2. 리뷰작성: 별점 주기
$(document).ready(function () {
  $("#star button").click(function () {
    $(this).parent().children("button").removeClass("on");
    $(this).addClass("on").prevAll("button").addClass("on");
    console.log($(this).attr("value"));
  });
});

//3. (우림)별점값 전달하기
//name=rating1을 클릭하면. rating1의 value값을 input hidden의 value에 넣어서 폼으로 전송
$(document).ready(function(){
	$("[name='rating1']").click(function(){
		var rating=$(this).attr('value');
	 	$('input[id=ratings]').val(rating);
	});
	$("[name='rating2']").click(function(){
		var rating=$(this).attr('value');
	 	$('input[id=ratings]').val(rating);
	});
	$("[name='rating3']").click(function(){
		var rating=$(this).attr('value');
	 	$('input[id=ratings]').val(rating);
	});
	$("[name='rating4']").click(function(){
		var rating=$(this).attr('value');
	 	$('input[id=ratings]').val(rating);
	});
	$("[name='rating5']").click(function(){
		var rating=$(this).attr('value');
	 	$('input[id=ratings]').val(rating);
	});
});


//4. 글 작성 버튼 누르면 해당 폼 hide되도록
$(document).ready(function(){
	$("[name='submit_btn']").click(function(){
		$("[name='review_form']").hide();
	})
})

/*작성 완료 리뷰 기능*/
//1. rating 값 가져와서 별 출력
//2. 리뷰 삭제: 삭제버튼 누를시 경고창 띄우고, 삭제 클릭하면 리뷰 hide 및 이메일 리뷰번호 deleteMyReview.do로 전송

//작성완료 리뷰박스
$(document).ready(function () {
  //삭제 클릭 시 작성완료 위치에 스크롤 고정
  var offset = $(".review_wrapper").offset(); //이동할 위치

  $(" .delete_btn").click(function () {
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
	    var newForm = $("<form></form");
	    newForm.attr("method", "post");
    	newForm.attr("action", "deleteMyReview.do");

   		newForm.append(
	      $("<input>", {
	        type: "hidden",
	        name: "review_number",
	        value: $("[class='review_number']").val(),
	      })
    	);
  		newForm.appendTo("body");
		newForm.submit();
    	return false;
       
      } else {
        swal("리뷰가 삭제되지 않았습니다.");
      } 
    });  
  });
}); 