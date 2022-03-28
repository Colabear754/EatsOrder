package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;
import menu.MenuCategoryDTO;

public class UpdateMenuCategoryFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		MenuCategoryDTO category = menuProcess.getMenuCategory(category_id);
		
		request.setAttribute("category", category);
		
		return "/updateMenuCategoryForm.jsp";
	}
}
