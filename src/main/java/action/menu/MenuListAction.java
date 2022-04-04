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
		ArrayList<MenuList> menuList = new ArrayList<>();

		for (MenuCategoryDTO category : categoryList) {
			MenuList categoryMenuList = new MenuList(category, menuProcess.getMenuList(rst_id, category.getCategory_id()));
			menuList.add(categoryMenuList);
		}

		request.setAttribute("menuList", menuList);

		return "/menuList.jsp";
	}
}

class MenuList {
	MenuCategoryDTO category;
	ArrayList<MenuDTO> menuList;

	public MenuList(MenuCategoryDTO category, ArrayList<MenuDTO> menuList) {
		this.category = category;
		this.menuList = menuList;
	}

	public MenuCategoryDTO getCategory() {
		return category;
	}

	public void setCategory(MenuCategoryDTO category) {
		this.category = category;
	}

	public ArrayList<MenuDTO> getMenuList() {
		return menuList;
	}

	public void setMenuList(ArrayList<MenuDTO> menuList) {
		this.menuList = menuList;
	}
}