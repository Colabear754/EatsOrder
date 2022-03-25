package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class UpdateRstMgrFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		String phone = request.getParameter("phone");
		
		request.setAttribute("rst_id", rst_id);
		request.setAttribute("phone", phone);
		
		return "/updateRstMgrForm.jsp";
	}
}
