package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.ReviewDAO;

public class UpdateReplyProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 댓글 수정 액션클래스
		int reply_number = Integer.parseInt(request.getParameter("reply_number"));
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		String content = request.getParameter("content");
		ReviewDAO reviewProcess = new ReviewDAO();
		
		int result = reviewProcess.updateReply(reply_number, rst_id, content);
		
		request.setAttribute("result", result);
		
		return "/updateReplyProc.jsp";
	}
}
