package order;

public class CartItemDTO {
	private String menu_name;
	private String option_name;
	private int quantity;
	private int price;

	public CartItemDTO() {
		super();
	}

	public CartItemDTO(String menu_name, String option_name, int quantity, int price) {
		this.menu_name = menu_name;
		this.option_name = option_name;
		this.quantity = quantity;
		this.price = price;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getOption_name() {
		return option_name;
	}

	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
