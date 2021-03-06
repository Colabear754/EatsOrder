<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 마이페이지 사이드 메뉴
    
    jsp변환 : 정건영
    변환일 : 2022/04/06
    --%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>
    <link rel="stylesheet" href="./css/mypage_style.css">
</head>

<body>
	<c:if test="${account == null}">
		<meta http-equiv="Refresh" content="0;url=/EatsOrder/main/main.do"> 
	</c:if>
    <h2 class="mypage_head">마이페이지</h2>
    <div class="mypage_wrap">
        <div class="profile_box">
            <div class="membership_lv">
                <div><strong>${member.membership}</strong></div>
            </div>
            <p>안녕하세요, ${member.nickname}님</p>
            <ul>
                <li><a href="/EatsOrder/member/couponList.do"><strong>${couponCount}장</strong><br>쿠폰</a></li>
                <li><a><strong>${member.point}P</strong><br>포인트</a></li>
                <li><a href="/EatsOrder/member/favoriteRstList.do"><strong>찜목록</strong></a></li>
                <li><a href="/EatsOrder/member/orderList.do"><strong>주문내역</strong></a></li>
            </ul>
        </div>
        <div class="quick_menu" id="quick_menu">
            <div>
                <h3>quick menu</h3>
            </div>
            <ul>
                <li><a href="/EatsOrder/member/orderList.do">회원주문내역</a></li>
                <li><a href="/EatsOrder/member/favoriteRstList.do">찜 페이지</a></li>
                <li><a href="/EatsOrder/member/couponList.do">쿠폰함</a></li>
                <li><a href="/EatsOrder/member/myReviewList.do">리뷰관리</a></li>
                <li><a href="/EatsOrder/member/updateMemberForm.do">내 정보 수정</a></li>
            </ul>
        </div>
    </div>
</body>

</html>