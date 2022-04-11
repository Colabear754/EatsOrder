<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
	작성자: 정건영
	작성일: 2022/04/06
	내용: 회원정보 수정 작업을 처리하는 페이지
--%>
<c:if test="${result == 1}">
	<script>
		alert("회원정보가 수정되었습니다.");
	</script>
	<meta http-equiv="Refresh" content="0;url=/EatsOrder/member/myPage.do">
</c:if>
<c:if test="${result != 1}">
	<script>
		alert("비밀번호를 확인해주세요.");
		history.back();
	</script>
</c:if>