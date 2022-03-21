package action.review;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import member.MemberDAO;
import review.*;

public class ReviewListAction implements CommandAction {
	private static final int PAGESIZE = 10;

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 리뷰와 리뷰 댓글 목록을 조회하는 페이지에 대한 액션클래스
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		boolean onlyPhotoReview = Boolean.parseBoolean(request.getParameter("onlyPhotoReview"));
		ReviewDAO reviewProcess = new ReviewDAO();
		MemberDAO memberProcess = new MemberDAO();

		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;

		ArrayList<ReviewDTO> reviewList = reviewProcess.getReviews(rst_id, onlyPhotoReview, start, end);	// 리뷰 리스트
		ArrayList<ReplyDTO> replyList = reviewProcess.getReply(rst_id);	// 댓글 리스트
		ArrayList<String> writerNicknameList = new ArrayList<>();	// 작성자 닉네임 리스트
		ArrayList<Integer> likeCountList = new ArrayList<>();	// 리뷰 좋아요 개수 리스트

		for (ReviewDTO review : reviewList) {
			writerNicknameList.add(memberProcess.getNickname(reviewProcess.getReviewWriter(review.getReview_number())));
			likeCountList.add(reviewProcess.getLikeCount(review.getReview_number()));
		}

		int reviewCount = reviewList.size();
		int replyCount = replyList.size();

		if (!onlyPhotoReview) {
			System.out.println("전체 리뷰 개수 : " + reviewCount);
		} else {
			System.out.println("사진 리뷰 개수 : " + reviewCount);
		}

		request.setAttribute("reviewCount", reviewCount);
		request.setAttribute("replyCount", replyCount);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("replyList", replyList);
		request.setAttribute("writerNicknameList", writerNicknameList);
		request.setAttribute("likeCountList", likeCountList);
		request.setAttribute("onlyPhotoReview", onlyPhotoReview);

		return "/reviewList.jsp";
	}
}
