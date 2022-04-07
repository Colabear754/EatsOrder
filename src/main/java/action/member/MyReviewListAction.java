package action.member;

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
		String email = (String) request.getSession().getAttribute("account");
		ReviewDAO reviewProcess = new ReviewDAO();
		ArrayList<ReviewAndLikeCountDTO> reviewData = new ArrayList<>();
		
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;
		
		ArrayList<ReviewDTO> reviewList = reviewProcess.getMyReviews(email, start, end);
		
		for (ReviewDTO review : reviewList) {
			reviewData.add(new ReviewAndLikeCountDTO(review, reviewProcess.getLikeCount(review.getReview_number())));
		}
		
		request.setAttribute("myReviews", reviewData);
		
		return "/member/myReviewList.jsp";
	}
}
