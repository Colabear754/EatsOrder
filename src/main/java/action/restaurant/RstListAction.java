package action.restaurant;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;
import restaurant.RestaurantDTO;
import restaurant.RestaurantDetailDTO;
import review.ReviewDAO;

public class RstListAction implements CommandAction {
	private static final int PAGESIZE = 12;

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 카테고리별 매장 목록을 조회하는 액션클래스
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		int category_id = request.getParameter("category_id") == null ? 0 :Integer.parseInt(request.getParameter("category_id"));
		int orderBy = request.getParameter("orderBy") == null ? 1 : Integer.parseInt(request.getParameter("orderBy"));
		String address = request.getParameter("address");
		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		String bname = request.getParameter("bname");
		String searchText = request.getParameter("searchText") != null ? request.getParameter("searchText") : "";
		RestaurantDAO rstProcess = new RestaurantDAO();
		ReviewDAO reviewProcess = new ReviewDAO();
		ArrayList<RestaurantDetailDTO> rstData = new ArrayList<>();

		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * PAGESIZE + 1;
		int end = currentPage * PAGESIZE;
		ArrayList<RestaurantDTO> rstList = null;

		if (searchText.isBlank()) {
			rstList = rstProcess.getRestaurants(category_id, orderBy, sido, sigungu, bname, start, end);
		} else {
			rstList = rstProcess.getRestaurants(category_id, orderBy, sido, sigungu, bname, start, end, searchText);
		}
		
		for (RestaurantDTO rst : rstList) {
			rstData.add(new RestaurantDetailDTO(rst, reviewProcess.getReviewCount(rst.getRst_id()),
					reviewProcess.getReplyCount(rst.getRst_id()), rstProcess.getRating(rst.getRst_id())));
		}
		
		int rst_count = rstProcess.getRestaurantCount(category_id, sido, sigungu, bname, searchText);
		
		request.setAttribute("category_id", category_id);
		request.setAttribute("orderBy", orderBy);
		request.setAttribute("address", address);
		request.setAttribute("sido", sido);
		request.setAttribute("sigungu", sigungu);
		request.setAttribute("bname", bname);
		request.setAttribute("searchText", searchText);
		request.setAttribute("rstData", rstData);
		request.setAttribute("rst_count", rst_count);

		return "/restaurant/rstList.jsp";
	}
}
