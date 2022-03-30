package order;

import java.sql.Timestamp;

public class OrderBasicInfoDTO {
	private String order_number;
	private String rst_name;
	private String rst_logo;
	private String menu_name;
	private int count;
	private Timestamp pay_date;

	public OrderBasicInfoDTO() {
		super();
	}

	public OrderBasicInfoDTO(String order_number, String rst_name, String rst_logo, String menu_name, int count,
			Timestamp pay_date) {
		this.order_number = order_number;
		this.rst_name = rst_name;
		this.rst_logo = rst_logo;
		this.menu_name = menu_name;
		this.count = count;
		this.pay_date = pay_date;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public String getRst_name() {
		return rst_name;
	}

	public void setRst_name(String rst_name) {
		this.rst_name = rst_name;
	}

	public String getRst_logo() {
		return rst_logo;
	}

	public void setRst_logo(String rst_logo) {
		this.rst_logo = rst_logo;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Timestamp getPay_date() {
		return pay_date;
	}

	public void setPay_date(Timestamp pay_date) {
		this.pay_date = pay_date;
	}
}
