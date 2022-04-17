<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<!-- 
작성자:허우림. 작성일자:22-04-14. 페이지이름:리뷰작성페이지.
설명:복수 파일 업로드 부분만 해둠 -->
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰작성페이지</title>
    <link rel="stylesheet" href="./css/myPage_like_style.css">
<script language="JavaScript">
/*js부분은 테스트 완료하면 따로 파일 분리해서 링크걸기  */

 //폼에 있는 요소의 이름에서, 폼요소의 인덱스(배치된 순서대로 0,1,~)을 인자로 받음
	function inputValue(form1,param,form2,idx){
		//폼요소들 중 첫번째 요소값 변수에 저장(frmName1의 user)
		var paramValue=form1.elements[idx].value;
		form2.elements[idx].value=paramValue;
		return;
	}
	function addFile(formName){
		if(formName.addcnt.value==""){
			alert("업로드할 파일 개수를 입력한 후 확인버튼을 눌러주세요.");
			formName.addcnt.focus();
			return;
		}
		formName.submit();
	}
	function elementCheck(formName){
		paramIndex=1;
		for(idx=0;idx<formName.elements.length;idx++){
			if(formName.elements[idx].type=="file"){
				if(formName.elements[idx].value=""){
					var msg=paramIndex+"번째 파일정보가 누락되었습니다.\n 업로드할 파일을 선택해주세요.";
					alert(msg);
					formName.elements[idx].focus();
					return;
				}
				paramIndex++;
			}
		}
		formName.action="리뷰상세보기.jsp";
		formName.submit();
	}
</script>
</head>
<body>
<form name="frmName1" method="post">
	<!--getParam 메서드는 InsertReviewFormAction에 있음. 리뷰 사진과 함께 넘길 닉네임 등의 값들은 밑의 양식에 맞춰서 바꾸면됨  -->
	<input name="user" onkeyup="inputValue(this.form,user,frmName2,0)" value="<%=getParam(request,"user") %>">
	<!-- 확인버튼 누르면, 현재 페이지로 폼을 전송. addcnt 값을 뽑아서 filecounter변수에 담음. -->
	업로드할 파일수 입력:<input name="addcnt">
	<input type="button" value="확인" onclick="addFile(this.form)">
</form>

<form name="frmName2" method="post" enctype="multipart/form-data">
 	<input type="hidden" name="user" value="<%=getParam(request,"user") %>">
 	<% for(int i=0;i<filecounter;i++){ %>
 	<input type="file" size="50" name="selectFile<%=i%>"><br>
 	<%} %>
 	 <input type="button" value="리뷰등록" onclick="elementCheck(this.form)">
</form>
</body>

</html>