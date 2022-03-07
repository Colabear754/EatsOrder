package restaurant;

public class RestaurantCategoryDTO {
	private int category_id;
	private String category_name;
	private String category_icon;

	public RestaurantCategoryDTO() {
		super();
	}

	public RestaurantCategoryDTO(int category_id, String category_name, String category_icon) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.category_icon = category_icon;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_icon() {
		return category_icon;
	}

	public void setCategory_icon(String category_icon) {
		this.category_icon = category_icon;
	}
}
