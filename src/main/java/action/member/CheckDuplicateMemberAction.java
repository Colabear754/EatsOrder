package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;

public class CheckDuplicateMemberAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 회원 정보 중복 확인 액션클래스
		String type = request.getParameter("type");
		String data = request.getParameter(type);
		MemberDAO memberProcess = new MemberDAO();
		
		boolean result = memberProcess.checkDuplicateMember(data, type);
		
		request.setAttribute("result", result);
		
		return "/member/process/checkDuplicateMember.jsp";
	}

}
