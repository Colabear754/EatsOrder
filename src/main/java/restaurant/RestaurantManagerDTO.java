package restaurant;

public class RestaurantManagerDTO {
	private int rst_id;
	private String password;
	private String phone;

	public RestaurantManagerDTO() {
		super();
	}

	public RestaurantManagerDTO(int rst_id, String password, String phone) {
		this.rst_id = rst_id;
		this.password = password;
		this.phone = phone;
	}

	public int getRst_id() {
		return rst_id;
	}

	public void setRst_id(int rst_id) {
		this.rst_id = rst_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
