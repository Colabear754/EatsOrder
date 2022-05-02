<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <link href="./css/cancelOrderForm.css" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
  <script type="text/javascript" src="./js/cancelOrderForm.js"></script>
  <title>주문취소</title>
 </head>
 <body>
 <jsp:include page="../component/main_header_logAfter.html" />
 <main>
 <div class="order_cell1">
   <div class="order_cell2">
     <div>
        <ul>
	      <li class="order_cc">주문 취소</li>	
        </ul>
     </div>
   <div>
      <ul>
	     <li class="cc1">회원 이메일 : ${account}</li>
	     <li class="cc2">주문 번호 : <span id="order_number">${order_number}</span></li>
         <li class="cc2">주문 메뉴 : ${order_item_list}</li>
	     <li class="cc2">주문취소 사유
	     	<textarea class="cc4" id="resaon_cancellation" rows="3" maxlength="100" placeholder="주문취소 사유를 적어주세요." ></textarea>
        	<div class="text_cnt"><span id="text-length">0</span>/100</div>
        	<div class="error">취소 사유를 5자 이상 입력해주세요.</div>
        	</li>
	          
	  </ul>
    </div>
  </div>
 </div>
     <div class="cc5">
       <input type="button" id="cancel" value="주문 취소">
	 </div>
	 </main>
	 <jsp:include page="../component/footer.html" />
   </body>
</html>
