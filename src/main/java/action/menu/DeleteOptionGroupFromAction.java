package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class DeleteOptionGroupFromAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		
		request.setAttribute("group_id", group_id);
		
		return "/deleteOptionGroupForm.jsp";
	}
}
