package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class InsertMenuProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 메뉴를 추가하는 액션클래스
		int rst_id = (int) request.getSession().getAttribute("rst_id");
		String menu_name = request.getParameter("menu_name");
		String menu_info = request.getParameter("menu_info");
		String menu_photo = request.getParameter("menu_photo");
		int price = Integer.parseInt(request.getParameter("price"));
		String temp = request.getParameter("enable");
		int enable;
		
		if (temp == null || temp.isBlank()) {
			enable = 0;
		} else {
			enable = 1;
		}
		
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		int result = menuProcess.insertMenu(rst_id, menu_name, menu_info, menu_photo, price, enable);
		
		request.setAttribute("result", result);
		
		return "/insertMenuProc.jsp";
	}
}
