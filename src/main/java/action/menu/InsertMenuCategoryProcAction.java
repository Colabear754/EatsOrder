package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class InsertMenuCategoryProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 메뉴 카테고리를 추가하는 액션클래스
		int rst_id = (int) request.getSession().getAttribute("rst_id");
		String category_name = request.getParameter("category_name");
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		int result = menuProcess.insertMenuCategory(rst_id, category_name);
		
		request.setAttribute("result", result);
		
		return "/insertMenuCategoryProc.jsp";
	}
}
