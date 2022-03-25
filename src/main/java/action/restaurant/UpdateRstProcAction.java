package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import restaurant.*;

public class UpdateRstProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		String rst_name = request.getParameter("rst_name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		int min_order = Integer.parseInt(request.getParameter("min_order"));
		String origin = request.getParameter("origin");
		String hours = request.getParameter("hours");
		String bussiness_number = request.getParameter("bussiness_number");
		String bussiness_name = request.getParameter("bussiness_name");
		String payment = request.getParameter("payment");
		int delivery_tip = Integer.parseInt(request.getParameter("delivery_tip"));
		String rst_notice = request.getParameter("rst_notice");
		String estimated_time = request.getParameter("estimated_time");
		String rst_photo = request.getParameter("rst_photo");
		String rst_logo = request.getParameter("rst_logo");
		int enable = Integer.parseInt(request.getParameter("enable"));
		String password = request.getParameter("password");
		RestaurantDAO rstProcess = new RestaurantDAO();

		RestaurantDTO restaurant = new RestaurantDTO(rst_id, category_id, rst_name, phone, address, min_order, origin, hours,
				bussiness_number, bussiness_name, payment, delivery_tip, rst_notice, estimated_time, rst_photo, rst_logo,
				enable);
		
		int result = rstProcess.updateRestaurant(restaurant, password);
		
		request.setAttribute("result", result);
		
		return "/updateRstProc.jsp";
	}
}
