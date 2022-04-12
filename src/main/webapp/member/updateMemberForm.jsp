<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 회원정보수정
    
    jsp변환 : 정건영
    변환일 : 2022/04/06
    추가수정내용 : 동적 웹 페이지 작동을 위한 태그 추가
    --%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원정보수정</title>
    <link rel="stylesheet" href="./css/update_member_style.css">
    <link rel="stylesheet" href="./css/login_header_style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./js/update_member_script.js"></script>
</head>
<body>
	<header>
        <div class="header_top">
            <div class="header_box">
                <a href="/EatsOrder/main/main.do" class="logo"><img src="./img/Logo_white.png" alt="로고-아이콘"></a>
                <ul class="icon_menu">
                    <li><a href="#"><i class="fa-solid fa-cart-shopping"> Cart</i></a></li>
                    <li><a href="/EatsOrder/member/loginForm.do"><i class="fa-solid fa-user"> Login</i></a></li>
                </ul>
            </div>
        </div>
    </header>
    <main>
        <form class="all_box" action="/EatsOrder/member/updateMemberProc.do" method="post">
            <div class="title_box">
                <h1>회원정보수정</h1>
                <hr>
            </div>
            <table>
                <tbody>
                    <tr>
                        <th>이메일</th>
                        <td>
                            <input type="text" name="email" value="${member.email}" disabled="disabled"/>
                            <input type="hidden" name="email" id="email" value="${member.email}">
                        </td>
                    </tr>
                    <tr>
                        <th>현재 비밀번호</th>
                        <td>
                            <input type="password" name="password" id="password" placeholder="현재 비밀번호를 입력해주세요."/>
                        </td>
                    </tr>
                    <tr>
                        <th>새 비밀번호</th>
                        <td>
                            <input type="password" name="newPassword" id="newPassword" placeholder="새 비밀번호를 입력해주세요.">
                        </td>
                    </tr>
                    <tr id="form_helper1" class="form_helper">
                            <th></th>
                            <td>
                            <p>10자 이상 입력</p>
                            <p>영문/숫자/특수문자(공백 제외)만 허용하며, 2개 이상 조합</p>
                            <p>동일한 숫자 3개 이상 연속 사용 불가</p> 
                            </td>
                    </tr>
                    <tr>
                        <th>새 비밀번호 확인</th>
                        <td>
                            <input type="password" name="renew_pwd" id="renew_pwd" placeholder="새 비밀번호를 한번 더 입력해주세요.">
                        </td>
                    </tr>
                    <tr id="form_helper2" class="form_helper">
                        <th></th>
                        <td>
                            <p>비밀번호를 한번 더 입력해주세요</p>
                        </td>
                    </tr>
                    <tr>
                        <th>닉네임</th>
                        <td>
                            <input type="text" name="nickname" id="nickname" value="${member.nickname}" placeholder="닉네임을 입력해주세요.">
                            <a href="#">중복확인</a>
                        </td>
                    </tr>
                    <tr id="form_helper3" class="form_helper">
                        <th></th>
                        <td>
                            <p>한글, 영어, 숫자만 사용</p>
                        </td>
                    </tr>
                    <tr>
                        <th>휴대폰</th>
                        <td>
                            <input type="text" name="phone" id="phone" value="${member.phone}" oninput="this.value = this.value.replaceAll(/\D/g, '')" placeholder="휴대폰 번호를 입력하세요." maxlength="13">
                            <a href="#">중복확인</a>
                        </td>
                    </tr>
                    <tr id="form_helper4" class="form_helper">
                        <th></th>
                        <td>
                            <p>숫자만 입력하세요</p>
                        </td>
                    </tr>
                    <tr>
                        <th>이용약관 동의</th>
                        <td class="check_box"> 
                            <label>
                                <input type="checkbox">
                                <span>이용약관 동의</span>
                                <a href="#">약관보기></a>
                            </label>
                            <label>
                                <input type="checkbox" name="receive_marketing" value="1">
                                <span>마케팅 수신동의</span>
                                <a href="#">약관보기></a>
                            </label>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="member_box">
                <input type="submit" value="수정 완료" id="btn"> <button onclick="history.back()">취소</button>
            </div>
        </form>
    </main>
</body>

</html>