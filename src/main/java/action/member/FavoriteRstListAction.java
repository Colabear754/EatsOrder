package action.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.*;
import review.ReviewDAO;

public class FavoriteRstListAction implements CommandAction {
	private static final int PAGESIZE = 10;

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 찜한 매장 목록을 조회하는 액션클래스
		String pageNum = request.getParameter("pageNum");
		String email = request.getParameter("email");
		RestaurantDAO rstProcess = new RestaurantDAO();
		ReviewDAO reviewProcess = new ReviewDAO();
		ArrayList<RestaurantDetailDTO> favoriteRstData = new ArrayList<>();

		if (pageNum == null || pageNum.isBlank()) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);

		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;

		ArrayList<RestaurantDTO> favoriteRstList = rstProcess.getFavoriteRestaurants(email, start, end);

		for (RestaurantDTO rst : favoriteRstList) {
			favoriteRstData.add(new RestaurantDetailDTO(rst, reviewProcess.getReplyCount(rst.getRst_id()),
					reviewProcess.getReplyCount(rst.getRst_id()), rstProcess.getRating(rst.getRst_id())));
		}

		request.setAttribute("email", email);
		request.setAttribute("favoriteRstData", favoriteRstData);

		return "favoriteRstList.jsp";
	}
}
