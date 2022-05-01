function removeCheck() {

 if (confirm("주문 취소하시겠습니까?") == true){    //확인
     document.removefrm.submit();
     location.href="file:///C:/webtest/order/cancell/cancell.html"
 }else{   //취소

     return false;

 }

}

$(function() {
	$('#home-btn').click(function() {
		location.href = '/EatsOrder/main/main.do';
	})
})