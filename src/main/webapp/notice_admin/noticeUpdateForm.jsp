<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page import="notice.*" %>
<!DOCTYPE html>
<html>
<head>
<!-- 작성자:허우림. 작성일자:22-04-15 -->
<title>글수정페이지</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="./js/noticeWriteForm.js"></script>
<link href="./css/noticewrite.css" rel="stylesheet" type="text/css">
</head>
<body>  
	<c:if test="${admin_id == null}">
		<meta http-equiv="Refresh" content="0;url=/EatsOrder/main/main.do"> 
	</c:if>
<center><b>글수정</b>
<br>
        <div class="outer-grid">
            <form method="post" name="writeform" enctype="multipart/form-data" action="/EatsOrder/notice_admin/noticeUpdatePro.do?pageNum=${pageNum}&category=${article.category}" 
            			onsubmit="return writeSave()">
                <input type="hidden" name="notice_number" value="${article.notice_number}">
                <input type="hidden" name="pageNum" value="${pageNum}">
                <table>
                    <thead>
                        <tr>
                            <th class="subject">공지사항/이벤트 글수정</th>
                        </tr>
                    </thead>    
                    <tbody>              
                        <tr>
                        	<td class="td1 subject">말머리</td>
                        </tr>
                        <tr width="100%"> 
                            <td class="td2">
                                <select name="category">
                                    <option value="1">[공지]</option>
                                    <option value="2">[이벤트]</option>
                                    <option value="3">[FAQ]</option>
                                </select>
                            </td>
                        </tr>
                        <tr><td class="td1 subject">제목</td></tr>
                        <tr>
                            <td class="td2">
                            	<input type="text" class="write_sub" name="title" placeholder="제목을 입력해주세요."
                            				value="${article.title}">
                            </td>
                        </tr>
                        <tr><td class="td1 subject">내용</td></tr>
                        <tr>
                            <td class="td2"><textarea class="write_cont" name="content">${article.content}</textarea></td>
                        </tr>
                        <c:if test="${article.filename != null}">
                        <tr><td class="td1 subject">기존 첨부 사진</td></tr>
                        <tr><td class="td2 center"><img src="../filestorage/${article.filename}"></td></tr>
                        </c:if>
                        <tr><td class="td1 subject">새로 첨부할 사진</td></tr>
                        <tr>
                            <td class="td1 td2"><input type="file" name="fileName"></td>
                        </tr>       
                    </tbody>
                </table>
                <br>
                <div class="btn">
                    <input  type="submit" value="글수정">
                    <input  type="reset" value="다시 쓰기">
                    <input type="button" value="목록보기" 
       			onclick="document.location.href='/EatsOrder/notice_admin/noticeList.do?pageNum=${pageNum}&category=${article.category}'">
                </div>
            </form>
        </div>  
</body>
</html>      
