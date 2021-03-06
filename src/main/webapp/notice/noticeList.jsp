<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<!-- 작성자: 허우림. 작성일: 22-04-25. 페이지명: 공지사항/이벤트/FAQ 게시판목록 페이지  -->
<head>
<title>공지사항/이벤트/FAQ탭 게시판</title>
<link href="./css/noticelist.css?ver=1" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./css/admin_main.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script>
/* js파일로 따로 분리해서 링크걸면 js가 적용이 안 되서 우선 합쳐둠 */
 $(function(){
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
                <a href="/EatsOrder/notice/noticeList.do?pageNum=1&category=1" class="logo"><img src="./img/logo_white.png" alt="로고-아이콘"></a>
            </div>
        </div>
    </header>
    <div id="container">
 
    <ul class="tabs">
        <li class="tab1 tabmenu" data-tab="tab1" id="default" value="1"><a class="tab11" href="/EatsOrder/notice/noticeList.do?category=1">공지사항</a></li>
        <li class="tab2 tabmenu" data-tab="tab2" value="2"><a href="/EatsOrder/notice/noticeList.do?category=2">이벤트</a></li>
        <li class="tab3 tabmenu" data-tab="tab3" value="3"><a href="/EatsOrder/notice/noticeList.do?category=3">FAQ</a></li>
    </ul>
    <br>
    <div class="search">
        <form name="test" action="/EatsOrder/notice/noticeList.do">
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
    
    <br><br>
    <b class="board_count">총 ${pgList.count}건 [ ${pgList.currentPage} / ${pgList.pageCount} ]</b>
    <br><br>
    
    <div class="outer-grid">
    <c:if test="${pgList.count==0}"> 
            <table>
                <tr>
                    <td>게시판에 저장된 글이 없습니다.</td>
                </tr>
            </table>
	</c:if>
	<c:if test="${pgList.count>0 }">
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
	                    <a href="/EatsOrder/notice/noticeContent.do?notice_number=${article.notice_number}&pageNum=${pgList.currentPage}&category=${category}">
	                    ${article.title}</a> 
	                </td>
	                <td>
	                    <fmt:formatDate value="${article.regist_date}" timeStyle="medium" pattern="yy.MM.dd" />
	                </td>
	            </tr>
            </c:forEach>
        </table>
    </c:if>           
    </div>
	
	<div class="numbering">
	    <c:if test="${pgList.startPage > pgList.blockSize}">
			<a href="/EatsOrder/notice/noticeList.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}&category=${category}">[이전]</a>
		</c:if>
	    <c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
					<a href="/EatsOrder/notice/noticeList.do?pageNum=${i}&search=${search}&searchtext=${searchtext}&category=${category}">
						<c:if test="${pgList.currentPage==i}">
								<b style="color: red;">[${i}]</b>
						</c:if>
						<c:if test="${pgList.currentPage!=i}">
									${i}
						</c:if>
					</a>
		</c:forEach>
	
	    <c:if test="${pgList.endPage < pgList.pageCount}">
					<a href="/EatsOrder/notice/noticeList.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}&category=${category}">[다음]</a> 
		</c:if>
	</div>
    </div>
 </body>
</html>