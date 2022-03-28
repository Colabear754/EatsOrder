package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class DeleteMenuProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 메뉴를 삭제하는 액션클래스
		int rst_id = (int) request.getSession().getAttribute("rst_id");
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		int result = menuProcess.deleteMenu(rst_id, menu_id);
		
		request.setAttribute("result", result);
		
		return "/deleteMenuProc.jsp";
	}
}
