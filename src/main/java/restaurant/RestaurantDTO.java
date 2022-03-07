package restaurant;

public class RestaurantDTO {
	private int rst_id;
	private int category_id;
	private String rst_name;
	private String phone;
	private String address;
	private int min_order;
	private String origin;
	private String hours;
	private String bussiness_number;
	private String bussiness_name;
	private String payment;
	private int delivery_tip;
	private String rst_notice;
	private String estimated_time;
	private String rst_photo;
	private String rst_logo;
	private int enable;
	
	public RestaurantDTO() {
		super();
	}

	public RestaurantDTO(int rst_id, int category_id, String rst_name, String phone, String address, int min_order, String origin,
			String hours, String bussiness_number, String bussiness_name, String payment, int delivery_tip, String rst_notice,
			String estimated_time, String rst_photo, String rst_logo, int enable) {
		this.rst_id = rst_id;
		this.category_id = category_id;
		this.rst_name = rst_name;
		this.phone = phone;
		this.address = address;
		this.min_order = min_order;
		this.origin = origin;
		this.hours = hours;
		this.bussiness_number = bussiness_number;
		this.bussiness_name = bussiness_name;
		this.payment = payment;
		this.delivery_tip = delivery_tip;
		this.rst_notice = rst_notice;
		this.estimated_time = estimated_time;
		this.rst_photo = rst_photo;
		this.rst_logo = rst_logo;
		this.enable = enable;
	}

	public int getRst_id() {
		return rst_id;
	}

	public void setRst_id(int rst_id) {
		this.rst_id = rst_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getRst_name() {
		return rst_name;
	}

	public void setRst_name(String rst_name) {
		this.rst_name = rst_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMin_order() {
		return min_order;
	}

	public void setMin_order(int min_order) {
		this.min_order = min_order;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getBussiness_number() {
		return bussiness_number;
	}

	public void setBussiness_number(String bussiness_number) {
		this.bussiness_number = bussiness_number;
	}

	public String getBussiness_name() {
		return bussiness_name;
	}

	public void setBussiness_name(String bussiness_name) {
		this.bussiness_name = bussiness_name;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public int getDelivery_tip() {
		return delivery_tip;
	}

	public void setDelivery_tip(int delivery_tip) {
		this.delivery_tip = delivery_tip;
	}

	public String getRst_notice() {
		return rst_notice;
	}

	public void setRst_notice(String rst_notice) {
		this.rst_notice = rst_notice;
	}

	public String getEstimated_time() {
		return estimated_time;
	}

	public void setEstimated_time(String estimated_time) {
		this.estimated_time = estimated_time;
	}

	public String getRst_photo() {
		return rst_photo;
	}

	public void setRst_photo(String rst_photo) {
		this.rst_photo = rst_photo;
	}

	public String getRst_logo() {
		return rst_logo;
	}

	public void setRst_logo(String rst_logo) {
		this.rst_logo = rst_logo;
	}

	public int is_enable() {
		return enable;
	}

	public void set_enable(int enable) {
		this.enable = enable;
	}
}
