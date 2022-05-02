package order;

import java.sql.Timestamp;

public class OrderBasicInfoDTO {
	private String order_number;
	private int rst_id;
	private String rst_name;
	private String rst_logo;
	private String menu_name;
	private int count;
	private Timestamp pay_date;
	private int payment_status;
	private long elapsed_time;

	public OrderBasicInfoDTO() {
		super();
	}

	public OrderBasicInfoDTO(String order_number, int rst_id, String rst_name, String rst_logo, String menu_name, int count,
			Timestamp pay_date, int payment_status) {
		this.order_number = order_number;
		this.rst_id = rst_id;
		this.rst_name = rst_name;
		this.rst_logo = rst_logo;
		this.menu_name = menu_name;
		this.count = count;
		this.pay_date = pay_date;
		this.payment_status = payment_status;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
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

	public int getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	public long getElapsed_time() {
		return elapsed_time;
	}

	public void setElapsed_time(long elapsed_time) {
		this.elapsed_time = elapsed_time;
	}
}
