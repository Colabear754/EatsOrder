package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.ReviewDAO;

public class DeleteMyReviewAction implements CommandAction {
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub

		int review_number = Integer.parseInt(request.getParameter("review_number"));
		String email = request.getParameter("email");
		
		ReviewDAO reviewProcess = new ReviewDAO();
		
		int result = reviewProcess.deleteReview(review_number, email);
		
		request.setAttribute("review_number", review_number);
		request.setAttribute("email", email);
		request.setAttribute("result", result);
		
		return "/member/deleteMyReview.jsp";
	}
}
