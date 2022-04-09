<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${result}">
		<meta http-equiv="Refresh" content="0;url=/EatsOrder/main/main.do"> 
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			alert('계정과 비밀번호를 확인하세요.')
			history.back()
		</script>
	</c:otherwise>
</c:choose>