package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class DeleteMemberFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String email = (String) request.getSession().getAttribute("email");
		
		request.setAttribute("email", email);
		
		return "deleteMemberForm.jsp";
	}
}
