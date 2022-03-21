package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;

public class UpdateMemberProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String phone = request.getParameter("phone");
		String nickname = request.getParameter("nickname");
		String temp = request.getParameter("receive_marketing");
		int receive_marketing;
		MemberDAO memberProcess = new MemberDAO();
		
		if (temp == null || temp.isBlank()) {
			receive_marketing = 0;
		} else {
			receive_marketing = Integer.parseInt(temp);
		}
		
		int result = memberProcess.updateMember(email, password, newPassword, phone, nickname, receive_marketing);
		
		request.setAttribute("result", result);
		
		return "/updateMemberProc.jsp";
	}
}
