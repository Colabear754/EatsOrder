package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.ReviewDAO;
import review.ReviewDTO;

public class UpdateReviewFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int review_number = Integer.parseInt(request.getParameter("review_number"));
		String email = request.getParameter("email");
		ReviewDAO reviewProcess = new ReviewDAO();
		ReviewDTO review = reviewProcess.getReview(review_number);
		
		request.setAttribute("review", review);
		request.setAttribute("email", email);
		
		return "/updateReviewForm.jsp";
	}
}
