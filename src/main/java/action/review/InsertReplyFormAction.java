package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class InsertReplyFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 댓글 작성 폼 액션클래스
		int review_number = Integer.parseInt(request.getParameter("review_number"));
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		
		request.setAttribute("review_number", review_number);
		request.setAttribute("rst_id", rst_id);
		
		return "/insertReplyForm.jsp";
	}
}
