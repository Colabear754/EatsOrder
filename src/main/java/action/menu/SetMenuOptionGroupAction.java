package action.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;

public class SetMenuOptionGroupAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 메뉴와 옵션그룹을 연결하는 액션클래스
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		
		menuProcess.setMenuOptionGroup(menu_id, group_id);
		
		return "/setMenuOptionGroup.jsp";
	}
}
