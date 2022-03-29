package action.menu;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.CommandAction;
import menu.*;

public class MenuListAction implements CommandAction {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// 메뉴 리스트를 조회하는 액션클래스
		request.setCharacterEncoding("utf-8");
		int rst_id = Integer.parseInt(request.getParameter("rst_id"));
		MenuAndOptionDAO menuProcess = new MenuAndOptionDAO();
		ArrayList<MenuCategoryDTO> categoryList = menuProcess.getMenuCategories(rst_id);
		ArrayList<HashMap<String, Object>> menuList = new ArrayList<>();
		
		for (MenuCategoryDTO category : categoryList) {
			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("category", category);
			hashMap.put("menuList", menuProcess.getMenuList(rst_id, category.getCategory_id()));
			menuList.add(hashMap);
		}
		
		request.setAttribute("menuList", menuList);
		
		return "/menuList.jsp";
	}
}
