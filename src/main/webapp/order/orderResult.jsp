<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
 <head>
  <meta charset="UTF-8">
  <title>주문완료</title>
  <link href="./css/orderResult.css?ver=1" rel="stylesheet" type="text/css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script type="text/javascript" src="./js/orderResult.js"></script>
  <script type="text/javascript" src="./js/time.js"></script>
 </head>
 <body>
 	<jsp:include page="../component/main_header_logAfter.html" />
 <div class="body"> 
   <div class="t1">
  <div class="a1">
      <ul>
	    <li>주문완료</li>
	  </ul>
	</div><!--주문완료-->
	  <div class="a2">
	    <h3><span style="color:#FC6E4D">주문 감사합니다</span></h3>
		   주문 요청이 완료되었으며 곧 고객님의 휴대전화 번호로 주문 확인 문자가 곧 발송됩니다.
		   <fmt:formatNumber value="${total_price / 100}" pattern="#,###" /> 포인트가 적립되었습니다.
	  </div>
	<div class="a3">
	   <ul>
	      <li>배달 정보</li>
		</ul>
	</div><!--배달 정보-->
      <div class="b2">
		  <ul>
		     <li><strong>주문 식당 </strong>: ${rst_name}</li>
			 <li><strong>결제 수단 </strong>: ${orderInfo.payment_method}</li>
		 </ul>
	</div><!--주문식당-->
	 <div class="a4">
	   <ul>
	      <li>주문내역</li>
		</ul>
     </div>
       <div class="b3">
	   <ul>
	      <li><strong>음식명 </strong>: ${orderedItems}</li>
		  <li><strong>배달료 </strong>: <fmt:formatNumber value="${delivery_tip}" pattern="#,###"/>원</li>
		  <li><strong>포인트 할인 </strong>: ${orderInfo.used_point}</li>
		  <li style="color:#FC6E4D"><strong>합계 </strong>: <fmt:formatNumber value="${delivery_tip + total_price - orderInfo.used_point}" pattern="#,###"/>원</li>
		</ul>
     </div>
  </div><!--전체 텍스트-->
   <div id="remove" class="remove">

    <form name="removefrm" method="post">
	   <tr>

    <td class="submit">

     <input id="tblbutton" type="button" value="주문 취소하기" onclick="removeCheck()">

    </td>
    <td>
    	<button id="home-btn">메인으로 돌아가기</button>
    </td>

   </tr>
     </form>
</div>
 <div class="time" id="ViewTimer"></div>
</div>
  <jsp:include page="../component/footer.html" />
 </body>
</html>

