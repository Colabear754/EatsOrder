package menu;

import java.util.ArrayList;

public class MenuListDTO {
	MenuCategoryDTO category;
	ArrayList<MenuDTO> menuList;

	public MenuListDTO(MenuCategoryDTO category, ArrayList<MenuDTO> menuList) {
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