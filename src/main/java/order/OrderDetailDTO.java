package order;

public class OrderDetailDTO {
	private int order_number;
	private int menu_id;
	private int option_id;
	private int quantity;

	public OrderDetailDTO() {
		super();
	}

	public OrderDetailDTO(int order_number, int menu_id, int option_id, int quantity) {
		this.order_number = order_number;
		this.menu_id = menu_id;
		this.option_id = option_id;
		this.quantity = quantity;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
