package order;

import java.util.ArrayList;

import menu.OptionInfoDTO;

public class CartItemDTO {
	private String menu_name;
	private ArrayList<OptionInfoDTO> selectedOptions;
	private int quantity;
	private int price;

	public CartItemDTO() {
		super();
	}

	public CartItemDTO(String menu_name, ArrayList<OptionInfoDTO> selectedOptions, int quantity, int price) {
		this.menu_name = menu_name;
		this.selectedOptions = selectedOptions;
		this.quantity = quantity;
		this.price = price;
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
