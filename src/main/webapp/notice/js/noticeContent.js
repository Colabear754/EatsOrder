$(function(){
	var num=$("[name='category']").val();
	
	switch (num){
		case '1':
			$("#category").text('공지사항');
			break;
		case '2':
			$("#category").text('이벤트');
			break;
		case '3':
			$("#category").text('FAQ');
			break;			
	}
})