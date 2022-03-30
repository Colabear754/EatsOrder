package coupon;

public class CouponDetailDTO {
	private ValidCouponDTO coupon;
	private int available_count;
	
	public CouponDetailDTO() {
		super();
	}

	public CouponDetailDTO(ValidCouponDTO coupon, int available_count) {
		this.coupon = coupon;
		this.available_count = available_count;
	}

	public ValidCouponDTO getCoupon() {
		return coupon;
	}

	public void setCoupon(ValidCouponDTO coupon) {
		this.coupon = coupon;
	}

	public int getAvailable_count() {
		return available_count;
	}

	public void setAvailable_count(int available_count) {
		this.available_count = available_count;
	}
}
