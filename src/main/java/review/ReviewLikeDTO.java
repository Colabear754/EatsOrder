package review;

public class ReviewLikeDTO {
	private int review_number;
	private String email;

	public ReviewLikeDTO() {
		super();
	}

	public ReviewLikeDTO(int review_number, String email) {
		this.review_number = review_number;
		this.email = email;
	}

	public int getNumber() {
		return review_number;
	}

	public void setNumber(int review_number) {
		this.review_number = review_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
