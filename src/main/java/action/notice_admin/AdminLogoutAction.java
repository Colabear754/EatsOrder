package action.notice_admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class AdminLogoutAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 관리자 로그아웃 액션클래스
		request.getSession().removeAttribute("admin_id");	// 세션 해제
		
		return "/member/process/logout.jsp";
	}

}
