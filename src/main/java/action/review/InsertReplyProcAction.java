package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.ReviewDAO;

public class InsertReplyProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 댓글 작성 액션클래스
		request.setCharacterEncoding("utf-8");
		int review_number = Integer.parseInt(request.getParameter("review_number"));
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		String content = request.getParameter("content");
		ReviewDAO reviewProcess = new ReviewDAO();
		
		reviewProcess.insertReply(review_number, rst_id, content);
		
		return "/insertReplyProc.jsp";
	}
}
