package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.ReplyDTO;
import review.ReviewDAO;

public class UpdateReplyForm implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 댓글 수정 폼 액션클래스
		int reply_number = Integer.parseInt(request.getParameter("reply_number"));
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		ReviewDAO reviewProcess = new ReviewDAO();
		ReplyDTO reply = reviewProcess.getReply(reply_number);
		
		request.setAttribute("reply", reply);
		request.setAttribute("rst_id", rst_id);
		
		return "/updateReplyForm.jsp";
	}
}
