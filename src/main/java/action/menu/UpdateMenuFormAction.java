package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;
import menu.MenuDTO;

public class UpdateMenuFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		MenuDTO menu = menuProcess.getMenu(menu_id);
		
		request.setAttribute("menu", menu);
		
		return "/updateMenuForm.jsp";
	}
}
