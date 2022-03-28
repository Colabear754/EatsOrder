package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class DeleteOptionProcAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 옵션을 삭제하는 액션클래스
		int rst_id = (int) request.getSession().getAttribute("rst_id");
		int option_id = Integer.parseInt(request.getParameter("option_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		int result = menuProcess.deleteOption(option_id, rst_id);
		
		request.setAttribute("result", result);
		
		return "/deleteOptionProc.jsp";
	}
}
