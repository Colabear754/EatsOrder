package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class UpdateMenuCategoryProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 메뉴 카테고리를 수정하는 액션클래스
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		String category_name = request.getParameter("category_name");
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		int result = menuProcess.updateMenuCategory(category_id, category_name);
		
		request.setAttribute("result", result);
		
		return "/updateMenuCategoryProc.jsp";
	}
}
