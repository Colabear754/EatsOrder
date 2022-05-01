<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<!-- 작성자: 허우림. 작성일: 22-04-18. 페이지명: 공지사항/이벤트/FAQ 게시판목록 페이지(관리자)  -->
<head>
<title>공지사항/이벤트/FAQ탭 게시판(관리자)</title>
<link href="./css/noticelist.css?ver=12" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./css/admin_main.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script>
/* js파일로 따로 분리해서 링크걸면 js가 적용이 안 되서 우선 합쳐둠 */
 $(document).ready(function(){
	    $('.tabmenu').hover(
	    function(event){
	        $(this).addClass('hover');
	    },
	    function(){
	        $(this).removeClass('hover');
	    }
	    );
	});    		
</script>
</head>
<body>
    <header>
        <div class="header_top">
            <div class="header_box">
                <a href="/EatsOrder/notice_admin/noticeList.do?pageNum=1&category=1" class="logo"><img src="./img/logo_white.png" alt="로고-아이콘"></a>
            </div>
        </div>
    </header>
    <div id="container">
 
    <ul class="tabs">
        <li class="current tabmenu" data-tab="tab1" id="default"><a href="/EatsOrder/notice_admin/noticeList.do?category=1">공지사항</a></li>
        <li class="tabmenu" data-tab="tab2"><a href="/EatsOrder/notice_admin/noticeList.do?category=2">이벤트</a></li>
        <li class="tabmenu" data-tab="tab3"><a href="/EatsOrder/notice_admin/noticeList.do?category=3">FAQ</a></li>
    </ul>
    <br>
    <div class="search">
        <form name="test" action="/EatsOrder/notice_admin/noticeList.do">
            <select name="search" >
                <option value="title">제목</option>
                <option value="content">본문</option>
                <option value="title_content">제목+본문</option>
            </select>
            <input class="search2" type="text" name="searchtext">
            <input type="hidden" name="category" value="${category}">
            <input class="search_btn"  type="submit" value="검색">
            <br><br>
        </form>
    </div>
    <a class="write_btn" href="/EatsOrder/notice_admin/noticeWriteForm.do?category=${category}">글쓰기</a>
    <br><br>
    <b class="board_count">총 ${pgList.count}건 [ ${pgList.currentPage} / ${pgList.pageCount} ]</b>
    <br><br>
    <c:if test="${pgList.count==0}">   
        <div class="outer-grid">
            <table>
                <tr>
                    <td>게시판에 저장된 글이 없습니다.</td>
                </tr>
            </table>
        </div>
    </c:if>

    <c:if test="${pgList.count>0 }">
    <div class="outer-grid">
        <table>
            <tr>
                <th class="td1">글번호</th>
                <th class="td2">글제목</th>
                <th>등록일</th>
            </tr>
            <c:set var="number" value="${pgList.number}"/>
            <c:forEach var="article" items="${articleList}">
	            <tr>
	                <td class="td1">
	                    <c:out value="${article.notice_number}"/>
	                    <c:set var="notice_number" value="${article.notice_number-1}"/>
	                </td>
	
	                <td class="td2">
	                    <a href="/EatsOrder/notice_admin/noticeContent.do?notice_number=${article.notice_number}&pageNum=${pgList.currentPage}&category=${category}">
	                    ${article.title}</a> 
	                </td>
	                <td>
	                    <fmt:formatDate value="${article.regist_date}" timeStyle="medium" pattern="yy.MM.dd" />
	                </td>
	            </tr>
            </c:forEach>
        </table>
    </div> 
    </c:if>
	
	<div class="numbering">
	    <c:if test="${pgList.startPage > pgList.blockSize}">
			<a href="/EatsOrder/notice_admin/noticeList.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}&category=${category}">[이전]</a>
		</c:if>
	    <c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
					<a href="/EatsOrder/notice_admin/noticeList.do?pageNum=${i}&search=${search}&searchtext=${searchtext}&category=${category}">
						<c:if test="${pgList.currentPage==i}">
								<b style="color: red;">[${i}]</b>
						</c:if>
						<c:if test="${pgList.currentPage!=i}">
									${i}
						</c:if>
					</a>
		</c:forEach>
	
	    <c:if test="${pgList.endPage < pgList.pageCount}">
					<a href="/EatsOrder/notice_admin/noticeList.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}&category=${category}">[다음]</a> 
		</c:if>
	</div>
    </div>
 </body>
</html>