package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;

public class DeleteMenuCategoryFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		
		request.setAttribute("category_id", category_id);
		
		return "/deleteMenuCategoryForm.jsp";
	}
}
