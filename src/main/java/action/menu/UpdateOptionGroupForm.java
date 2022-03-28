package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;
import menu.OptionGroupDTO;

public class UpdateOptionGroupForm implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		OptionGroupDTO group = menuProcess.getOptionGroup(group_id);
		
		request.setAttribute("group", group);
		
		return "/updateOptionGroupForm.jsp";
	}
}
