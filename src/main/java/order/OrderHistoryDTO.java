package order;

import java.sql.Date;

public class OrderHistoryDTO {
	private int order_number;
	private String orderer;
	private String destination;
	private String coupon_id;
	private int used_point;
	private String payment_method;
	private Date pay_date;
	private String order_request;
	private int payment_status;
	private String reason_cancellation;

	public OrderHistoryDTO() {
		super();
	}

	public OrderHistoryDTO(int order_number, String orderer, String destination, String coupon_id, int used_point,
			String payment_method, Date pay_date, String order_request, int payment_status, String reason_cancellation) {
		this.order_number = order_number;
		this.orderer = orderer;
		this.destination = destination;
		this.coupon_id = coupon_id;
		this.used_point = used_point;
		this.payment_method = payment_method;
		this.pay_date = pay_date;
		this.order_request = order_request;
		this.payment_status = payment_status;
		this.reason_cancellation = reason_cancellation;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public String getOrderer() {
		return orderer;
	}

	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public int getUsed_point() {
		return used_point;
	}

	public void setUsed_point(int used_point) {
		this.used_point = used_point;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}

	public String getOrder_request() {
		return order_request;
	}

	public void setOrder_request(String order_request) {
		this.order_request = order_request;
	}

	public int isPayment_status() {
		return payment_status;
	}

	public void setPayment_status(int payment_status) {
		this.payment_status = payment_status;
	}

	public String getReason_cancellation() {
		return reason_cancellation;
	}

	public void setReason_cancellation(String reason_cancellation) {
		this.reason_cancellation = reason_cancellation;
	}
}
