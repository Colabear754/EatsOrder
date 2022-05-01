package action.notice_admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import administrator.AdministratorDAO;

public class AdminLoginAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 관리자 로그인 액션클래스
		String admin_id = request.getParameter("admin_id");
		String password = request.getParameter("password");
		AdministratorDAO adminProcess = new AdministratorDAO();
		boolean result = false;
		
		result = adminProcess.adminLogin(admin_id, password);
		
		if (result) {
			request.getSession().setAttribute("admin_id", admin_id);	// 계정과 비밀번호 체크에 성공하면 세션 설정
		}
		
		request.setAttribute("result", result);
		
		return "/component/result.jsp";
	}
}
