package order;

public class OrderDetailDTO {
	private String order_number;
	private int menu_id;
	private int bundle_id;
	private int quantity;

	public OrderDetailDTO() {
		super();
	}

	public OrderDetailDTO(String order_number, int menu_id, int bundle_id, int quantity) {
		this.order_number = order_number;
		this.menu_id = menu_id;
		this.bundle_id = bundle_id;
		this.quantity = quantity;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public int getBundle_id() {
		return bundle_id;
	}

	public void setBundle_id(int bundle_id) {
		this.bundle_id = bundle_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
