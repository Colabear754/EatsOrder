package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.*;

import action.CommandAction;

public class NoticeContentAction implements CommandAction{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int notice_number=Integer.parseInt(request.getParameter("notice_number"));
		String pageNum=request.getParameter("pageNum");
		
		NoticeDAO dbPro=new NoticeDAO();
		NoticeDTO article=dbPro.getArticle(notice_number);

		request.setAttribute("notice_number", notice_number);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article",article);
		
		return "/notice/noticeContent.jsp";
	}
}
