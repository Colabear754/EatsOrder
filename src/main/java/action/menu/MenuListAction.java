package action.menu;

import java.util.ArrayList;

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
		ArrayList<MenuListDTO> menuList = new ArrayList<>();

		for (MenuCategoryDTO category : categoryList) {
			MenuListDTO categoryMenuList = new MenuListDTO(category, menuProcess.getMenuList(rst_id, category.getCategory_id()));
			menuList.add(categoryMenuList);
		}

		request.setAttribute("menuList", menuList);

		return "/menu/menuList.jsp";
	}
}
