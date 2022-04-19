package action.notice_admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

import notice.*;

public class NoticeDeleteProAction implements CommandAction{
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum=request.getParameter("pageNum");
		int notice_number=Integer.parseInt(request.getParameter("notice_number"));
		int category=Integer.parseInt(request.getParameter("category"));
		
		NoticeDAO dbPro=new NoticeDAO();
		int check=dbPro.deleteArticle(notice_number);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		request.setAttribute("category", category);
		
		return "/notice_admin/noticeDeletePro.jsp";
	}
}
