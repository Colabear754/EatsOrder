package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

import notice.*;

public class NoticeDeleteFormAction implements CommandAction {
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int notice_number=Integer.parseInt(request.getParameter("notice_number"));
		String pageNum=request.getParameter("pageNum");		

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("notice_number", notice_number);//${article.num}
		
		return "/notice/noticeDeleteForm.jsp";
	}
}
