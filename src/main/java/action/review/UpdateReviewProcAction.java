package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.ReviewDAO;

public class UpdateReviewProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int review_number = Integer.parseInt(request.getParameter("review_number"));
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		ReviewDAO reviewProcess = new ReviewDAO();
		
		int result = reviewProcess.updateReview(review_number, content, email);
		
		request.setAttribute("result", result);
		
		return "/updateReviewProc.jsp";
	}
}
