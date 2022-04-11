package menu;

public class MenuRstDTO {
	private MenuDTO menu;
	private int rst_id;
	private String rst_name;
	private double rating;
	private int count;

	public MenuRstDTO() {
		super();
	}

	public MenuRstDTO(MenuDTO menu, int rst_id, String rst_name, double rating, int count) {
		this.menu = menu;
		this.rst_id = rst_id;
		this.rst_name = rst_name;
		this.rating = rating;
		this.count = count;
	}

	public MenuDTO getMenu() {
		return menu;
	}

	public void setMenu(MenuDTO menu) {
		this.menu = menu;
	}

	public int getRst_id() {
		return rst_id;
	}

	public void setRst_id(int rst_id) {
		this.rst_id = rst_id;
	}

	public String getRst_name() {
		return rst_name;
	}

	public void setRst_name(String rst_name) {
		this.rst_name = rst_name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
