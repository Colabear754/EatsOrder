package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;

public class DeleteMemberProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 회원 탈퇴 액션클래스
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String reason_withdraw = request.getParameter("reason_withdraw");
		MemberDAO memberProcess = new MemberDAO();

		int result = memberProcess.deleteMember(email, password, reason_withdraw);

		request.setAttribute("result", result);

		return "/deleteMemberProc.jsp";
	}
}
