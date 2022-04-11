package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;

public class FindEmailProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 이메일 계정을 찾는 액션클래스
		String phone = request.getParameter("phone");
		MemberDAO memberProcess = new MemberDAO();
		
		String email = memberProcess.findEmail(phone);
		
		request.setAttribute("account", email);
		
		return "/member/process/findEmailProc.jsp";
	}
}
