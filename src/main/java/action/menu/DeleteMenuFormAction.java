package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class DeleteMenuFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		
		request.setAttribute("menu_id", menu_id);
		
		return "/deleteMenuForm.jsp";
	}
}
