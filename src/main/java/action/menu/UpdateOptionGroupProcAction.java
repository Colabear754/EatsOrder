package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class UpdateOptionGroupProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 옵션그룹을 수정하는 액션클래스
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		String group_name = request.getParameter("group_name");
		int essential = Integer.parseInt(request.getParameter("essential"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		int result = menuProcess.updateOptionGroup(group_id, group_name, essential);
		
		request.setAttribute("result", result);
		
		return "/updateOptionGroupProc.jsp";
	}
}
