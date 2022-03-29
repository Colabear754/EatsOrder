package action.menu;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.MenuAndOptionDAO;
import menu.OptionGroupDTO;

public class OptionListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 옵션 리스트를 출력하는 액션클래스
		request.setCharacterEncoding("utf-8");
		int menu_id = Integer.parseInt(request.getParameter("menu_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		ArrayList<OptionGroupDTO> optionGroupList = menuProcess.getMenuOptionGroups(menu_id);
		ArrayList<HashMap<String, Object>> optionList = new ArrayList<>();
		
		for (OptionGroupDTO optionGroup : optionGroupList) {
			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("optionGroup", optionGroup);
			hashMap.put("optionList", menuProcess.getOptionList(optionGroup.getGroup_id()));
			optionList.add(hashMap);
		}
		
		request.setAttribute("optionList", optionList);
		
		return "/optionList.jsp";
	}
}
