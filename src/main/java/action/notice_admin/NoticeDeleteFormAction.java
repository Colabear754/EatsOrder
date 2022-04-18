package action.notice_admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class NoticeDeleteFormAction implements CommandAction {
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int notice_number=Integer.parseInt(request.getParameter("notice_number"));
		String pageNum=request.getParameter("pageNum");		
		int category=Integer.parseInt(request.getParameter("category"));
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("notice_number", notice_number);
		request.setAttribute("category", category);
		
		return "/notice_admin/noticeDeleteForm.jsp";
	}
}
