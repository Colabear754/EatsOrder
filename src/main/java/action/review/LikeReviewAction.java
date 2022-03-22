package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.ReviewDAO;

public class LikeReviewAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 리뷰에 좋아요를 등록하거나 취소하는 액션클래스
		int review_number = Integer.parseInt(request.getParameter("review_number"));
		String email = request.getParameter("email");
		ReviewDAO reviewProcess = new ReviewDAO();
		
		reviewProcess.likeReview(review_number, email);
		
		return "/likeReview.jsp";
	}
}
