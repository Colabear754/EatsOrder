package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class DeleteMenuCategoryProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 메뉴 카테고리를 삭제하는 액션클래스
		int rst_id = (int) request.getSession().getAttribute("rst_id");
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		int result = menuProcess.deleteMenuCategory(rst_id, category_id);
		
		request.setAttribute("result", result);
		
		return "/deleteMenuCategoryProc.jsp";
	}
}
