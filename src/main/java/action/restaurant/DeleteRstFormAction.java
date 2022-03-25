package action.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class DeleteRstFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		
		request.setAttribute("rst_id", rst_id);
		
		return "/deleteRstForm.jsp";
	}
}
