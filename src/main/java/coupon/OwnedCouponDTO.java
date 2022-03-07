package coupon;

public class OwnedCouponDTO {
	private String coupon_id;
	private String owner_id;
	private int available_count;

	public OwnedCouponDTO() {
		super();
	}

	public OwnedCouponDTO(String coupon_id, String owner_id, int available_count) {
		this.coupon_id = coupon_id;
		this.owner_id = owner_id;
		this.available_count = available_count;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public int getAvailable_count() {
		return available_count;
	}

	public void setAvailable_count(int available_count) {
		this.available_count = available_count;
	}
}
