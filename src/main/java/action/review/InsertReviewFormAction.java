package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class InsertReviewFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 리뷰 작성 폼 액션클래스
		String order_number = request.getParameter("order_number");
		String email = request.getParameter("email");
		
		request.setAttribute("order_number", order_number);
		request.setAttribute("email", email);
		
		return "/insertReviewForm.jsp";
	}
}
