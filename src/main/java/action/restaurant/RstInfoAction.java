package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;
import restaurant.RestaurantDTO;

public class RstInfoAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 매장 정보를 조회하는 액션 클래스
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		RestaurantDAO rstProcess = new RestaurantDAO();
		RestaurantDTO restaurant = rstProcess.getRestaurant(rst_id);
		double rating = rstProcess.getRating(rst_id);
		
		request.setAttribute("restaurant", restaurant);
		request.setAttribute("rating", rating);
		
		return "/restaurant/rst_info.jsp";
	}
}
