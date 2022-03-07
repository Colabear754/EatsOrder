package menu;

public class MenuDTO {
	private int menu_id;
	private int category_id;
	private String menu_name;
	private String menu_info;
	private String menu_photo;
	private int price;
	private int enable;

	public MenuDTO() {
		super();
	}

	public MenuDTO(int menu_id, int category_id, String menu_name, String menu_info, String menu_photo, int price, int enable) {
		this.menu_id = menu_id;
		this.category_id = category_id;
		this.menu_name = menu_name;
		this.menu_info = menu_info;
		this.menu_photo = menu_photo;
		this.price = price;
		this.enable = enable;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_info() {
		return menu_info;
	}

	public void setMenu_info(String menu_info) {
		this.menu_info = menu_info;
	}

	public String getMenu_photo() {
		return menu_photo;
	}

	public void setMenu_photo(String menu_photo) {
		this.menu_photo = menu_photo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int is_enable() {
		return enable;
	}

	public void set_enable(int enable) {
		this.enable = enable;
	}
}
