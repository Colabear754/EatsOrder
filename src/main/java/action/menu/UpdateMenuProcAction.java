package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class UpdateMenuProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 메뉴를 수정하는 액션클래스
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		String menu_name = request.getParameter("menu_name");
		String menu_info = request.getParameter("menu_info");
		String menu_photo = request.getParameter("menu_photo");
		int price = Integer.parseInt(request.getParameter("price"));
		int enable = Integer.parseInt(request.getParameter("enable"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		int result = menuProcess.updateMenu(menu_id, category_id, menu_name, menu_info, menu_photo, price, enable);
		
		request.setAttribute("result", result);
		
		return "/updateMenuProc.jsp";
	}
}
