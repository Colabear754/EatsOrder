package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class InsertOptionGroupProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 옵션 그룹을 추가하는 액션클래스
		int rst_id = (int) request.getSession().getAttribute("rst_id");
		String group_name = request.getParameter("group_name");
		String temp = request.getParameter("essential");
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		int essential;
		
		if (temp == null || temp.isBlank()) {
			essential = 0;
		} else {
			essential = Integer.parseInt(request.getParameter("essential"));
		}
		
		menuProcess.insertOptionGroup(rst_id, group_name, essential);
		
		return "/insertOptionGroupProc.jsp";
	}
}
