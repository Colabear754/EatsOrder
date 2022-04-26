package action.menu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;
import menu.MenuDTO;
import menu.OptionGroupDTO;
import menu.OptionListDTO;

public class MenuInfoAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 메뉴 정보와 옵션들을 조회하는 액션클래스
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		MenuDTO menu = menuProcess.getMenu(menu_id);
		ArrayList<OptionGroupDTO> optionGroupList = menuProcess.getMenuOptionGroups(menu_id);
		ArrayList<OptionListDTO> optionList = new ArrayList<>();
		
		for (OptionGroupDTO optionGroup : optionGroupList) {
			OptionListDTO groupOptionList = new OptionListDTO(optionGroup, menuProcess.getOptionList(optionGroup.getGroup_id()));
			optionList.add(groupOptionList);
		}
		
		request.setAttribute("menu", menu);
		request.setAttribute("optionList", optionList);
		
		return "/menu/menuInfo.jsp";
	}
}
