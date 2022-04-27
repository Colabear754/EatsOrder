package order;

public class CartDTO {
	private String orderer;
	private int menu_id;
	private int bundle_id;
	private int quantity;

	public CartDTO() {
		super();
	}

	public CartDTO(String orderer, int menu_id, int bundle_id, int quantity) {
		this.orderer = orderer;
		this.menu_id = menu_id;
		this.bundle_id = bundle_id;
		this.quantity = quantity;
	}

	public String getOrderer() {
		return orderer;
	}

	public void setOrderer(String orderer) {
		this.orderer = orderer;
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
