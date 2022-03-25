package action.restaurant;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;

public class FindRstIdAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		String phone = request.getParameter("phone");
		RestaurantDAO rstProcess = new RestaurantDAO();
		
		ArrayList<Integer> rst_idList = rstProcess.findRst_id(phone);
		
		request.setAttribute("rst_idList", rst_idList);
		
		return "/findRstId.jsp";
	}
}
