package action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

import notice.*;

public class NoticeDeleteProAction implements CommandAction{
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNum=request.getParameter("pageNum");
		int notice_number=Integer.parseInt(request.getParameter("notice_number"));
		
		NoticeDAO dbPro=new NoticeDAO();
		int check=dbPro.deleteArticle(notice_number);
		
		request.setAttribute("pageNum", pageNum);//수정한 레코드가 있는 페이지로 이동
		request.setAttribute("check", check);//${check} 데이터 수정성공 유무
		
		return "/notice/noticeDeletePro.jsp";
	}
}
