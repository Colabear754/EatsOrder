package menu;

public class MenuCategoryDTO {
	private int category_id;
	private String category_name;
	private int rst_id;

	public MenuCategoryDTO() {
		super();
	}

	public MenuCategoryDTO(int category_id, String category_name, int rst_id) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.rst_id = rst_id;
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

	public int getRst_id() {
		return rst_id;
	}

	public void setRst_id(int rst_id) {
		this.rst_id = rst_id;
	}
}
