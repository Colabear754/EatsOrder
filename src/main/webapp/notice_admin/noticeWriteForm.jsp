<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!-- 작성자:허우림. 작성일:22-04-15. 페이지명:공지사항 글쓰기 페이지 -->
<head>
<title>공지사항 글쓰기 페이지</title>
<link href="./css/noticewrite.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="./js/noticeWriteForm.js"></script>
</head>
<body>  
	<c:if test="${admin_id == null}">
		<meta http-equiv="Refresh" content="0;url=/EatsOrder/main/main.do"> 
	</c:if>
       <br>
        <div class="outer-grid">
            <form method="post" name="writeform" enctype="multipart/form-data" action="/EatsOrder/notice_admin/noticeWritePro.do">
                <input type="hidden" name="notice_number" value="${notice_number}">
                <table>
                    <thead>
                        <tr>
                            <th class="subject">공지사항/이벤트 글쓰기</th>
                        </tr>
                    </thead>    
                    <tbody>              
                        <tr><td class="td1 subject">말머리</td></tr>
                        <tr width="100%"> 
                            <td class="td2">
                                <select name="category">
                                    <option value="1" selected>[공지]</option>
                                    <option value="2">[이벤트]</option>
                                    <option value="3">[FAQ]</option>
                                </select>
                            </td>
                        </tr>
                        <tr><td class="td1 subject">제목</td></tr>
                        <tr>
                            <td class="td2"><input type="text" class="write_sub" name="title" placeholder="제목을 입력해주세요."></td>
                        </tr>
                        <tr><td class="td1 subject">작성자</td></tr>
						<tr><td><input type="text" name="writer"></td></tr>
                        <tr><td class="td1 subject">내용</td></tr>
                        <tr>
                            <td class="td2"><textarea class="write_cont" name="content"></textarea>
                        </tr>
                        <tr><td class="td1 subject">첨부할 사진</td></tr>
                        <tr>
                            <td class="td1 td2"><input type="file" name="filename"></td>
                        </tr>
                    </tbody>
                </table>
                <br>
                <div class="btn">
                    <input  type="submit" value="글등록하기">
                    <input  type="reset" value="다시 쓰기">
                    <input  type="button"value="목록보기" OnClick="window.location='/EatsOrder/notice_admin/noticeList.do?category=${category}'">
                </div>
            </form>
        </div>  
</body>
</html>      