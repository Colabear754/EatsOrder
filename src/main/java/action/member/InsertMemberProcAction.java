package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;

public class InsertMemberProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 회원가입을 위한 액션클래스
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String nickname = request.getParameter("nickname");
		boolean isChecked = Boolean.parseBoolean(request.getParameter("receive_marketing"));
		int receive_marketing = isChecked ? 1 : 0;
		MemberDAO memberProcess = new MemberDAO();

		int result = memberProcess.insertMember(email, password, phone, nickname, receive_marketing);

		request.setAttribute("result", result);

		return "/member/process/insertMemberProc.jsp";
	}
}
