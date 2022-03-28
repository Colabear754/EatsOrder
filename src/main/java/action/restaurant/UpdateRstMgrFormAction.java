package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;
import restaurant.RestaurantManagerDTO;

public class UpdateRstMgrFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		RestaurantDAO rstProcess = new RestaurantDAO();
		
		RestaurantManagerDTO rstManager = rstProcess.getRestaurantManager(rst_id);
		
		request.setAttribute("rst_id", rst_id);
		request.setAttribute("phone", rstManager.getPhone());
		
		return "/updateRstMgrForm.jsp";
	}
}
