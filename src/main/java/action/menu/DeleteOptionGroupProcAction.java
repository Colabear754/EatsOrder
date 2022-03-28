package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class DeleteOptionGroupProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 옵션 그룹을 삭제하는 액션클래스
		int rst_id = (int) request.getSession().getAttribute("rst_id");
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();

		int result = menuProcess.deleteOptionGroup(group_id, rst_id);

		request.setAttribute("result", result);
		
		return "/deleteOptionGroupProc.jsp";
	}
}
