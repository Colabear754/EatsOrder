package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class InsertOptionProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 옵션을 추가하는 액션클래스
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		String option_name = request.getParameter("option_name");
		int price = Integer.parseInt(request.getParameter("price"));
		String temp = request.getParameter("enable");
		int enable;
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		if (temp == null || temp.isBlank()) {
			enable = 0;
		} else {
			enable = 1;
		}
		
		menuProcess.insertOption(group_id, option_name, price, enable);
		
		return "/insertOptionProc.jsp";
	}
}
