package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;

public class UpdateRstMgrProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 사장님 정보 수정 액션클래스
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		String phone = request.getParameter("phone");
		RestaurantDAO rstProcess = new RestaurantDAO();
		
		int result = rstProcess.updateRstManager(rst_id, password, newPassword, phone);
		
		request.setAttribute("result", result);
		
		return "updateRstMgrProc.jsp";
	}
}
