<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%-- 
    작성자: 김나연
    작성완료일: 22/04/04
    페이지명: 회원가입 폼
    
    jsp변환 : 정건영
    변환일 : 2022/04/09
    추가수정내용 : 동적 웹 페이지 작동을 위한 태그 추가
    --%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="./css/insert_member_style.css">
    <link rel="stylesheet" href="./css/login_header_style.css">
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./js/insert_member_script.js"></script>
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
        <div class="all_box">
            <div class="title_box">
                <h1 id="test">회원가입</h1>
                <hr>
            </div>
            <div class="join_form"> 
                <table>
                    <tbody>
                        <tr>
                            <th>이메일</th>
                            <td>
                                <input type="text" name="id_input" id="email" placeholder="이메일을 입력해주세요.">
                                <label class="duplicate_check" id="email_check">중복확인</label>
                            </td>
                        </tr>
                        <tr id="form_helper1" class="form_helper">
                            <th></th>
                            <td>
                            <p>예시)food123@eatsorder.com</p>
                            <p class="error" id="email_error"></p>
                            <p class="available" id="available_email">사용 가능한 이메일입니다.</p>
                            </td>
                        </tr>
                        <tr>
                            <th>비밀번호</th>
                            <td>
                                <input type="password" name="id_input" id="password" placeholder="비밀번호를 입력해주세요.">
                            </td>
                        </tr>
                        <tr id="form_helper2" class="form_helper">
                            <th></th>
                            <td>
                            <p>10자 이상 입력</p>
                            <p>영문/숫자/특수문자 모두 조합</p>
                            <p>동일한 숫자 3개 이상 연속 사용 불가</p> 
                            <p class="error" id="password_error"></p>
                            <p class="available" id="available_password">사용 가능한 비밀번호입니다.</p>
                            </td>
                        </tr>
                        <tr>
                            <th>비밀번호 확인</th>
                            <td>
                                <input type="password" name="id_input" id="re_pwd" placeholder="비밀번호를 입력해주세요.">
                            </td>
                        </tr>
                        <tr id="form_helper3" class="form_helper">
                            <th></th>
                            <td>
                                <p>비밀번호를 한번 더 입력해주세요</p>
                                <p class="error" id="re_pwd_error"></p>
                                <p class="available" id="re_pwd_equal">비밀번호가 일치합니다.</p>
                            </td>
                        </tr>
                        <tr>
                            <th>닉네임</th>
                            <td>
                                <input type="text" name="id_input" id="nickname" placeholder="닉네임을 입력해주세요.">
                                <label class="duplicate_check" id="nickname_check">중복확인</label>
                            </td>
                        </tr>
                        <tr id="form_helper4" class="form_helper">
                            <th></th>
                            <td>
                                <p id="nickname_notice">한글, 영어, 숫자만 사용</p>
                                <p class="error" id="nickname_error"></p>
                                <p class="available" id="available_nickname">사용 가능한 닉네임입니다.</p>
                            </td>
                        </tr>
                        <tr>
                            <th>휴대폰</th>
                            <td>
                                <input type="text" name="id_input" id="phone" oninput="this.value = this.value.replaceAll(/\D/g, '')" maxlength="13">
                                <label class="duplicate_check" id="phone_check">중복확인</label>
                            </td>
                        </tr>
                        <tr id="form_helper5" class="form_helper">
                            <th></th>
                            <td>
                                <p id="phone_notice">숫자만 입력하세요.</p>
                                <p class="error" id="phone_error"></p>
                                <p class="available" id="available_phone">사용 가능한 휴대폰 번호입니다.</p>
                            </td>
                        </tr>
                        <tr>
                            <th>이용약관 동의</th>
                            <td class="check_box">
                                <label>
                                    <input type="checkbox" id="receive_marketing">
                                    <span>마케팅 수신동의</span>
                                    <a href="#">약관보기></a>
                                </label>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="join_box">
                    <hr>
                    <button class="button" id="submit">가입하기</button> <button class="button" onclick="window.location.href='/EatsOrder/member/loginForm.do'">취소</button>
                </div>
            </div>
        </div>
    </main>
</body>
</html>