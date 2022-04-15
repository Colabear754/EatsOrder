package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class InsertMemberResultAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 회원가입 완료 페이지
		String email = request.getParameter("email");
		
		request.setAttribute("email", email);
		return "/member/insertMemberResult.jsp";
	}
}
