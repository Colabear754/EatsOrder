package action.menu;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;
import menu.OptionGroupDTO;
import menu.OptionListDTO;

public class OptionListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 옵션 리스트를 출력하는 액션클래스
		request.setCharacterEncoding("utf-8");
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		ArrayList<OptionGroupDTO> optionGroupList = menuProcess.getMenuOptionGroups(menu_id);
		ArrayList<OptionListDTO> optionList = new ArrayList<>();
		
		for (OptionGroupDTO optionGroup : optionGroupList) {
			OptionListDTO groupOptionList = new OptionListDTO(optionGroup, menuProcess.getOptionList(optionGroup.getGroup_id()));
			optionList.add(groupOptionList);
		}
		
		request.setAttribute("optionList", optionList);
		
		return "/optionList.jsp";
	}
}