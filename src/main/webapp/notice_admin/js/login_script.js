/* 
작성자 : 정건영
작성일 : 2022/05/01
내용 : 관리자 로그인
*/

$(function() {
    // 로그인버튼으로 로그인
    $("#login").click(function() {
    	var admin_id = $("#admin_id").val()
    	var password = $("#password").val()
    	$.ajax({
    		type: "POST",
    		url: "/EatsOrder/notice_admin/adminLogin.do",
    		data: "admin_id=" + admin_id + "&password=" + password,
    		dataType: "text",
    		success: function(data) {
    			if (data.indexOf("true") > 0) {
    				window.location.href = "/EatsOrder/notice_admin/adminMain.do"
    			} else {
    				$("#login_error").css("display", "block")
    				$("#admin_id").css("outline", "2px solid red")
    				$("#password").css("outline", "2px solid red")
    			}
    		},
    		error: function(request) {
    			alert("오류 발생 : " + request.status)
    		}
    	})
    })
    
    // 엔터키를 눌렀을 때 로그인
    $('#account').keyup(function(e) {
		$(this).css('outline', '')
		if (e.keyCode == 13) {
			login()
		}
	})
	
	$('#password').keyup(function(e) {
		$(this).css('outline', '')
		if (e.keyCode == 13) {
			login()
		}
	})
});

//엔터키를 눌렀을 때 로그인 되도록 별도 함수로 작성
function login() {
	var admin_id = $("#admin_id").val()
	var password = $("#password").val()
	$.ajax({
		type: "POST",
		url: "/EatsOrder/notice_admin/adminLogin.do",
		data: "admin_id=" + admin_id + "&password=" + password,
		dataType: "text",
		success: function(data) {
			if (data.indexOf("true") > 0) {
				window.location.href = "/EatsOrder/notice_admin/adminMain.do"
			} else {
				$("#login_error").css("display", "block")
				$("#admin_id").css("outline", "2px solid red")
				$("#password").css("outline", "2px solid red")
			}
		},
		error: function(request) {
			alert("오류 발생 : " + request.status)
		}
	})
}