<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="../menu/js/menuList_script.js"></script>
</head>
<body>
	<div class="dropdown-menu-box">
		<c:forEach var="menuListData" items="${menuList}">
			<div class="btn-group-vertical">
				<!-- 추가 버튼태그 -->
				<button type="button" class="btn btn-default dropdown-toggle" aria-expanded="false">
					${menuListData.category.category_name} <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<c:forEach var="menu" items="${menuListData.menuList}">
					<li class="menu"><a data-toggle="modal" data-target="#menu-modal"><input type="hidden" class="menu_id" value="${menu.menu_id}">
							<div class="row">
								<img class="img-responsive col-sm-3" src="../menu/img/${menu.menu_photo}">
								<ul class="list-unstyled col-sm-9">
									<li><strong>${menu.menu_name}</strong></li>
									<li class="menu-info">${menu.menu_info}</li>
									<li id="amount"><fmt:formatNumber value="${menu.price}" pattern="#,###" />원</li>
								</ul>
							</div>
					</a></li>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
	</div>
	<div class="modal fade" id="menu-modal" role="dialog">
		<div class="modal-dialog" id="menu-info"></div>	
	</div>
</body>
</html>