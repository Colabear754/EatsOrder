<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1}">
	<meta http-equiv="Refresh"
	           content="0;url=/EatsOrder/notice_admin/noticeList.do?pageNum=${pageNum}&category=${category}">
</c:if>
