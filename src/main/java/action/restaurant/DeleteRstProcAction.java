package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;

public class DeleteRstProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 매장 삭제 액션클래스
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		String password = request.getParameter("password");
		RestaurantDAO rstProcess = new RestaurantDAO();
		
		int result = rstProcess.deleteRestaurant(rst_id, password);
		
		request.setAttribute("result", result);
		
		return "deleteRstProc.jsp";
	}
}
