package order;

import java.util.ArrayList;

import menu.OptionInfoDTO;

public class CartItemDTO {
	private int menu_id;
	private String menu_name;
	private ArrayList<OptionInfoDTO> selectedOptions;
	private int quantity;
	private int price;

	public CartItemDTO() {
		super();
	}

	public CartItemDTO(int menu_id, String menu_name, ArrayList<OptionInfoDTO> selectedOptions, int quantity, int price) {
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.selectedOptions = selectedOptions;
		this.quantity = quantity;
		this.price = price;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public ArrayList<OptionInfoDTO> getSelectedOptions() {
		return selectedOptions;
	}

	public void setSelectedOptions(ArrayList<OptionInfoDTO> selectedOptions) {
		this.selectedOptions = selectedOptions;
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
