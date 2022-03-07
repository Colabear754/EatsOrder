package menu;

public class MenuOptionDTO {
	private int menu_id;
	private int option_id;

	public MenuOptionDTO() {
		super();
	}

	public MenuOptionDTO(int menu_id, int option_id) {
		this.menu_id = menu_id;
		this.option_id = option_id;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public int getOption_id() {
		return option_id;
	}

	public void setOption_id(int option_id) {
		this.option_id = option_id;
	}
}
