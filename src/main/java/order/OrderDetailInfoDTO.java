package order;

import java.util.ArrayList;

public class OrderDetailInfoDTO {
	private OrderHistoryDTO orderHistory;
	private ArrayList<String> menuList;
	private ArrayList<String> optionList;
	private ArrayList<Integer> quantityList;
	private int used_point = 0;
	private int discount_amount = 0;
	private int price = 0;

	public OrderDetailInfoDTO() {
		super();
	}

	public OrderDetailInfoDTO(OrderHistoryDTO orderHistory, ArrayList<String> menuList, ArrayList<String> optionList,
			ArrayList<Integer> quantityList, int used_point, int discount_amount, int price) {
		this.orderHistory = orderHistory;
		this.menuList = menuList;
		this.optionList = optionList;
		this.quantityList = quantityList;
		this.used_point = used_point;
		this.discount_amount = discount_amount;
		this.price = price;
	}

	public OrderHistoryDTO getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(OrderHistoryDTO orderHistory) {
		this.orderHistory = orderHistory;
	}

	public ArrayList<String> getMenuList() {
		return menuList;
	}

	public void setMenuList(ArrayList<String> menuList) {
		this.menuList = menuList;
	}

	public ArrayList<String> getOptionList() {
		return optionList;
	}

	public void setOptionList(ArrayList<String> optionList) {
		this.optionList = optionList;
	}

	public ArrayList<Integer> getQuantityList() {
		return quantityList;
	}

	public void setQuantityList(ArrayList<Integer> quantityList) {
		this.quantityList = quantityList;
	}

	public int getUsed_point() {
		return used_point;
	}

	public void setUsed_point(int used_point) {
		this.used_point = used_point;
	}

	public int getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(int discount_amount) {
		this.discount_amount = discount_amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
