package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class InsertReviewFormAction implements CommandAction {

	//추가. 작성자:허우림. 작성일자:22-04-17. 내용:여러 개의 파일 업로드
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		int review_number=1;
		String content=request.getParameter("content");
		int rating=Integer.parseInt(request.getParameter("rating"));
		
		request.setAttribute("review_number", review_number);
		request.setAttribute("content", content);
		request.setAttribute("rating", rating);
		
	//-----------------------
		
		// 리뷰 작성 폼 액션클래스
		String order_number = request.getParameter("order_number");
		String email = request.getParameter("email");
		
		request.setAttribute("order_number", order_number);
		request.setAttribute("email", email);
		
		return "/review/insertReviewForm.jsp";
	}
}
