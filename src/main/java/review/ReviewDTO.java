package review;

import java.sql.Date;

public class ReviewDTO {
	private int review_number;
	private int order_number;
	private Date regist_date;
	private String content;
	private String photo1;
	private String photo2;
	private String photo3;
	private String photo4;
	private String photo5;
	private int rating;

	public ReviewDTO() {
		super();
	}

	public ReviewDTO(int review_number, int order_number, Date regist_date, String content, String photo1, String photo2, String photo3,
			String photo4, String photo5, int rating) {
		this.review_number = review_number;
		this.order_number = order_number;
		this.regist_date = regist_date;
		this.content = content;
		this.photo1 = photo1;
		this.photo2 = photo2;
		this.photo3 = photo3;
		this.photo4 = photo4;
		this.photo5 = photo5;
		this.rating = rating;
	}

	public int getReview_number() {
		return review_number;
	}

	public void setReview_number(int review_number) {
		this.review_number = review_number;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public Date getRegist_date() {
		return regist_date;
	}

	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhoto1() {
		return photo1;
	}

	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}

	public String getPhoto2() {
		return photo2;
	}

	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}

	public String getPhoto3() {
		return photo3;
	}

	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}

	public String getPhoto4() {
		return photo4;
	}

	public void setPhoto4(String photo4) {
		this.photo4 = photo4;
	}

	public String getPhoto5() {
		return photo5;
	}

	public void setPhoto5(String photo5) {
		this.photo5 = photo5;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
