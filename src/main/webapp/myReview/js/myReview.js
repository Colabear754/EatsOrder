
//우림. 별점값 전달하기
//#rating1을 클릭하면. rating1의 value값을 알림창으로띄우고. rating1의 value값을 input hidden의 value에 넣어서 폼으로 전송

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
/*
$(document).ready(function(){
	$("[name='photo1']").change(function(){
		var photo=$("[name='photo1']").val();
		alert(photo);
	})
})


//작성버튼 누르면, form을 만들어서 값들 전달하도록
$(document).ready(function(){
	$("[name='submit_btn']").click(function(){
		
		var form= $("#uploadForm")[0];
		alert("form:"+form);
		var formData=new FormData(form);
		alert("formData"+formData);
		$.ajax({
			url:'insertMyReview.do',
			type:'POST',
			data:formData,
			success:function(data){
				alert(data)
			},
			error:function(data){
				alert(data)
			},
			contentType:false,
			processData:false,
			cache:false
		}).done(function(data){
			callback(data);
		});
		
	    var newForm=$('<form></form>');
	    newForm.attr('id','review_form');
	    newForm.attr('method','post');
	    newForm.attr('action','insertMyReview.do');
	    newForm.attr('enctype','multipart/form-data');
	
	    newForm.append($('<input>',{type:'hidden',name:'email',value:$("[name='emails']").val()}));
	    newForm.append($('<input>',{type:'hidden',name:'pageNum',value:$("[name='pageNums']").val()}));
	    newForm.append($('<input>',{type:'hidden',name:'order_number',value:$("[name='order_numbers']").val()}));
	    newForm.append($('<input>',{type:'hidden',name:'rating',value:$("#ratings").val()}));
	   	    
	    newForm.append($('<input>',{type:'hidden',name:'content',value:$("[name='contents']").val()}));
	    
	    newForm.appendTo('body');
	    
	    newForm.submit();
	    return false;
	})	;
});*/

/*$(document).ready(function(){
	$("[name='submit_btn']").click(function(){
		
	    var newForm=$('<form></form>');
	    newForm.attr('id','review_form');
	    newForm.attr('method','post');
	    newForm.attr('action','insertMyReview.do');
	    newForm.attr('enctype','multipart/form-data');
	
	    newForm.append($('<input>',{type:'hidden',name:'email',value:$("[name='emails']").val()}));
	    newForm.append($('<input>',{type:'hidden',name:'pageNum',value:$("[name='pageNums']").val()}));
	    newForm.append($('<input>',{type:'hidden',name:'order_number',value:$("[name='order_numbers']").val()}));
	    newForm.append($('<input>',{type:'hidden',name:'rating',value:$("#ratings").val()}));
	   
	    newForm.append($('<input>',{type:'file',name:'photo1',style:'display:none',value:$("[name='photo1']").val()}));
	    newForm.append($('<input>',{type:'file',name:'photo2',style:'display:none',value:$("[name='photo2']").val()}));
	    newForm.append($('<input>',{type:'file',name:'photo3',style:'display:none',value:$("[name='photo3']").val()}));
	    newForm.append($('<input>',{type:'file',name:'photo4',style:'display:none',value:$("[name='photo4']").val()}));
	    newForm.append($('<input>',{type:'file',name:'photo5',style:'display:none',value:$("[name='photo5']").val()}));
	    
	    newForm.append($('<input>',{type:'hidden',name:'content',value:$("[name='contents']").val()}));
	    
	    newForm.appendTo('body');
	    
	    newForm.submit();
	    return false;
	})	;
});
*/
