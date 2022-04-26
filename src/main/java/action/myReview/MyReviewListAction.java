package action.myReview;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import coupon.CouponDAO;
import member.*;
import order.OrderBasicInfoDTO;
import review.*;

public class MyReviewListAction implements CommandAction {
	private static final int PAGESIZE = 10;

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	// 내 리뷰 목록 조회 액션클래스
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		String email=request.getParameter("account");
		System.out.println("account: "+email);
		System.out.println("pageNum: "+pageNum);
		//String email = (String) request.getSession().getAttribute("account");
		MemberDAO memberProcess = new MemberDAO();
		CouponDAO couponProcess = new CouponDAO();
		ReviewDAO reviewProcess = new ReviewDAO();
		ArrayList<ReviewDetailDTO> reviewData = new ArrayList<>();
		ArrayList<OrderBasicInfoDTO> orderList = reviewProcess.getReviewToWrite(email);
		long overDate;	// 작성일로부터 경과 일수를 저장하기 위한 변수
		
		
		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;

		ArrayList<ReviewDTO> reviewList = reviewProcess.getMyReviews(email, start, end);

		for (ReviewDTO review : reviewList) {
			overDate = (System.currentTimeMillis() - review.getRegist_date().getTime()) / 1000 / 60 / 60 / 24;
			
			reviewData.add(new ReviewDetailDTO(review, null, reviewProcess.getLikeCount(review.getReview_number()),
					reviewProcess.getReviewRst(review.getReview_number()), overDate, null));
		}

		MemberInfoDTO member = memberProcess.getMember(email);
		int couponCount = couponProcess.getCouponCount(email);
		
		System.out.println("reviewData"+reviewData);
		System.out.println("orderList"+orderList);
		
		request.setAttribute("email",email);
		request.setAttribute("member", member);
		request.setAttribute("couponCount", couponCount);
		request.setAttribute("reviewData", reviewData);
		request.setAttribute("orderList", orderList);

		return "/myReview/myReviewList.jsp";
	}
}
