package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class RstManagerLogoutAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 사장님 로그아웃 액션클래스
		request.getSession().invalidate();
		
		return "/rstManagerLoginForm.jsp";
	}
}
