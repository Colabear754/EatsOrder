package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class DeleteOptionFromAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int option_id = Integer.parseInt(request.getParameter("option_id"));
		
		request.setAttribute("option_id", option_id);
		
		return "/deleteOptionForm.jsp";
	}
}
