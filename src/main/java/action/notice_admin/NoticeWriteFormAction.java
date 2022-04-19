package action.notice_admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class NoticeWriteFormAction implements CommandAction{
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int notice_number=1, category=Integer.parseInt(request.getParameter("category"));
		   		   
		if(request.getParameter("notice_number")!=null){
		notice_number=Integer.parseInt(request.getParameter("notice_number"));
		}
		  
		request.setAttribute("notice_number", notice_number);
		request.setAttribute("category", category);
		   
		return "/notice_admin/noticeWriteForm.jsp";
	}
}
