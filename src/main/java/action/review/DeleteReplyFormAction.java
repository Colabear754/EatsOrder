package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class DeleteReplyFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 댓글 삭제 폼 액션클래스
		int reply_number = Integer.parseInt(request.getParameter("reply_number"));
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		
		request.setAttribute("reply_number", reply_number);
		request.setAttribute("rst_id", rst_id);
		
		return "/deleteReplyForm.jsp";
	}
}
