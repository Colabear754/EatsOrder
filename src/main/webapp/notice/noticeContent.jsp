<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="notice.*,java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page import="java.util.*,java.io.*,notice.*" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<link href="./css/noticewrite.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./js/noticeContent.js"></script>

</head>
<body>  
<div class="outer-grid">
<b>글 상세보기</b>
<br>
<form>
<input type="hidden" name="category" value="${article.category }">
<table> 
  <tr>
    <td class="td1 subject">글번호</td>
    <td class="td2">${article.notice_number}</td>
  </tr>
  
  <tr>
  	<td class="td1 subject">카테고리(말머리)</td>
  	<td class="td2" id="category">${article.category}</td>
  </tr>
  
  <tr>
    <td class="td1 subject">작성일</td>
    <td class="td2">${article.regist_date}</td>
  </tr>
  
  <tr>
    <td class="td1 subject">글제목</td>
    <td class="td2">${article.title}</td>
  </tr>
  
  <tr>
    <td class="td1 subject">글내용</td>
    <td class="td2">
    	<pre>${article.content}</pre>
    </td>
  </tr>
  <c:if test="${article.filename != null}"> 
  <tr>
  	<td colspan="2" class="td2 center" style="text-align:center;">
  		<img src="../filestorage/${article.filename}" alt="예시1">
  	</td>
  </tr>
  <tr>
	  <td class="td1 subject">파일명</td>
	  <td class="td2">${article.filename}</td>
  </tr>
  </c:if>
  </table> 
  <br>
  <div class="btn">
       <input type="button" value="글목록" 
       onclick="document.location.href='/EatsOrder/notice/noticeList.do?pageNum=${pageNum}&category=${article.category}'">
    </div>
</form>      
</div>
</body>
</html>      
