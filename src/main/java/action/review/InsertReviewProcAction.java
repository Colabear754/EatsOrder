package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import review.ReviewDAO;

public class InsertReviewProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 리뷰 작성 액션클래스
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String order_number = request.getParameter("order_number");
		String content = request.getParameter("content");
		int rating = Integer.parseInt(request.getParameter("rating"));
		ReviewDAO reviewProcess = new ReviewDAO();
		String[] photos = new String[5];
		int photoCount = 0;	// 사진 개수
		
		for (int i = 0; i < photos.length; i++) {
			photos[i] = request.getParameter("photo" + (i + 1));
		}
		
		for (String photo : photos) {	// 사진 개수 계산
			photoCount = photo != null ? photoCount + 1 : photoCount;
		}
		
		switch (photoCount) {	// 사진 개수에 따라 호출하는 메소드가 달라짐
		case 0:
			reviewProcess.insertReivew(email, order_number, content, rating);
			break;
		case 1:
			reviewProcess.insertReivew(email, order_number, content, photos[0], rating);
			break;
		case 2:
			reviewProcess.insertReivew(email, order_number, content, photos[0], photos[1], rating);
			break;
		case 3:
			reviewProcess.insertReivew(email, order_number, content, photos[0], photos[1], photos[2], rating);
			break;
		case 4:
			reviewProcess.insertReivew(email, order_number, content, photos[0], photos[1], photos[2], photos[3], rating);
			break;
		case 5:
			reviewProcess.insertReivew(email, order_number, content, photos[0], photos[1], photos[2], photos[3], photos[4], rating);
			break;
		default:
			break;
		}
		
		return "/insertReviewProc.jsp";
	}
}
