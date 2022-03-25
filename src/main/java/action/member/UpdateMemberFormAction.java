package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.*;

public class UpdateMemberFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String email = (String) request.getSession().getAttribute("email");
		MemberDAO memberProcess = new MemberDAO();
		MemberInfoDTO member = memberProcess.getMember(email);
		
		request.setAttribute("member", member);
		
		return "updateMemberForm.jsp";
	}
}
