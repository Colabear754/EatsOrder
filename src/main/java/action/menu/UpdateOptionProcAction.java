package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class UpdateOptionProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 옵션을 수정하는 액션클래스
		int option_id = Integer.parseInt(request.getParameter("option_id"));
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		String option_name = request.getParameter("option_name");
		int price = Integer.parseInt(request.getParameter("price"));
		int enable = Integer.parseInt(request.getParameter("enable"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		int result = menuProcess.updateOption(option_id, group_id, option_name, price, enable);
		
		request.setAttribute("result", result);
		
		return "/updateOptionProc.jsp";
	}
}
