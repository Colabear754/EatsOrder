package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;

public class FavoriteRstAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 매장 찜하기 액션클래스
		String email = request.getParameter("email");
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		RestaurantDAO rstProcess = new RestaurantDAO();
		
		int result = rstProcess.favoriteRestaurant(email, rst_id);
		
		request.setAttribute("result", result);
		
		return "/component/result.jsp";
	}
}
