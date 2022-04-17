package action.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class InsertReviewFormAction implements CommandAction {

	//추가. 작성자:허우림. 작성일자:22-04-17. 내용:여러 개의 파일 업로드
	
	//request,paramName을 인자로 전달받아서 현재의 폼 요소들 중 paramName으로 지정된 부분에 입력된 값을 보존하고 현재의 폼을 전송하는 메소드
	public String getParam(HttpServletRequest request,String paramName) {
		//이 페이지가 처음 실행될때 request.getParameter()로 받아오는 값이 없기 때문에 이부분이 null인지 아닌지를 체크
		if(request.getParameter(paramName)!=null){
			//if조건이 null이 아닐때(이 페이지를 전송했을때), 파라미터(폼 요소들)을 가져와서 결과값으로 반환
			return request.getParameter(paramName);
		}else {
			return "";
		}
	}
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		int filecounter=0;
		//InsertReviewForm.jsp의 addcnt(업로드할 파일수)를 입력했다면,그것을 filecounter에 담음
		if(request.getParameter("addcnt")!=null) {
			filecounter=Integer.parseInt(request.getParameter("addcnt"));
		}
		
		int review_number=1;
		String content=request.getParameter("content");
		int rating=Integer.parseInt(request.getParameter("rating"));
		
		request.setAttribute("filecounter",filecounter);
		request.setAttribute("review_number", review_number);
		request.setAttribute("content", content);
		request.setAttribute("rating", rating);
		
	//-----------------------
		
		// 리뷰 작성 폼 액션클래스
		String order_number = request.getParameter("order_number");
		String email = request.getParameter("email");
		
		request.setAttribute("order_number", order_number);
		request.setAttribute("email", email);
		
		return "/insertReviewForm.jsp";
	}
}
