package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.*;

import action.CommandAction;

public class NoticeUpdateFormAction implements CommandAction {
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {

	  	int notice_number=Integer.parseInt(request.getParameter("notice_number"));
		String pageNum=request.getParameter("pageNum");

		NoticeDAO dbPro=new NoticeDAO();
		NoticeDTO article=dbPro.updateGetArticle(notice_number);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);

		return "/notice/noticeUpdateForm.jsp";
	}
}
