package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class DeleteReviewForm implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int review_number = Integer.parseInt(request.getParameter("review_number"));
		String email = request.getParameter("email");
		
		request.setAttribute("review_number", review_number);
		request.setAttribute("email", email);
		
		return "/deleteReviewForm.jsp";
	}
}
