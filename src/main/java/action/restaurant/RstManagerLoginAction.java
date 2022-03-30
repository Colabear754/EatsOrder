package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.RestaurantDAO;

public class RstManagerLoginAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 사장님 로그인 액션클래스
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		String password = request.getParameter("password");
		RestaurantDAO rstProcess = new RestaurantDAO();
		boolean result = rstProcess.rstManagerLogin(rst_id, password);
		
		if (result) {
			request.getSession().setAttribute("rst_id", rst_id);
		}
		
		return "/rstManagerMain.jsp";
	}
}
