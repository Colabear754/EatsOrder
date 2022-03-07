package restaurant;

public class FavoriteRestaurantDTO {
	private String email;
	private int rst_id;

	public FavoriteRestaurantDTO() {
		super();
	}

	public FavoriteRestaurantDTO(String email, int rst_id) {
		this.email = email;
		this.rst_id = rst_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRst_id() {
		return rst_id;
	}

	public void setRst_id(int rst_id) {
		this.rst_id = rst_id;
	}
}
