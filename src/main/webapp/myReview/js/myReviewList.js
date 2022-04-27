
//우림. 별점값 전달하기
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

//수정버튼 누르면 해당 폼 hide되도록
$(document).ready(function(){
	$("[name='submit_btn']").click(function(){
		$("[name='review_form']").hide();
	})
})

//삭제버튼 누르면 작성완료리뷰 삭제되게하고, 이메일 리뷰번호 deleteMyReview.do로 넘기게
$(document).ready(function(){
	
	$(".delete_btn").click(function(){
		
		$(".reveiw_box").hide();
		
		//이메일,리뷰번호 넘기기
		var newForm=$('<form></form');
		newForm.attr('method','post');
		newForm.attr('action','deleteMyReview.do');
		
		newForm.append($('<input>',{type:'hidden',name:'email',value:$("[class='email']").val()}));
		newForm.append($('<input>',{type:'hidden',name:'review_number',value:$("[class='review_number']").val()}));
		
		newForm.appendTo('body');
		
		newForm.submit();
		return false;
	});
});

