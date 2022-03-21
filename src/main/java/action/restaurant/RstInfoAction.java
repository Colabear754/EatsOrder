package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;
import restaurant.RestaurantDTO;

public class RstInfoAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		RestaurantDAO rstProcess = new RestaurantDAO();
		RestaurantDTO restaurant = rstProcess.getRestaurant(rst_id);
		
		request.setAttribute("restaurant", restaurant);
		
		return "/rstInfo.jsp";
	}
}
