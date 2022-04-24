package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;
import review.ReviewDAO;

public class ReviewListFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 리뷰 리스트 폼
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		RestaurantDAO rstProcess = new RestaurantDAO();
		ReviewDAO reviewProcess = new ReviewDAO();
		
		double rating = rstProcess.getRating(rst_id);
		int review_count = reviewProcess.getReviewCount(rst_id);
		int reply_count = reviewProcess.getReplyCount(rst_id);
		
		request.setAttribute("rst_id", rst_id);
		request.setAttribute("rating", rating);
		request.setAttribute("review_count", review_count);
		request.setAttribute("reply_count", reply_count);
		
		return "/review/reviewListForm.jsp";
	}
}
