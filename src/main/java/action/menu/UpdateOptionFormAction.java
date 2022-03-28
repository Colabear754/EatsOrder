package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;
import menu.OptionInfoDTO;

public class UpdateOptionFormAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		int option_id = Integer.parseInt(request.getParameter("option_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		OptionInfoDTO option = menuProcess.getOption(option_id);
		
		request.setAttribute("option", option);
		
		return "/updateOptionForm.jsp";
	}
}
