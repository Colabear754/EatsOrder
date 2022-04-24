package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;
import restaurant.RestaurantDTO;

public class RstFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 매장 정보 틀 액션클래스
		request.setCharacterEncoding("utf-8");
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		String address = request.getParameter("address");
		String sido = request.getParameter("sido");
		String sigungu = request.getParameter("sigungu");
		String bname = request.getParameter("bname");
		RestaurantDAO rstProcess = new RestaurantDAO();
		RestaurantDTO restaurant = rstProcess.getRestaurant(rst_id);
		double rating = rstProcess.getRating(rst_id);
		
		request.setAttribute("restaurant", restaurant);
		request.setAttribute("rating", rating);
		request.setAttribute("address", address);
		request.setAttribute("sido", sido);
		request.setAttribute("sigungu", sigungu);
		request.setAttribute("bname", bname);
		
		return "/restaurant/rst_form.jsp";
	}
}
