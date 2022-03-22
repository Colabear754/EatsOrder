package action.review;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.*;

public class MyReviewListAction implements CommandAction {
	private static final int PAGESIZE = 10;

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 내 리뷰 목록 조회 액션클래스
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		String email = request.getParameter("email");
		ReviewDAO reviewProcess = new ReviewDAO();
		
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;
		
		ArrayList<ReviewDTO> reviewList = reviewProcess.getMyReviews(email, start, end);
		ArrayList<Integer> likeCountList = new ArrayList<>();
		
		for (ReviewDTO review : reviewList) {
			likeCountList.add(reviewProcess.getLikeCount(review.getReview_number()));
		}
		
		request.setAttribute("myReviews", reviewList);
		request.setAttribute("likeCountList", likeCountList);
		
		return "/myReviewList.jsp";
	}
}
