package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;

public class ResetPasswordProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 비밀번호를 재설정하기 위한 액션클래스
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		MemberDAO memberProcess = new MemberDAO();
		
		int result = memberProcess.resetPassword(email, password);
		
		request.setAttribute("result", result);
		
		return "/member/process/result.jsp";
	}
}
