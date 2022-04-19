package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;

public class CheckValidMemberAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 비밀번호 재설정을 위해 계정과 전화번호를 확인하는 메소드
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		MemberDAO memberProcess = new MemberDAO();
		
		boolean result = memberProcess.checkValidMember(email, phone);
		
		request.setAttribute("result", result);
		
		return "/member/process/result.jsp";
	}
}
