<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용약관</title>
</head>
<body>
	<c:if test="${account != null}">
	    <jsp:include page="../component/main_header_logAfter.html" />
    </c:if>
    <c:if test="${account == null}">
	    <jsp:include page="../component/main_header_logBefore.html" />
    </c:if>
    <jsp:include page="../component/Utilization.html" />
    <jsp:include page="../component/footer.html" />
</body>
</html>