package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class LogoutAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 로그아웃 액션클래스
		request.getSession().invalidate();	// 세션 해제
		
		return "/loginForm.jsp";
	}

}
