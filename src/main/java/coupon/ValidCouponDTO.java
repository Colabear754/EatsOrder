package coupon;

import java.sql.Date;

public class ValidCouponDTO {
	private String coupon_id;
	private String coupon_name;
	private String coupon_info;
	private int available_price;
	private int discount_amount;
	private Date expiration_date;

	public ValidCouponDTO() {
		super();
	}

	public ValidCouponDTO(String coupon_id, String coupon_name, String coupon_info, int available_price, int discount_amount,
			Date expiration_date) {
		this.coupon_id = coupon_id;
		this.coupon_name = coupon_name;
		this.coupon_info = coupon_info;
		this.available_price = available_price;
		this.discount_amount = discount_amount;
		this.expiration_date = expiration_date;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public String getCoupon_info() {
		return coupon_info;
	}

	public void setCoupon_info(String coupon_info) {
		this.coupon_info = coupon_info;
	}

	public int getAvailable_price() {
		return available_price;
	}

	public void setAvailable_price(int available_price) {
		this.available_price = available_price;
	}

	public int getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(int discount_amount) {
		this.discount_amount = discount_amount;
	}

	public Date getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}
}
