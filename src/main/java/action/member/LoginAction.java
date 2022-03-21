package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CommandAction;
import member.MemberDAO;

public class LoginAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 로그인 액션클래스
		int type = Integer.parseInt(request.getParameter("type")); // 회원타입
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		MemberDAO memberProcess = new MemberDAO();
		boolean result = false;
		
		if (type == 1) {
			result = memberProcess.memberLogin(account, password);
		} else {
			result = memberProcess.nonmemberLogin(account, password);
		}
		
		if (result) {
			request.getSession().setAttribute("account", account);	// 계정과 비밀번호 체크에 성공하면 세션 설정
		}
		
		return "/loginForm.jsp";
	}
}
